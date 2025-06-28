package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.auth.CreateUserRegisterTokenResponse;
import com.api.wasrenaTaskApi2025.model.graphql.auth.NewUserAccountInput;
import com.api.wasrenaTaskApi2025.model.graphql.auth.UpdateUserAccountInput;
import com.api.wasrenaTaskApi2025.model.graphql.auth.UserAccountResponse;
import com.api.wasrenaTaskApi2025.service.common.JwtService;
import com.api.wasrenaTaskApi2025.service.line.LineAuthApiService;
import com.api.wasrenaTaskApi2025.service.userAccount.UserAccountService;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import java.util.HashMap;

@Controller
public class AuthController {

    @Autowired
    LineAuthApiService lineApiService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    JwtService jwtService;

    // LINEの認証コードを元にユーザー登録用のトークンを返す
    @QueryMapping
    public CreateUserRegisterTokenResponse getUserRegisterToken(@Argument String lineAuthCode) throws GraphqlErrorException {
        // LINEのAPIからユーザ情報を取得
        var lineUserInfo = lineApiService.getLineUserInfoFromAuthCode(lineAuthCode);

        // 必要なユーザー情報をMapに変換してtokenを生成
        var claimMap = new HashMap<String, String>();
        claimMap.put("lineId", lineUserInfo.userId());
        claimMap.put("pictureUrl", lineUserInfo.pictureUrl());
        // ユーザー登録用トークン期限は3時間
        var token = jwtService.makeJwtToken(1000 * 60 * 60 * 3, claimMap);

        return new CreateUserRegisterTokenResponse(token, lineUserInfo.displayName());
    }

    // LINEの認証コードを元に登録されているユーザの情報を返す
    @QueryMapping
    public UserAccountResponse getRegisteredUser(@Argument String lineAuthCode) throws GraphqlErrorException {
        // LINEのAPIからユーザ情報を取得
        var lineUserInfo = lineApiService.getLineUserInfoFromAuthCode(lineAuthCode);
        // LINEのIDからユーザー情報を取得
        return userAccountService.getUserAccountByLineId(lineUserInfo.userId());
    }

    // ヘッダーに設定されているトークンを元にユーザ情報を返す
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public UserAccountResponse getUserAccountFromAuthHeader(Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        // IDからユーザー情報を取得
        return userAccountService.getUserAccountById(userId);
    }

    // ユーザーの新規登録
    @MutationMapping
    public UserAccountResponse createUserAccount(@Argument NewUserAccountInput input) throws GraphqlErrorException {
        return userAccountService.createUserAccount(input);
    }

    // ユーザーの更新
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public UserAccountResponse updateUserAccount(
            @Argument UpdateUserAccountInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return userAccountService.updateUserAccount(input, userId);
    }
}
