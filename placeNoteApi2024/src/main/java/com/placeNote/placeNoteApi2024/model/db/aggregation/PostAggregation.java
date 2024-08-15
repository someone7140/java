package com.placeNote.placeNoteApi2024.model.db.aggregation;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.placeNote.placeNoteApi2024.model.db.subDocument.post.UrlDocument;

@Document
public record PostAggregation(
        @Id
        @Field("_id")
        String id,
        String title,
        @Field("user_setting_id")
        String userSettingId,
        @Field("place_id")
        String placeId,
        @Field("place_name")
        String placeName,
        @Field("place_url")
        String placeUrl,
        @Field("place_prefecture_code")
        String placePrefectureCode,
        @Field("visited_date")
        Date visitedDate,
        @Field("is_open")
        Boolean isOpen,
        @Field("category_id_list")
        List<String> categoryIdList,
        @Nullable
        @Field("detail")
        String detail,
        @Field("url_list")
        List<UrlDocument> urlList
) {
}
