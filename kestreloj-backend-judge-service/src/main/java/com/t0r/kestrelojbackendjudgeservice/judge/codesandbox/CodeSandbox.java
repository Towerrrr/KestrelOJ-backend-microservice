package com.t0r.kestrelojbackendjudgeservice.judge.codesandbox;


import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.t0r.kestrelojbackendmodel.model.codesandbox.ExecuteCodeResponse;

public interface CodeSandbox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

}
