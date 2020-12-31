package com.phish.search.repository;

import com.phish.search.model.db.PhishUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhishUrlRepository extends JpaRepository<PhishUrl, String> {
    // urlをキーにレコード取得
    PhishUrl findByPhishUrl(String phishUrl);

    // URLに紐付きがない不要なURLレコードを削除（PhishTankに未登録かつURL投稿が無い）
    @Query(value = "delete from phish_urls u where u.phish_tank_id is null and not exists (select 'x' from user_posts p where u.id = p.url_id)", nativeQuery = true)
    @Modifying
    void deleteUnnecessaryUrl();

    // IDのリストで削除
    void deleteByIdIn(List<Long> ids);

    // 対象IDのPhisTankの情報を空にする
    @Query(value = "update phish_urls u set u.phish_tank_id = null, u.phish_tank_url = null where u.id IN :ids", nativeQuery = true)
    @Modifying
    void updatePhishTankInfoEmpty(@Param("ids") List<Long> ids);
}
