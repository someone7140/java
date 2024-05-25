package com.placeNote.placeNoteApi2024.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.placeNote.placeNoteApi2024.model.graphql.auth.AccountUserResponse;
import com.placeNote.placeNoteApi2024.model.graphql.auth.GoogleAuthCodeVerifyResponse;
import com.placeNote.placeNoteApi2024.service.userAccount.GoogleAuthService;
import com.placeNote.placeNoteApi2024.service.userAccount.UserAccountService;

@Controller
public class AuthController {
    @Autowired
    GoogleAuthService googleAuthService;
    @Autowired
    UserAccountService userAccountService;

    @MutationMapping
    public GoogleAuthCodeVerifyResponse googleAuthCodeVerify(@Argument String authCode) throws GraphqlErrorException {
        // 認証コードから認証情報を取得
        GoogleIdToken.Payload googleAuthPayload = googleAuthService.getGoogleAuthPayloadFromAuthCode(authCode);
        // トークンの取得
        String token = googleAuthService.getTokenGoogleAuthForRegister(googleAuthPayload.getEmail());
        return new GoogleAuthCodeVerifyResponse(token);
    }

    @MutationMapping
    public AccountUserResponse addAccountUserByGoogle(
            @Argument String userSettingId,
            @Argument String name,
            @Argument String authToken) throws GraphqlErrorException {
        // トークンからgmailを取得
        String gmail = googleAuthService.getGmailFromGoogleAuthToken(authToken);
        // ユーザを登録
        return userAccountService.addAccountUserByGmail(userSettingId, name, gmail);
    }

    @QueryMapping
    public AccountUserResponse getAccountUserByToken() {
        return new AccountUserResponse("testToken", "testId", "testName");
    }
}
