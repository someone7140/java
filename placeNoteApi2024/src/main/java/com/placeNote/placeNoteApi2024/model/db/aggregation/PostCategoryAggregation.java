package com.placeNote.placeNoteApi2024.model.db.aggregation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

@Document
public record PostCategoryAggregation(
        @Id
        @Field("_id")
        String id,
        String name,
        @Field("user_setting_id")
        String userSettingId,
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
