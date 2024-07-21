package com.placeNote.placeNoteApi2024.service.postPlace;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.placeNote.placeNoteApi2024.model.db.PostCategoryDocument;
import com.placeNote.placeNoteApi2024.model.db.PostPlaceDocument;
import com.placeNote.placeNoteApi2024.model.db.aggregation.PostPlaceAggregation;
import com.placeNote.placeNoteApi2024.model.graphql.postPlace.LatLonInput;
import com.placeNote.placeNoteApi2024.model.graphql.postPlace.LatLonResponse;
import com.placeNote.placeNoteApi2024.model.graphql.postPlace.PostPlaceResponse;
import com.placeNote.placeNoteApi2024.repository.PostRepository;
import com.placeNote.placeNoteApi2024.repository.PostCategoryRepository;
import com.placeNote.placeNoteApi2024.repository.PostPlaceRepository;

@Service
public class PostPlaceService {
    @Autowired
    PostCategoryRepository postCategoryRepository;
    @Autowired
    PostPlaceRepository postPlaceRepository;
    @Autowired
    PostRepository postRepository;

    // 住所から緯度・経度の取得
    public LatLonResponse getLatLonFromGeocodingService(String address) throws GraphqlErrorException {
        RestClient restClient = RestClient.create();
        Element resultElem;
        try {
            String encodedUrlResult = "https://www.geocoding.jp/api/?q=" + address;
            ResponseEntity<String> responseRest = restClient.get()
                    .uri(encodedUrlResult)
                    .retrieve()
                    .toEntity(String.class);
            if (responseRest.getStatusCode().isError()) {
                throw GraphqlErrorException
                        .newErrorException()
                        .errorClassification(ErrorType.INTERNAL_ERROR)
                        .message("Can not get geocoding result")
                        .build();
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(responseRest.getBody()));
            Document document = builder.parse(is);
            resultElem = document.getDocumentElement();
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }

        NodeList coordinateNodes = resultElem.getElementsByTagName("coordinate");
        if (coordinateNodes.getLength() > 0) {
            Element coordinateElem = (Element) coordinateNodes.item(0);
            NodeList latNodes = coordinateElem.getElementsByTagName("lat");
            NodeList lonNodes = coordinateElem.getElementsByTagName("lng");
            if (latNodes.getLength() > 0 && lonNodes.getLength() > 0) {
                return new LatLonResponse(
                        Double.parseDouble(latNodes.item(0).getTextContent()),
                        Double.parseDouble(lonNodes.item(0).getTextContent())
                );
            }
        }

        return null;
    }

    // 場所の追加
    public Boolean addPostPlace(
            String userAccountId,
            String name,
            String address,
            LatLonInput latLon,
            String prefectureCode,
            List<String> categoryIdList,
            String detail,
            List<String> urlList) throws GraphqlErrorException {
        PostPlaceDocument postPlaceDocument = new PostPlaceDocument(
                UUID.randomUUID().toString(),
                name,
                userAccountId,
                address,
                latLon != null ? latLon.getLonLatArray() : null,
                prefectureCode,
                categoryIdList,
                detail,
                urlList
        );
        postPlaceRepository.save(postPlaceDocument);
        return true;
    }

    // 場所の編集
    public Boolean editPostPlace(
            String userAccountId,
            String id,
            String name,
            String address,
            LatLonInput latLon,
            String prefectureCode,
            List<String> categoryIdList,
            String detail,
            List<String> urlList) throws GraphqlErrorException {
        PostPlaceDocument postPlaceDocument = new PostPlaceDocument(
                id,
                name,
                userAccountId,
                address,
                latLon != null ? latLon.getLonLatArray() : null,
                prefectureCode,
                categoryIdList,
                detail,
                urlList
        );
        postPlaceRepository.save(postPlaceDocument);
        return true;
    }

    // 場所の削除
    public Boolean deletePostPlace(String userAccountId, String id) throws GraphqlErrorException {
        // 該当の場所の配下の投稿を削除
        postRepository.deleteByCreateUserAccountIdAndPlaceId(userAccountId, id);
        // 場所の削除
        postPlaceRepository.deleteByIdAndCreateUserAccountId(id, userAccountId);
        return true;
    }

    // 場所の一覧
    public List<PostPlaceResponse> getPostPlaceList(String userAccountId, String idFilter, String categoryFilter, String nameFilter) throws GraphqlErrorException {
        List<PostCategoryDocument> categories = new ArrayList<>();
        if (categoryFilter != null) {
            // 指定したカテゴリーの子カテゴリーを含めて取得
            categories = postCategoryRepository.findPostCategoriesWithChildren(userAccountId, categoryFilter);
        }

        List<PostPlaceAggregation> aggregateResults = postPlaceRepository.getPlaceListAggregate(
                userAccountId,
                idFilter == null ? "" : idFilter,
                categories.stream().map(c -> c.id()).toList(),
                nameFilter);
        return aggregateResults.stream().map(r -> new PostPlaceResponse(
                r.id(),
                r.userSettingId(),
                r.name(),
                r.address(),
                r.lonLat() != null ? new LatLonResponse(r.lonLat()) : null,
                r.prefectureCode(),
                r.categoryIdList(),
                r.detail(),
                r.urlList()
        )).toList();
    }
}
