package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.category.CategoryInput;
import com.api.wasrenaTaskApi2025.model.graphql.category.TaskCategoryResponse;
import com.api.wasrenaTaskApi2025.service.category.TaskCategoryService;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    TaskCategoryService taskCategoryService;

    // カテゴリーの新規登録
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean createCategory(
            @Argument CategoryInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskCategoryService.createTaskCategory(input, userId);
        return true;
    }

    // カテゴリーの更新
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean updateCategory(
            @Argument String id,
            @Argument CategoryInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskCategoryService.updateTaskCategory(id, input, userId);
        return true;
    }

    // カテゴリーの削除
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean deleteCategory(
            @Argument String id,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskCategoryService.deleteTaskCategory(id, userId);
        return true;
    }

    // カテゴリーの一覧取得
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<TaskCategoryResponse> getTaskCategories(Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return taskCategoryService.getTaskCategories(userId);
    }

    // ID指定でのカテゴリー取得
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public TaskCategoryResponse getTaskCategoryById(
            @Argument String id,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return taskCategoryService.getTaskCategoryById(id, userId);
    }
}
