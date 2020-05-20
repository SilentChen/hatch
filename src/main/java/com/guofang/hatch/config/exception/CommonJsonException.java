package com.guofang.hatch.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.util.CommonUtil;
import com.guofang.hatch.util.constant.ErrorEnum;

public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }

    public JSONObject getResultJson() {
        return this.resultJson;
    }
}
