package com.placeNote.placeNoteApi2024.model.db;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_accounts")
public record UserAccountDocument(
        @Id
        @Field("_id")
        String id,
        String name,
        String userSettingId,
        Optional<String> gmail,
        Optional<String> email,
        Optional<String> password,
        Optional<String> imageUrl
) {}
