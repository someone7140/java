package com.api.wasrenaTaskApi2025.model.graphql.task;

import com.api.wasrenaTaskApi2025.model.db.queryResult.TaskCheckQueryResult;
import com.api.wasrenaTaskApi2025.model.enumeration.DeadLineCheck;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

public record TaskCheckResponse(
        String id,
        String title,
        Boolean displayFlag,
        Boolean notificationFlag,
        Optional<String> categoryId,
        Optional<String> categoryName,
        Optional<DeadLineCheck> deadLineCheck,
        Optional<Map<String, Object>> deadLineCheckSubSetting,
        Optional<String> detail,
        Optional<OffsetDateTime> latestExecDateTime,
        Optional<OffsetDateTime> nextDeadLineDateTime,
        Boolean isExceedDeadLine
) {
    public TaskCheckResponse(
            TaskCheckQueryResult queryResult,
            Optional<OffsetDateTime> nextDeadLineDateTime,
            Boolean isExceedDeadLine
    ) {
        this(queryResult.getId(),
                queryResult.getTitle(),
                queryResult.isDisplayFlag(),
                queryResult.isNotificationFlag(),
                queryResult.getCategoryIdOptional(),
                queryResult.getCategoryNameOptional(),
                queryResult.getDeadLineCheckOptional(),
                queryResult.getDeadLineCheckSubSettingOptional(),
                queryResult.getDetailOptional(),
                queryResult.getLatestExecDateTimeOptional(),
                nextDeadLineDateTime,
                isExceedDeadLine);
    }
}
