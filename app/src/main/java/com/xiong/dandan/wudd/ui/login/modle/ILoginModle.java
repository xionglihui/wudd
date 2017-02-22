package com.xiong.dandan.wudd.ui.login.modle;

import com.xiong.dandan.wudd.net.response.UserInfo;

/**
 * Created by xionglh on 2017/1/4.
 */
public interface ILoginModle {
    void savaUserInfo(UserInfo userInfo);

    UserInfo getUserInfo();
}
