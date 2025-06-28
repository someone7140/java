package com.api.wasrenaTaskApi2025.model.db;

import com.api.wasrenaTaskApi2025.model.db.base.TaskDefinitionEntityBase;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Table(name = "task_definition")
public class TaskDefinitionEntity extends TaskDefinitionEntityBase {
    // カテゴリーのリレーション設定
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TaskCategoryEntity category;

    // categoryはOptionalを返すGetterメソッドを追加
    public Optional<TaskCategoryEntity> getCategoryOptional() {
        return Optional.ofNullable(category);
    }
}
