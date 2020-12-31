package com.phish.search.repository;

import com.phish.search.model.db.EmailAuthTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAuthTempRepository extends JpaRepository<EmailAuthTemp, String> {
    // userIdをキーにレコード削除
    void deleteByUserId(long userId);
    // userIdをキーにレコード取得
    EmailAuthTemp findByUserId(long userId);

}
