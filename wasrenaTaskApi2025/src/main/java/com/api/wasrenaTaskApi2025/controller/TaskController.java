package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionInput;
import com.api.wasrenaTaskApi2025.service.task.TaskDefinitionService;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {
    @Autowired
    TaskDefinitionService taskDefinitionService;

    @PreAuthorize("isAuthenticated()")
    @MutationMapping
    public boolean createTaskDefinition(
            @Argument TaskDefinitionInput input,
            Authentication authentication) throws GraphqlErrorException {
        var userId = authentication.getPrincipal().toString();
        taskDefinitionService.createTaskDefinition(input, userId);
        return true;
    }

}
