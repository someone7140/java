package com.phish.search.api.controller.user;

import com.phish.search.api.controller.BaseController;
import com.phish.search.exception.AppException;
import com.phish.search.model.api.user.*;
import com.phish.search.model.db.User;
import com.phish.search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends BaseController {

  @Autowired
  UserService userService;

  @RequestMapping(value="/user_registration", method=RequestMethod.POST)
  public void userRegistration(
    @RequestBody UserRegistrationRequest req
  ) {
    if (req.inputCheck()) {
      userService.addUser(req.getEmail(), req.getName(), req.getPassword());
    } else {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    }
  }

  @RequestMapping(value="/user_edit", method=RequestMethod.POST)
  public void userEdit(
    @RequestBody UserEditRequest req
  ) {
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    userService.editUser(user, req.getName(), req.getPassword());
  }

  @RequestMapping(value="/user_cancel", method=RequestMethod.POST)
  public void userCancel() {
    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    boolean successFlg = userService.cancelUser(user);
    if (!successFlg) {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    }
  }

  @RequestMapping(value="/user_email_auth", method=RequestMethod.POST)
  public MyUserInfoResponse userEmailAuth(
    @RequestBody UserEmailAuthRequest req
  ) {
    User user = userService.authEmail(req.getUserId(), req.getToken(), req.getPassword());
    return new MyUserInfoResponse(user.getId(), user.getName(), user.getEmail());
  }

  @RequestMapping(value="/user_info_ref", method=RequestMethod.GET)
  public UserInfoResponse getUserInfo(
    @RequestParam("user_id") long userId
  ) {
    // userを取得
    User user = userService.getUser(userId);
    return new UserInfoResponse(
      user.getId(),
      user.getName()
    );
  }

  @RequestMapping(value="/login_user_info", method=RequestMethod.GET)
  public MyUserInfoResponse getLoginUserInfo(
          @AuthenticationPrincipal User user
  ) {
    return new MyUserInfoResponse(user.getId(), user.getName(), user.getEmail());
  }

}
