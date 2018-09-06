package com.xionglihui.dandan.netlibrary.net.exception;

/**
 * Created by xionglh on 2018/9/6
 */
public enum  NetError {

    JSON_PWD_ERROR(10001, "JSON ERROR"),
    SYSTEM_ERROR(100, "System error");

    private final Integer code;
    private final String description;

    NetError(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
