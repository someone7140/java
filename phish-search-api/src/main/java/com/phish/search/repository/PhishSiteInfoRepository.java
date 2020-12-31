package com.phish.search.repository;

import com.phish.search.model.aggregate.PhishSiteInfoQueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhishSiteInfoRepository extends JpaRepository<PhishSiteInfoQueryResult, String> {
  List<PhishSiteInfoQueryResult> getAllPhishSiteInfo();
  List<PhishSiteInfoQueryResult> searchPhishSiteInfo(@Param("domain") String domain);
  List<PhishSiteInfoQueryResult> recentPhishSite(@Param("limit") long limit);
  List<PhishSiteInfoQueryResult> refPhishSiteUserPost(@Param("user_id") long userId);
  List<PhishSiteInfoQueryResult> refPhishSiteByPostId(@Param("post_id") long postId);
}
