package com.placeNote.placeNoteApi2024.model.graphql.auth;

public record AccountUserResponse(
        String token, String userSettingId, String name, String imageUrl) {
}
