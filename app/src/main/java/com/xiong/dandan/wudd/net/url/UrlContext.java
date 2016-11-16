package com.xiong.dandan.wudd.net.url;


import com.xiong.dandan.wudd.Config;

public enum UrlContext {

    /**登录**/
    Url_Login("/system/login.whtml");

    private String context;

    UrlContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return Config.COMMON_URL + context;
    }

    @Override
    public String toString() {
        return this.context;
    }

}