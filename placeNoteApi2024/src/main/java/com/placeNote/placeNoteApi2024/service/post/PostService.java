package com.placeNote.placeNoteApi2024.service.post;

import java.util.List;
import java.util.UUID;

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
            Boolean isOpen,
            List<String> categoryIdList,
            String detail,
            List<String> urlList) throws GraphqlErrorException {
        PostDocument postDocument = new PostDocument(
                UUID.randomUUID().toString(),
                title,
                userAccountId,
                placeId,
                isOpen,
                categoryIdList,
                detail,
                urlList
        );
        postRepository.save(postDocument);
        return true;
    }

}
