package com.placeNote.placeNoteApi2024.service.userAccount;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import graphql.GraphqlErrorException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.placeNote.placeNoteApi2024.model.db.UserAccountDocument;
import com.placeNote.placeNoteApi2024.model.graphql.auth.AccountUserResponse;
import com.placeNote.placeNoteApi2024.repository.UserAccountRepository;
import com.placeNote.placeNoteApi2024.service.common.GoogleStorageService;
import com.placeNote.placeNoteApi2024.service.common.JwtService;

@Service
public class UserAccountService {
    String ICON_IMAGE_FOLDER = "user_icon_image";

    @Autowired
    GoogleStorageService googleStorageService;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserAccountRepository userAccountRepository;

    // gmailでユーザの追加
    public AccountUserResponse addAccountUserByGmail(
            String userSettingId,
            String name,
            String gmail,
            MultipartFile imageFile) throws GraphqlErrorException {

        // userSettingId・gmailを指定してユーザを取得し未登録か確認
        var userOptional = userAccountRepository.findByUserSettingIdOrGmail(userSettingId, gmail);
        if (userOptional.isPresent()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Already registered account")
                    .build();
        }

        try {
            // アイコン画像がある場合はアップロード
            String imageUrl = null;
            if (imageFile != null) {
                imageUrl = uploadNewIconImage(imageFile);
            }
            // DBにユーザ追加
            UserAccountDocument userAccountDocument = new UserAccountDocument(
                    UUID.randomUUID().toString(),
                    name,
                    userSettingId,
                    gmail,
                    null,
                    null,
                    imageUrl
            );
            userAccountRepository.save(userAccountDocument);
            // idをトークン化してレスポンスを返す
            return getAccountUserResponseFromDocument(userAccountDocument);
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    // gmailでのユーザ取得
    public AccountUserResponse getAccountUserByGmail(String gmail) throws GraphqlErrorException {
        var userOptional = userAccountRepository.findByGmail(gmail);
        if (userOptional.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Already registered account")
                    .build();
        }
        var userAccountDocument = userOptional.get();
        // idをトークン化してレスポンスを返す
        return getAccountUserResponseFromDocument(userAccountDocument);
    }

    // ユーザの編集
    public AccountUserResponse editAccountUser(
            String userAccountId,
            String userSettingId,
            String name,
            MultipartFile imageFile) throws GraphqlErrorException {
        // 既存のユーザ情報をDBから取得
        var userOptional = userAccountRepository.findById(userAccountId);
        if (userOptional.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Already registered account")
                    .build();
        }
        var registeredUserAccountDocument = userOptional.get();

        // userSettingIdに変更がある場合に他で登録されているか
        if (!userSettingId.equals(registeredUserAccountDocument.userSettingId())) {
            var userByUserSettingIdOptional = userAccountRepository.findByUserSettingId(userSettingId);
            if (userByUserSettingIdOptional.isPresent()) {
                throw GraphqlErrorException
                        .newErrorException()
                        .errorClassification(ErrorType.BAD_REQUEST)
                        .message("Already registered id")
                        .build();
            }
        }

        try {
            // アイコン画像がある場合はアップロード
            String imageUrl = registeredUserAccountDocument.imageUrl();
            if (imageFile != null) {
                // すでに画像がある場合は一度削除
                if (registeredUserAccountDocument.imageUrl() != null) {
                    deleteIconImage(registeredUserAccountDocument.imageUrl());
                }
                imageUrl = uploadNewIconImage(imageFile);
            }

            // DBユーザ更新
            UserAccountDocument userAccountDocument = new UserAccountDocument(
                    registeredUserAccountDocument.id(),
                    name,
                    userSettingId,
                    registeredUserAccountDocument.gmail(),
                    registeredUserAccountDocument.email(),
                    registeredUserAccountDocument.password(),
                    imageUrl
            );
            userAccountRepository.save(userAccountDocument);
            // idをトークン化してレスポンスを返す
            return getAccountUserResponseFromDocument(userAccountDocument);
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    // idでのユーザ取得
    public AccountUserResponse getAccountUserById(String id) throws GraphqlErrorException {
        Optional<UserAccountDocument> userOptional = userAccountRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Can not get user")
                    .build();
        }
        UserAccountDocument userAccountDocument = userOptional.get();
        // idをトークン化してレスポンスを返す
        return getAccountUserResponseFromDocument(userAccountDocument);
    }

    private AccountUserResponse getAccountUserResponseFromDocument(UserAccountDocument doc) {
        return new AccountUserResponse(
                getUserAuthToken(doc.id()),
                doc.userSettingId(),
                doc.name(),
                doc.imageUrl()
        );
    }

    // ユーザ認証用のトークンを生成
    public String getUserAuthToken(String id) throws GraphqlErrorException {
        // idをトークン化
        return jwtService.makeJwtToken(
                4320L * 60L * 60L * 1000L,
                Map.of("userAccountId", id));
    }

    // ユーザ認証用のトークンからidを取得
    public String getUserIdFromAuthToken(String token) throws GraphqlErrorException {
        var decodeResult = jwtService.decodeToken(token);
        return decodeResult.getClaim("userAccountId").asString();
    }

    // アイコン画像の新規アップロード
    private String uploadNewIconImage(MultipartFile imageFile) throws GraphqlErrorException {
        // BlobIdの生成
        var ext = FilenameUtils.getExtension(imageFile.getOriginalFilename());
        var newFileName = UUID.randomUUID().toString();
        var blobId = googleStorageService.getGcsBlobId(ICON_IMAGE_FOLDER, newFileName + "." + ext);
        // アップロードして画像URL取得
        return googleStorageService.uploadFileGcs(imageFile, blobId);
    }

    // アイコン画像の削除
    private void deleteIconImage(String imageUrl) throws GraphqlErrorException {
        googleStorageService.deleteFileGcs(ICON_IMAGE_FOLDER, imageUrl);
    }

}
