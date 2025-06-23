package com.api.wasrenaTaskApi2025.model.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "task_category")
public class TaskCategoryEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner_user_id")
    private String ownerUserId;

    @Column(name = "display_order", nullable = true)
    private Integer displayOrder;

    // getDisplayOrderはOptionalを返すGetterメソッドを追加
    public Optional<Integer> getDisplayOrderOptional() {
        return Optional.ofNullable(displayOrder);
    }
}
