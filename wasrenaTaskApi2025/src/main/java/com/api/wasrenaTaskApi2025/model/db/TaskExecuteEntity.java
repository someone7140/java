package com.api.wasrenaTaskApi2025.model.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task_execute")
public class TaskExecuteEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "task_definition_id")
    private String taskDefinitionId;

    @Column(name = "execute_user_id")
    private String executeUserId;

    @Column(name = "execute_date_time")
    private OffsetDateTime executeDateTime;

    @Column(name = "memo")
    private String memo;

    // memoはOptionalを返すGetterメソッドを追加
    public Optional<String> getMemoOptional() {
        return Optional.ofNullable(memo);
    }

}
