package com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.impl;


import com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.t0r.kestrelojbackendmodel.model.codesandbox.JudgeInfo;
import com.t0r.kestrelojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.t0r.kestrelojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("执行示例代码沙箱");

        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setErrorMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
