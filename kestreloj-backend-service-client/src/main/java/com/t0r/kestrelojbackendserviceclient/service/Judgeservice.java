package com.t0r.kestrelojbackendserviceclient.service;


import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;

/**
 * 判题服务
 */
public interface Judgeservice {

    /**
     * 判题
     * @param questionId
     * @return
     */
    QuestionSubmit doJudge(long questionId);
}
