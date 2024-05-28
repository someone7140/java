package com.placeNote.placeNoteApi2024.model.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.placeNote.placeNoteApi2024.service.userAccount.UserAccountService;

@Component
public class RequestManager {
    @Autowired
    UserAccountService userAccountService;

    private final String LOGIN_USER_ID_SESSION = "userAccountId";

    public void saveUserAccountIdSession(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        // Authorizationヘッダーが設定されているか判定
        if (authHeader != null && authHeader.split(" ").length > 1) {
            String bearerToken = authHeader.split(" ")[1];
            try {
                // トークンの検証
                String userAccountId = userAccountService.getUserIdFromAuthToken(bearerToken);
                request.setAttribute(LOGIN_USER_ID_SESSION, userAccountId);
            } catch (Exception e) {
                // 失敗した場合は何もしない
            }
        }
    }

    public String getUserAccountIdSession() {
        Object session = RequestContextHolder
                .getRequestAttributes().getAttribute(LOGIN_USER_ID_SESSION, RequestAttributes.SCOPE_REQUEST);
        return (String) session;
    }

}
