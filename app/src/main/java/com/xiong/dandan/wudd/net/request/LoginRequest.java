package com.xiong.dandan.wudd.net.request;

import com.android.volley.Request;

import java.util.List;
import java.util.Map;

/**
 * 请求登录
 * Created by xionglh on 2017/1/4.
 */
public class LoginRequest  extends  BaseVolleyRequest{

    private String mName;
    private String mPwd;
    public LoginRequest(String name,String pwd){
        this.mName=name;
        this.mPwd=pwd;
    }

    @Override
    protected int toHttpMethod() {
        return Request.Method.POST;
    }

    @Override
    protected String toRequestURL() {
        return null;
    }

    @Override
    protected void toHttpRequestParams(Map<String, Object> params) {
        params.put("name",mName);
        params.put("pwd",mPwd);
    }

    @Override
    protected void toHttpRequestHeader(Map<String, String> header) {

    }

    @Override
    protected List<String> toUploadFilePath() {
        return null;
    }
}
