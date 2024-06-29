package com.placeNote.placeNoteApi2024.model.db.aggregation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import java.util.List;

@Document
public record PostPlaceAggregation(
        @Id
        @Field("_id")
        String id,
        String name,
        @Field("user_setting_id")
        String userSettingId,
        @Nullable
        @Field("address")
        String address,
        @Nullable
        @Field("lon_lat")
        Double[] lonLat,
        @Nullable
        @Field("prefecture_code")
        String prefectureCode,
        @Field("category_id_list")
        List<String> categoryIdList,
        @Nullable
        @Field("detail")
        String detail,
        @Field("url_list")
        List<String> urlList
) {
}
