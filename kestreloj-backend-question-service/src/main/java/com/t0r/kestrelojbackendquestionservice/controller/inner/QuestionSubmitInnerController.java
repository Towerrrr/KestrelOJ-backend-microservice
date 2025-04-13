package com.t0r.kestrelojbackendquestionservice.controller.inner;

import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;
import com.t0r.kestrelojbackendquestionservice.service.QuestionSubmitService;
import com.t0r.kestrelojbackendserviceclient.service.QuestionSubmitFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后端内部调用
 */
@RestController
@RequestMapping("/inner")
public class QuestionSubmitInnerController implements QuestionSubmitFeignClient {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Override
    @GetMapping("/question_submit/get/id")
    public QuestionSubmit getById(long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @Override
    @PostMapping("/question_submit/update")
    public boolean updateById(QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }
}
