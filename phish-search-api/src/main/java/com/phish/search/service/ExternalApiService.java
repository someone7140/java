package com.phish.search.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.phish.search.model.csv.PhishTankCsv;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class ExternalApiService {

  // PhishTankからCSV情報を取得
  public List<PhishTankCsv> getPhisTankCsvBean() {
    try {
      // csvのURLを叩いてリダイレクト前のURLに接続
      URL url = new URL("https://data.phishtank.com/data/online-valid.csv");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setInstanceFollowRedirects(false);
      String redirectLocation = conn.getHeaderField("Location");
      conn.disconnect();
      // リダイレクト先のURLで再度接続
      URL afterRedirectUrl = new URL(redirectLocation);
      InputStreamReader in = new InputStreamReader(afterRedirectUrl.openStream());
      // beanに変換
      CsvToBean<PhishTankCsv> csvToBean = new CsvToBeanBuilder<PhishTankCsv>(in).withType(PhishTankCsv.class).build();
      return csvToBean.parse();
    } catch (Exception e) {
      return null;
    }
  }
}
