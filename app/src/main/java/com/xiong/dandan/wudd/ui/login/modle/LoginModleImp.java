package com.xiong.dandan.wudd.ui.login.modle;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.entity.BaseEntity;
import com.xiong.dandan.wudd.libs.utils.StrUtils;
import com.xiong.dandan.wudd.net.exception.BaseErrorListener;
import com.xiong.dandan.wudd.net.request.LoginRequest;
import com.xiong.dandan.wudd.ui.login.presenter.ILoginPresenterListener;

/**
 * Created by xionglh on 2017/1/4.
 */
public class LoginModleImp implements ILoginModle {

    @Override
    public void login(String name, String pwd, ILoginPresenterListener iLoginPresenterListener) {
        new LoginRequest(name, pwd).doRequest(LoginModleImp.class.getName(), UserInfo.class, new LoginSuccessListener(iLoginPresenterListener), new LoginErrorListener(iLoginPresenterListener) );
    }


    private class LoginSuccessListener implements Response.Listener<BaseEntity> {

        private ILoginPresenterListener mILoginPresenterListener;

        public LoginSuccessListener(ILoginPresenterListener iLoginPresenterListener) {
            this.mILoginPresenterListener = iLoginPresenterListener;
        }


        @Override
        public void onResponse(BaseEntity baseEntity) {
            UserInfo userInfo = (UserInfo) baseEntity;
            if (userInfo != null)
                mILoginPresenterListener.loginSuccess(userInfo);
        }
    }

    private class LoginErrorListener  extends BaseErrorListener{


        private ILoginPresenterListener mILoginPresenterListener;

        public LoginErrorListener(ILoginPresenterListener iLoginPresenterListener) {
            this.mILoginPresenterListener = iLoginPresenterListener;
        }


        @Override
        public void authFailureError() {

        }

        @Override
        public void onErrorResponseListener(VolleyError volleyError) {
            String msg = volleyError.getMessage();
            if (StrUtils.isEmpty(msg)) {
                msg = AppAplicition.genInstance().getString(R.string.exception_json_error);
            }
            AppAplicition.genInstance().showToast(msg);
            mILoginPresenterListener.loginFails();
        }
    }

}
