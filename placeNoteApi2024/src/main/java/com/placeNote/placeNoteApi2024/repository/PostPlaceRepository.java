package com.placeNote.placeNoteApi2024.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import com.placeNote.placeNoteApi2024.model.db.PostPlaceDocument;
import com.placeNote.placeNoteApi2024.model.db.aggregation.PostPlaceAggregation;

@Repository
public interface PostPlaceRepository extends MongoRepository<PostPlaceDocument, String> {
    public void deleteByIdAndCreateUserAccountId(String id, String createUserAccountId);

    @Update("{ '$pull' : { 'category_id_list' : ?1 } }")
    void findAndPullCategoryIdListByCreateUserAccountIdAndCategoryIdList(String createUserAccountId, String categoryId);

    @Aggregation(
            pipeline = {
                    "{ '$match': { 'create_user_account_id': ?0 } }",
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?1, ''] } }, " +
                            " { '$expr': { '$eq': [?1, '$_id'] } }" +
                            "] } }",
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?2, []] } }, " +
                            " { 'category_id_list': { '$elemMatch': { '$in': ?2 } } }" +
                            "] } }",
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?3, ''] } }, " +
                            " { 'name': { '$regex': ?3, '$options': 'i' } }" +
                            "] } }",
                    "{ '$sort': { 'name': 1 } }",
                    "{ '$lookup': {" +
                            " 'from': 'user_accounts', " +
                            " 'localField': 'create_user_account_id', " +
                            " 'foreignField': '_id', " +
                            " 'as': 'user_accounts' " +
                            " } }",
                    "{ '$unwind': '$user_accounts' }",
                    "{ '$project': {" +
                            " '_id': '$_id', " +
                            " 'name': 1, " +
                            " 'user_setting_id': '$user_accounts.user_setting_id', " +
                            " 'address': 1, " +
                            " 'lon_lat': 1, " +
                            " 'prefecture_code': 1, " +
                            " 'category_id_list': 1, " +
                            " 'detail': 1, " +
                            " 'url': 1" +
                            " } }",
            }
    )
    public List<PostPlaceAggregation> getPlaceListAggregate(
            String userAccountId,
            String idFilter,
            List categoriesFilter,
            String nameFilter);

}
