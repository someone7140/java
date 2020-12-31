package com.phish.search.model.api.phish_site;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.phish.search.exception.AppException;
import org.springframework.http.HttpStatus;

import java.net.MalformedURLException;
import java.net.URL;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PostUserPhishSiteRequest {
  private String url;
  private String contents;

  public String getUrl() {
    return this.url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  public String getContents() {
    return this.contents;
  }
  public void setContents(String contents) {
    this.contents = contents;
  }

  public URL getUrlObj() {
    try {
      return new URL(this.url);
    } catch (MalformedURLException e) {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    }
  }

}
