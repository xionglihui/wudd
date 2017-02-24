package com.xiong.dandan.wudd.ui.login.presenter;

import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.common.base.BaseMVPPersenter;
import com.xiong.dandan.wudd.libs.tools.CortyTool;
import com.xiong.dandan.wudd.net.api.RequestCallBack;
import com.xiong.dandan.wudd.net.request.LoginParamsRequest;
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
    public void login(String name ,String pwd) {

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
            mLoginModleImp.savaDataInfo(userInfo);
            AppMyAplicition.genInstance().showToast("登录成功");

        }
    };


    @Override
    public void loginSuccess(UserInfo userInfo) {
        this.getView().dissProgress();
    }

    @Override
    public void loginFails() {
        this.getView().dissProgress();
    }

}
