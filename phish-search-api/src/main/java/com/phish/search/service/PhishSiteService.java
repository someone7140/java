package com.phish.search.service;

import com.phish.search.exception.AppException;
import com.phish.search.model.aggregate.PhishSiteInfoAggregate;
import com.phish.search.model.aggregate.PhishSiteInfoQueryResult;
import com.phish.search.model.aggregate.PostInfo;
import com.phish.search.model.db.*;
import com.phish.search.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PhishSiteService {

  @Autowired
  CommonService commonService;
  @Autowired
  DateService dateService;

  @Autowired
  PhishDomainRepository phishDomainRepository;
  @Autowired
  PhishUrlRepository phishUrlRepository;
  @Autowired
  PhishSiteInfoRepository phishSiteInfoRepository;
  @Autowired
  UserPostRepository userPostRepository;

  // ユーザ投稿のフィッシュサイトを登録
  public void registPhishSite(long userId, URL url, String postContents) {
    UserPost userPost = userPostRepository.findByUserIdAndUrl(userId, url.toString());
    if (userPost == null) {
      // 現在日付
      LocalDateTime now = dateService.getNowLocalDateTime();
      // ドメインの登録判定
      String domain = commonService.getDomainFromUrl(url);
      PhishDomain phishDomain = phishDomainRepository.findByPhishDomain(domain);
      if(phishDomain == null) {
        phishDomain = phishDomainRepository.save(new PhishDomain(domain));
      }
      // URLの登録判定
      PhishUrl phishUrl = phishUrlRepository.findByPhishUrl(url.toString());
      if(phishUrl == null) {
        phishUrl = phishUrlRepository.save(new PhishUrl(
          phishDomain.getId(),
          url.toString(),
          null,
          null,
          now
        ));
      }
      // ユーザ投稿の登録
      userPost = new UserPost(userId, phishUrl.getId(), postContents, now);
      userPostRepository.save(userPost);
    } else {
      throw new AppException(HttpStatus.BAD_REQUEST, "BadRequest");
    }
  }

  // ユーザ投稿のフィッシュサイトを削除
  public void deletePhishSiteRecord(long userPostId, long userId) {
    userPostRepository.deleteByIdAndUserId(userPostId, userId);
  }

  // ユーザ投稿のフィッシュサイトを参照
  public List<PhishSiteInfoAggregate> refPhishSiteUserPost(long userId) {
    return transferPhishSiteAggregateFromQueryResult(phishSiteInfoRepository.refPhishSiteUserPost(userId));
  }

  // ポストID指定でのユーザ投稿のフィッシュサイトを参照
  public PhishSiteInfoAggregate refPostPhishSite(long postId) {
    List<PhishSiteInfoAggregate> results = transferPhishSiteAggregateFromQueryResult(phishSiteInfoRepository.refPhishSiteByPostId(postId));
    if (results ==null || results.size() == 0) {
      return null;
    } else {
      return results.get(0);
    }
  }

  // 不要URLレコードの削除（PhishTankに未登録かつURL投稿が無い）
  public void deleteUnnecessaryUrlRecord() {
    phishUrlRepository.deleteUnnecessaryUrl();
  }

  // 不要ドメインレコードの削除（URLへの紐付きが無い）
  public void deleteUnnecessaryDomainRecord() {
    phishDomainRepository.deleteUnnecessaryDomain();
  }

  // phishsiteの全件取得
  public List<PhishSiteInfoAggregate> getPhishSiteAll() {
    return transferPhishSiteAggregateFromQueryResult(
      phishSiteInfoRepository.getAllPhishSiteInfo()
    );
  }

  // phishsiteの最新取得
  public List<PhishSiteInfoAggregate> getRecentPhishSite(long limit) {
    return transferPhishSiteAggregateFromQueryResult(
      phishSiteInfoRepository.recentPhishSite(limit)
    );
  }

  // phishsiteの検索
  public List<PhishSiteInfoAggregate> serchPhishSite(String urlStr) {
    String domain = "";
    try {
      domain = commonService.getDomainFromUrl(new URL(urlStr));
    } catch(Exception e) {
      domain = urlStr;
    }
    return transferPhishSiteAggregateFromQueryResult(
      // 前方一致で検索するので%を付与
      phishSiteInfoRepository.searchPhishSiteInfo(domain + "%")
    );
  }

  // entityからaggregateへの変換
  private List<PhishSiteInfoAggregate> transferPhishSiteAggregateFromQueryResult(List<PhishSiteInfoQueryResult> resultList) {
    List<PhishSiteInfoAggregate> aggregateList = new ArrayList<>();
    PhishSiteInfoAggregate beforeAggregate = null;
    List<PostInfo> tempPostInfos = new ArrayList<>();
    int resultListSize = resultList.size();
    for(int i = 0; i < resultListSize; i++) {
      PhishSiteInfoQueryResult result = resultList.get(i);
      if (i == (resultListSize - 1)) {
        if (beforeAggregate == null) {
          if (result.getPostId() != null) {
            tempPostInfos.add(generatePostInfo(result));
          }
          aggregateList.add(generatePhishSiteInfoAggregate(result, tempPostInfos));
        } else {
          // 前のレコードとURLIDが同じ場合はpostinfoを追加して登録
          if (beforeAggregate.getPhishUrlId() == result.getPhishUrlId()) {
            if (result.getPostId() != null) {
              tempPostInfos.add(generatePostInfo(result));
            }
            beforeAggregate.setPostInfos(tempPostInfos);
            aggregateList.add(beforeAggregate);
          // 前のレコードとURLIDが違う場合は2レコード登録
          } else {
            aggregateList.add(beforeAggregate);
            tempPostInfos = new ArrayList<>();
            if (result.getPostId() != null) {
              tempPostInfos.add(generatePostInfo(result));
            }
            aggregateList.add(generatePhishSiteInfoAggregate(result, tempPostInfos));
          }
        }
      }
      else if (beforeAggregate == null) {
        if (result.getPostId() != null) {
          tempPostInfos.add(generatePostInfo(result));
        }
        beforeAggregate = generatePhishSiteInfoAggregate(result, tempPostInfos);
      } else if (beforeAggregate.getPhishUrlId() == result.getPhishUrlId()) {
        if (result.getPostId() != null) {
          tempPostInfos.add(generatePostInfo(result));
        }
        beforeAggregate.setPostInfos(tempPostInfos);
      } else {
        aggregateList.add(beforeAggregate);
        tempPostInfos = new ArrayList<>();
        if (result.getPostId() != null) {
          tempPostInfos.add(generatePostInfo(result));
        }
        beforeAggregate = generatePhishSiteInfoAggregate(result, tempPostInfos);
      }
    }
    return aggregateList;
  }

  private PhishSiteInfoAggregate generatePhishSiteInfoAggregate(
    PhishSiteInfoQueryResult result, List<PostInfo> postInfos
  ) {
    return new PhishSiteInfoAggregate(
      result.getPhishUrlId(),
      result.getPhishUrl(),
      result.getPhishTankId(),
      result.getPhishTankUrl(),
      result.getRegisterAt(),
      result.getPhishDomainId(),
      result.getPhishDomain(),
      postInfos
    );
  }

  private PostInfo generatePostInfo(
    PhishSiteInfoQueryResult result
  ) {
    return new PostInfo(
      result.getPostId(),
      result.getPostContents(),
      result.getPostRegisterAt(),
      result.getUserId(),
      result.getUserName()
    );
  }

  // batchでドメインを保存
  public void phishDomainBatchSave(List<PhishDomain> addPhishDomainList) {
    // 2000件で分割
    int batchSize = 2000;   // 一度に処理を行うリスト数
    int start = 0;
    int listSize = addPhishDomainList.size();
    int sizeCount = 1;
    for (int i = 1; i <= listSize; i++) {
      if (sizeCount == batchSize || i == listSize) {
        List<PhishDomain> addList = new ArrayList<>(
          addPhishDomainList.subList(start, i)
        );
        phishDomainRepository.saveAll(addList);
        commonService.sleep(4);
        start = i;
        sizeCount = 1;
      } else {
        sizeCount++;
      }
    }
  }

  // 不要ドメインの削除
  public void deleteUnnecessaryDomain() {
    phishDomainRepository.deleteUnnecessaryDomain();
  }

  // batchでURLを保存
  public void phishUrlBatchSave(List<PhishUrl> addPhishUrlList) {
    // 2000件で分割
    int batchSize = 2000;   // 一度に処理を行うリスト数
    int start = 0;
    int listSize = addPhishUrlList.size();
    int sizeCount = 1;
    for (int i = 1; i <= listSize; i++) {
      if (sizeCount == batchSize || i == listSize) {
        List<PhishUrl> addList = new ArrayList<>(
          addPhishUrlList.subList(start, i)
        );
        phishUrlRepository.saveAll(addList);
        commonService.sleep(4);
        start = i;
        sizeCount = 1;
      } else {
        sizeCount++;
      }
    }
  }

  // IDによるURLの削除
  public void deleteUrlByIdIn(List<Long> deletePhishUrlIdList) {
    // 500件で分割
    int batchSize = 500;   // 一度に処理を行うリスト数
    int start = 0;
    int listSize = deletePhishUrlIdList.size();
    int sizeCount = 1;
    for (int i = 1; i <= listSize; i++) {
      if (sizeCount == batchSize || i == listSize) {
        List<Long> deleteList = new ArrayList<>(
          deletePhishUrlIdList.subList(start, i)
        );
        phishUrlRepository.deleteByIdIn(deleteList);
        start = i;
        sizeCount = 1;
      } else {
        sizeCount++;
      }
    }

  }

  // IDによる該当URLのPhishTank情報を空にする
  public void updateUrlPhishTankInfoEmptyById(List<Long> updatePhishUrlIdList) {
    phishUrlRepository.updatePhishTankInfoEmpty(updatePhishUrlIdList);
  }

}
