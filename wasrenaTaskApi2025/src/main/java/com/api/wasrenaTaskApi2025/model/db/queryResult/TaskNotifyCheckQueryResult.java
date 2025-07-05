package com.api.wasrenaTaskApi2025.model.db.queryResult;

import com.api.wasrenaTaskApi2025.model.db.base.TaskDefinitionEntityBase;
import com.api.wasrenaTaskApi2025.model.db.converter.OffsetDateTimeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.Optional;

// TaskDefinitionEntityをベースにした通知対象一覧の格納用のクラス
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TaskNotifyCheckQueryResult extends TaskDefinitionEntityBase {
    @Column(name = "line_id")
    private String lineId;

    @Column(name = "latest_exec_date_time")
    @Convert(converter = OffsetDateTimeConverter.class)
    private OffsetDateTime latestExecDateTime;

    // latestExecDateTimeはOptionalを返すGetterメソッドを追加
    public Optional<OffsetDateTime> getLatestExecDateTimeOptional() {
        return Optional.ofNullable(latestExecDateTime);
    }
}
