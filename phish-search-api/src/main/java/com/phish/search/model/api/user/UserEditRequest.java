package com.phish.search.model.api.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserEditRequest {
  private String name;
  private String password;

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) { this.password = password; }

}
