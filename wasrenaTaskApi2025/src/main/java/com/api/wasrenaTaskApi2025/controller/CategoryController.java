package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.category.CategoryInput;

import graphql.GraphqlErrorException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean createCategory(
            @Argument CategoryInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        System.out.println(userId);
        return true;
    }

}
