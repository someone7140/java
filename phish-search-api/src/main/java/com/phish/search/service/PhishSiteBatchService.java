package com.phish.search.service;

import com.codepoetics.protonpack.StreamUtils;
import com.phish.search.model.aggregate.PhishSiteInfoAggregate;
import com.phish.search.model.csv.PhishTankCsv;
import com.phish.search.model.db.PhishDomain;
import com.phish.search.model.db.PhishUrl;
import com.phish.search.repository.PhishDomainRepository;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhishSiteBatchService {
  @Autowired
  CommonService commonService;

  @Autowired
  PhishDomainRepository phishDomainRepository;
  @Autowired
  PhishSiteService phishSiteService;

  // PhishTankのcsvからDBにレコード登録
  public void registPhishTankCsvToDb(List<PhishTankCsv> phishTankCsvRecords) {
    // 現状の登録全件リストを取得
    List<PhishSiteInfoAggregate> registeredPhishSites = phishSiteService.getPhishSiteAll();
    // maxのurlId
    long insertUrlId = registeredPhishSites.stream().map(s -> s.getPhishUrlId()).max(Long::compareTo).orElse(0L) + 1;
    // 追加URLリスト
    List<PhishUrl> addPhishUrlList = new ArrayList<>();
    // 追加ドメインリスト
    List<PhishDomain> addPhishDomainList = new ArrayList<>();
    // 削除URLIDのリスト
    List<Long> deletePhishUrlIdList = new ArrayList<>();
    // PhishTankの情報を空にする更新URLIDリスト
    List<Long> updatePhishTankEmptyIdList = new ArrayList<>();

    List<PhishTankCsv> distinctPhishTankCsvRecords = Observable.fromStream(phishTankCsvRecords.stream()).distinct(p -> p.getPhishUrl()).toList().blockingGet();
    //　どのリストに入れるか判断
    for (PhishTankCsv csv : distinctPhishTankCsvRecords) {
      // PhishTankIdがあるか
      Optional<PhishSiteInfoAggregate> findResult = registeredPhishSites.stream().filter(
        r -> (r.getPhishTankId() != null && r.getPhishTankId().equals(csv.getPhishTankId())) ||
          r.getPhishUrl().equals(csv.getPhishUrl())
      ).findFirst();
      if (findResult.isPresent()) {
        // 存在する場合は元リストから削除
        registeredPhishSites.remove(findResult.get());
      } else {
        // 念のためURLの重複チェック
        if (addPhishUrlList.stream().filter(a -> a.getPhishUrl().equals(csv.getPhishUrl())).findFirst().isEmpty()) {
          // URLのリストに登録（ドメインのIDは一旦仮で置く）
          addPhishUrlList.add(new PhishUrl(
            0L,
            csv.getPhishUrl(),
            csv.getPhishTankId(),
            csv.getPhishTankUrl(),
            csv.getSubmissionTime()
          ));
        }
      }
    }

    // URLに紐付きがない不要なドメインレコードを削除
    phishSiteService.deleteUnnecessaryDomain();
    // ドメイン全件リスト
    List<PhishDomain> registeredDomains = phishDomainRepository.findAll();
    // maxのID
    long insertDomainId = registeredDomains.stream().map(d-> d.getId()).max(Long::compareTo).orElse(0L) + 1;
    addPhishDomainList = StreamUtils.zipWithIndex(addPhishUrlList.stream().map(p -> {
      try {
        String extractDomain = commonService.getDomainFromUrl(new URL(p.getPhishUrl()));
        if (registeredDomains.stream().anyMatch(r -> r.getPhishDomain().equals(extractDomain))) {
          return null;
        } else {
          return extractDomain;
        }
      } catch (Exception e) {
        return null;
      }
    }).filter(p -> p != null).distinct()).map(d ->
       new PhishDomain(d.getIndex() + insertDomainId, d.getValue())
    ).collect(Collectors.toList());
    // 更新
    if (!addPhishDomainList.isEmpty()) {
      phishSiteService.phishDomainBatchSave(addPhishDomainList);
    }

    List<PhishDomain> recentDomains = phishDomainRepository.findAll();

    // ドメイン情報を付加して登録
    addPhishUrlList = StreamUtils.zipWithIndex(addPhishUrlList.stream().map(u -> {
      Optional<PhishDomain> domain = recentDomains.stream().filter(d -> {
        try {
          return d.getPhishDomain().equals(commonService.getDomainFromUrl(new URL(u.getPhishUrl())));
        } catch (Exception e) {
          return false;
        }}).findFirst();
      if (domain.isEmpty() || u.getPhishUrl().length() >3500) {
        return null;
      } else {
        return new PhishUrl(
          domain.get().getId(),
          u.getPhishUrl(),
          u.getPhishTankId(),
          u.getPhishTankUrl(),
          u.getRegisterAt()
        );
      }
    }).filter(u -> u != null)).map(u -> {
      PhishUrl pu = u.getValue();
      pu.setId(u.getIndex() + insertUrlId);
      return pu;
    }).collect(Collectors.toList());
    if (!addPhishUrlList.isEmpty()) {
      phishSiteService.phishUrlBatchSave(addPhishUrlList);
    }

    // URLの削除（CSVにない＆PhishTankIDがある＆ユーザ登録でない）
    deletePhishUrlIdList = registeredPhishSites.stream().filter(p ->
            p.getPhishTankId() != null && p.getPostInfos().isEmpty()
    ).map(p -> p.getPhishUrlId()).collect(Collectors.toList());
    if (deletePhishUrlIdList.size() > 0) {
      phishSiteService.deleteUrlByIdIn(deletePhishUrlIdList);
    }

    // PhishTankの情報を空にする更新
    updatePhishTankEmptyIdList = registeredPhishSites.stream().filter(p ->
      p.getPhishTankId() != null && !p.getPostInfos().isEmpty()
    ).map(p -> p.getPhishUrlId()).collect(Collectors.toList());
    if (updatePhishTankEmptyIdList.size() > 0) {
      phishSiteService.updateUrlPhishTankInfoEmptyById(updatePhishTankEmptyIdList);
    }

    // URLに紐付きがない不要なドメインレコードを削除
    phishSiteService.deleteUnnecessaryDomain();
  }

}
