package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.taskExecute.TaskExecuteInput;
import com.api.wasrenaTaskApi2025.model.graphql.taskExecute.TaskExecuteResponse;
import com.api.wasrenaTaskApi2025.service.task.TaskExecuteService;

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
public class TaskExecuteController {
    @Autowired
    TaskExecuteService taskExecuteService;

    // タスク実行履歴の登録
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean createTaskExecute(
            @Argument TaskExecuteInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskExecuteService.createTaskExecute(input, userId);
        return true;
    }

    // タスク実行履歴の削除
    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean deleteTaskExecute(
            @Argument String id, Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskExecuteService.deleteTaskExecute(id, userId);
        return true;
    }

    // タスク実行履歴の取得
    @PreAuthorize("isAuthenticated()")
    @QueryMapping
    public List<TaskExecuteResponse> getTaskExecuteListByDefinitionId(
            @Argument String taskDefinitionId,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        return taskExecuteService.getTaskExecuteListByDefinitionId(taskDefinitionId, userId);
    }
}
