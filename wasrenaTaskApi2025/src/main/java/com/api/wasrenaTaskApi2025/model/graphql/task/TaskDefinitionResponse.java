package com.api.wasrenaTaskApi2025.model.graphql.task;

import com.api.wasrenaTaskApi2025.model.enumeration.DeadLineCheck;

import java.util.Map;
import java.util.Optional;

public record TaskDefinitionResponse(
        String id,
        String title,
        Boolean displayFlag,
        Boolean notificationFlag,
        Optional<String> categoryId,
        Optional<String> categoryName,
        Optional<DeadLineCheck> deadLineCheck,
        Optional<Map<String, Object>> deadLineCheckSubSetting,
        Optional<String> detail
) {
}
