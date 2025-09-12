package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.TaskExecuteEntity;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskCheckResponse;
import com.api.wasrenaTaskApi2025.model.graphql.taskExecute.TaskExecuteInput;
import com.api.wasrenaTaskApi2025.model.graphql.taskExecute.TaskExecuteResponse;
import com.api.wasrenaTaskApi2025.repository.TaskCheckRepository;
import com.api.wasrenaTaskApi2025.repository.TaskExecuteRepository;
import com.api.wasrenaTaskApi2025.service.common.DateService;

import com.fasterxml.uuid.Generators;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskExecuteService {
    @Autowired
    DateService dateService;
    @Autowired
    TaskCheckService taskCheckService;
    @Autowired
    TaskCheckRepository taskCheckRepository;
    @Autowired
    TaskExecuteRepository taskExecuteRepository;

    // タスク実行履歴を追加
    public TaskCheckResponse createTaskExecute(TaskExecuteInput input, String userId) {
        var uuid = Generators.timeBasedEpochGenerator().generate().toString();
        var nowDateTime = dateService.getNowDateTime();
        var taskExecuteEntity = TaskExecuteEntity.builder()
                .id(uuid.toString())
                .taskDefinitionId(input.taskDefinitionId())
                .executeUserId(userId)
                .executeDateTime(nowDateTime)
                .memo(input.memo().orElse(null))
                .build();
        taskExecuteRepository.save(taskExecuteEntity);

        // 最新のタスクの状況を取得する
        var queryResultList = taskCheckRepository.findTaskCheckDisplay(userId, input.taskDefinitionId());
        // 取得できなかった場合はエラー
        if (queryResultList.isEmpty()) {
            taskExecuteRepository.delete(taskExecuteEntity);
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not get definition")
                    .build();
        }
        return taskCheckService.convertResponseFromTaskCheckQueryResult(queryResultList.getFirst(), nowDateTime);
    }

    // タスク実行履歴を取得
    public List<TaskExecuteResponse> getTaskExecuteListByDefinitionId(String taskDefinitionId, String userId, OffsetDateTime startDateTime, OffsetDateTime endDateTime) {
        var queryResult = taskExecuteRepository.findTaskExecuteList(
                taskDefinitionId,
                userId,
                startDateTime == null ? null : startDateTime
                        .withHour(0)
                        .withMinute(0)
                        .withSecond(0)
                        .withNano(0),
                endDateTime == null ? null : endDateTime
                        .withHour(23)
                        .withMinute(59)
                        .withSecond(59)
                        .withNano(999_999_999));

        var responseList = new ArrayList<TaskExecuteResponse>();
        for (var entity : queryResult) {
            responseList.add(new TaskExecuteResponse(
                    entity.getId(),
                    entity.getTaskDefinitionId(),
                    entity.getExecuteDateTime(),
                    entity.getMemoOptional()
            ));
        }
        return responseList;
    }

    // タスク実行履歴を削除
    public void deleteTaskExecute(String id, String userId) {
        var registeredTaskExec = taskExecuteRepository.findByIdAndExecuteUserId(id, userId);
        // 取得できなかった場合は処理終了
        if (registeredTaskExec.isEmpty()) {
            return;
        }

        taskExecuteRepository.delete(registeredTaskExec.get());
    }
}
