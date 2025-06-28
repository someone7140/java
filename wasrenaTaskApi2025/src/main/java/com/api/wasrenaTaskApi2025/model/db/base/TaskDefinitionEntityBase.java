package com.api.wasrenaTaskApi2025.model.db.base;

import com.api.wasrenaTaskApi2025.model.db.TaskCategoryEntity;
import com.api.wasrenaTaskApi2025.model.enumeration.DeadLineCheck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;
import java.util.Optional;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class TaskDefinitionEntityBase {

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

}
