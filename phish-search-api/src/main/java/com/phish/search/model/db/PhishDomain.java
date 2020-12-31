package com.phish.search.model.db;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "phish_domains")
public class PhishDomain {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOMAIN_SEQ")
  @SequenceGenerator(sequenceName = "seq_phish_domain_id", allocationSize = 1, name = "DOMAIN_SEQ")
  private long id;
  private String phishDomain;

  public PhishDomain(){}
  public PhishDomain(long id, String phishDomain) {
    this.id = id;
    this.phishDomain = phishDomain;
  }
  public PhishDomain(String phishDomain) {
    this.phishDomain = phishDomain;
  }

  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getPhishDomain() {
    return this.phishDomain;
  }
  public void setPhishDomain(String phishDomain) {
    this.phishDomain = phishDomain;
  }

}
