package com.phish.search.repository;

import com.phish.search.model.db.EmailAuthTemp;
import com.phish.search.model.db.PhishDomain;
import com.phish.search.model.db.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhishDomainRepository extends JpaRepository<PhishDomain, String> {
    // domainをキーにレコード取得
    PhishDomain findByPhishDomain(String phishDomain);

    // URLに紐付きがない不要なドメインレコードを削除
    @Query(value = "delete from phish_domains d where not exists (select 'x' from phish_urls u where d.id = u.domain_id)", nativeQuery = true)
    @Modifying
    void deleteUnnecessaryDomain();

}
