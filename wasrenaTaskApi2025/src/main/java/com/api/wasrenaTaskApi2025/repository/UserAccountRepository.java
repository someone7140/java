package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.UserAccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
    Optional<UserAccountEntity> findById(String id);

    Optional<UserAccountEntity> findByLineId(String lineId);

    Optional<UserAccountEntity> findByUserSettingId(String userSettingId);

    Optional<UserAccountEntity> findByUserSettingIdOrLineId(String userSettingId, String lineId);
}
