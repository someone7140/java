package com.api.wasrenaTaskApi2025.service.task;

import com.api.wasrenaTaskApi2025.model.db.queryResult.TaskCheckQueryResult;
import com.api.wasrenaTaskApi2025.model.domain.TaskCheckLogic;
import com.api.wasrenaTaskApi2025.model.graphql.task.TaskCheckDisplayResponse;
import com.api.wasrenaTaskApi2025.repository.TaskCheckRepository;
import com.api.wasrenaTaskApi2025.service.common.DateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TaskCheckService {
    @Autowired
    DateService dateService;
    @Autowired
    TaskCheckRepository taskCheckRepository;

    // チェック対象のタスク一覧を取得
    public List<TaskCheckDisplayResponse> getTaskCheckDisplayList(String userId) {
        // DBからチェック対象の一覧を取得
        var queryResultList = taskCheckRepository.findTaskCheckDisplay(userId);

        var nowDateTime = dateService.getNowDateTime();
        var exceededList = new ArrayList<TaskCheckDisplayResponse>();
        var notExceededList = new ArrayList<TaskCheckDisplayResponse>();
        // 期限超過の判定と次回実行期限の取得
        for (var queryResult : queryResultList) {
            if (queryResult.getDeadLineCheckOptional().isEmpty()) {
                notExceededList.add(
                        convertResponseFromTaskCheckQueryResult(
                                queryResult,
                                Optional.empty(),
                                false)
                );
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
                // 期限超過しているか
                var isExceededDeadLine = taskCheckLogic.getIsExceededDeadLine(nowDateTime);
                // 次回実行期限
                var nextDeadLineDateTime = taskCheckLogic.getNextDeadLineDateTime(nowDateTime);

                if (isExceededDeadLine) {
                    exceededList.add(
                            convertResponseFromTaskCheckQueryResult(
                                    queryResult,
                                    nextDeadLineDateTime,
                                    true)
                    );
                } else {
                    notExceededList.add(
                            convertResponseFromTaskCheckQueryResult(
                                    queryResult,
                                    nextDeadLineDateTime,
                                    false)
                    );
                }
            }
        }

        // 期限が超過しているリストを前にして返す
        return Stream.concat(
                exceededList.stream(),
                notExceededList.stream()).collect(Collectors.toList());
    }

    // クエリの結果をベースにレスポンス形式に変換
    private TaskCheckDisplayResponse convertResponseFromTaskCheckQueryResult(
            TaskCheckQueryResult queryResult,
            Optional<OffsetDateTime> nextDeadLineDateTime,
            Boolean isExceedDeadLine) {

        return new TaskCheckDisplayResponse(
                queryResult.getId(),
                queryResult.getTitle(),
                queryResult.isDisplayFlag(),
                queryResult.isNotificationFlag(),
                queryResult.getCategoryIdOptional(),
                queryResult.getCategoryNameOptional(),
                queryResult.getDeadLineCheckOptional(),
                queryResult.getDeadLineCheckSubSettingOptional(),
                queryResult.getDetailOptional(),
                queryResult.getLatestExecDateTimeOptional(),
                nextDeadLineDateTime,
                isExceedDeadLine
        );
    }
}
