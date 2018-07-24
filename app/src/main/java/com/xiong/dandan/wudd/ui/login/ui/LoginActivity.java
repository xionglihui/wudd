package com.xiong.dandan.wudd.ui.login.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding.view.RxView;
import com.xiong.dandan.common.util.StrUtils;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseCommonActivity;
import com.xiong.dandan.wudd.ui.login.contract.ILoginContract;
import com.xiong.dandan.wudd.ui.login.presenter.LoginPresenterImp;
import com.xiong.routerlibrary.url.ARouterPageUrl;

import java.util.concurrent.TimeUnit;

/**
 * login
 * Created by xionglh on 2017/1/4.
 */
@Route(path = ARouterPageUrl.ACTIVITY_LOGIN)
public class LoginActivity extends BaseCommonActivity<ILoginContract.View, LoginPresenterImp> implements ILoginContract.View {


     TextInputEditText mTxtName, mTxtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    protected void initViews() {
        mTxtName = (TextInputEditText) findViewById(R.id.txt_login_phone);
        mTxtPwd = (TextInputEditText) findViewById(R.id.txt_login_pwd);
        Button btnCommint = (Button) findViewById(R.id.btn_login_commint);
        RxView.clicks(btnCommint).throttleFirst(2000, TimeUnit.MILLISECONDS).subscribe(t -> {
            onClickLogin();
        });
    }

    private void onClickLogin() {
        String name = mTxtName.getText().toString();
        String pwd = mTxtPwd.getText().toString();
        if (StrUtils.isEmpty(name)) {
            mTxtName.setError("帐号不能为为空");
            return;
        }
        if (StrUtils.isEmpty(pwd)) {
            mTxtName.setError("密码不能为空");
            return;
        }
        mPersenter.login(name, pwd);
    }

    @Override
    protected LoginPresenterImp createPersenter() {
        return new LoginPresenterImp();
    }


    @Override
    public String getLoginName() {
        return mTxtName.getText().toString();
    }

    @Override
    public String getLoginPwd() {
        return mTxtPwd.getText().toString();
    }

    @Override
    public void cleanLoginName() {
        mTxtName.setText("");
    }

    @Override
    public void cleanLoginPwd() {
        mTxtPwd.setText("");
    }

    @Override
    public void errorLoginNameInfo(String errorInfo) {

    }

    @Override
    public void errorLoginPwdInfo(String errorInfo) {
        mTxtPwd.setError(errorInfo);
    }

    @Override
    public void dissProgress() {
        mProgressDialog.dismiss();

    }

    @Override
    public void showProgress() {
        mProgressDialog.show();

    }


}
