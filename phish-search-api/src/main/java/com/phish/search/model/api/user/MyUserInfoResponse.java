package com.phish.search.model.api.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MyUserInfoResponse {
  private long id;
  private String name;
  private String email;

  public MyUserInfoResponse(long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }
  public void setEnail(String email) {
    this.email = email;
  }

}
