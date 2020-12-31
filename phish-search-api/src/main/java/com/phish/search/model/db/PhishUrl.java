package com.phish.search.model.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "phish_urls")
public class PhishUrl {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "URL_SEQ")
  @SequenceGenerator(sequenceName = "seq_phish_url_id", allocationSize = 1, name = "URL_SEQ")
  private long id;
  private long domainId;
  private String phishUrl;
  private Long phishTankId;
  private String phishTankUrl;
  private LocalDateTime registerAt;

  public PhishUrl(){}
  public PhishUrl(long domainId, String phishUrl, Long phishTankId, String phishTankUrl, LocalDateTime registerAt) {
    this.domainId = domainId;
    this.phishUrl = phishUrl;
    this.phishTankId = phishTankId;
    this.phishTankUrl = phishTankUrl;
    this.registerAt = registerAt;
  }
  public PhishUrl(long id, long domainId, String phishUrl, Long phishTankId, String phishTankUrl, LocalDateTime registerAt) {
    this.id = id;
    this.domainId = domainId;
    this.phishUrl = phishUrl;
    this.phishTankId = phishTankId;
    this.phishTankUrl = phishTankUrl;
    this.registerAt = registerAt;
  }

  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public long getDomainId() {
    return this.domainId;
  }
  public void setDomainId(long domainId) {
    this.domainId = domainId;
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

}
