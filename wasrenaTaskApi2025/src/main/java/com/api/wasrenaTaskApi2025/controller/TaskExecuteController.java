package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.task.TaskExecuteInput;

import com.api.wasrenaTaskApi2025.service.task.TaskExecuteService;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


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

}
