package com.placeNote.placeNoteApi2024.model.graphql.postPlace;

import java.util.List;

public record PostPlaceResponse(
        String id,
        String userSettingId,
        String name,
        String address,
        LatLonResponse latLon,
        String prefectureCode,
        List<String> categoryIdList,
        String detail,
        String url
) {
}
