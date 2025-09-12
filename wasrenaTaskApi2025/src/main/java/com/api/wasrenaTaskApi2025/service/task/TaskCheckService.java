package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.queryResult.TaskCheckQueryResult;
import com.api.wasrenaTaskApi2025.model.domain.TaskCheckLogic;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskCheckResponse;
import com.api.wasrenaTaskApi2025.repository.TaskCheckRepository;
import com.api.wasrenaTaskApi2025.repository.UserAccountRepository;
import com.api.wasrenaTaskApi2025.service.common.DateService;
import com.api.wasrenaTaskApi2025.service.line.LineMessageApiService;

import graphql.GraphqlErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskCheckService {
    @Autowired
    Environment env;
    @Autowired
    DateService dateService;
    @Autowired
    LineMessageApiService lineMessageApiService;
    @Autowired
    TaskCheckRepository taskCheckRepository;
    @Autowired
    UserAccountRepository userAccountRepository;

    // チェック対象のタスク一覧を取得
    public List<TaskCheckResponse> getTaskCheckDisplayList(String userId) {
        // DBからチェック対象の一覧を取得
        var queryResultList = taskCheckRepository.findTaskCheckDisplay(userId, null);

        var nowDateTime = dateService.getNowDateTime();
        var exceededList = new ArrayList<TaskCheckResponse>();
        var notExceededList = new ArrayList<TaskCheckResponse>();
        for (var queryResult : queryResultList) {
            var response = convertResponseFromTaskCheckQueryResult(queryResult, nowDateTime);
            if (response.isExceedDeadLine()) {
                exceededList.add(response);
            } else {
                notExceededList.add(response);
            }
        }

        // 期限が超過しているリストを前にして返す
        return Stream.concat(
                exceededList.stream(),
                notExceededList.stream()).collect(Collectors.toList());
    }

    // チェック対象のLINE通知
    public boolean taskCheckNotify(String batchToken) {
        // トークンのチェック
        if (!env.getProperty("wasurena-task.jwt.batch.token").equals(batchToken)) {
            throw GraphqlErrorException
                    .newErrorException()
                    .errorClassification(ErrorType.UNAUTHORIZED)
                    .message("Invalid token")
                    .build();
        }

        // 通知対象をLINEユーザIDごとに格納
        var lineIdNotifyCheckMap = new HashMap<String, List<TaskCheckLogic>>();
        // ユーザID順にチェック対象を取得
        var queryResultList = taskCheckRepository.findTaskNotifyCheckDisplay();
        var nowDateTime = dateService.getNowDateTime();
        for (var queryResult : queryResultList) {
            // 期限判定を行うためドメインオブジェクトに
            var taskCheckLogic = TaskCheckLogic
                    .builder()
                    .id(queryResult.getId())
                    .title(queryResult.getTitle())
                    .deadLineCheck(queryResult.getDeadLineCheck())
                    .deadLineCheckSubSetting(queryResult.getDeadLineCheckSubSettingOptional())
                    .latestExecDateTime(queryResult.getLatestExecDateTimeOptional())
                    .ownerUserId(queryResult.getOwnerUserId())
                    .build();

            var isExceededDeadLine = taskCheckLogic.getIsExceededDeadLine(nowDateTime);
            // 期限超過の有無で登録するリストを分ける
            if (isExceededDeadLine) {
                if (lineIdNotifyCheckMap.containsKey(queryResult.getLineId())) {
                    lineIdNotifyCheckMap.put(
                            queryResult.getLineId(),
                            Stream.concat(lineIdNotifyCheckMap.get(
                                                    queryResult.getLineId()).stream(),
                                            Stream.of(taskCheckLogic))
                                    .collect(Collectors.toList()));
                } else {
                    lineIdNotifyCheckMap.put(
                            queryResult.getLineId(), List.of(taskCheckLogic)
                    );
                }
            }
        }

        for (Map.Entry<String, List<TaskCheckLogic>> entry : lineIdNotifyCheckMap.entrySet()) {
            // LINEメッセージ送信
            lineMessageApiService.sendMessageToUser(
                    entry.getKey(),
                    getExceededTasksMessage(entry.getValue())
            );
            // 通知日時を更新
            userAccountRepository.updateLineNotifyLatestDate(
                    entry.getValue().getFirst().getOwnerUserId(),
                    OffsetDateTime.now()
            );
        }

        return true;
    }

    // チェック対象一覧クエリの結果をベースにレスポンス形式に変換
    public TaskCheckResponse convertResponseFromTaskCheckQueryResult(
            TaskCheckQueryResult queryResult,
            OffsetDateTime nowDateTime
    ) {
        if (queryResult.getDeadLineCheckOptional().isEmpty()) {
            return new TaskCheckResponse(queryResult, Optional.empty(), false);
        } else {
            // 期限判定を行うためドメインオブジェクトに
            var taskCheckLogic = TaskCheckLogic
                    .builder()
                    .id(queryResult.getId())
                    .title(queryResult.getTitle())
                    .deadLineCheck(queryResult.getDeadLineCheck())
                    .deadLineCheckSubSetting(queryResult.getDeadLineCheckSubSettingOptional())
                    .latestExecDateTime(queryResult.getLatestExecDateTimeOptional())
                    .build();
            var isExceededDeadLine = taskCheckLogic.getIsExceededDeadLine(nowDateTime);
            var nextDeadLineDateTime = taskCheckLogic.getNextDeadLineDateTime(nowDateTime);

            return new TaskCheckResponse(queryResult, nextDeadLineDateTime, isExceededDeadLine);
        }
    }

    // 超過タスクのリストを元にメッセージの文字列を作成
    private String getExceededTasksMessage(List<TaskCheckLogic> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("以下が期限が超過しているタスクです。\n\n");

        for (var task : tasks) {
            sb.append("・" + task.getTitle() + "\n");
        }

        sb.append(env.getProperty("wasurena-task.frontend.domain"));

        return sb.toString();
    }
}
