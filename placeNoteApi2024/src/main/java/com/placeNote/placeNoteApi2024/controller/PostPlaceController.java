package com.placeNote.placeNoteApi2024.controller;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.placeNote.placeNoteApi2024.annotation.loginStatus.LoggedInOnly;
import com.placeNote.placeNoteApi2024.model.auth.RequestManager;
import com.placeNote.placeNoteApi2024.model.graphql.postPlace.LatLonResponse;
import com.placeNote.placeNoteApi2024.service.postPlace.PostPlaceService;

@Controller
public class PostPlaceController {
    @Autowired
    PostPlaceService postPlaceService;
    @Autowired
    RequestManager requestManager;

    @QueryMapping
    @LoggedInOnly
    public LatLonResponse getLatLonFromAddress(@Argument String address) throws GraphqlErrorException {
        return postPlaceService.getLatLonFromGeocodingService(address);
    }
}
