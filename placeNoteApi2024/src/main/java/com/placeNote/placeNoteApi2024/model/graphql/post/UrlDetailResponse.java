package com.placeNote.placeNoteApi2024.model.graphql.post;

import com.placeNote.placeNoteApi2024.constants.UrlTypeEnum;

public record UrlDetailResponse(
        String urlId,
        String url,
        UrlTypeEnum urlType,
        UrlInfoResponse urlInfo
) {
}
