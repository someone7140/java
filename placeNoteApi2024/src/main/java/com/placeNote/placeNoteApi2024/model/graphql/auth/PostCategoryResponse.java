package com.placeNote.placeNoteApi2024.model.graphql.auth;

public record PostCategoryResponse(
        String id,
        String userSettingId,
        String name,
        String parentCategoryId,
        Integer displayOrder,
        String memo) {
}
