package com.api.wasrenaTaskApi2025.service.line;

import com.api.wasrenaTaskApi2025.repository.TaskDefinitionRepository;
import com.api.wasrenaTaskApi2025.repository.UserAccountRepository;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LineHandlerService {

    @Autowired
    private Environment env;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private TaskDefinitionRepository taskDefinitionRepository;
    @Autowired
    private MessagingApiClient messagingApiClient; // LINE Messaging APIのクライアント

    // ボットがフォローされた時の処理をハンドリング
    public void followBotAccount(String lineUserID, String replyToken) {
        String messageText; // LINEに送信するメッセージの内容

        // DBに登録済みか確認
        var registeredUserEntity = userAccountRepository.findByLineId(lineUserID);

        if (registeredUserEntity.isEmpty()) {
            // 未登録の場合のメッセージ
            messageText = String.format(
                    "会員登録されていません。一度ブロックをして頂いた後、こちらより会員登録をして頂き再度友達追加をお願いします。\n%s%s",
                    env.getProperty("wasurena-task.frontend.domain"),
                    env.getProperty("wasurena-task.line.register.redirect.path") // typo修正: regsiter -> register
            );
        } else {
            try {
                var entity = registeredUserEntity.get();
                // LINEボットのフォローフラグをtrueにする。
                entity.setIsLineBotFollow(true);
                userAccountRepository.save(entity);
                messageText = "通知登録をしました。"; // 成功時のメッセージ
            } catch (Exception e) {
                // DB更新エラー時のメッセージ
                messageText = "処理エラーが発生しました。一度ブロックをして頂いた後、再度友達登録お願いします。";
            }
        }

        // replyTokenが存在する場合のみメッセージを送信
        if (replyToken != null && !replyToken.isEmpty()) {
            TextMessage replyMessage = new TextMessage(messageText);
            replyMessageToUser(replyToken, Collections.singletonList(replyMessage));
        }
    }

    /**
     * ボットがアンフォローされた時の処理をハンドリングします。
     * ユーザーIDを受け取り、DB処理を行います。アンフォロー時には通常メッセージは返しません。
     */
    public void handleUnfollowEvent(String lineUserID) {
        // DBに登録済みユーザか確認
        var registeredUserEntity = userAccountRepository.findByLineId(lineUserID);
        if (registeredUserEntity.isEmpty()) {
            return; // 登録されていない場合は何もしない
        }

        // タスクのLINE通知をOFFにする
        taskDefinitionRepository.updateNotificationFlagOff(registeredUserEntity.get().getId());
        // ユーザーのLINEボットのフォローフラグをfalseにする。
        var entity = registeredUserEntity.get();
        entity.setIsLineBotFollow(false);
        userAccountRepository.save(entity);
        // アンフォローイベントに対しては、LINEにメッセージは送信しない（ユーザーがブロックしているため、メッセージが届かないため）
    }

    /**
     * LINEユーザーに応答メッセージを送信する共通プライベートメソッド。
     */
    private void replyMessageToUser(String replyToken, List<Message> messages) {
        try {
            ReplyMessageRequest replyMessageRequest = new ReplyMessageRequest(replyToken, messages, false); // falseは通知ON/OFF設定
            messagingApiClient.replyMessage(replyMessageRequest).get(); // 非同期実行の結果を待つ
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 中断状態を再設定
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while sending reply message: " + e.getMessage());
        }
    }
}