package com.placeNote.placeNoteApi2024.model.domain;

import org.springframework.beans.factory.annotation.Autowired;

import com.placeNote.placeNoteApi2024.constants.UrlTypeEnum;
import com.placeNote.placeNoteApi2024.service.common.UrlService;

import java.util.Map;

public record ExternalUrl(
        String url,
        UrlTypeEnum urlType,
        String title,
        String imageUrl,
        String siteName
) {
    @Autowired
    static UrlService urlService;
    static Map<String, UrlTypeEnum> urlDomainTypeMap = Map.of(
            "x.com", UrlTypeEnum.X,
            "twitter.com", UrlTypeEnum.X,
            "www.instagram.com", UrlTypeEnum.Instagram,
            "www.threads.net", UrlTypeEnum.Threads
    );

    public static ExternalUrl makeExternalUrlObjectFromUrl(String url) {
        // ドメインからURLの種別を特定
        var domain = urlService.getDomainFromUrl(url);
        var urlType = urlDomainTypeMap.getOrDefault(domain, UrlTypeEnum.WebNoInfo);
        if (urlType.equals(UrlTypeEnum.WebNoInfo)) {
            var ogpMap = urlService.getOgpElements(url);
            if (ogpMap != null) {
                // OGPが取得できた場合はWebWithInfoで返す
                return new ExternalUrl(
                        url,
                        UrlTypeEnum.WebWithInfo,
                        ogpMap.getOrDefault("og:title", null),
                        ogpMap.getOrDefault("og:image", null),
                        ogpMap.getOrDefault("og:site_name", null));
            }
        }
        // WebWithInfo以外はurlとurlTypeだけ設定して返す
        return new ExternalUrl(url, urlType, null, null, null);
    }
}
