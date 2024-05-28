package com.placeNote.placeNoteApi2024.model.auth;

import graphql.GraphqlErrorException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {
    @Autowired
    RequestManager requestManager;

    @Before("@annotation(com.placeNote.placeNoteApi2024.annotation.loginStatus.LoggedInOnly)")
    public void checkLoggedInOnly() throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        if (userAccountId == null) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.FORBIDDEN)
                    .message("Not Login")
                    .build();
        }
    }

    @Before("@annotation(com.placeNote.placeNoteApi2024.annotation.loginStatus.NotLoggedInOnly)")
    public void checkNotLoggedInOnly() throws GraphqlErrorException {
        String userAccountId = requestManager.getUserAccountIdSession();
        if (userAccountId != null) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.FORBIDDEN)
                    .message("Already Login")
                    .build();
        }
    }
}
