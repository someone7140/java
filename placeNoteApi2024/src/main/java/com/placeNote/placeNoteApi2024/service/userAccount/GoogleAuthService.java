package com.placeNote.placeNoteApi2024.service.userAccount;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.model.db.UserAccountDocument;
import com.placeNote.placeNoteApi2024.repository.UserAccountRepository;
import com.placeNote.placeNoteApi2024.service.common.JwtService;

@Service
public class GoogleAuthService {
    @Autowired
    Environment env;
    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    JwtService jwtService;

    // Googleの認証コードからユーザ情報を取得
    public GoogleIdToken.Payload getGoogleAuthPayloadFromAuthCode(String authCode) throws GraphqlErrorException {

        try {
            NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GsonFactory gson = GsonFactory.getDefaultInstance();

            // credentialファイルの読み込み
            Resource resource = new ClassPathResource(env.getProperty("google.auth.credential.resource.path"));
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(gson, new InputStreamReader(resource.getInputStream()));
            // flowの取得
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport,
                    gson,
                    clientSecrets,
                    Arrays.asList(
                            "openid",
                            "https://www.googleapis.com/auth/contacts.readonly",
                            "https://www.googleapis.com/auth/userinfo.profile",
                            "https://www.googleapis.com/auth/calendar",
                            "https://www.googleapis.com/auth/userinfo.email",
                            "https://www.googleapis.com/auth/drive.apps.readonly"))
                    .setAccessType("offline")
                    .setApprovalPrompt("force")
                    .build();
            // tokenの取得
            GoogleTokenResponse tokenRes = flow.newTokenRequest(authCode)
                    .setRedirectUri(env.getProperty("frontend.domain")).execute();
            // googleのユーザ情報を取得
            GoogleIdTokenVerifier verifier =
                    new GoogleIdTokenVerifier.Builder(httpTransport, gson)
                            .setAudience(Collections.singletonList(clientSecrets.getDetails().getClientId()))
                            .build();
            GoogleIdToken idToken = verifier.verify(tokenRes.getIdToken());
            return idToken.getPayload();
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.UNAUTHORIZED)
                    .message(e.getMessage())
                    .build();
        }
    }

    // gmailから登録用のトークンを取得
    public String getTokenGoogleAuthForRegister(String gmail) throws GraphqlErrorException {
        // gmailを指定してユーザを取得し未登録か確認
        Optional<UserAccountDocument> userOptional = userAccountRepository.findByGmail(gmail);
        if (userOptional.isPresent()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Already registered account")
                    .build();
        }

        // gmailをトークン化
        return jwtService.makeJwtToken(
                12L * 60L * 60L * 1000L,
                Map.of("gmail", gmail));

    }

}
