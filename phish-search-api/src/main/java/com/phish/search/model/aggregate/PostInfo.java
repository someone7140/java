package com.phish.search.model.aggregate;

import java.time.LocalDateTime;
import java.util.List;

public class PostInfo {
  private Long postId;
  private String postContents;
  private LocalDateTime postRegisterAt;
  private Long userId;
  private String userName;

  public PostInfo(Long postId, String postContents, LocalDateTime postRegisterAt, Long userId, String userName) {
    this.postId = postId;
    this.postContents = postContents;
    this.postRegisterAt = postRegisterAt;
    this.userId = userId;
    this.userName = userName;
  }

  public Long getPostId() { return this.postId; }
  public void setPostId(Long postId) { this.postId = postId; }

  public String getPostContents() { return this.postContents; }
  public void setPostContents(String postContents) {
        this.postContents = postContents;
    }

  public LocalDateTime getPostRegisterAt() { return this.postRegisterAt; }
  public void setPostRegisterAt(LocalDateTime postRegisterAt) {
        this.postRegisterAt = postRegisterAt;
    }

  public long getUserId() { return this.userId; }
  public void setUserId(long userId) { this.userId = userId; }

  public String getUserName() { return this.userName; }
  public void setUserName(String userName) { this.userName = userName; }

}

