package com.xiong.dandan.wudd.ui.login.modle;

import com.xiong.dandan.wudd.ui.login.presenter.ILoginPresenterListener;

/**
 * Created by xionglh on 2017/1/4.
 */
public interface ILoginModle {
    void login(String name, String pwd, ILoginPresenterListener  iLoginPresenterListener);
}
