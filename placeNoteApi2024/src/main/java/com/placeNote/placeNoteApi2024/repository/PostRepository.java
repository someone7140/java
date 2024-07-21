package com.placeNote.placeNoteApi2024.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeNote.placeNoteApi2024.model.db.PostDocument;

@Repository
public interface PostRepository extends MongoRepository<PostDocument, String> {
    public void deleteByCreateUserAccountIdAndPlaceId(String createUserAccountId, String placeId);
}
