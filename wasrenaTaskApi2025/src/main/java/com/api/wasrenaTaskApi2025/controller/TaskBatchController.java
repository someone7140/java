package com.api.wasrenaTaskApi2025.controller;

import com.api.wasrenaTaskApi2025.service.task.TaskCheckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskBatchController {
    @Autowired
    TaskCheckService taskCheckService;

    // 期限チェックの結果をLINE通知するバッチ
    @MutationMapping
    public boolean executeTaskCheckNotifyBatch(@Argument String batchToken) {
        return taskCheckService.taskCheckNotify(batchToken);
    }

    // バッチで生存確認する用のクエリ
    @QueryMapping
    public boolean pingCheckExecute() {
        return true;
    }
}
