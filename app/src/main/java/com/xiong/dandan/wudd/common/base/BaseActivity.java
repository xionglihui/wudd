package com.xiong.dandan.wudd.common.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.common.dialog.CustomProgressDialog;
import com.xiong.dandan.wudd.libs.utils.SharedPreferencesKey;
import com.xiong.dandan.wudd.libs.utils.SharedPreferencesUtil;
import com.xiong.dandan.wudd.net.request.BaseVolleyRequest;
import com.xiong.dandan.wudd.ui.common.CommonWebViewActivity;
import com.xiong.dandan.wudd.ui.common.ImageFetchActivity;

public class BaseActivity extends FragmentActivity {
    private static final String SAVE_USER_INFO_KEY = "SAVE_USER_INFO_KEY";
    public String TAG;
    /**
     * 请求服务器加载框
     */
    public CustomProgressDialog mProgressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        mProgressDialog = new CustomProgressDialog(this, false, false);
        AppAplicition.genInstance().mTotalActivity.add(this);
    }


    @Override
    protected void onDestroy() {
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
        AppAplicition.genInstance().mTotalActivity.remove(this);
        AppAplicition.genQueue().cancelAll(TAG);
        super.onDestroy();
    }


    public void showProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }


    /**
     * 启动相机+截图
     *
     * @param requestCode
     */
    public void startCameraCropActivity(int requestCode, boolean isHeaderCrop) {
        Intent intent = new Intent(this, ImageFetchActivity.class);
        intent.putExtra(ImageFetchActivity.ACTION_TYPE,
                ImageFetchActivity.ACTION_TYPE_CAMERA);
        intent.putExtra(ImageFetchActivity.IS_HEADER_CROP, isHeaderCrop);
        startActivityForResult(intent, requestCode);
    }

    public void startCameraCropActivity(int requestCode) {
        startCameraCropActivity(requestCode, true);
    }

    /**
     * 启动图册+截图
     *
     * @param requestCode
     */
    public void startPicturePickCropActivity(int requestCode, boolean isHeaderCrop) {
        Intent intent = new Intent(this, ImageFetchActivity.class);
        intent.putExtra(ImageFetchActivity.ACTION_TYPE,
                ImageFetchActivity.ACTION_TYPE_PICTURE);
        intent.putExtra(ImageFetchActivity.IS_HEADER_CROP, isHeaderCrop);
        startActivityForResult(intent, requestCode);
    }

    public void startPicturePickCropActivity(int requestCode) {
        startPicturePickCropActivity(requestCode, true);
    }

    public void gotoWebViewActivity(String title, String url) {
        Intent intent = new Intent(this, CommonWebViewActivity.class);
        intent.putExtra(CommonWebViewActivity.WEB_SHOW_TITLE_TAG, title);
        intent.putExtra(CommonWebViewActivity.WEB_SHOW_URL_TAG, url);
        startActivity(intent);
    }


    public void loginOut(){
        BaseVolleyRequest.SESSION_ID="";
        SharedPreferencesUtil.cleanStringValue(this, SharedPreferencesKey.LOGIN_SESSION_ID);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
