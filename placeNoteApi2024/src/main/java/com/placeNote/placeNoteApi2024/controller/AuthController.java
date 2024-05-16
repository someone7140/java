package com.placeNote.placeNoteApi2024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import com.placeNote.placeNoteApi2024.model.graphql.auth.AccountUserResponse;
import com.placeNote.placeNoteApi2024.model.graphql.auth.GoogleAuthCodeVerifyResponse;

@Controller
public class AuthController {
    @MutationMapping
    public GoogleAuthCodeVerifyResponse googleAuthCodeVerify(@Argument String authCode) {
        return new GoogleAuthCodeVerifyResponse("testToken");
    }


    @QueryMapping
    public AccountUserResponse getAccountUserByToken() {
        return new AccountUserResponse("testToken", "testId", "testName");
    }
}
