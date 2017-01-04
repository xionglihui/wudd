package com.xiong.dandan.wudd.ui.login.presenter;

import com.xiong.dandan.wudd.ui.login.modle.UserInfo;

/**
 * Created by xionglh on 2017/1/4.
 */
public interface ILoginPresenterListener {
    void loginSuccess(UserInfo  userInfo);
    void loginFails();
}
