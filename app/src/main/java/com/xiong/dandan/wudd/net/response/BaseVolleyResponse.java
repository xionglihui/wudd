package com.xiong.dandan.wudd.net.response;


import com.xiong.dandan.wudd.entity.BaseEntity;

/**
 * Created by wangyy on 2015/7/16.
 */
public class BaseVolleyResponse extends BaseEntity {
    /**
     * message : 操作成功!
     * success : true
     */
    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseEntity saveInfo(BaseVolleyResponse response) {
        return response;
    }
}
