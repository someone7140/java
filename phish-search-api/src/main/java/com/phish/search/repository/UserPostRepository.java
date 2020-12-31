package com.phish.search.repository;

import com.phish.search.model.db.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, String> {
    // ユーザIDを指定してレコード削除
    void deleteByUserId(long userId);
    // 投稿IDとユーザIDを指定してレコード削除
    void deleteByIdAndUserId(long id, long userId);
    // userIdとurlをキーにレコード取得
    @Query(value = "select * from user_posts p where p.user_id = :userId and p.url_id in (select u.id from phish_urls u where u.phish_url = :url)", nativeQuery = true)
    UserPost findByUserIdAndUrl(@Param("userId") long userId, @Param("url") String url );
}
