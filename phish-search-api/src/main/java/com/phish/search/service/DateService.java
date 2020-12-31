package com.phish.search.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class DateService {
  // 現在日付の取得
  public LocalDateTime getNowLocalDateTime() {
    return LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
  }

}
