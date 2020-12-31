package com.phish.search.security;

import com.phish.search.model.db.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderImpl extends DaoAuthenticationProvider {
  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    super.additionalAuthenticationChecks(userDetails, authentication);
    User user = (User) userDetails;

    // 追加の条件
    if (!user.getStatus().equals("active")) {
      throw new AccountStatusNotActiveException("Status is not active");
    }
  }

  public static class AccountStatusNotActiveException extends AuthenticationException {
    public AccountStatusNotActiveException(String message) {
      super(message);
    }
  }

  @Override
  protected void doAfterPropertiesSet() {}
}
