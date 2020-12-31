package com.phish.search.service;

import com.phish.search.exception.AppException;
import com.phish.search.model.db.EmailAuthTemp;
import com.phish.search.model.db.User;
import com.phish.search.repository.EmailAuthTempRepository;
import com.phish.search.repository.UserPostRepository;
import com.phish.search.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("userService")
@Transactional
public class UserService implements UserDetailsService {

  @Autowired
  CommonService commonService;
  @Autowired
  DateService dateService;
  @Autowired
  MailService mailService;
  @Autowired
  PhishSiteService phishSiteService;

  @Autowired
  EmailAuthTempRepository emailAuthTempRepository;
  @Autowired
  UserPostRepository userPostRepository;
  @Autowired
  UserRepository userRepository;

  @Autowired
  AuthenticationProvider authenticationProvider;

  @Override
  public UserDetails loadUserByUsername(final String email) {
    // emailでデータベースからユーザーエンティティを検索する
    UserDetails user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return user;
  }

  // ユーザの登録
  public void addUser(String email, String name, String password) {
    // 現在日付の取得
    LocalDateTime now = dateService.getNowLocalDateTime();
    // すでにユーザがいるか判定
    User registeredUser = userRepository.findByEmail(email);
    if (registeredUser != null) {
      // 確認中の場合は削除
      if (registeredUser.getStatus().equals("confirming")) {
        userRepository.delete(registeredUser);
        emailAuthTempRepository.deleteByUserId(registeredUser.getId());
      } else {
        throw new AppException(HttpStatus.FORBIDDEN, "ForBidden");
      }
    }
    // usersテーブルの登録
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    User addUser = new User(
      email,
      name,
      encoder.encode(password),
      "confirming",
      now
    );
    User addedUser = userRepository.save(addUser);
    // email_auth_tempsテーブルの登録
    EmailAuthTemp addEmailAuthTemp = new EmailAuthTemp(
      addedUser.getId(),
      commonService.generateToken(),
      now.plusDays(1)
    );
    emailAuthTempRepository.save(addEmailAuthTemp);
    // メールの送信
    mailService.sendUserAdd(addedUser.getId(), email, addEmailAuthTemp.getToken());
  }

  // ユーザの編集
  public void editUser(User user, String name, String password) {
    // 名前が空でない場合は更新
    if (!StringUtils.isEmpty(name)) {
      userRepository.updateName(user.getId(), name);
    }
    // パスワードが空でない場合は更新
    if (!StringUtils.isEmpty(password)) {
      PasswordEncoder encoder = new BCryptPasswordEncoder();
      userRepository.updatePassword(user.getId(), encoder.encode(password));
    }
  }

  // ユーザの参照
  public User getUser(long userId) {
    return userRepository.findById(userId);
  }

  // メールアドレスによるユーザの参照
  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  // ユーザの削除
  public boolean cancelUser(User user) {
    // 念のためUser存在確認
    User userGet = getUser(user.getId());
    if (userGet != null) {
      // 投稿の削除
      userPostRepository.deleteByUserId(userGet.getId());
      phishSiteService.deleteUnnecessaryUrlRecord();
      phishSiteService.deleteUnnecessaryDomainRecord();
      // ユーザの削除
      userRepository.deleteById(userGet.getId());
      return true;
    } else {
      return false;
    }
  }

  // ユーザのメール認証
  public User authEmail(long userId, String token, String password) {
    EmailAuthTemp emailAuthTemp = emailAuthTempRepository.findByUserId(userId);
    // email認証がない場合はエラー
    if (emailAuthTemp == null) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }
    // tokenが一致していない場合は認証エラー
    if (!emailAuthTemp.getToken().equals(token)) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }
    // 期限が過ぎている場合は認証エラー
    LocalDateTime now = dateService.getNowLocalDateTime();
    if (now.isAfter(emailAuthTemp.getExpiredAt())) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }

    // ユーザの認証
    User user = userRepository.findById(userId);

    // ユーザーがない場合はエラー
    if (user == null) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }

    // ステータスがconfirming以外はエラー
    if (!user.getStatus().equals("confirming")) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }

    // パスワードが一致しない場合はエラー
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    if (!encoder.matches(password, user.getPassword())) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }

    //　全てOKだったらステータスを変更
    user.setStatus("active");
    userRepository.save(user);
    emailAuthTempRepository.deleteByUserId(userId);

    // 認証
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), password);
    try {
      Authentication auth = authenticationProvider.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    catch (Exception exception) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "UnAuthorized");
    }

    return user;
  }

}
