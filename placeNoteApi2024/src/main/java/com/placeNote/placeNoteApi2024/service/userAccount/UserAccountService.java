package com.placeNote.placeNoteApi2024.service.userAccount;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.auth0.jwt.interfaces.DecodedJWT;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import com.placeNote.placeNoteApi2024.model.db.UserAccountDocument;
import com.placeNote.placeNoteApi2024.model.graphql.auth.AccountUserResponse;
import com.placeNote.placeNoteApi2024.repository.UserAccountRepository;
import com.placeNote.placeNoteApi2024.service.common.JwtService;

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
        Optional<UserAccountDocument> userOptional = userAccountRepository.findByGmail(gmail);
        if (userOptional.isEmpty()) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message("Already registered account")
                    .build();
        }
        UserAccountDocument userAccountDocument = userOptional.get();
        // idをトークン化してレスポンスを返す
        return getAccountUserResponseFromDocument(userAccountDocument);
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
                doc.name()
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
        DecodedJWT decodeResult = jwtService.decodeToken(token);
        return decodeResult.getClaim("userAccountId").asString();
    }
}
