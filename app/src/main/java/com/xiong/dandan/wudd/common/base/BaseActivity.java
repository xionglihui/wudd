package com.xiong.dandan.wudd.common.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.common.ActivityPageManager;
import com.xiong.dandan.wudd.common.dialog.CustomProgressDialog;
import com.xiong.dandan.wudd.libs.utils.SharedPreferencesKey;
import com.xiong.dandan.wudd.libs.utils.SharedPreferencesUtil;
import com.xiong.dandan.wudd.net.request.BaseVolleyRequest;
import com.xiong.dandan.wudd.ui.common.CommonWebViewActivity;
import com.xiong.dandan.wudd.ui.common.ImageFetchActivity;

public class BaseActivity extends FragmentActivity {

    private static final String SAVE_USER_INFO_KEY = "SAVE_USER_INFO_KEY";

    public static final String SKIP_ACTIVITY_WHERE_FROM = "fromWhere";

    /**
     * 来自哪个 页面
     */
    protected String fromWhere;
    /**
     * 页面布局的 根view
     */
    protected View mContentView;

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
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mContentView = view;
        //初始化页面
        init();
    }

    /**
     * 初始化页面
     */
    public void init() {
        initFromWhere();
    }


    protected void initFromWhere() {
        if (null != getIntent().getExtras()) {
            if (getIntent().getExtras().containsKey(SKIP_ACTIVITY_WHERE_FROM)) {
                fromWhere = getIntent().getStringExtra(SKIP_ACTIVITY_WHERE_FROM);
            }
        }
    }

    public String getFromWhere() {
        return fromWhere;
    }

    public void skipAct(Intent intent) {
        intent.putExtra(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(int requestCode, Intent intent) {
        intent.putExtra(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName());
        startActivityForResult(intent, requestCode);
    }


    public void skipAct(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(SKIP_ACTIVITY_WHERE_FROM, getClass().getSimpleName());
        intent.setFlags(flags);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityPageManager.unbindReferences(mContentView);
        ActivityPageManager.getInstance().removeActivity(this);
        mContentView = null;
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
        AppAplicition.genInstance().mTotalActivity.remove(this);
        AppAplicition.genQueue().cancelAll(TAG);
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
        skipAct(requestCode, intent);
    }

    public void startPicturePickCropActivity(int requestCode) {
        startPicturePickCropActivity(requestCode, true);
    }

    public void gotoWebViewActivity(String title, String url) {
        Intent intent = new Intent(this, CommonWebViewActivity.class);
        intent.putExtra(CommonWebViewActivity.WEB_SHOW_TITLE_TAG, title);
        intent.putExtra(CommonWebViewActivity.WEB_SHOW_URL_TAG, url);
        skipAct(intent);
    }


    public void loginOut() {
        BaseVolleyRequest.SESSION_ID = "";
        SharedPreferencesUtil.cleanStringValue(this, SharedPreferencesKey.LOGIN_SESSION_ID);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
