package com.twtter.api.service;

import com.twtter.api.exception.AppException;
import com.twtter.api.model.TwitterSearchRequest;
import org.springframework.http.HttpStatus;
public class TwitterSearchUrlService {
  private TwitterSearchRequest req;

  public TwitterSearchUrlService(TwitterSearchRequest req) {
    this.req = req;
  }

  public String getSearchUrl() {
    // クエリ内容の設定
    String userId = this.req.getUserId();
    String[] wordList = this.req.getWordList();
    // ユーザIDかキーワードかアンケートは必須
    if (userId == null && wordList.length == 0 && !this.req.getIsQuestionary()) {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    } else {
      String searchUrl = "https://twitter.com/search?q=";
      // ユーザIDの指定
      if (userId != null) {
        searchUrl += "from:" + userId + " ";
      }
      // アンケートで絞る
      if (this.req.getIsQuestionary()) {
        searchUrl += "card_name:poll2choice_text_only OR card_name:poll3choice_text_only OR card_name:poll4choice_text_only ";
      }
      // 検索キーワードの指定
      if (wordList.length > 0) {
        searchUrl += String.join(" ", wordList) + " ";
      }
      // リプライを含めない場合
      if (!this.req.getIsIncludeReply()) {
        searchUrl += "exclude:replies ";
      }
      // リツイートを含めない場合
      if (!this.req.getIsIncludeReTweet()) {
        searchUrl += "exclude:retweets ";
      }
      // いいね数
      if (this.req.getMinimumFavoriteCounts() > 0) {
        searchUrl += "min_faves:" + this.req.getMinimumFavoriteCounts() + " ";
      }
      // リツイート数
      if (this.req.getMinimumRetweetCounts() > 0) {
        searchUrl += "min_retweets:" + this.req.getMinimumRetweetCounts() + " ";
      }
      // リプライ数
      if (this.req.getMinimumReplyCounts() > 0) {
        searchUrl += "min_replies:" + this.req.getMinimumReplyCounts() + " ";
      }
      // since
      if (this.req.getSince() != null) {
        searchUrl += "since:" + this.req.getSince() + " ";
      }
      // until
      if (this.req.getUntil() != null) {
        searchUrl += "until:" + this.req.getUntil() + " ";
      }
      // ソートをプラスして返す
      return searchUrl.trim() + ("recent".equals(this.req.getSort()) ? "&f=live" : "");
    }
  }
}
