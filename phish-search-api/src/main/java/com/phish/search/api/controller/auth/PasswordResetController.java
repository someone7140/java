package com.phish.search.api.controller.auth;

import com.phish.search.api.controller.BaseController;
import com.phish.search.model.api.auth.PasswordResetRequest;
import com.phish.search.model.api.auth.PasswordResetUpdateRequest;
import com.phish.search.model.db.User;
import com.phish.search.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordResetController extends BaseController {

  @Autowired
  PasswordResetService passwordResetService;

  @RequestMapping(value="/password_reset_registration", method=RequestMethod.POST)
  public void passwordResetRegistration(
    @RequestBody PasswordResetRequest req
  ) {
    passwordResetService.addPasswordReset(req.getEmail());
  }

  @RequestMapping(value="/password_reset_update", method=RequestMethod.POST)
  public void passwordResetUpdate(
    @RequestBody PasswordResetUpdateRequest req
  ) {
    passwordResetService.updatePasswordReset(req.getUserId(), req.getToken(), req.getPassword());
  }

}
