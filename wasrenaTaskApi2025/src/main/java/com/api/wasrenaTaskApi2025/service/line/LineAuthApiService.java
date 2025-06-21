package com.api.wasrenaTaskApi2025.service.line;

import com.api.wasrenaTaskApi2025.model.auth.LineAuthUserInfo;

import com.api.wasrenaTaskApi2025.repository.UserAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class LineAuthApiService {
    @Autowired
    Environment env;
    @Autowired
    UserAccountRepository userAccountRepository;

    // 認証用のAPIのURL定数
    private final String LINE_TOKEN_API_URL = "https://api.line.me/oauth2/v2.1/token";
    private final String LINE_PROFILE_API_URL = "https://api.line.me/v2/profile";

    // WebClientのインスタンス
    private final WebClient webClient = WebClient.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024))
            .build();

    // 認証コードを使ってLINEのユーザー情報を取得
    public LineAuthUserInfo getLineUserInfoFromAuthCode(String authCode) {
        // アクセストークンを取得
        var accessToken = getLineAccessTokenFromAuthCode(authCode);

        // ユーザ情報を取得
        LineAuthUserInfo userInfo;
        try {
            userInfo = webClient.get()
                    .uri(LINE_PROFILE_API_URL)
                    .header("Authorization", "Bearer " + accessToken)
                    .retrieve()
                    .onStatus(
                            status -> status.isError(),
                            clientResponse -> {
                                return clientResponse.bodyToMono(String.class)
                                        .flatMap(errorBody -> Mono.error(
                                                new RuntimeException("LINE API Error: " +
                                                        clientResponse.statusCode() + " - " + errorBody)));
                            }
                    )
                    .bodyToMono(LineAuthUserInfo.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.UNAUTHORIZED)
                    .message(e.getMessage())
                    .build();
        }

        // 該当のlineIdが既に登録済みの場合はエラー
        var userEntity = userAccountRepository.findByLineId(userInfo.userId());
        if (userEntity.isPresent()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.FORBIDDEN)
                    .message("Duplicate line id")
                    .build();
        }

        return userInfo;
    }

    // 認証コードを使ってLINEのアクセストークンを取得
    private String getLineAccessTokenFromAuthCode(String authCode) {
        // リクエストボディにクライアント認証情報も含める
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", authCode);
        formData.add("redirect_uri", String.format("%s%s",
                env.getProperty("wasurena-task.frontend.domain"),
                env.getProperty("wasurena-task.line.regsiter.redirect.path")));
        formData.add("client_id", env.getProperty("line.auth.client-id"));
        formData.add("client_secret", env.getProperty("line.auth.secret-id"));

        try {
            // WebClientでPOSTリクエストを送信
            var response = webClient.post()
                    .uri(LINE_TOKEN_API_URL)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .onStatus(
                            status -> status.isError(),
                            clientResponse -> {
                                return clientResponse.bodyToMono(String.class)
                                        .flatMap(errorBody -> Mono.error(
                                                new RuntimeException("LINE API Error: " +
                                                        clientResponse.statusCode() + " - " + errorBody)));
                            }
                    )
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();

            // JSONレスポンスからアクセストークンを抽出
            var mapper = new ObjectMapper();
            var jsonNode = mapper.readTree(response);
            var accessToken = jsonNode.get("access_token").asText();

            return accessToken;
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.UNAUTHORIZED)
                    .message(e.getMessage())
                    .build();
        }
    }
}
