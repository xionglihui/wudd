package com.xiong.dandan.wudd.ui.login.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseTitleBarActivity;
import com.xiong.dandan.wudd.ui.login.presenter.LoginPresenterImp;

/**
 * login
 * Created by xionglh on 2017/1/4.
 */
public class LoginActivity extends BaseTitleBarActivity implements ILoginView ,View.OnClickListener{


    private TextInputEditText mTxtName, mTxtPwd;
    private LoginPresenterImp  mLoginPresenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTxtName = (TextInputEditText) findViewById(R.id.txt_login_phone);
        mTxtPwd = (TextInputEditText) findViewById(R.id.txt_login_pwd);
        findViewById(R.id.btn_login_commint).setOnClickListener(this);
        mLoginPresenterImp=new LoginPresenterImp(this);
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
        mTxtName.setError(errorInfo);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_commint:
                mLoginPresenterImp.login();
                break;
        }
    }
}
