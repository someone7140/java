package com.placeNote.placeNoteApi2024.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.placeNote.placeNoteApi2024.model.db.aggregation.PostAggregation;
import com.placeNote.placeNoteApi2024.model.db.PostDocument;

@Repository
public interface PostRepository extends MongoRepository<PostDocument, String> {
    public void deleteByCreateUserAccountIdAndPlaceId(String createUserAccountId, String placeId);

    @Aggregation(
            pipeline = {
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?0, ''] } }, " +
                            " { '$expr': { '$eq': [?0, '$create_user_account_id'] } }" +
                            "] } }",
                    "{ '$match': { '$or': [ " +
                            " { '$expr': { '$eq': [?1, ''] } }, " +
                            " { '$expr': { '$eq': [?1, '$_id'] } }" +
                            "] } }",
                    "{ '$sort': { 'visited_date': -1 } }",
                    "{ '$limit': ?2 }",
                    "{ '$lookup': {" +
                            " 'from': 'user_accounts', " +
                            " 'localField': 'create_user_account_id', " +
                            " 'foreignField': '_id', " +
                            " 'as': 'user_accounts' " +
                            " } }",
                    "{ '$unwind': '$user_accounts' }",
                    "{ '$lookup': {" +
                            " 'from': 'post_places', " +
                            " 'localField': 'place_id', " +
                            " 'foreignField': '_id', " +
                            " 'as': 'post_places' " +
                            " } }",
                    "{ '$unwind': '$post_places' }",
                    "{ '$project': {" +
                            " '_id': '$_id', " +
                            " 'title': 1, " +
                            " 'user_setting_id': '$user_accounts.user_setting_id', " +
                            " 'place_id': 1, " +
                            " 'place_name': '$post_places.name', " +
                            " 'visited_date': 1, " +
                            " 'is_open': 1, " +
                            " 'category_id_list': 1, " +
                            " 'detail': 1, " +
                            " 'url_list': 1" +
                            " } }",
            }
    )
    public List<PostAggregation> getPlaceAggregate(String userAccountId, String idFilter, Integer limit);
}
