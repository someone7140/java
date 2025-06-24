package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskCategoryEntity;
import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDefinitionRepository extends JpaRepository<TaskDefinitionEntity, String>,
        JpaSpecificationExecutor<TaskDefinitionEntity> {

    // ユーザーIDを指定してタスク定義の一覧を取得するspecification
    static Specification<TaskDefinitionEntity> specificationHasOwnerUserId(String ownerUserId) {
        return (root, query, criteriaBuilder) -> {
            // LEFT JOINでcategoryを取得
            Join<TaskDefinitionEntity, TaskCategoryEntity> categoryJoin =
                    root.join("category", JoinType.LEFT);

            // ORDER BYを設定
            Order displayOrderAsc = criteriaBuilder.asc(
                    criteriaBuilder.coalesce(categoryJoin.get("displayOrder"), Integer.MAX_VALUE)
            );
            Order idDesc = criteriaBuilder.desc(root.get("id"));
            query.orderBy(displayOrderAsc, idDesc);

            return criteriaBuilder.equal(root.get("ownerUserId"), ownerUserId);
        };
    }
}
