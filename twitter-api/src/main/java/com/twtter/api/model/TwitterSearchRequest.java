package com.twtter.api.model;

public class TwitterSearchRequest {
  private String userId;
  private String[] wordList;
  private boolean isIncludeReply;
  private boolean isIncludeReTweet;
  private boolean isQuestionary;
  private int minimumFavoriteCounts;
  private int minimumRetweetCounts;
  private int minimumReplyCounts;
  private String since;
  private String until;
  private String sort;

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String[] getWordList() {
    return this.wordList;
  }

  public void setWordList(String[] wordList) {
    this.wordList = wordList;
  }

  public boolean getIsIncludeReply() {
    return this.isIncludeReply;
  }

  public void setIsIncludeReply(boolean isIncludeReply) {
    this.isIncludeReply = isIncludeReply;
  }

  public boolean getIsIncludeReTweet() {
    return this.isIncludeReTweet;
  }

  public void setIsIncludeReTweet(boolean isIncludeReTweet) {
    this.isIncludeReTweet = isIncludeReTweet;
  }

  public boolean getIsQuestionary() {
    return this.isQuestionary;
  }

  public void setIsQuestionary(boolean isQuestionary) {
    this.isQuestionary = isQuestionary;
  }

  public int getMinimumFavoriteCounts() {
    return this.minimumFavoriteCounts;
  }

  public void setMinimumFavoriteCounts(int minimumFavoriteCounts) {
    this.minimumFavoriteCounts = minimumFavoriteCounts;
  }

  public int getMinimumRetweetCounts() {
    return this.minimumRetweetCounts;
  }

  public void setMinimumRetweetCounts(int minimumRetweetCounts) {
    this.minimumRetweetCounts = minimumRetweetCounts;
  }

  public int getMinimumReplyCounts() {
    return this.minimumReplyCounts;
  }

  public void setMinimumReplyCounts(int minimumReplyCounts) {
    this.minimumReplyCounts = minimumReplyCounts;
  }

  public String getSince() {
    return this.since;
  }

  public void setSince(String since) {
    this.since = since;
  }

  public String getUntil() {
    return this.until;
  }

  public void setUntil(String until) {
    this.until = until;
  }

  public String getSort() {
    return this.sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

}
