package com.t0r.kestrelojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.t0r.kestrelojbackendcommon.common.ErrorCode;
import com.t0r.kestrelojbackendcommon.exception.BusinessException;
import com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.CodeSandBoxFactory;
import com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.CodeSandBoxProxy;
import com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.t0r.kestrelojbackendjudgeservice.judge.strategy.JudgeContext;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.t0r.kestrelojbackendmodel.model.codesandbox.JudgeInfo;
import com.t0r.kestrelojbackendmodel.model.dto.question.JudgeCase;
import com.t0r.kestrelojbackendmodel.model.entity.Question;
import com.t0r.kestrelojbackendmodel.model.entity.QuestionSubmit;
import com.t0r.kestrelojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.t0r.kestrelojbackendserviceclient.service.QuestionFeignClient;
import com.t0r.kestrelojbackendserviceclient.service.QuestionSubmitFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    private static final Logger log = LoggerFactory.getLogger(JudgeServiceImpl.class);
    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private QuestionSubmitFeignClient questionSubmitFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type:example}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 传入题目提交id，获取对应题目、提交信息
        QuestionSubmit questionSubmit = questionSubmitFeignClient.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 如果题目提交状态不为等待中，不用重复执行
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题中");
        }
        // 更改题目状态为判题中，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitFeignClient.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        // 调用沙箱，获取执行结果
        CodeSandbox codeSandbox = CodeSandBoxFactory.newInstance(type);
        codeSandbox = new CodeSandBoxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        // 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        // 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        update = questionSubmitFeignClient.updateById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }
        QuestionSubmit questionSubmitResult = questionSubmitFeignClient.getById(questionId);
        return questionSubmitResult;
    }
}
