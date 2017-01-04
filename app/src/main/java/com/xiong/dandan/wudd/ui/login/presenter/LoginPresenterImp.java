package com.xiong.dandan.wudd.ui.login.presenter;

import com.xiong.dandan.wudd.libs.utils.StrUtils;
import com.xiong.dandan.wudd.ui.login.modle.LoginModleImp;
import com.xiong.dandan.wudd.ui.login.modle.UserInfo;
import com.xiong.dandan.wudd.ui.login.ui.ILoginView;

/**
 * Created by xionglh on 2017/1/4.
 */
public class LoginPresenterImp implements ILoginPresenterListener {

    private ILoginView mIloginView;

    private LoginModleImp mLoginModleImp;

    public LoginPresenterImp(ILoginView iLoginView) {
        mLoginModleImp = new LoginModleImp();
        this.mIloginView = iLoginView;
    }

    public  void login() {
        String name = mIloginView.getLoginName();
        String pwd = mIloginView.getLoginPwd();
        if(StrUtils.isEmpty(name)){
            mIloginView.errorLoginNameInfo("帐号不能为为空");
            return;
        }
        if(StrUtils.isEmpty(pwd)){
            mIloginView.errorLoginNameInfo("密码不能为空");
            return;
        }
        mIloginView.showProgress();
        mLoginModleImp.login(name, pwd, this);
    }


    @Override
    public void loginSuccess(UserInfo userInfo) {
        mIloginView.dissProgress();
    }

    @Override
    public void loginFails() {
        mIloginView.dissProgress();
    }
}
