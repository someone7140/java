package com.placeNote.placeNoteApi2024.service.common;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import graphql.GraphqlErrorException;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GoogleStorageService {
    @Autowired
    Environment env;

    // ファイルの名とパスからBlobIdを取得
    public BlobId getGcsBlobId(String filePath, String fileName) {
        return BlobId.of(env.getProperty("google.storage.bucket.name"), filePath + "/" + fileName);
    }

    // GCSにファイルをアップロード
    public String uploadFileGcs(MultipartFile imageFile, BlobId blobId) throws GraphqlErrorException {
        try {
            // keyファイルの読み込み
            Resource resource = new ClassPathResource(env.getProperty("google.storage.key.resource.path"));
            var credentials = GoogleCredentials
                    .fromStream(resource.getInputStream())
                    .createScoped("https://www.googleapis.com/auth/cloud-platform");
            // storageサービスの取得
            var storageService = StorageOptions.newBuilder()
                    .setCredentials(credentials)
                    .setProjectId(env.getProperty(("google.cloud.project.id")))
                    .build()
                    .getService();

            // ファイルをGCSに追加
            var blobInfo = BlobInfo.newBuilder(blobId).build();
            var blobFile = storageService.create(blobInfo, imageFile.getInputStream());
            // アップロードしたファイルへのリンクを返す
            return blobFile.getMediaLink();
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }
}
