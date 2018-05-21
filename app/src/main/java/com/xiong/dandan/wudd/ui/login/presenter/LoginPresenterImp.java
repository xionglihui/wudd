package com.xiong.dandan.wudd.ui.login.presenter;

import com.xiong.dandan.utilslibrary.security.CortyTool;
import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.common.base.BaseCommonPersenter;
import com.xiong.dandan.wudd.net.request.LoginParamsRequest;
import com.xiong.dandan.wudd.net.response.UserInfo;
import com.xiong.dandan.wudd.ui.login.contract.ILoginContract;
import com.xiong.dandan.wudd.ui.login.modle.LoginModleImp;
import com.xionglihui.dandan.netlibrary.net.RequestCallBack;

import rx.Subscription;

/**
 * Created by xionglh on 2017/1/4.
 */
public class LoginPresenterImp extends BaseCommonPersenter<ILoginContract.View>  {


    private LoginModleImp mLoginModleImp;

    public LoginPresenterImp() {
        mLoginModleImp = new LoginModleImp();
    }

    @SuppressWarnings("unchecked")
    public void login(String name ,String pwd) {

        this.getView().showProgress();
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



}
