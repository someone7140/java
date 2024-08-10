package com.placeNote.placeNoteApi2024.service.post;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.placeNote.placeNoteApi2024.constants.UrlTypeEnum;
import com.placeNote.placeNoteApi2024.model.db.subDocument.post.UrlDocument;
import com.placeNote.placeNoteApi2024.model.db.subDocument.post.UrlInfoDocument;
import com.placeNote.placeNoteApi2024.model.domain.ExternalUrl;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.model.db.PostDocument;
import com.placeNote.placeNoteApi2024.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    // 投稿の追加
    public Boolean addPost(
            String userAccountId,
            String title,
            String placeId,
            OffsetDateTime visitedDate,
            Boolean isOpen,
            List<String> categoryIdList,
            String detail,
            List<String> urlList) throws GraphqlErrorException {

        PostDocument postDocument = new PostDocument(
                UUID.randomUUID().toString(),
                title,
                userAccountId,
                placeId,
                new Date(visitedDate.toInstant().toEpochMilli()),
                isOpen,
                categoryIdList,
                detail,
                getUrlDocumentList(urlList)
        );
        postRepository.save(postDocument);
        return true;
    }

    private List<UrlDocument> getUrlDocumentList(List<String> urlList) {
        List<UrlDocument> urlDocumentList = new ArrayList<>();
        for (var url : urlList) {
            var externalUrlModel = ExternalUrl.makeExternalUrlObjectFromUrl(url);
            var urlId = UUID.randomUUID().toString();
            UrlInfoDocument urlInfo = null;

            if (externalUrlModel.urlType().equals(UrlTypeEnum.WebWithInfo)) {
                urlInfo = new UrlInfoDocument(externalUrlModel.title(), externalUrlModel.imageUrl(), externalUrlModel.siteName());
            }
            urlDocumentList.add(
                    new UrlDocument(urlId, externalUrlModel.url(), externalUrlModel.urlType(), urlInfo)
            );
        }
        return urlDocumentList;
    }
}
