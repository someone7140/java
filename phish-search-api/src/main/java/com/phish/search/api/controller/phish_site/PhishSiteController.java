package com.phish.search.api.controller.phish_site;

import com.phish.search.api.controller.BaseController;
import com.phish.search.exception.AppException;
import com.phish.search.model.aggregate.PhishSiteInfoAggregate;
import com.phish.search.model.api.phish_site.DeleteUserPhishSiteRequest;
import com.phish.search.model.api.phish_site.PostUserPhishSiteRequest;
import com.phish.search.model.api.phish_site.SearchPhishSiteRequest;
import com.phish.search.model.db.User;
import com.phish.search.service.PhishSiteService;
import com.phish.search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.List;

@RestController
public class PhishSiteController extends BaseController {

  @Autowired
  PhishSiteService phishSiteService;
  @Autowired
  UserService userService;

  @RequestMapping(value="/post_user_phish_site", method=RequestMethod.POST)
  public void postUserPhishSite(
    @RequestBody PostUserPhishSiteRequest req,
    @AuthenticationPrincipal User user
  ) {
    URL url = req.getUrlObj();
    phishSiteService.registPhishSite(user.getId(), url, req.getContents());
  }

  @RequestMapping(value="/delete_user_phish_site", method=RequestMethod.POST)
  public void deleteUserPhishSite(
    @RequestBody DeleteUserPhishSiteRequest req,
    @AuthenticationPrincipal User user
  ) {
    phishSiteService.deletePhishSiteRecord(req.getPostId(), user.getId());
    phishSiteService.deleteUnnecessaryUrlRecord();
    phishSiteService.deleteUnnecessaryDomainRecord();
  }

  @RequestMapping(value="/ref_user_posts", method=RequestMethod.GET)
  public List<PhishSiteInfoAggregate> refUserPosts(
    @RequestParam("user_id") long userId
  ) {
    // userを取得
    User user = userService.getUser(userId);

    if (user != null) {
      return phishSiteService.refPhishSiteUserPost(user.getId());
    } else {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    }
  }

  @RequestMapping(value="/ref_post_phish_site", method=RequestMethod.GET)
  public PhishSiteInfoAggregate refPostPhishSite(
    @RequestParam("post_id") long postId
  ) {
    return phishSiteService.refPostPhishSite(postId);
  }

  @RequestMapping(value="/search_phish_site", method=RequestMethod.POST)
  public List<PhishSiteInfoAggregate> searchPhishSite(
    @RequestBody SearchPhishSiteRequest req
  ) {
    if (req.getUrl().length() > 0) {
      return phishSiteService.serchPhishSite(req.getUrl());
    } else {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    }
  }

  @RequestMapping(value="/recent_phish_site", method=RequestMethod.GET)
  public List<PhishSiteInfoAggregate> recentPhishSite(
    @RequestParam("limit") long limit
  ) {
    return phishSiteService.getRecentPhishSite(limit);
  }

}
