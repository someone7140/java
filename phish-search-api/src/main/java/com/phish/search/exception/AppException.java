package com.phish.search.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {
  private HttpStatus status;

  public HttpStatus getHttpStatus() {
    return this.status;
  }

  public void setHttpStatus(HttpStatus status) {
    this.status = status;
  }

  public AppException(HttpStatus status, String message) {
    super(message);
    setHttpStatus(status);
  }

}
