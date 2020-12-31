package com.phish.search.model.db;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "user_posts")
public class UserPost {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ")
  @SequenceGenerator(sequenceName = "seq_post_id", allocationSize = 1, name = "POST_SEQ")
  private long id;
  private long userId;
  private long urlId;
  private String postContents;
  private LocalDateTime registerAt;

  public UserPost(){}
  public UserPost(long userId, long urlId, String postContents, LocalDateTime registerAt){
    this.userId = userId;
    this.urlId = urlId;
    this.postContents = postContents;
    this.registerAt = registerAt;
  }

  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return this.userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getUrlId() {
    return this.urlId;
  }
  public void setUrlId(long userId) {
    this.urlId = urlId;
  }

  public String getPostContents() {
    return this.postContents;
  }
  public void setPostContents(String postContents) {
    this.postContents = postContents;
  }

  public LocalDateTime getRegisterAt() {
    return this.registerAt;
  }
  public void setRegisterAt(LocalDateTime registerAt) {
    this.registerAt = registerAt;
  }

}
