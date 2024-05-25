package com.placeNote.placeNoteApi2024.service.userAccount;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.placeNote.placeNoteApi2024.service.common.JwtService;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.model.db.UserAccountDocument;
import com.placeNote.placeNoteApi2024.model.graphql.auth.AccountUserResponse;
import com.placeNote.placeNoteApi2024.repository.UserAccountRepository;


@Service
public class UserAccountService {
    @Autowired
    JwtService jwtService;
    @Autowired
    UserAccountRepository userAccountRepository;

    // gmailでユーザの追加
    public AccountUserResponse addAccountUserByGmail(
            String userSettingId,
            String name,
            String gmail) throws GraphqlErrorException {

        // userSettingId・gmailを指定してユーザを取得し未登録か確認
        Optional<UserAccountDocument> userOptional = userAccountRepository.findByUserSettingIdOrGmail(userSettingId, gmail);
        if (userOptional.isPresent()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Already registered account")
                    .build();
        }

        try {
            UserAccountDocument userAccountDocument = new UserAccountDocument(
                    UUID.randomUUID().toString(),
                    name,
                    userSettingId,
                    gmail,
                    null,
                    null,
                    null
            );
            userAccountRepository.save(userAccountDocument);
            // idをトークン化してレスポンスを返す
            return new AccountUserResponse(
                    getUserAuthToken(userAccountDocument.id()),
                    userAccountDocument.userSettingId(),
                    userAccountDocument.name()
            );
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

    // ユーザ認証用のトークンを生成
    private String getUserAuthToken(String id) throws GraphqlErrorException {
        // idをトークン化
        return jwtService.makeJwtToken(
                4320L * 60L * 60L * 1000L,
                Map.of("userAccountId", id));
    }
}
