package com.phish.search.service;

import com.phish.search.exception.AppException;
import com.phish.search.model.db.EmailAuthTemp;
import com.phish.search.model.db.PasswordResetTemp;
import com.phish.search.model.db.User;
import com.phish.search.repository.EmailAuthTempRepository;
import com.phish.search.repository.PasswordResetTempRepository;
import com.phish.search.repository.UserPostRepository;
import com.phish.search.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class PasswordResetService {

  @Autowired
  CommonService commonService;
  @Autowired
  DateService dateService;
  @Autowired
  MailService mailService;
  @Autowired
  UserService userService;

  @Autowired
  PasswordResetTempRepository passwordResetTempRepository;
  @Autowired
  UserRepository userRepository;

  // パスワードリセットの登録
  public void addPasswordReset(String email) {
    User user = userService.getUserByEmail(email);
    if (user == null) {
      throw new AppException(HttpStatus.BAD_REQUEST, "Bad Request");
    }
    // 現在日付の取得
    LocalDateTime now = dateService.getNowLocalDateTime();
    // 既存レコードの削除
    passwordResetTempRepository.deleteByUserId(user.getId());
    PasswordResetTemp addPasswordResetTemp = new PasswordResetTemp(
      user.getId(),
      commonService.generateToken(),
      now.plusDays(1)
    );
    passwordResetTempRepository.save(addPasswordResetTemp);
    // メールの送信
    mailService.sendPasswordResetAdd(user.getId(), user.getEmail(), addPasswordResetTemp.getToken());
  }

  // パスワードリセットの更新
  public void updatePasswordReset(Long userId, String token, String password) {
    PasswordResetTemp passwordResetTemp= passwordResetTempRepository.findByUserId(userId);
    // 認証がない場合はエラー
    if (passwordResetTemp == null) {
      throw new AppException(HttpStatus.BAD_REQUEST, "Bad Request");
    }
    // tokenが一致していない場合はエラー
    if (!passwordResetTemp.getToken().equals(token)) {
      throw new AppException(HttpStatus.BAD_REQUEST, "Bad Request");
    }
    // パスワードが6文字以上でない場合はエラー
    if (password == null || password.length() < 6) {
      throw new AppException(HttpStatus.BAD_REQUEST, "Bad Request");
    }
    // 期限が過ぎている場合はエラー
    LocalDateTime now = dateService.getNowLocalDateTime();
    if (now.isAfter(passwordResetTemp.getExpiredAt())) {
      throw new AppException(HttpStatus.FORBIDDEN, "Forbidden");
    }

    // ユーザの認証
    User user = userService.getUser(userId);

    // nullとステータスチェック
    if (user == null || !user.getStatus().equals("active")) {
      throw new AppException(HttpStatus.UNAUTHORIZED, "Unuthorize");
    }

    // 既存レコードの削除
    passwordResetTempRepository.deleteByUserId(user.getId());
    // 更新
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    userRepository.updatePassword(user.getId(), encoder.encode(password));
  }

}
