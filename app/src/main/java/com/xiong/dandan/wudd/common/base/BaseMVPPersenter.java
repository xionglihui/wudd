package com.xiong.dandan.wudd.common.base;

import com.google.gson.Gson;
import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.net.api.Api;
import com.xiong.dandan.wudd.net.api.ApiWrapper;
import com.xiong.dandan.wudd.net.api.RequestCallBack;
import com.xiong.dandan.wudd.net.response.HttpExceptionBean;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xionglh on 2017/2/7.
 */
public abstract class BaseMVPPersenter<T> {

    protected WeakReference<T> mViewRef;
    protected ApiWrapper mApiWrapper;
    protected CompositeSubscription mCompositeSubscription;


    protected void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
        mCompositeSubscription = new CompositeSubscription();
        mApiWrapper = new ApiWrapper();
    }


    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param requestCallBack
     * @param <T>
     * @return
     */
    protected <T> Subscriber newMySubscriber(final RequestCallBack requestCallBack) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                requestCallBack.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    AppMyAplicition.genInstance().showToast(exception.message);
                } else if (e instanceof HttpException) {
                    ResponseBody body = ((HttpException) e).response().errorBody();
                    try {
                        String json = body.string();
                        Gson gson = new Gson();
                        HttpExceptionBean mHttpExceptionBean = gson.fromJson(json, HttpExceptionBean.class);
                        if (mHttpExceptionBean != null && mHttpExceptionBean.getMessage() != null) {
                            AppMyAplicition.genInstance().showToast(mHttpExceptionBean.getMessage());
                            requestCallBack.onError(mHttpExceptionBean);
                        }
                    } catch (IOException IOe) {
                        IOe.printStackTrace();
                    }

                }
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    requestCallBack.onNext(t);
                }
            }

        };
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
