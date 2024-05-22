package com.placeNote.placeNoteApi2024.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQLExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError exceptionHandle(Exception e) {
        if (e instanceof GraphqlErrorException) {
            GraphqlErrorException graphqlErrorException = (GraphqlErrorException) e;
            return GraphQLError
                    .newError()
                    .errorType(graphqlErrorException.getErrorType())
                    .message(graphqlErrorException.getMessage())
                    .build();
        }
        return GraphQLError
                .newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(e.getMessage())
                .build();
    }
}
