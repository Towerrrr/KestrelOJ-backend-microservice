package com.t0r.kestrelojbackendquestionservice.controller.inner;

import com.t0r.kestrelojbackendmodel.model.entity.Question;
import com.t0r.kestrelojbackendquestionservice.service.QuestionService;
import com.t0r.kestrelojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后端内部调用
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;


    @Override
    @GetMapping("/get/id")
    public Question getById(long questionId) {
        return questionService.getById(questionId);
    }
}
