package com.placeNote.placeNoteApi2024.model.db;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

@Document(collection = "post_places")
public record PostPlaceDocument(
        @Id
        @Field("_id")
        String id,
        String name,
        @Field("create_user_account_id")
        String createUserAccountId,
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
