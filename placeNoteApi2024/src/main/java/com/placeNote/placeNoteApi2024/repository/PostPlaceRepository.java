package com.placeNote.placeNoteApi2024.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeNote.placeNoteApi2024.model.db.PostPlaceDocument;

@Repository
public interface PostPlaceRepository extends MongoRepository<PostPlaceDocument, String> {
}
