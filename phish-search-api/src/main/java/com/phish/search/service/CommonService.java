package com.phish.search.service;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.UUID;

@Service
public class CommonService {

  // トークンの生成
  public String generateToken() {
    return UUID.randomUUID().toString();
  }

  // URLからドメインを取得
  public String getDomainFromUrl(URL url) {
    String hostName = url.getHost();
    return hostName.startsWith("www.") ? hostName.substring(4) : hostName;
  }

  // sleep
  public void sleep(long sec) {
    try {
      Thread.sleep(sec * 1000);
    } catch (Exception e) {
      // exceptionはスルーする
    }
  }
}
