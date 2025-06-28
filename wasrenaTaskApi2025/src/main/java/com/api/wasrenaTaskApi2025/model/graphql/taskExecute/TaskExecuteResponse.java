package com.api.wasrenaTaskApi2025.model.graphql.taskExecute;

import java.time.OffsetDateTime;
import java.util.Optional;

public record TaskExecuteResponse(
        String id,
        String taskDefinitionId,
        OffsetDateTime executeDateTime,
        Optional<String> memo
) {
}
