package com.t0r.kestrelojbackendserviceclient.service;

import com.t0r.kestrelojbackendmodel.model.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author Towerrrr
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2025-03-18 19:51:38
*/
@FeignClient(
        name = "kestreloj-backend-question-service",
        contextId = "questionFeignClient",
        path = "/api/question/inner")
public interface QuestionFeignClient {

    @GetMapping("/get/id")
    Question getById(@RequestParam("questionId") long questionId);

}
