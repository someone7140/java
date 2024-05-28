package com.placeNote.placeNoteApi2024.filter;

import java.io.IOException;

import com.placeNote.placeNoteApi2024.model.auth.RequestManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestFilter extends OncePerRequestFilter {
    @Autowired
    RequestManager requestManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // リクエストヘッダーのトークンを検証してユーザIDを格納する処理
        requestManager.saveUserAccountIdSession(request);
        filterChain.doFilter(request, response);
    }

}
