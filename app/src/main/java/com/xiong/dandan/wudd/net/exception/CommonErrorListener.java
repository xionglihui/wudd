package com.xiong.dandan.wudd.net.exception;

import com.android.volley.VolleyError;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseActivity;
import com.xiong.dandan.wudd.libs.utils.StrUtils;


/**
 * 常用请求错误回调  toast 错误信息
 * Created by wangyy on 2016/1/7.
 */
public class CommonErrorListener extends BaseErrorListener {

    private BaseActivity mActivity;

    public CommonErrorListener(BaseActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onErrorResponseListener(VolleyError volleyError) {
        if (mActivity != null)
            mActivity.dismissProgressDialog();
        String msg = volleyError.getMessage();
        if (StrUtils.isEmpty(msg)) {
            msg = AppAplicition.genInstance().getString(R.string.exception_json_error);
        }
        AppAplicition.genInstance().showToast(msg);
    }

    @Override
    public void authFailureError() {
        for(BaseActivity baseActivity:AppAplicition.genInstance().mTotalActivity){
            baseActivity.finish();
        }
        mActivity.loginOut();
//        mActivity.startActivity(new Intent(mActivity, LoginActivity.class));
    }
}
