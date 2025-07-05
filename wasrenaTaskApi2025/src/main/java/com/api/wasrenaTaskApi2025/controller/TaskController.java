package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.task.TaskCheckDisplayResponse;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionInput;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionResponse;
import com.api.wasrenaTaskApi2025.service.task.TaskCheckService;
import com.api.wasrenaTaskApi2025.service.task.TaskDefinitionService;

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
public class TaskController {
    @Autowired
    TaskCheckService taskCheckService;
    @Autowired
    TaskDefinitionService taskDefinitionService;

    // タスク定義の新規登録
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean createTaskDefinition(
            @Argument TaskDefinitionInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskDefinitionService.createTaskDefinition(input, userId);
        return true;
    }

    // タスク定義の更新
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean updateTaskDefinition(
            @Argument String id,
            @Argument TaskDefinitionInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskDefinitionService.updateTaskDefinition(id, input, userId);
        return true;
    }

    // タスク定義の削除
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean deleteTaskDefinition(
            @Argument String id,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskDefinitionService.deleteTaskDefinition(id, userId);
        return true;
    }

    // タスク定義の一覧取得
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<TaskDefinitionResponse> getTaskDefinitions(Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return taskDefinitionService.getTaskDefinitionList(userId);
    }

    // ID指定でのタスク定義取得
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public TaskDefinitionResponse getTaskDefinitionById(
            @Argument String id,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return taskDefinitionService.getTaskDefinitionById(userId, id);
    }

    // チェック対象のタスク一覧取得
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<TaskCheckDisplayResponse> getTaskCheckDisplayList(Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return taskCheckService.getTaskCheckDisplayList(userId);
    }
}
