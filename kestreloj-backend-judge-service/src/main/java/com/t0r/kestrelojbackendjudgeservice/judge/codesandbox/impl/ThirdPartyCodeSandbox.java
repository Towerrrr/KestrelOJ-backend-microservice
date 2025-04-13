package com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.impl;


import com.t0r.kestrelojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("执行第三方代码沙箱");
        return null;
    }
}
