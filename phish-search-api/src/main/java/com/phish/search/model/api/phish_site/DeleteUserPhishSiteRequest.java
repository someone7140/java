package com.phish.search.model.api.phish_site;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phish.search.exception.AppException;
import org.springframework.http.HttpStatus;

import java.net.MalformedURLException;
import java.net.URL;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeleteUserPhishSiteRequest {
  private long postId;

  public long getPostId() {
    return this.postId;
  }
  public void setPostId(long postId) {
    this.postId = postId;
  }

}
