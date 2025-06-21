package com.api.wasrenaTaskApi2025.filter;

import com.api.wasrenaTaskApi2025.service.common.JwtService;

import graphql.GraphqlErrorException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        // ヘッダーのjwtトークンを検証
        var requestTokenHeader = request.getHeader("Authorization");
        Optional<String> userIdOpt = Optional.empty();
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            try {
                var jwtToken = requestTokenHeader.substring(7);
                var decodedJwt = jwtService.decodeToken(jwtToken);
                userIdOpt = Optional.of(decodedJwt.getClaim("userId").asString());
            } catch (Exception e) {
                // 何もしない
            }
        }

        // 認証情報の設定
        if (userIdOpt.isPresent()) {
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            var usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userIdOpt.get(), null, authorities);
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        chain.doFilter(request, response);
    }
}
