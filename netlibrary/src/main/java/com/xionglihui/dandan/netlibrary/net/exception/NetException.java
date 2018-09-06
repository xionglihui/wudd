package com.xionglihui.dandan.netlibrary.net.exception;

/**
 * Created by xionglh on 2017/2/23.
 */
public class NetException  extends RuntimeException {

    private Integer errorCode;
    private String errorDesc;

    public NetException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = NetError.SYSTEM_ERROR.getCode();
        this.errorDesc = message;
    }

    public NetException(Throwable cause) {
        super(cause);
        this.errorCode = NetError.SYSTEM_ERROR.getCode();
        this.errorDesc = NetError.SYSTEM_ERROR.getDescription();
    }

    public NetException(NetError errEnum) {
        this(errEnum.getCode(), errEnum.getDescription());
    }

    public NetException(NetError errEnum, String message) {
        this(errEnum.getCode(), message);
    }

    public NetException(Integer errCode, String message) {
        super(message);
        this.errorCode = errCode;
        this.errorDesc = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

}
