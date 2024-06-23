package com.placeNote.placeNoteApi2024.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import com.placeNote.placeNoteApi2024.model.db.PostCategoryDocument;
import com.placeNote.placeNoteApi2024.model.db.aggregation.PostCategoryAggregation;

@Repository
public interface PostCategoryRepository extends MongoRepository<PostCategoryDocument, String> {
    public Optional<PostCategoryDocument> findByIdAndCreateUserAccountId(String id, String createUserAccountId);

    public void deleteByIdAndCreateUserAccountId(String id, String createUserAccountId);

    @Aggregation(
            pipeline = {
                    "{ '$match': { 'create_user_account_id': ?0 } }",
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?1, ''] } }, " +
                            " { '$expr': { '$eq': [?1, '$_id'] } }" +
                            "] } }",
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?2, ''] } }, " +
                            " { 'name': { '$regex': ?2, '$options': 'i' } }" +
                            "] } }",
                    "{ '$addFields': { 'sort_field': { '$ifNull': ['$display_order', " + Integer.MAX_VALUE + "] } } }",
                    "{ '$sort': { 'sort_field': 1 } }",
                    "{ '$lookup': {" +
                            " 'from': 'user_accounts', " +
                            " 'localField': 'create_user_account_id', " +
                            " 'foreignField': '_id', " +
                            " 'as': 'user_accounts' " +
                            " } }",
                    "{ '$unwind': '$user_accounts' }",
                    "{ '$project': {" +
                            " '_id': 1, " +
                            " 'name': 1, " +
                            " 'user_setting_id': '$user_accounts.user_setting_id', " +
                            " 'parent_category_id': 1, " +
                            " 'memo': 1, " +
                            " 'display_order': 1" +
                            " } }",
            }
    )
    public List<PostCategoryAggregation> getCategoryListAggregate(String userAccountId, String idFilter, String nameFilter);

    @Update("{ '$set' : { 'parent_category_id' : null } }")
    void findAndSetParentCategoryIdNullByCreateUserAccountIdAndParentCategoryId(String createUserAccountId, String parentCategoryId);
}
