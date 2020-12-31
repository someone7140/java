package com.phish.search.model.aggregate;

import java.time.LocalDateTime;
import java.util.List;

public class PhishSiteInfoAggregate {
  private Long phishUrlId;
  private String phishUrl;
  private Long phishTankId;
  private String phishTankUrl;
  private LocalDateTime registerAt;
  private Long phishDomainId;
  private String phishDomain;
  private List<PostInfo> postInfos;

  public PhishSiteInfoAggregate(
    Long phishUrlId,
    String phishUrl,
    Long phishTankId,
    String phishTankUrl,
    LocalDateTime registerAt,
    Long phishDomainId,
    String phishDomain,
    List<PostInfo> postInfos
  ){
    this.phishUrlId = phishUrlId;
    this.phishUrl = phishUrl;
    this.phishTankId = phishTankId;
    this.phishTankUrl = phishTankUrl;
    this.registerAt = registerAt;
    this.phishDomainId = phishDomainId;
    this.phishDomain = phishDomain;
    this.postInfos = postInfos;
  }

  public Long getPhishUrlId() {
    return this.phishUrlId;
  }
  public void setPhishUrlId(Long phishUrlId) {
    this.phishUrlId = phishUrlId;
  }

  public String getPhishUrl() {
    return this.phishUrl;
  }
  public void setPhishUrl(String phishUrl) {
    this.phishUrl = phishUrl;
  }

  public Long getPhishTankId() {
    return this.phishTankId;
  }
  public void setPhishTankId(Long phishTankId) {
    this.phishTankId = phishTankId;
  }

  public String getPhishTankUrl() {
    return this.phishTankUrl;
  }
  public void setPhishTankUrl(String phishTankUrl) {
    this.phishTankUrl = phishTankUrl;
  }

  public LocalDateTime getRegisterAt() {
    return this.registerAt;
  }
  public void setRegisterAt(LocalDateTime registerAt) { this.registerAt = registerAt; }

  public Long getPhishDomainId() {
    return this.phishDomainId;
  }
  public void setPhishDomainId(Long phishDomainId) {
    this.phishDomainId = phishDomainId;
  }

  public String getPhishDomain() {
    return this.phishDomain;
  }
  public void setPhishDomain(String phishDomain) {
    this.phishDomain = phishDomain;
  }

  public List<PostInfo> getPostInfos() {
    return this.postInfos;
  }
  public void setPostInfos(List<PostInfo> postInfos) {
    this.postInfos = postInfos;
  }

}

