package com.placeNote.placeNoteApi2024.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

@Document(collection = "user_accounts")
public record UserAccountDocument(
        @Id
        @Field("_id")
        String id,
        String name,
        String userSettingId,
        @Nullable
        String gmail,
        @Nullable
        String email,
        @Nullable
        String password,
        @Nullable
        String imageUrl
) {
}
