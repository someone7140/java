package com.phish.search.api.controller;

import com.phish.search.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseController {
  @ExceptionHandler(AppException.class)
  public ResponseEntity<String> getException(HttpServletRequest req, AppException e){
    return new ResponseEntity<String>(e.getMessage(),e.getHttpStatus());
  }
}
