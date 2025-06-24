package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionInput;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionResponse;
import com.api.wasrenaTaskApi2025.repository.TaskDefinitionRepository;

import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // タスク定義の一覧を取得
    public List<TaskDefinitionResponse> getTaskDefinitionListByUserId(String userId) {
        // Limit300件でDBからデータ取得
        var specifications = TaskDefinitionRepository.specificationHasOwnerUserId(userId);
        var queryResult = taskDefinitionRepository.findBy(specifications, fetchable ->
                fetchable.project("category").limit(300).all());

        var responseList = new ArrayList<TaskDefinitionResponse>();
        for (var entity : queryResult) {
            responseList.add(new TaskDefinitionResponse(
                    entity.getId(),
                    entity.getTitle(),
                    entity.isDisplayFlag(),
                    entity.isNotificationFlag(),
                    entity.getCategoryIdOptional(),
                    entity.getCategoryOptional().map(c -> c.getName()),
                    entity.getDeadLineCheckOptional(),
                    entity.getDeadLineCheckSubSettingOptional(),
                    entity.getDetailOptional()
            ));
        }
        return responseList;
    }

}
