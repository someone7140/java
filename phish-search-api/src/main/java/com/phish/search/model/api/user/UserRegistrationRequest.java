package com.phish.search.model.api.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRegistrationRequest {
  private String email;
  private String name;
  private String password;

  public String getEmail() {
    return this.email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

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

  public boolean inputCheck() {
    if (
      StringUtils.isEmpty(getEmail()) ||
      StringUtils.isEmpty(getName()) ||
      StringUtils.isEmpty(getPassword())
    ) {
      return false;
    } else {
      return (
        EmailValidator.getInstance().isValid(getEmail()) &&
        getPassword().length() > 5
      );
    }
  }
}
