package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionInput;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskDefinitionResponse;
import com.api.wasrenaTaskApi2025.repository.TaskDefinitionRepository;
import com.api.wasrenaTaskApi2025.repository.TaskExecuteRepository;

import com.fasterxml.uuid.Generators;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskDefinitionService {
    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;
    @Autowired
    TaskExecuteRepository taskExecuteRepository;

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

    // タスク定義を更新
    public void updateTaskDefinition(
            String id,
            TaskDefinitionInput input,
            String userId) {
        var registeredDefinition = taskDefinitionRepository.findByIdAndOwnerUserId(id, userId);

        // 取得できなかった場合はエラー
        if (registeredDefinition.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not get definition")
                    .build();
        }

        // 更新処理
        var entity = registeredDefinition.get();
        entity.setTitle(input.title());
        entity.setDisplayFlag(input.displayFlag());
        entity.setNotificationFlag(input.notificationFlag());
        entity.setCategoryId(input.categoryId().orElse(null));
        entity.setDeadLineCheck(input.deadLineCheck().orElse(null));
        entity.setDeadLineCheckSubSetting(input.deadLineCheckSubSetting().orElse(null));
        entity.setDetail(input.detail().orElse(null));
        taskDefinitionRepository.save(entity);
    }

    // タスク定義を削除
    public void deleteTaskDefinition(String id, String userId) {
        var registeredTask = taskDefinitionRepository.findByIdAndOwnerUserId(id, userId);
        // 取得できなかった場合は処理終了
        if (registeredTask.isEmpty()) {
            return;
        }

        // タスク定義の実行履歴を削除
        taskExecuteRepository.deleteByTaskDefinitionIdAndExecuteUserId(id, userId);
        // タスク定義を削除
        taskDefinitionRepository.delete(registeredTask.get());
    }

    // タスク定義の一覧を取得
    public List<TaskDefinitionResponse> getTaskDefinitionList(String userId) {
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

    // ID指定でのタスク定義の取得
    public TaskDefinitionResponse getTaskDefinitionById(String userId) {
        var specifications = TaskDefinitionRepository.specificationHasOwnerUserId(userId);
        var queryResult = taskDefinitionRepository.findBy(specifications, fetchable ->
                fetchable.project("category").first());

        // 取得できなかった場合はエラー
        if (queryResult.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not get definition")
                    .build();
        }

        var entity = queryResult.get();
        return new TaskDefinitionResponse(
                entity.getId(),
                entity.getTitle(),
                entity.isDisplayFlag(),
                entity.isNotificationFlag(),
                entity.getCategoryIdOptional(),
                entity.getCategoryOptional().map(c -> c.getName()),
                entity.getDeadLineCheckOptional(),
                entity.getDeadLineCheckSubSettingOptional(),
                entity.getDetailOptional()
        );
    }
}
