package com.api.wasrenaTaskApi2025.service.category;

import com.api.wasrenaTaskApi2025.model.db.TaskCategoryEntity;
import com.api.wasrenaTaskApi2025.model.graphql.category.CategoryInput;
import com.api.wasrenaTaskApi2025.model.graphql.category.TaskCategoryResponse;
import com.api.wasrenaTaskApi2025.repository.TaskCategoryRepository;
import com.api.wasrenaTaskApi2025.repository.TaskDefinitionRepository;

import com.fasterxml.uuid.Generators;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskCategoryService {
    @Autowired
    TaskDefinitionRepository taskDefinitionRepository;
    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    // カテゴリーを追加
    public void createTaskCategory(CategoryInput input, String userId) {
        var uuid = Generators.timeBasedEpochGenerator().generate().toString();
        var taskCategoryEntity = TaskCategoryEntity.builder()
                .id(uuid.toString())
                .name(input.name())
                .ownerUserId(userId)
                .displayOrder(input.displayOrder().orElse(null))
                .build();
        taskCategoryRepository.save(taskCategoryEntity);
    }

    // カテゴリーを更新
    public void updateTaskCategory(
            String id,
            CategoryInput input,
            String userId) {
        var registeredCategory = taskCategoryRepository.findByIdAndOwnerUserId(id, userId);
        // 取得できなかった場合はエラー
        if (registeredCategory.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not get category")
                    .build();
        }

        // 更新処理
        var entity = registeredCategory.get();
        entity.setName(input.name());
        entity.setDisplayOrder(input.displayOrder().orElse(null));
        taskCategoryRepository.save(entity);
    }

    // カテゴリーを削除
    public void deleteTaskCategory(String id, String userId) {
        var registeredCategory = taskCategoryRepository.findByIdAndOwnerUserId(id, userId);
        // 取得できなかった場合は処理終了
        if (registeredCategory.isEmpty()) {
            return;
        }

        // タスク定義のカテゴリー設定をnullに更新
        taskDefinitionRepository.updateCategoryIdNull(id, userId);
        // カテゴリーを削除
        taskCategoryRepository.delete(registeredCategory.get());
    }

    // カテゴリーの一覧取得
    public List<TaskCategoryResponse> getTaskCategories(String userId) {
        var specifications = TaskCategoryRepository.specificationHasOwnerUserId(userId);
        var queryResult = taskCategoryRepository.findAll(specifications);

        var responseList = new ArrayList<TaskCategoryResponse>();
        for (var entity : queryResult) {
            responseList.add(new TaskCategoryResponse(
                    entity.getId(),
                    entity.getName(),
                    entity.getDisplayOrderOptional()
            ));
        }
        return responseList;
    }

    // ID指定でのカテゴリー取得
    public TaskCategoryResponse getTaskCategoryById(String id, String userId) {
        var registeredCategory = taskCategoryRepository.findByIdAndOwnerUserId(id, userId);
        // 取得できなかった場合はエラー
        if (registeredCategory.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not get category")
                    .build();
        }

        var entity = registeredCategory.get();
        return new TaskCategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getDisplayOrderOptional()
        );
    }
}
