package com.placeNote.placeNoteApi2024.model.graphql.post;

import java.time.OffsetDateTime;
import java.util.List;

public record PostResponse(
        String id,
        String userSettingId,
        String title,
        String placeId,
        String placeName,
        String placeUrl,
        String placePrefectureCode,
        OffsetDateTime visitedDate,
        Boolean isOpen,
        List<String> categoryIdList,
        String detail,
        List<UrlDetailResponse> urlList
) {
}
