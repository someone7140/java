package com.api.wasrenaTaskApi2025.model.db.queryResult;

import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;

import com.api.wasrenaTaskApi2025.model.db.base.TaskDefinitionEntityBase;
import com.api.wasrenaTaskApi2025.model.db.converter.OffsetDateTimeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.TimeZoneColumn;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.time.OffsetDateTime;
import java.util.Optional;

// TaskDefinitionEntityをベースにしたチェック一覧の格納用のクラス
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TaskCheckQueryResult extends TaskDefinitionEntityBase {
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "latest_exec_date_time")
    @Convert(converter = OffsetDateTimeConverter.class)
    private OffsetDateTime latestExecDateTime;

    // categoryNameはOptionalを返すGetterメソッドを追加
    public Optional<String> getCategoryNameOptional() {
        return Optional.ofNullable(categoryName);
    }

    // latestExecDateTimeはOptionalを返すGetterメソッドを追加
    public Optional<OffsetDateTime> getLatestExecDateTimeOptional() {
        return Optional.ofNullable(latestExecDateTime);
    }
}
