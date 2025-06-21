package com.api.wasrenaTaskApi2025.service.common;

import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("wasurena-task.jwt.secret"));
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + expireMilliSecond);
        try {
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(env.getProperty("wasurena-task.jwt.issuer"))
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

    public DecodedJWT decodeToken(String token) throws GraphqlErrorException {
        Algorithm algorithm = Algorithm.HMAC256(env.getProperty("wasurena-task.jwt.secret"));
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(env.getProperty("wasurena-task.jwt.issuer"))
                    .build();
            return verifier.verify(token);
        } catch (Exception e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
        }
    }
}
