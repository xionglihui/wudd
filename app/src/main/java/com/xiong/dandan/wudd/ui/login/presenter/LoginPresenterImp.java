package com.xiong.dandan.wudd.ui.login.presenter;

import com.xiong.dandan.wudd.common.base.BaseMVPPersenter;
import com.xiong.dandan.wudd.libs.utils.StrUtils;
import com.xiong.dandan.wudd.ui.login.modle.LoginModleImp;
import com.xiong.dandan.wudd.ui.login.modle.UserInfo;
import com.xiong.dandan.wudd.ui.login.ui.ILoginView;

/**
 * Created by xionglh on 2017/1/4.
 */
public class LoginPresenterImp extends BaseMVPPersenter<ILoginView> implements ILoginPresenterListener  {


    private LoginModleImp mLoginModleImp;

    public LoginPresenterImp() {
        mLoginModleImp = new LoginModleImp();
    }

    public  void login() {
        String name = this.getView().getLoginName();
        String pwd = this.getView().getLoginPwd();
        if(StrUtils.isEmpty(name)){
            this.getView().errorLoginNameInfo("帐号不能为为空");
            return;
        }
        if(StrUtils.isEmpty(pwd)){
            this.getView().errorLoginNameInfo("密码不能为空");
            return;
        }
        this.getView().showProgress();
        mLoginModleImp.login(name, pwd, this);
    }


    @Override
    public void loginSuccess(UserInfo userInfo) {
        this.getView().dissProgress();
    }

    @Override
    public void loginFails() {
        this.getView().dissProgress();
    }
}
