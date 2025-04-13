package com.t0r.kestrelojbackendserviceclient.service;

import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author Towerrrr
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2025-03-18 19:54:13
*/
@FeignClient(
        name = "kestreloj-backend-question-service",
        contextId = "questionSubmitFeignClient",
        path = "/api/question/inner")
public interface QuestionSubmitFeignClient {

    @GetMapping("/question_submit/get/id")
    QuestionSubmit getById(@RequestParam("questionId") long questionSubmitId);

    @PostMapping("/question_submit/update")
    boolean updateById(@RequestBody QuestionSubmit questionSubmit);
}
