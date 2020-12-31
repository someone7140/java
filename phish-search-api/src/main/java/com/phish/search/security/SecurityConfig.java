package com.phish.search.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthenticationProviderImpl authenticationProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors().and()
      // AUTHORIZE
      .authorizeRequests()
        .mvcMatchers(
          "/user_registration",
          "/user_email_auth",
          "/search_phish_site",
          "/ref_user_posts",
          "/ref_post_phish_site",
          "/recent_phish_site",
          "/password_reset_registration",
          "/password_reset_update",
          "/user_info_ref"
        ).permitAll()
         .anyRequest()
         .authenticated()
      .and()
      .exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint())
        .accessDeniedHandler(accessDeniedHandler())
      .and()
      .formLogin()
        .loginProcessingUrl("/login").permitAll()
          .usernameParameter("email")
          .passwordParameter("password")
        .successHandler(authenticationSuccessHandler())
        .failureHandler(authenticationFailureHandler())
      .and()
      .logout()
        .logoutUrl("/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessHandler(logoutSuccessHandler())
      .and()
      .csrf().disable();
  }

  @Autowired
  public void configureGlobal(
    AuthenticationManagerBuilder auth,
    @Qualifier("userService") UserDetailsService userDetailsService,
    PasswordEncoder passwordEncoder) throws Exception {

    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    auth.eraseCredentials(true)
      .authenticationProvider(authenticationProvider);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  AuthenticationEntryPoint authenticationEntryPoint() {
    return new SimpleAuthenticationEntryPoint();
  }

  AccessDeniedHandler accessDeniedHandler() {
    return new SimpleAccessDeniedHandler();
  }

  AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new SimpleAuthenticationSuccessHandler();
  }

  AuthenticationFailureHandler authenticationFailureHandler() {
    return new SimpleAuthenticationFailureHandler();
  }

  LogoutSuccessHandler logoutSuccessHandler() {
    return new HttpStatusReturningLogoutSuccessHandler();
  }
}
