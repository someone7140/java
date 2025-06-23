package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDefinitionRepository extends JpaRepository<TaskDefinitionEntity, String> {
    @EntityGraph(attributePaths = {"category"})
    List<TaskDefinitionEntity> findByOwnerUserId(String ownerUserId);
}
