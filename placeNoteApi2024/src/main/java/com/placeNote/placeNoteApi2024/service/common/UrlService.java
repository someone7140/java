package com.placeNote.placeNoteApi2024.service.common;

import java.net.URI;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    public String getDomainFromUrl(String urlStr) {
        try {
            var url = URI.create(urlStr).toURL();
            return url.getHost();
        } catch (Exception e) {
            return null;
        }
    }

    public HashMap<String, String> getOgpElements(String url) {
        try {
            var document = Jsoup.connect(url).get();
            var ogpElements = document.select("meta[property~=og:*]");
            // property名とcontentsをMapにして返す
            var ogpMap = new HashMap<String, String>();
            for (var element : ogpElements) {
                ogpMap.put(element.attr("property"), element.attr("content"));
            }
            return ogpMap;
        } catch (Exception e) {
            return null;
        }
    }
}
