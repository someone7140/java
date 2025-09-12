package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskExecuteEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskExecuteRepository extends JpaRepository<TaskExecuteEntity, String> {

    Optional<TaskExecuteEntity> findByIdAndExecuteUserId(String id, String executeUserId);


    @Query("""
            select t from TaskExecuteEntity t 
            where t.taskDefinitionId = :taskDefinitionId 
            and t.executeUserId = :executeUserId
            and t.executeDateTime >= coalesce(:startDateTime, t.executeDateTime)
            and t.executeDateTime <= coalesce(:endDateTime, t.executeDateTime)
            order by t.executeDateTime desc
            limit 300
            """)
    List<TaskExecuteEntity> findTaskExecuteList(
            @Param("taskDefinitionId") String taskDefinitionId,
            @Param("executeUserId") String executeUserId,
            @Param("startDateTime") OffsetDateTime startDateTime,
            @Param("endDateTime") OffsetDateTime endDateTime);

    @Modifying
    @Transactional
    void deleteByTaskDefinitionIdAndExecuteUserId(String taskDefinitionId, String executeUserId);

}
