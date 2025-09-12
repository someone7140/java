package com.api.wasrenaTaskApi2025.repository;

import com.api.wasrenaTaskApi2025.model.db.UserAccountEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
    Optional<UserAccountEntity> findById(String id);

    Optional<UserAccountEntity> findByLineId(String lineId);

    Optional<UserAccountEntity> findByUserSettingId(String userSettingId);

    Optional<UserAccountEntity> findByUserSettingIdOrLineId(String userSettingId, String lineId);

    @Modifying
    @Transactional
    @Query("update UserAccountEntity u " +
            "set u.lineNotifyLatestDate = :lineNotifyLatestDate " +
            "where u.id = :accountUserId")
    void updateLineNotifyLatestDate(
            @Param("accountUserId") String accountUserId,
            @Param("lineNotifyLatestDate") OffsetDateTime lineNotifyLatestDate
    );
}
