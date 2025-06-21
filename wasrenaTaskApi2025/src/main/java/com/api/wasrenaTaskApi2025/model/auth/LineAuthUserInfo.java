package com.api.wasrenaTaskApi2025.model.auth;


import com.fasterxml.jackson.annotation.JsonProperty;

public record LineAuthUserInfo(
        @JsonProperty("userId")
        String userId,
        @JsonProperty("displayName")
        String displayName,
        @JsonProperty("pictureUrl")
        String pictureUrl
) {
}
