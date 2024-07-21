package com.placeNote.placeNoteApi2024.controller;

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
    public Boolean addPost(
            @Argument String title,
            @Argument String placeId,
            @Argument Boolean isOpen,
            @Argument List<String> categoryIdList,
            @Argument String detail,
            @Argument List<String> urlList) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        postService.addPost(
                userAccountId,
                title,
                placeId,
                isOpen,
                categoryIdList,
                detail,
                urlList);
        return true;
    }

}
