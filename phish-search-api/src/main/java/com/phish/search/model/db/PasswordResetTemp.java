package com.phish.search.model.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_temps")
public class PasswordResetTemp {
  @Id
  private long userId;
  private String token;
  private LocalDateTime expiredAt;

  public PasswordResetTemp(){}
  public PasswordResetTemp(long userId, String token, LocalDateTime expiredAt) {
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
