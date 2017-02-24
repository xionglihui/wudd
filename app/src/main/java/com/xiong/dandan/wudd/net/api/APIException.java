package com.xiong.dandan.wudd.net.api;

/**
 * Created by xionglh on 2017/2/23.
 */
public class APIException extends Exception {

    public String code;
    public String message;

    public APIException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
