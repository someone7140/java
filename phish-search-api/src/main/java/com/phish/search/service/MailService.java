package com.phish.search.service;

import com.phish.search.exception.AppException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailService {

  @Autowired
  private Environment env;

  // SendGridオブジェクトの生成
  private SendGrid generateSendGridObj() {
    return new SendGrid(env.getProperty("sendgrid.api.key"));
  }

  // ユーザ登録メールの送信
  public void sendUserAdd(long userId, String toMailAddress, String token) {
    SendGrid sg = generateSendGridObj();
    // メールの生成
    Email from = new Email(env.getProperty("send.from.email"));
    String subject = "【フィッシュサーチ】ユーザ登録のお知らせ";
    Email to = new Email(toMailAddress);
    String authUrl = env.getProperty("view.domain") + "/auth_email?user_id=" + userId + "&token=" + token;
    Content content = new Content(
      "text/plain",
      "下記のURLから認証を行ってください。有効期限は1日です。" + System.lineSeparator() + System.lineSeparator() + authUrl
    );
    Mail mail = new Mail(from, subject, to, content);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      if (
        response.getStatusCode() != HttpStatus.OK.value() &&
        response.getStatusCode() != HttpStatus.ACCEPTED.value() &&
        response.getStatusCode() != HttpStatus.NO_CONTENT.value()
      ) {
        throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "MailSendFailed");
      }
    } catch (IOException ex) {
      throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "MailSendFailed");
    }
  }

  // パスワードリセットメールの送信
  public void sendPasswordResetAdd(long userId, String toMailAddress, String token) {
    SendGrid sg = generateSendGridObj();
    // メールの生成
    Email from = new Email(env.getProperty("send.from.email"));
    String subject = "【フィッシュサーチ】パスワードリセットのお知らせ";
    Email to = new Email(toMailAddress);
    String authUrl = env.getProperty("view.domain") + "/auth_password_reset?user_id=" + userId + "&token=" + token;
    Content content = new Content(
            "text/plain",
            "下記のURLからパスワードリセットを行ってください。有効期限は1日です。" + System.lineSeparator() + System.lineSeparator() + authUrl
    );
    Mail mail = new Mail(from, subject, to, content);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      if (
        response.getStatusCode() != HttpStatus.OK.value() &&
          response.getStatusCode() != HttpStatus.ACCEPTED.value() &&
          response.getStatusCode() != HttpStatus.NO_CONTENT.value()
      ) {
        throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "MailSendFailed");
      }
    } catch (IOException ex) {
      throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "MailSendFailed");
    }
  }

  // バッチエラー時のメールの送信
  public void sendBatchError(String stacktrace) throws IOException {
    SendGrid sg = generateSendGridObj();
    // メールの生成
    Email from = new Email(env.getProperty("send.from.email"));
    String subject = "【フィッシュサーチ】バッチエラー";
    Email to = new Email(env.getProperty("batch.error.tomail"));
    Content content = new Content("text/plain", stacktrace);
    Mail mail = new Mail(from, subject, to, content);
    // リクエストの送信
    Request request = new Request();
    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    request.setBody(mail.build());
    sg.api(request);
  }
}
