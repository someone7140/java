package com.phish.search.batch;


import com.phish.search.model.csv.PhishTankCsv;
import com.phish.search.service.ExternalApiService;
import com.phish.search.service.MailService;
import com.phish.search.service.PhishSiteBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class PhishTankBatch {

  @Autowired
  ExternalApiService externalApiService;
  @Autowired
  MailService mailService;
  @Autowired
  PhishSiteBatchService phishSiteBatchService;

  int MAX_RETRY_COUNT = 5;

  // initialDelay：アプリケーションが起動してから何秒後に実行するか（ミリ秒指定）
  // fixedDelay：何秒ごとに処理を実行するか（ミリ秒指定）
  // アプリケーション起動5秒後と24時間間隔で実行
  @Scheduled(initialDelay = 5L, fixedDelay = 86400000L)
  public void executePhishTankBatch() throws IOException {
    List<PhishTankCsv> phishTankCsvRecords = null;
    for (int i = 0; i < MAX_RETRY_COUNT; i++) {
      phishTankCsvRecords = externalApiService.getPhisTankCsvBean();
      if (phishTankCsvRecords != null) {
        break;
      }
    }
    if (phishTankCsvRecords == null) {
      // メール送信
      mailService.sendBatchError("Get Csv Error");
    } else {
      try {
        phishSiteBatchService.registPhishTankCsvToDb(phishTankCsvRecords);
      } catch (Exception e) {
        // メール送信
        mailService.sendBatchError("Database Write Error");
      }
    }
  }

}
