package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.TaskExecuteEntity;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskExecuteInput;
import com.api.wasrenaTaskApi2025.repository.TaskExecuteRepository;

import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TaskExecuteService {
    @Autowired
    TaskExecuteRepository taskExecuteRepository;

    // タスク実行履歴を追加
    public void createTaskExecute(TaskExecuteInput input, String userId) {
        var uuid = Generators.timeBasedEpochGenerator().generate().toString();
        var taskExecuteEntity = TaskExecuteEntity.builder()
                .id(uuid.toString())
                .taskDefinitionId(input.taskDefinitionId())
                .executeUserId(userId)
                .executeDateTime(OffsetDateTime.now())
                .memo(input.memo().orElse(null))
                .build();
        taskExecuteRepository.save(taskExecuteEntity);
    }

}
