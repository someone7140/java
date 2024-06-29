package com.placeNote.placeNoteApi2024.controller;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.placeNote.placeNoteApi2024.annotation.loginStatus.LoggedInOnly;
import com.placeNote.placeNoteApi2024.model.auth.RequestManager;
import com.placeNote.placeNoteApi2024.model.graphql.postCategory.PostCategoryResponse;
import com.placeNote.placeNoteApi2024.service.postCategory.PostCategoryService;

import java.util.List;

@Controller
public class PostCategoryController {
    @Autowired
    PostCategoryService postCategoryService;
    @Autowired
    RequestManager requestManager;

    @MutationMapping
    @LoggedInOnly
    public Boolean addPostCategory(
            @Argument String name,
            @Argument String parentCategoryId,
            @Argument Integer displayOrder,
            @Argument String memo
    ) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        return postCategoryService.addPostCategory(
                userAccountId, name, parentCategoryId, displayOrder, memo
        );
    }

    @MutationMapping
    @LoggedInOnly
    public Boolean editPostCategory(
            @Argument String id,
            @Argument String name,
            @Argument String parentCategoryId,
            @Argument Integer displayOrder,
            @Argument String memo
    ) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        return postCategoryService.editPostCategory(
                id, userAccountId, name, parentCategoryId, displayOrder, memo
        );
    }

    @MutationMapping
    @LoggedInOnly
    public Boolean deletePostCategory(@Argument String id) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        postCategoryService.deletePostCategory(id, userAccountId);
        return true;
    }

    @QueryMapping
    @LoggedInOnly
    public List<PostCategoryResponse> getMyPostCategories(@Argument String nameFilter) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        return postCategoryService.getPostCategoryList(nameFilter, userAccountId);
    }

    @QueryMapping
    @LoggedInOnly
    public PostCategoryResponse getMyPostCategoryById(@Argument String idFilter) throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        return postCategoryService.getPostCategoryById(idFilter, userAccountId);
    }
}
