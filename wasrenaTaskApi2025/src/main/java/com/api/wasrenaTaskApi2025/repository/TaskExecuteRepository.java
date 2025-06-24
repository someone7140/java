package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskExecuteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskExecuteRepository extends JpaRepository<TaskExecuteEntity, String> {
}
