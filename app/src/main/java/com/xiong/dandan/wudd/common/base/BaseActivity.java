package com.xiong.dandan.wudd.common.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.common.ActivityPageManager;
import com.xiong.dandan.wudd.common.dialog.CustomProgressDialog;
import com.xiong.dandan.wudd.net.api.APIException;
import com.xiong.dandan.wudd.net.api.ApiWrapper;
import com.xiong.dandan.wudd.net.api.RequestCallBack;
import com.xiong.dandan.wudd.ui.common.CommonWebViewActivity;
import com.xiong.dandan.wudd.ui.common.ImageFetchActivity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseActivity extends FragmentActivity {


    public static final String SKIP_ACTIVITY_WHERE_FROM = "fromWhere";

    /**
     * 来自哪个 页面
     */
    protected String fromWhere;
    /**
     * 页面布局的 根view
     */
    protected View mContentView;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;
    /**
     * Api类的包装 对象
     */
    protected ApiWrapper mApiWrapper;

    /**
     * 请求服务器加载框
     */
    public CustomProgressDialog mProgressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new CustomProgressDialog(this, false, false);
        ActivityPageManager.getInstance().addActivity(this);
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
        initApi();
    }
    /**
     * 初始化页面
     */
    public void init() {
        initFromWhere();
    }

    protected abstract   void initViews();
    /**
     * 初始化 Api  更具需要初始化
     */
    public void initApi() {
        mCompositeSubscription = new CompositeSubscription();
        mApiWrapper = new ApiWrapper();
    }

    public ApiWrapper getApiWrapper() {
        if (mApiWrapper == null) {
            mApiWrapper = new ApiWrapper();
        }
        return mApiWrapper;
    }

    public CompositeSubscription getCompositeSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param requestCallBack
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T> Subscriber newMySubscriber(final RequestCallBack requestCallBack) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                mProgressDialog.dismiss();
                requestCallBack.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof APIException) {
                    APIException exception = (APIException) e;
                    AppMyAplicition.genInstance().showToast(exception.message);
                    requestCallBack.onError(exception);
                }  else if (e instanceof SocketTimeoutException) {
                    AppMyAplicition.genInstance().showToast(e.getMessage());
                } else if (e instanceof ConnectException) {
                    AppMyAplicition.genInstance().showToast(e.getMessage());
                }else if (e instanceof HttpException) {
                    AppMyAplicition.genInstance().showToast(((HttpException) e).message());
                }
                mProgressDialog.dismiss();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    requestCallBack.onNext(t);
                }
            }

        };
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
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
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



    @Override
    public void onBackPressed() {
        finish();
    }
}
