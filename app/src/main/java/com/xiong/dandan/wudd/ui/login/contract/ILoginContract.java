package com.xiong.dandan.wudd.ui.login.contract;

import com.xiong.dandan.wudd.common.base.IBasePersenter;
import com.xiong.dandan.wudd.common.base.IBaseView;

/**
 * 登录协议类
 * Created by xionglh on 2017/3/10.
 */
public interface ILoginContract {

    interface View extends IBaseView {
        String getLoginName();

        String getLoginPwd();

        void cleanLoginName();

        void cleanLoginPwd();

        void errorLoginNameInfo(String errorInfo);

        void errorLoginPwdInfo(String errorInfo);

    }

    interface Presenter extends IBasePersenter {

    }

}
