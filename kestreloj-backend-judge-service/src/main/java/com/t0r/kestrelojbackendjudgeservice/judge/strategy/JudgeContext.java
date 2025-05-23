package com.t0r.kestrelojbackendjudgeservice.judge.strategy;

import com.t0r.kestrelojbackendmodel.model.codesandbox.JudgeInfo;
import com.t0r.kestrelojbackendmodel.model.dto.question.JudgeCase;
import com.t0r.kestrelojbackendmodel.model.entity.Question;
import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在上下文要传递的参数）
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private List<JudgeCase> judgeCaseList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
