package com.xiong.dandan.wudd.ui.login.modle;

import com.xiong.dandan.wudd.net.response.CommonResponse;

/**
 * Created by xionglh on 2017/1/4.
 */
public class UserLoginInfo extends CommonResponse {

    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
