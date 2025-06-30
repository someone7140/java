package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.service.line.LineHandlerService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LineBotController {

    @Autowired
    private LineHandlerService lineHandlerService;

    /**
     * LINEからのWebhookリクエスト受信エンドポイント
     */
    @PostMapping("/line_callback")
    public ResponseEntity<String> handleWebhook(@RequestBody JsonNode requestBody) {
        try {
            JsonNode eventsNode = requestBody.get("events");
            if (eventsNode != null && eventsNode.isArray()) {
                for (JsonNode eventNode : eventsNode) {
                    String type = eventNode.get("type").asText();

                    // ユーザーIDと返信用トークンを抽出
                    JsonNode sourceNode = eventNode.get("source");
                    String userId = sourceNode != null ? sourceNode.get("userId").asText() : null;
                    String replyToken = eventNode.get("replyToken") != null ? eventNode.get("replyToken").asText() : null;

                    if ("follow".equals(type)) {
                        lineHandlerService.followBotAccount(userId, replyToken);
                    } else if ("unfollow".equals(type)) {
                        lineHandlerService.handleUnfollowEvent(userId);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return ResponseEntity.ok().build();
    }
}
