package com.phish.search.repository;

import com.phish.search.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // emailをキーにユーザを取得
    User findByEmail(String email);
    // idをキーにユーザを取得
    User findById(long id);
    // ユーザIDを指定してレコード削除
    void deleteById(long id);
    // 名前を更新
    @Query(value = "update users u set u.name = :name where u.id = :id", nativeQuery = true)
    @Modifying
    void updateName(@Param("id") long id, @Param("name") String name);
    // パスワード更新
    @Query(value = "update users u set u.password = :password where u.id = :id", nativeQuery = true)
    @Modifying
    void updatePassword(@Param("id") long id, @Param("password") String password);
}
