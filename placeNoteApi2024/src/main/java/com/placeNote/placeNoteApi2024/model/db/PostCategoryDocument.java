package com.placeNote.placeNoteApi2024.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

@Document(collection = "post_categories")
public record PostCategoryDocument(
        @Id
        @Field("_id")
        String id,
        String name,
        @Field("create_user_account_id")
        String createUserAccountId,
        @Nullable
        @Field("parent_category_id")
        String parentCategoryId,
        @Nullable
        @Field("display_order")
        Integer displayOrder,
        @Nullable
        String memo
) {
}
