package com.placeNote.placeNoteApi2024.model.db.subDocument.post;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.Nullable;

import com.placeNote.placeNoteApi2024.constants.UrlTypeEnum;

public record UrlDocument(
        @Field("url_id")
        String urlId,
        String url,
        @Field("url_type")
        UrlTypeEnum urlType,
        @Nullable
        @Field("url_info")
        UrlInfoDocument urlInfo
) {
}
