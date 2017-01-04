package com.xiong.dandan.wudd.ui.login.ui;

/**
 * Created by xionglh on 2017/1/4.
 */
public interface ILoginView {

    String getLoginName();

    String getLoginPwd();

    void cleanLoginName();

    void cleanLoginPwd();

    void errorLoginNameInfo(String errorInfo);

    void errorLoginPwdInfo(String errorInfo);

    void dissProgress();
    void showProgress();


}
