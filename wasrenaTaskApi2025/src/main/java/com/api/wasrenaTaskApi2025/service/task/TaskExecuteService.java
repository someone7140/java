package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.TaskExecuteEntity;
import com.api.wasrenaTaskApi2025.model.graphql.taskExecute.TaskExecuteInput;
import com.api.wasrenaTaskApi2025.model.graphql.taskExecute.TaskExecuteResponse;
import com.api.wasrenaTaskApi2025.repository.TaskExecuteRepository;

import com.api.wasrenaTaskApi2025.service.common.DateService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskExecuteService {
    @Autowired
    DateService dateService;
    @Autowired
    TaskExecuteRepository taskExecuteRepository;

    // タスク実行履歴を追加
    public void createTaskExecute(TaskExecuteInput input, String userId) {
        var uuid = Generators.timeBasedEpochGenerator().generate().toString();
        var taskExecuteEntity = TaskExecuteEntity.builder()
                .id(uuid.toString())
                .taskDefinitionId(input.taskDefinitionId())
                .executeUserId(userId)
                .executeDateTime(dateService.getNowDateTime())
                .memo(input.memo().orElse(null))
                .build();
        taskExecuteRepository.save(taskExecuteEntity);
    }

    // タスク実行履歴を取得
    public List<TaskExecuteResponse> getTaskExecuteListByDefinitionId(
            String taskDefinitionId, String userId) {
        var queryResult = taskExecuteRepository.findTop300ByTaskDefinitionIdAndExecuteUserIdOrderByExecuteDateTimeDesc(
                taskDefinitionId, userId);

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
