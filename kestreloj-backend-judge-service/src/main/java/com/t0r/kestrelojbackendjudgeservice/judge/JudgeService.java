package com.t0r.kestrelojbackendjudgeservice.judge;

import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface JudgeService {

    /**
     * 判题
     * @param questionId
     * @return
     */
    QuestionSubmit doJudge(long questionId);
}
