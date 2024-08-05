package com.placeNote.placeNoteApi2024.controller;

import java.time.OffsetDateTime;
import java.util.List;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.placeNote.placeNoteApi2024.annotation.loginStatus.LoggedInOnly;
import com.placeNote.placeNoteApi2024.model.auth.RequestManager;
import com.placeNote.placeNoteApi2024.service.post.PostService;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    RequestManager requestManager;

    @MutationMapping
    @LoggedInOnly
    public String addPost(
            @Argument String title,
            @Argument String placeId,
            @Argument OffsetDateTime visitedDate,
            @Argument Boolean isOpen,
            @Argument List<String> categoryIdList,
            @Argument String detail,
            @Argument List<String> urlList) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        return postService.addPost(
                userAccountId,
                title,
                placeId,
                visitedDate,
                isOpen,
                categoryIdList,
                detail,
                urlList);
    }

}
