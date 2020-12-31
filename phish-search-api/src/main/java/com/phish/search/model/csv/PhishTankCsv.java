package com.phish.search.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.time.LocalDateTime;

public class PhishTankCsv {
  @CsvBindByName(column = "phish_id")
  private Long phishTankId;
  @CsvBindByName(column = "url")
  private String phishUrl;
  @CsvBindByName(column = "phish_detail_url")
  private String phishTankUrl;
  @CsvCustomBindByName(column = "submission_time", converter = PhishTankDateConverter.class)
  private LocalDateTime submissionTime;
  @CsvBindByName(column = "verified")
  private String verified;
  @CsvCustomBindByName(column = "verification_time", converter = PhishTankDateConverter.class)
  private LocalDateTime verificationTime;
  @CsvBindByName(column = "online")
  private String online;
  @CsvBindByName(column = "target")
  private String target;

  public PhishTankCsv() {}

  public Long getPhishTankId() { return this.phishTankId; }
  public void setPhishTankId(Long phishTankId) {
    this.phishTankId = phishTankId;
  }

  public String getPhishUrl() {
    return this.phishUrl;
  }
  public void setPhishUrl(String phishUrl) {
    this.phishUrl = phishUrl;
  }

  public String getPhishTankUrl() {
    return this.phishTankUrl;
  }
  public void setPhishTankUrl(String phishTankUrl) {
    this.phishTankUrl = phishTankUrl;
  }

  public LocalDateTime getSubmissionTime() {
    return this.submissionTime;
  }
  public void setSubmissionTime(LocalDateTime submissionTime) {
    this.submissionTime = submissionTime;
  }

  public String getVerified() {
    return this.verified;
  }
  public void setVerified(String verified) {
    this.verified = verified;
  }

  public LocalDateTime getVerificationTime() {
    return this.verificationTime;
  }
  public void setVerificationTime(LocalDateTime verificationTime) {
    this.verificationTime = verificationTime;
  }

  public String getOnline() {
    return this.online;
  }
  public void setOnline(String online) {
    this.online = online;
  }

  public String getTarget() { return this.target; }
  public void setTarget(String target) {
    this.target = target;
  }

}

