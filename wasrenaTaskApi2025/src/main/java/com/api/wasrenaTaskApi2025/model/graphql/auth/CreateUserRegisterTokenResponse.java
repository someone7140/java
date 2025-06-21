package com.api.wasrenaTaskApi2025.model.graphql.auth;

public record CreateUserRegisterTokenResponse(
        String token,
        String lineName
) {
}
