package com.api.wasrenaTaskApi2025.service.userAccount;

import com.api.wasrenaTaskApi2025.model.db.UserAccountEntity;
import com.api.wasrenaTaskApi2025.model.graphql.auth.NewUserAccountInput;
import com.api.wasrenaTaskApi2025.model.graphql.auth.UserAccountResponse;
import com.api.wasrenaTaskApi2025.repository.UserAccountRepository;
import com.api.wasrenaTaskApi2025.service.common.JwtService;

import com.fasterxml.uuid.Generators;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserAccountService {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserAccountRepository userAccountRepository;

    // ユーザーの登録
    public UserAccountResponse createUserAccount(NewUserAccountInput input) {
        // 登録用のトークンからlineIdを取得
        var decodedJwt = jwtService.decodeToken(input.authToken());
        var lineId = decodedJwt.getClaim("lineId").asString();
        var pictureUrl = decodedJwt.getClaim("pictureUrl").asString();

        // 該当のuserSettingIdかlineIdが既に登録済みの場合はエラー
        var registeredUserEntity = userAccountRepository.findByUserSettingIdOrLineId(input.userSettingId(), lineId);
        if (registeredUserEntity.isPresent()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.FORBIDDEN)
                    .message("Duplicate user")
                    .build();
        }

        // ユーザ情報を登録
        var uuid = Generators.timeBasedEpochGenerator().generate().toString();
        var userAccountEntity = UserAccountEntity.builder()
                .id(uuid.toString())
                .userSettingId(input.userSettingId())
                .lineId(lineId)
                .userName(input.userName())
                .imageUrl(pictureUrl)
                .isLineBotFollow(false)
                .build();
        userAccountRepository.save(userAccountEntity);

        // レスポンス情報を返す
        return convertUserAccountResponseFromEntity(userAccountEntity);
    }

    // LINEのIDからユーザ情報を取得
    public UserAccountResponse getUserAccountByLineId(String lineId) {
        var registeredUserEntity = userAccountRepository.findByLineId(lineId);
        // 取得できなかった場合はエラー
        if (registeredUserEntity.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.UNAUTHORIZED)
                    .message("Can not get user")
                    .build();
        }

        // レスポンス情報を返す
        return convertUserAccountResponseFromEntity(registeredUserEntity.get());
    }

    // IDからユーザ情報を取得
    public UserAccountResponse getUserAccountById(String id) {
        var registeredUserEntity = userAccountRepository.findById(id);
        // 取得できなかった場合はエラー
        if (registeredUserEntity.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.UNAUTHORIZED)
                    .message("Can not get user")
                    .build();
        }

        // レスポンス情報を返す
        return convertUserAccountResponseFromEntity(registeredUserEntity.get());
    }
    // ユーザーのentityからレスポンスを取得
    private UserAccountResponse convertUserAccountResponseFromEntity(UserAccountEntity userAccountEntity) {
        return new UserAccountResponse(
                getUserAccountToken(userAccountEntity.getId()),
                userAccountEntity.getUserSettingId(),
                userAccountEntity.getUserName(),
                userAccountEntity.getImageUrl(),
                userAccountEntity.getIsLineBotFollow()
        );
    }

    // ユーザー認証用のトークン
    private String getUserAccountToken(String userId) {
        var claimMap = new HashMap<String, String>();
        claimMap.put("userId", userId);
        // トークン期限は3ヶ月
        long expireMilliSecond = 1000L * 60L * 60L * 24L * 90L;
        return jwtService.makeJwtToken(expireMilliSecond, claimMap);
    }
}
