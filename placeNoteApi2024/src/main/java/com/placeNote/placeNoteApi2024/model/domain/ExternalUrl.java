package com.placeNote.placeNoteApi2024.model.domain;

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
    static Map<String, UrlTypeEnum> urlDomainTypeMap = Map.of(
            "x.com", UrlTypeEnum.X,
            "twitter.com", UrlTypeEnum.X,
            "www.instagram.com", UrlTypeEnum.Instagram,
            "www.threads.net", UrlTypeEnum.Threads
    );

    public static ExternalUrl makeExternalUrlObjectFromUrl(String url) {
        var urlService = new UrlService();
        // ドメインからURLの種別を特定
        var domain = urlService.getDomainFromUrl(url);
        var urlType = urlDomainTypeMap.getOrDefault(domain, UrlTypeEnum.WebNoInfo);
        if (urlType.equals(UrlTypeEnum.WebNoInfo)) {
            var ogpMap = urlService.getOgpElements(url);
            if (ogpMap != null) {
                // OGPでタイトルが取得できた場合はWebWithInfoで返す
                var title = ogpMap.getOrDefault("og:title", null);
                if (title != null) {
                    return new ExternalUrl(
                            url,
                            UrlTypeEnum.WebWithInfo,
                            ogpMap.getOrDefault("og:title", null),
                            ogpMap.getOrDefault("og:image", null),
                            ogpMap.getOrDefault("og:site_name", null));
                }
            }
        }
        // WebWithInfo以外はurlとurlTypeだけ設定して返す
        return new ExternalUrl(url, urlType, null, null, null);
    }
}
