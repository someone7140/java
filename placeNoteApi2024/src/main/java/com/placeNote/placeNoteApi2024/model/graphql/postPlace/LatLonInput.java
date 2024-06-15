package com.placeNote.placeNoteApi2024.model.graphql.postPlace;

public record LatLonInput(double lat, double lon) {
    public Double[] getLonLatArray() {
        return new Double[]{this.lon, this.lat};
    }
}
