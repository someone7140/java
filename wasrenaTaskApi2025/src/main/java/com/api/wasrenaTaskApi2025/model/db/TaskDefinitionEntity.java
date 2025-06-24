package com.api.wasrenaTaskApi2025.model.db;

import com.api.wasrenaTaskApi2025.model.enumeration.DeadLineCheck;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task_definition")
public class TaskDefinitionEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "owner_user_id")
    private String ownerUserId;

    @Column(name = "display_flag")
    private boolean displayFlag;

    @Column(name = "notification_flag")
    private boolean notificationFlag;

    @Column(name = "category_id", nullable = true)
    private String categoryId;

    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    @Column(name = "dead_line_check", nullable = true)
    private DeadLineCheck deadLineCheck;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "dead_line_check_sub_setting", nullable = true)
    private Map<String, Object> deadLineCheckSubSetting;

    @Column(name = "detail", nullable = true)
    private String detail;

    // categoryIdはOptionalを返すGetterメソッドを追加
    public Optional<String> getCategoryIdOptional() {
        return Optional.ofNullable(categoryId);
    }

    // deadLineCheckはOptionalを返すGetterメソッドを追加
    public Optional<DeadLineCheck> getDeadLineCheckOptional() {
        return Optional.ofNullable(deadLineCheck);
    }

    // deadLineCheckSubSettingはOptionalを返すGetterメソッドを追加
    public Optional<Map<String, Object>> getDeadLineCheckSubSettingOptional() {
        return Optional.ofNullable(deadLineCheckSubSetting);
    }

    // detailはOptionalを返すGetterメソッドを追加
    public Optional<String> getDetailOptional() {
        return Optional.ofNullable(detail);
    }

    // カテゴリーのリレーション設定
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TaskCategoryEntity category;

    // categoryはOptionalを返すGetterメソッドを追加
    public Optional<TaskCategoryEntity> getCategoryOptional() {
        return Optional.ofNullable(category);
    }
}
