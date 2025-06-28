package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.queryResult.TaskCheckQueryResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCheckRepository extends JpaRepository<TaskCheckQueryResult, String> {
    @Query(value = "select def.*, exec.execute_date_time as latest_exec_date_time, cate.name as category_name " +
            "from task_definition def " +
            "left outer join (" +
            "select task_definition_id, max(execute_date_time) as execute_date_time " +
            "from task_execute " +
            "group by task_definition_id) exec on def.id = exec.task_definition_id " +
            "left outer join task_category cate on cate.id = def.category_id " +
            "where def.display_flag = true and def.owner_user_id = :ownerUserId " +
            "order by case when def.dead_line_check is null then 1 else 0 end," +
            "cate.display_order nulls last, exec.execute_date_time"
            , nativeQuery = true
    )
    List<TaskCheckQueryResult> findTaskCheckDisplay(@Param("ownerUserId") String ownerUserId);

}
