package com.api.wasrenaTaskApi2025.service.line;

import com.fasterxml.uuid.Generators;
import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.PushMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;
import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LineMessageApiService {
    @Autowired
    Environment env;
    @Autowired
    private MessagingApiClient messagingApiClient; // LINE Messaging APIのクライアント

    // LINEメッセージを送信
    public void sendMessageToUser(String userLineId, String messageText) {
        TextMessage textMessage = new TextMessage(messageText);
        // プッシュメッセージリクエストを作成
        var pushMessageRequest = new PushMessageRequest.Builder(
                userLineId,
                List.of(textMessage)
        ).build();

        try {
            // メッセージを送信
            messagingApiClient.pushMessage(Generators.timeBasedEpochGenerator().generate(), pushMessageRequest).get();
        } catch (InterruptedException | ExecutionException e) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.INTERNAL_ERROR)
                    .message(e.getMessage())
                    .build();
        }
    }
}
