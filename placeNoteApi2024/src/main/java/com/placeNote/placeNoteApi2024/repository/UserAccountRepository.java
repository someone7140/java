package com.placeNote.placeNoteApi2024.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeNote.placeNoteApi2024.model.db.UserAccountDocument;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccountDocument, String> {
    public Optional<UserAccountDocument> findByUserSettingIdOrGmail(String userSettingId, String gmail);
    public Optional<UserAccountDocument> findByGmail(String gmail);
    public Optional<UserAccountDocument> findById(String id);
    public Optional<UserAccountDocument> findByUserSettingId(String userSettingId);
}
