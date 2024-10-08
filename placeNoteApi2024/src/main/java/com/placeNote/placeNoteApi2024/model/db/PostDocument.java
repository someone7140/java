package com.placeNote.placeNoteApi2024.model.db;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.placeNote.placeNoteApi2024.model.db.subDocument.post.UrlDocument;

@Document(collection = "posts")
public record PostDocument(
        @Id
        @Field("_id")
        String id,
        String title,
        @Field("create_user_account_id")
        String createUserAccountId,
        @Field("place_id")
        String placeId,
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
