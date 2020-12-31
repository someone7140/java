package com.phish.search.model.api.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PasswordResetUpdateRequest {
  private Long userId;
  private String token;
  private String password;

  public Long getUserId() {
    return this.userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getToken() {
    return this.token;
  }
  public void setToken(String token) {
    this.token = token;
  }

  public String getPassword() {
    return this.password;
  }
  public void setPasword(String password) { this.password = password; }

}
