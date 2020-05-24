package com.twtter.api.controller;

import com.twtter.api.exception.AppException;
import com.twtter.api.model.TwitterSearchRequest;
import com.twtter.api.model.TwitterSearchResponse;
import com.twtter.api.service.TwitterSearchUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/twitter_search")
public class TwitterSearchController {

  @RequestMapping(method=RequestMethod.POST)
  public TwitterSearchResponse twitterSearch(
    @RequestBody TwitterSearchRequest req
  ) {
    TwitterSearchUrlService service = new TwitterSearchUrlService(req);
    TwitterSearchResponse res = new TwitterSearchResponse();
    res.setUrl(service.getSearchUrl());
    return res;
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<String> getException(HttpServletRequest req, AppException e){
    return new ResponseEntity<String>(e.getMessage(),e.getHttpStatus());
  }
}
