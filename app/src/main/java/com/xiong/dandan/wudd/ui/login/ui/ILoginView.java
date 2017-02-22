package com.xiong.dandan.wudd.ui.login.ui;

import com.xiong.dandan.wudd.common.base.BaseView;

/**
 * Created by xionglh on 2017/1/4.
 */
public interface ILoginView extends BaseView {

    String getLoginName();

    String getLoginPwd();

    void cleanLoginName();

    void cleanLoginPwd();

    void errorLoginNameInfo(String errorInfo);

    void errorLoginPwdInfo(String errorInfo);


}
