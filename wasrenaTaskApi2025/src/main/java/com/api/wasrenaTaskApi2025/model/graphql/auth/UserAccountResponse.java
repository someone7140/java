package com.api.wasrenaTaskApi2025.model.graphql.auth;

public record UserAccountResponse(
        String token,
        String userSettingId,
        String userName,
        String imageUrl,
        Boolean isLineBotFollow
) {
}
