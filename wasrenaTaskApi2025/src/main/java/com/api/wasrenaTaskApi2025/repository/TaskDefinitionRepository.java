package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.TaskCategoryEntity;
import com.api.wasrenaTaskApi2025.model.db.TaskDefinitionEntity;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TaskDefinitionRepository extends JpaRepository<TaskDefinitionEntity, String>,
        JpaSpecificationExecutor<TaskDefinitionEntity> {

    Optional<TaskDefinitionEntity> findByIdAndOwnerUserId(String id, String ownerUserId);

    // ユーザーIDを指定してタスク定義の一覧を取得するspecification
    static Specification<TaskDefinitionEntity> specificationHasOwnerUserId(String ownerUserId, Optional<String> definitionIdOptional) {
        return (root, query, criteriaBuilder) -> {
            // LEFT JOINでcategoryを取得
            Join<TaskDefinitionEntity, TaskCategoryEntity> categoryJoin =
                    root.join("category", JoinType.LEFT);

            // 条件のリストを作成
            var predicates = new ArrayList<Predicate>();
            // 基本条件（ownerUserId）
            predicates.add(criteriaBuilder.equal(root.get("ownerUserId"), ownerUserId));
            // 条件付きでdefinitionIdの条件を追加
            if (definitionIdOptional.isPresent()) {
                predicates.add(criteriaBuilder.equal(root.get("id"), definitionIdOptional.get()));
            }

            // ORDER BYを設定
            Order displayOrderAsc = criteriaBuilder.asc(
                    criteriaBuilder.coalesce(categoryJoin.get("displayOrder"), Integer.MAX_VALUE)
            );
            Order idDesc = criteriaBuilder.desc(root.get("id"));
            query.orderBy(displayOrderAsc, idDesc);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    // カテゴリーIDを指定して一括でnullに更新
    @Modifying
    @Transactional
    @Query("update TaskDefinitionEntity def " +
            "set def.categoryId = null " +
            "where def.categoryId = :categoryId AND def.ownerUserId = :ownerUserId")
    void updateCategoryIdNull(
            @Param("categoryId") String categoryId,
            @Param("ownerUserId") String ownerUserId);

    // ユーザーIDを指定して一括でLINE通知をOFFにする
    @Modifying
    @Transactional
    @Query("update TaskDefinitionEntity def " +
            "set def.notificationFlag = false " +
            "where def.ownerUserId = :ownerUserId")
    void updateNotificationFlagOff(
            @Param("ownerUserId") String ownerUserId);
}
