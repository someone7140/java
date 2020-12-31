package com.phish.search.model.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_auth_temps")
public class EmailAuthTemp {
  @Id
  private long userId;
  private String token;
  private LocalDateTime expiredAt;

  public EmailAuthTemp(){}
  public EmailAuthTemp(long userId, String token, LocalDateTime expiredAt) {
    this.userId = userId;
    this.token = token;
    this.expiredAt = expiredAt;
  }

  public long getUserId() {
    return this.userId;
  }
  public void setUserId(long userId) {
    this.userId = userId;
  }

  public String getToken() {
    return this.token;
  }
  public void setToken(String token) {
    this.token = token;
  }

  public LocalDateTime getExpiredAt() {
    return this.expiredAt;
  }
  public void setExpiredAt(LocalDateTime expiredAt) { this.expiredAt = expiredAt; }

}
