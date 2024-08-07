package com.placeNote.placeNoteApi2024.service.postCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.model.db.PostCategoryDocument;
import com.placeNote.placeNoteApi2024.model.db.aggregation.PostCategoryAggregation;
import com.placeNote.placeNoteApi2024.model.graphql.postCategory.PostCategoryResponse;
import com.placeNote.placeNoteApi2024.repository.PostCategoryRepository;
import com.placeNote.placeNoteApi2024.repository.PostPlaceRepository;

@Service
public class PostCategoryService {
    @Autowired
    PostCategoryRepository postCategoryRepository;
    @Autowired
    PostPlaceRepository postPlaceRepository;

    // 投稿カテゴリーの追加
    public Boolean addPostCategory(
            String userAccountId,
            String name,
            String parentCategoryId,
            Integer displayOrder,
            String memo) throws GraphqlErrorException {

        PostCategoryDocument postCategoryDocument = new PostCategoryDocument(
                UUID.randomUUID().toString(),
                name,
                userAccountId,
                parentCategoryId,
                displayOrder,
                memo
        );
        postCategoryRepository.save(postCategoryDocument);
        return true;
    }

    // 投稿カテゴリーの編集
    public Boolean editPostCategory(
            String id,
            String userAccountId,
            String name,
            String parentCategoryId,
            Integer displayOrder,
            String memo) throws GraphqlErrorException {
        Optional<PostCategoryDocument> categoryOptional = postCategoryRepository.findByIdAndCreateUserAccountId(id, userAccountId);
        if (categoryOptional.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not find category")
                    .build();
        }

        // カテゴリーの更新
        PostCategoryDocument postCategoryDocument = new PostCategoryDocument(
                id, name, userAccountId, parentCategoryId, displayOrder, memo
        );
        postCategoryRepository.save(postCategoryDocument);
        // 親カテゴリーが更新された場合、設定済みの子カテゴリーも新しい親カテゴリーにする
        postCategoryRepository.findAndSetParentCategoryIdByCreateUserAccountIdAndParentCategoryId(
                userAccountId, id, parentCategoryId
        );

        return true;
    }

    // 投稿カテゴリーの削除
    public Boolean deletePostCategory(String id, String userAccountId) throws GraphqlErrorException {
        // 場所のカテゴリー設定削除
        postPlaceRepository.findAndPullCategoryIdListByCreateUserAccountIdAndCategoryIdList(userAccountId, id);
        // 親として設定してあるカテゴリーの親設定削除
        postCategoryRepository.findAndSetParentCategoryIdNullByCreateUserAccountIdAndParentCategoryId(userAccountId, id);
        // 自身の削除
        postCategoryRepository.deleteByIdAndCreateUserAccountId(id, userAccountId);
        return true;
    }

    // 自身の投稿カテゴリー一覧取得
    public List<PostCategoryResponse> getPostCategoryList(String nameFilter, String userAccountId) throws GraphqlErrorException {
        return postCategoryRepository.getCategoryListAggregate(userAccountId, "", nameFilter == null ? "" : nameFilter).stream().map(c -> {
            return new PostCategoryResponse(
                    c.id(),
                    c.userSettingId(),
                    c.name(),
                    c.parentCategoryId(),
                    c.displayOrder(),
                    c.memo()
            );
        }).collect(Collectors.toList());
    }

    // id指定で投稿カテゴリー取得
    public PostCategoryResponse getPostCategoryById(String id, String userAccountId) throws GraphqlErrorException {
        List<PostCategoryAggregation> categoryList = postCategoryRepository.getCategoryListAggregate(userAccountId, id, "");
        if (categoryList.size() < 1) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not find category")
                    .build();
        }

        PostCategoryAggregation postCategory = categoryList.getFirst();
        return new PostCategoryResponse(
                id,
                postCategory.userSettingId(),
                postCategory.name(),
                postCategory.parentCategoryId(),
                postCategory.displayOrder(),
                postCategory.memo()
        );
    }
}
