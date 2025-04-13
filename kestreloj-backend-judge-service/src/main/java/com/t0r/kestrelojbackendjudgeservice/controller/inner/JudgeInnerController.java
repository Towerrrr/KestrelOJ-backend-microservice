package com.t0r.kestrelojbackendjudgeservice.controller.inner;

import com.t0r.kestrelojbackendjudgeservice.judge.JudgeService;
import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;
import com.t0r.kestrelojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后端内部调用
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    private JudgeService judgeService;

    /**
     * 判题
     * @param questionId
     * @return
     */
    @Override
    @PostMapping("/do")
    public QuestionSubmit doJudge(long questionId) {
        return judgeService.doJudge(questionId);
    }
}
