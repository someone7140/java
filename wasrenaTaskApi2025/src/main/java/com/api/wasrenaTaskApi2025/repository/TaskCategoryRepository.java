package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskCategoryEntity;

import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategoryEntity, String>,
        JpaSpecificationExecutor<TaskCategoryEntity> {

    Optional<TaskCategoryEntity> findByIdAndOwnerUserId(String id, String ownerUserId);

    // ユーザーIDを指定してカテゴリーの一覧を取得するspecification
    static Specification<TaskCategoryEntity> specificationHasOwnerUserId(String ownerUserId) {
        return (root, query, criteriaBuilder) -> {

            // ORDER BYを設定
            Order displayOrderAsc = criteriaBuilder.asc(
                    criteriaBuilder.coalesce(root.get("displayOrder"), Integer.MAX_VALUE)
            );
            Order idDesc = criteriaBuilder.desc(root.get("id"));
            query.orderBy(displayOrderAsc, idDesc);

            return criteriaBuilder.equal(root.get("ownerUserId"), ownerUserId);
        };
    }
}
