package com.phish.search.model.db;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  @SequenceGenerator(sequenceName = "seq_user_id", allocationSize = 1, name = "USER_SEQ")
  private long id;
  private String email;
  private String name;
  private String password;
  private String status;
  private LocalDateTime registerAt;

  public User(){}
  public User(String email, String name, String password, String status, LocalDateTime registerAt) {
    this.email = email;
    this.name = name;
    this.password = password;
    this.status = status;
    this.registerAt = registerAt;
  }

  public long getId() {
    return this.id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) { this.password = password; }

  public String getStatus() {
    return this.status;
  }
  public void setStatus(String status) { this.status = status; }

  public LocalDateTime getRegisterAt() {
    return this.registerAt;
  }
  public void setRegisterAt(LocalDateTime registerAt) { this.registerAt = registerAt; }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO Auto-generated method stub
    Collection<GrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
    return authorityList;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
