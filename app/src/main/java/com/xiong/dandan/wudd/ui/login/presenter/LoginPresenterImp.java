package com.xiong.dandan.wudd.ui.login.presenter;

import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.common.base.BaseMVPPersenter;
import com.xiong.dandan.wudd.libs.tools.CortyTool;
import com.xiong.dandan.wudd.libs.tools.SystemLog;
import com.xiong.dandan.wudd.libs.utils.StrUtils;
import com.xiong.dandan.wudd.net.api.RequestCallBack;
import com.xiong.dandan.wudd.net.request.LoginParamsRequest;
import com.xiong.dandan.wudd.net.response.HttpExceptionBean;
import com.xiong.dandan.wudd.net.response.UserInfo;
import com.xiong.dandan.wudd.ui.login.modle.LoginModleImp;
import com.xiong.dandan.wudd.ui.login.ui.ILoginView;

import rx.Subscription;

/**
 * Created by xionglh on 2017/1/4.
 */
public class LoginPresenterImp extends BaseMVPPersenter<ILoginView> implements ILoginPresenterListener {


    private LoginModleImp mLoginModleImp;

    public LoginPresenterImp() {
        mLoginModleImp = new LoginModleImp();
    }

    @SuppressWarnings("unchecked")
    public void login() {
        String name = this.getView().getLoginName();
        String pwd = this.getView().getLoginPwd();
        if (StrUtils.isEmpty(name)) {
            this.getView().errorLoginNameInfo("帐号不能为为空");
            return;
        }
        if (StrUtils.isEmpty(pwd)) {
            this.getView().errorLoginNameInfo("密码不能为空");
            return;
        }
//        this.getView().showProgress();
        LoginParamsRequest loginParamsRequest = new LoginParamsRequest(name, CortyTool.encryptMD5(pwd));
        Subscription subscription = mApiWrapper.getLoginUserInfo(loginParamsRequest).subscribe(newMySubscriber(mRequestCallBack));
        mCompositeSubscription.add(subscription);
    }


    private RequestCallBack<UserInfo> mRequestCallBack = new RequestCallBack<UserInfo>() {
        @Override
        public void onNext(UserInfo userInfo) {
            if (userInfo == null)
                return;
            LoginPresenterImp.this.loginSuccess(userInfo);

        }

        @Override
        public void onError(HttpExceptionBean mHttpExceptionBean) {
            super.onError(mHttpExceptionBean);
            LoginPresenterImp.this.loginFails();
        }
    };


    @Override
    public void loginSuccess(UserInfo userInfo) {
        AppMyAplicition.genInstance().showToast("登录成功");
        SystemLog.D("userinfo.name", userInfo.getLoginName());
        this.getView().dissProgress();
    }

    @Override
    public void loginFails() {
        this.getView().dissProgress();
    }

}
