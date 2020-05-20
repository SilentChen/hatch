package com.guofang.hatch.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.guofang.hatch.util.CommonUtil;
import com.guofang.hatch.util.constant.Constant;
import com.guofang.hatch.util.constant.ErrorEnum;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(value = Exception.class)
    public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        if(e.getStackTrace().length > 0){
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "undefinded error file" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + " : " + lineNumber;
        }
        JSONObject jsObject = new JSONObject();
        jsObject.put(Constant.JSON_RESPONSE_CODE, ErrorEnum.E_400.getErrorCode());
        jsObject.put(Constant.JSON_RESPONSE_MESSAGE, ErrorEnum.E_400.getErrorMsg());
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + " Error Position: " + errorPosition);
        jsObject.put(Constant.JSON_RESPONSE_INFO, errorObject);
        logger.error("Exception", e);
        return jsObject;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodNotSupportedException() {
        return CommonUtil.errorJson(ErrorEnum.E_500);
    }

    @ExceptionHandler(CommonJsonException.class)
    public JSONObject commonJsonException(CommonJsonException commonJsonException) {
        return commonJsonException.getResultJson();
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public JSONObject unauthenticatedException() {
        return CommonUtil.errorJson(ErrorEnum.E_20011);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public JSONObject unauthorizedException(){
        return CommonUtil.errorJson(ErrorEnum.E_502);
    }
}
