package com.phish.search.model.api.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserEmailAuthRequest {
  private long userId;
  private String token;
  private String password;

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

  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) { this.password = password; }

}
