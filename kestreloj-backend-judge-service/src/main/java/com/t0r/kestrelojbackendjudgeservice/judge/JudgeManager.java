package com.t0r.kestrelojbackendjudgeservice.judge;

import com.t0r.kestrelojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.t0r.kestrelojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.t0r.kestrelojbackendjudgeservice.judge.strategy.JudgeContext;
import com.t0r.kestrelojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.t0r.kestrelojbackendmodel.model.codesandbox.JudgeInfo;
import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {
    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("Java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
