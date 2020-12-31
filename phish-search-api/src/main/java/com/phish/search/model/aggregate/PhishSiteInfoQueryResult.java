package com.phish.search.model.aggregate;

import javax.persistence.*;
import java.time.LocalDateTime;

@SqlResultSetMapping(
  name = "phishSiteInfoMap",
  entities = {
    @EntityResult(
      entityClass= PhishSiteInfoQueryResult.class,
        fields = {
          @FieldResult(name="phishUrlId", column="phish_url_id"),
          @FieldResult(name="phishUrl", column="phish_url"),
          @FieldResult(name="phishTankId", column="phish_tank_id"),
          @FieldResult(name="phishTankUrl", column="phish_tank_url"),
          @FieldResult(name="registerAt", column="register_at"),
          @FieldResult(name="phishDomainId", column="domain_id"),
          @FieldResult(name="phishDomain", column="phish_domain"),
          @FieldResult(name="postId", column = "post_id"),
          @FieldResult(name="postContents", column = "post_contents"),
          @FieldResult(name="postRegisterAt", column = "post_register_at"),
          @FieldResult(name="userId", column = "user_id"),
          @FieldResult(name="userName", column = "user_name")
        }
    )
  }
)
@NamedNativeQuery(
  name = "PhishSiteInfoQueryResult.getAllPhishSiteInfo",
  resultSetMapping = "phishSiteInfoMap",
  query = "select u.id as phish_url_id, u.phish_url, u.phish_tank_id, u.phish_tank_url, u.register_at, u.domain_id, d.phish_domain, po.post_id, po.post_contents, po.post_register_at, po.user_id, po.user_name " +
          "from phish_urls u inner join phish_domains d on d.id = u.domain_id " +
          "left outer join (select p.id as post_id, p.url_id, p.post_contents, p.register_at as post_register_at, p.user_id, us.name as user_name from user_posts p inner join users us on p.user_id = us.id where us.status = 'active') po on u.id = po.url_id " +
          "order by u.register_at desc, u.phish_url, po.post_register_at desc"
)
@NamedNativeQuery(
  name = "PhishSiteInfoQueryResult.searchPhishSiteInfo",
  resultSetMapping = "phishSiteInfoMap",
  query = "select u.id as phish_url_id, u.phish_url, u.phish_tank_id, u.phish_tank_url, u.register_at, u.domain_id, d.phish_domain, po.post_id, po.post_contents, po.post_register_at, po.user_id, po.user_name " +
          "from phish_urls u inner join phish_domains d on d.id = u.domain_id " +
          "left outer join (select p.id as post_id, p.url_id, p.post_contents, p.register_at as post_register_at, p.user_id, us.name as user_name from user_posts p inner join users us on p.user_id = us.id where us.status = 'active') po on u.id = po.url_id " +
          "where d.phish_domain like :domain " +
          "order by u.register_at desc, u.phish_url, po.post_register_at desc"
)
@NamedNativeQuery(
  name = "PhishSiteInfoQueryResult.recentPhishSite",
  resultSetMapping = "phishSiteInfoMap",
  query = "select u.id as phish_url_id, u.phish_url, u.phish_tank_id, u.phish_tank_url, u.register_at, u.domain_id, d.phish_domain, po.post_id, po.post_contents, po.post_register_at, po.user_id, po.user_name " +
          "from phish_urls u inner join phish_domains d on d.id = u.domain_id " +
          "left outer join (select p.id as post_id, p.url_id, p.post_contents, p.register_at as post_register_at, p.user_id, us.name as user_name from user_posts p inner join users us on p.user_id = us.id where us.status = 'active') po on u.id = po.url_id " +
          "where u.id in (select * from (select u.id from phish_urls u ORDER BY u.register_at desc) WHERE rownum <= :limit) " +
          "order by u.register_at desc, u.phish_url, po.post_register_at desc"
)
@NamedNativeQuery(
  name = "PhishSiteInfoQueryResult.refPhishSiteUserPost",
  resultSetMapping = "phishSiteInfoMap",
  query = "select u.id as phish_url_id, u.phish_url, u.phish_tank_id, u.phish_tank_url, u.register_at, u.domain_id, d.phish_domain, po.post_id, po.post_contents, po.post_register_at, po.user_id, po.user_name " +
          "from phish_urls u inner join phish_domains d on d.id = u.domain_id " +
          "left outer join (select p.id as post_id, p.url_id, p.post_contents, p.register_at as post_register_at, p.user_id, us.name as user_name from user_posts p inner join users us on p.user_id = us.id where us.status = 'active') po on u.id = po.url_id " +
          "where po.user_id = :user_id " +
          "order by po.post_register_at desc"
)
@NamedNativeQuery(
        name = "PhishSiteInfoQueryResult.refPhishSiteByPostId",
        resultSetMapping = "phishSiteInfoMap",
        query = "select u.id as phish_url_id, u.phish_url, u.phish_tank_id, u.phish_tank_url, u.register_at, u.domain_id, d.phish_domain, po.post_id, po.post_contents, po.post_register_at, po.user_id, po.user_name " +
                "from phish_urls u inner join phish_domains d on d.id = u.domain_id " +
                "left outer join (select p.id as post_id, p.url_id, p.post_contents, p.register_at as post_register_at, p.user_id, us.name as user_name from user_posts p inner join users us on p.user_id = us.id where us.status = 'active') po on u.id = po.url_id " +
                "where po.post_id = :post_id " +
                "order by po.post_register_at desc"
)
@Entity
public class PhishSiteInfoQueryResult {
  @Id
  private Long phishUrlId;
  private String phishUrl;
  private Long phishTankId;
  private String phishTankUrl;
  private LocalDateTime registerAt;
  private Long phishDomainId;
  private String phishDomain;

  private Long postId;
  private String postContents;
  private LocalDateTime postRegisterAt;
  private Long userId;
  private String userName;

  public PhishSiteInfoQueryResult(){}

  public Long getPhishUrlId() {
    return this.phishUrlId;
  }
  public void setPhishUrlId(Long phishUrlId) {
    this.phishUrlId = phishUrlId;
  }

  public String getPhishUrl() {
    return this.phishUrl;
  }
  public void setPhishUrl(String phishUrl) {
    this.phishUrl = phishUrl;
  }

  public Long getPhishTankId() {
    return this.phishTankId;
  }
  public void setPhishTankId(Long phishTankId) {
    this.phishTankId = phishTankId;
  }

  public String getPhishTankUrl() {
    return this.phishTankUrl;
  }
  public void setPhishTankUrl(String phishTankUrl) {
    this.phishTankUrl = phishTankUrl;
  }

  public LocalDateTime getRegisterAt() {
    return this.registerAt;
  }
  public void setRegisterAt(LocalDateTime registerAt) { this.registerAt = registerAt; }

  public Long getPhishDomainId() {
    return this.phishDomainId;
  }
  public void setPhishDomainId(Long phishDomainId) {
    this.phishDomainId = phishDomainId;
  }

  public String getPhishDomain() {
    return this.phishDomain;
  }
  public void setPhishDomain(String phishDomain) {
    this.phishDomain = phishDomain;
  }

  public Long getPostId() {
    return this.postId;
  }
  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public String getPostContents() {
    return this.postContents;
  }
  public void setPostContents(String postContents) {
    this.postContents = postContents;
  }

  public LocalDateTime getPostRegisterAt() {
    return this.postRegisterAt;
  }
  public void setPostRegisterAt(LocalDateTime postRegisterAt) {
    this.postRegisterAt = postRegisterAt;
  }

  public long getUserId() {
    return this.userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return this.userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }

}

