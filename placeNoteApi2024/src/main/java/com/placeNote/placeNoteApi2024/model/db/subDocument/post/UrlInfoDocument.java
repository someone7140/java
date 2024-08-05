package com.placeNote.placeNoteApi2024.model.db.subDocument.post;

import org.springframework.data.mongodb.core.mapping.Field;

public record UrlInfoDocument(
        String title,
        @Field("image_url")
        String imageUrl,
        @Field("site_name")
        String siteName
) {
}
