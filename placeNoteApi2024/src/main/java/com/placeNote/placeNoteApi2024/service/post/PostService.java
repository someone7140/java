package com.placeNote.placeNoteApi2024.service.post;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.constants.UrlTypeEnum;
import com.placeNote.placeNoteApi2024.model.db.subDocument.post.UrlDocument;
import com.placeNote.placeNoteApi2024.model.db.subDocument.post.UrlInfoDocument;
import com.placeNote.placeNoteApi2024.model.db.PostDocument;
import com.placeNote.placeNoteApi2024.model.domain.ExternalUrl;
import com.placeNote.placeNoteApi2024.model.graphql.post.PostResponse;
import com.placeNote.placeNoteApi2024.model.graphql.post.UrlDetailResponse;
import com.placeNote.placeNoteApi2024.model.graphql.post.UrlInfoResponse;
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

    // 投稿一覧取得
    public List<PostResponse> getPostList(
            String idFilter, String userAccountId) throws GraphqlErrorException {
        return postRepository.getPlaceAggregate(userAccountId, idFilter != null ? idFilter : "", 150).stream().map(p -> {
            var urlList = p.urlList().stream().map(u -> {
                        var urlInfo = u.urlInfo() != null ? new UrlInfoResponse(
                                u.urlInfo().title(),
                                u.urlInfo().imageUrl(),
                                u.urlInfo().siteName()
                        ) : null;
                        return new UrlDetailResponse(
                                u.urlId(), u.url(), u.urlType(), urlInfo
                        );
                    }
            ).toList();

            return new PostResponse(
                    p.id(),
                    p.userSettingId(),
                    p.title(),
                    p.placeId(),
                    p.placeName(),
                    p.visitedDate().toInstant().atOffset(ZoneOffset.ofHours(9)),
                    p.isOpen(),
                    p.categoryIdList(),
                    p.detail(),
                    urlList
            );
        }).toList();
    }
}
