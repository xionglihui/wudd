package com.xiong.dandan.wudd.net.request;

/**
 * 请求登录
 * Created by xionglh on 2017/1/4.
 */
public class LoginParamsRequest {

    private String mobile;
    private String loginPassword;

    public LoginParamsRequest(String mobile, String loginPassword){
        this.mobile=mobile;
        this.loginPassword=loginPassword;
    }

}
