package com.api.wasrenaTaskApi2025.model.graphql.auth;

public record NewUserAccountInput(
        String authToken,
        String userSettingId,
        String userName
) {
}
