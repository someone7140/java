package com.api.wasrenaTaskApi2025.model.graphql.task;

import java.util.Optional;

public record TaskExecuteInput(
        String taskDefinitionId,
        Optional<String> memo
) {
}
