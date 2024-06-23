package com.placeNote.placeNoteApi2024.controller;

import java.util.List;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.placeNote.placeNoteApi2024.annotation.loginStatus.LoggedInOnly;
import com.placeNote.placeNoteApi2024.model.auth.RequestManager;
import com.placeNote.placeNoteApi2024.model.graphql.postPlace.LatLonInput;
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

    @MutationMapping
    @LoggedInOnly
    public Boolean addPostPlace(
            @Argument String name,
            @Argument String address,
            @Argument LatLonInput latLon,
            @Argument String prefectureCode,
            @Argument List<String> categoryIdList,
            @Argument String detail,
            @Argument List<String> urlList) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        postPlaceService.addPostPlace(
                userAccountId,
                name,
                address,
                latLon,
                prefectureCode,
                categoryIdList,
                detail,
                urlList);
        return true;
    }

    @MutationMapping
    @LoggedInOnly
    public Boolean editPostPlace(
            @Argument String id,
            @Argument String name,
            @Argument String address,
            @Argument LatLonInput latLon,
            @Argument String prefectureCode,
            @Argument List<String> categoryIdList,
            @Argument String detail,
            @Argument List<String> urlList) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        postPlaceService.editPostPlace(
                userAccountId,
                id,
                name,
                address,
                latLon,
                prefectureCode,
                categoryIdList,
                detail,
                urlList);
        return true;
    }

    @MutationMapping
    @LoggedInOnly
    public Boolean deletePostPlace(@Argument String id) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        postPlaceService.deletePostPlace(userAccountId, id);
        return true;
    }
}
