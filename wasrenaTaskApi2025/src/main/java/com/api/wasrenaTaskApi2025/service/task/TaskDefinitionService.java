package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionInput;
import com.api.wasrenaTaskApi2025.repository.TaskDefinitionRepository;

import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskDefinitionService {
    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;

    // タスク定義を追加
    public void createTaskDefinition(TaskDefinitionInput input, String userId) {
        var uuid = Generators.timeBasedEpochGenerator().generate().toString();
        var taskDefinitionEntity = TaskDefinitionEntity.builder()
                .id(uuid.toString())
                .title(input.title())
                .ownerUserId(userId)
                .displayFlag(input.displayFlag())
                .notificationFlag(input.notificationFlag())
                .categoryId(input.categoryId().orElse(null))
                .deadLineCheck(input.deadLineCheck().orElse(null))
                .deadLineCheckSubSetting(input.deadLineCheckSubSetting().orElse(null))
                .detail(input.detail().orElse(null))
                .build();
        taskDefinitionRepository.save(taskDefinitionEntity);
    }

}
