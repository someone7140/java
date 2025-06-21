package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.auth.CreateUserRegisterTokenResponse;
import com.api.wasrenaTaskApi2025.model.graphql.auth.NewUserAccountInput;
import com.api.wasrenaTaskApi2025.model.graphql.auth.UserAccountResponse;
import com.api.wasrenaTaskApi2025.service.common.JwtService;
import com.api.wasrenaTaskApi2025.service.line.LineAuthApiService;
import com.api.wasrenaTaskApi2025.service.userAccount.UserAccountService;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping
    public UserAccountResponse createUserAccount(@Argument NewUserAccountInput input) throws GraphqlErrorException {
        return userAccountService.createUserAccount(input);
    }

}
