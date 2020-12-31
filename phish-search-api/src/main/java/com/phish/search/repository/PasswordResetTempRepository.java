package com.phish.search.repository;

import com.phish.search.model.db.EmailAuthTemp;
import com.phish.search.model.db.PasswordResetTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTempRepository extends JpaRepository<PasswordResetTemp, String> {
    // userIdをキーにレコード削除
    void deleteByUserId(long userId);
    // userIdをキーにレコード取得
    PasswordResetTemp findByUserId(long userId);

}
