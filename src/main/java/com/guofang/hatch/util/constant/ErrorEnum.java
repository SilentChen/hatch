package com.guofang.hatch.util.constant;

public enum  ErrorEnum {
    E_400("400", "请求处理异常, 请稍后重试"),
    E_500("500", "请求方式有误， 请检查"),
    E_501("501", "请求路径不存在"),
    E_502("500", "权限不足"),
    E_10001("10001", "参数错误"),
    E_20001("20001", "创建失败"),
    E_20002("20002", "更新失败"),
    E_20011("20011", "登陆已过期,请重新登陆"),
    E_30001("20002", "两次密码不一致");

    private String errorCode;
    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg  = errorMsg;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
