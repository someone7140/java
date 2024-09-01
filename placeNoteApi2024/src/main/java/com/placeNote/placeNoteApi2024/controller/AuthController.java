package com.placeNote.placeNoteApi2024.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.multipart.MultipartFile;

import com.placeNote.placeNoteApi2024.annotation.loginStatus.LoggedInOnly;
import com.placeNote.placeNoteApi2024.annotation.loginStatus.NotLoggedInOnly;
import com.placeNote.placeNoteApi2024.model.auth.RequestManager;
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
    @Autowired
    RequestManager requestManager;

    @MutationMapping
    @NotLoggedInOnly
    public GoogleAuthCodeVerifyResponse googleAuthCodeVerify(@Argument String authCode) throws GraphqlErrorException {
        // 認証コードからgoogle認証情報を取得
        GoogleIdToken.Payload googleAuthPayload = googleAuthService.getGoogleAuthPayloadFromAuthCode(authCode);
        // トークンの取得
        String token = googleAuthService.getTokenGoogleAuthForRegister(googleAuthPayload.getEmail());
        return new GoogleAuthCodeVerifyResponse(token);
    }

    @MutationMapping
    @NotLoggedInOnly
    public AccountUserResponse loginByGoogleAuthCode(@Argument String authCode) throws GraphqlErrorException {
        // 認証コードからgoogle認証情報を取得
        GoogleIdToken.Payload googleAuthPayload = googleAuthService.getGoogleAuthPayloadFromAuthCode(authCode);
        // ユーザ情報を取得
        return userAccountService.getAccountUserByGmail(googleAuthPayload.getEmail());
    }

    @MutationMapping
    @NotLoggedInOnly
    public AccountUserResponse addAccountUserByGoogle(
            @Argument String userSettingId,
            @Argument String name,
            @Argument String authToken,
            @Argument MultipartFile imageFile) throws GraphqlErrorException {
        // トークンからgmailを取得
        String gmail = googleAuthService.getGmailFromGoogleAuthToken(authToken);
        // ユーザを登録
        return userAccountService.addAccountUserByGmail(userSettingId, name, gmail, imageFile);
    }

    @QueryMapping
    @LoggedInOnly
    public AccountUserResponse getAccountUserByToken() throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        return userAccountService.getAccountUserById(userAccountId);
    }
}
