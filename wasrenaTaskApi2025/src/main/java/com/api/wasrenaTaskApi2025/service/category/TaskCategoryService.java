package com.api.wasrenaTaskApi2025.service.category;

import com.api.wasrenaTaskApi2025.model.db.TaskCategoryEntity;
import com.api.wasrenaTaskApi2025.model.graphql.category.CategoryInput;
import com.api.wasrenaTaskApi2025.repository.TaskCategoryRepository;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCategoryService {
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

}
