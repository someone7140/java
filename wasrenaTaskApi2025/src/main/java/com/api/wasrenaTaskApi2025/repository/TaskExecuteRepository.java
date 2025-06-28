package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskExecuteEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskExecuteRepository extends JpaRepository<TaskExecuteEntity, String> {

    Optional<TaskExecuteEntity> findByIdAndExecuteUserId(String id, String executeUserId);

    List<TaskExecuteEntity> findTop300ByTaskDefinitionIdAndExecuteUserIdOrderByExecuteDateTimeDesc(
            String taskDefinitionId, String executeUserId);

    @Modifying
    @Transactional
    void deleteByTaskDefinitionIdAndExecuteUserId(String taskDefinitionId, String executeUserId);

}
