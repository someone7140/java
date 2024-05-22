package com.placeNote.placeNoteApi2024.service.common;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    Environment env;

    public String makeJwtToken(long expireMilliSecond, Map<String, String> claimMap) throws GraphqlErrorException {
        try {
            Date expireTime = new Date();
            expireTime.setTime(expireTime.getTime() + expireMilliSecond);

            Algorithm algorithm = Algorithm.HMAC256(env.getProperty("jwt.issuer"));
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(env.getProperty("jwt.secret"))
                    .withExpiresAt(expireTime);
            claimMap.forEach((k, v) -> {
                builder.withClaim(k, v);
            });

            return builder.sign(algorithm);
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }

}
