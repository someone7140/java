package com.api.wasrenaTaskApi2025.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError exceptionHandle(Exception e) {
        // GraphqlErrorが明示的に投げられた場合
        if (e instanceof GraphqlErrorException) {
            GraphqlErrorException graphqlErrorException = (GraphqlErrorException) e;
            return GraphQLError
                    .newError()
                    .errorType(graphqlErrorException.getErrorType())
                    .message(graphqlErrorException.getMessage())
                    .build();
        }

        // 認証が必要な関数で認証されてない場合
        if (e instanceof AccessDeniedException) {
            return GraphQLError
                    .newError()
                    .errorType(ErrorType.UNAUTHORIZED)
                    .message(e.getMessage())
                    .build();
        }

        // その他はシステムエラー
        return GraphQLError
                .newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(e.getMessage())
                .build();
    }
}
