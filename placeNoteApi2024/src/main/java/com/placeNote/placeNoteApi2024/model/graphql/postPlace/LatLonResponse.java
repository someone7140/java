package com.placeNote.placeNoteApi2024.model.graphql.postPlace;

public record LatLonResponse(double lat, double lon) {
    public LatLonResponse(Double[] lonLat) {
        this(lonLat[1], lonLat[0]);
    }
}
