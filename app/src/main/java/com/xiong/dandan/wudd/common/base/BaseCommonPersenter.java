package com.xiong.dandan.wudd.common.base;

import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.net.api.ApiWrapper;
import com.xionglihui.dandan.netlibrary.net.APIException;
import com.xionglihui.dandan.netlibrary.net.RequestCallBack;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xionglh on 2017/2/7.
 */
public abstract class BaseCommonPersenter<T>  {

    protected WeakReference<T> mViewRef;
    protected ApiWrapper mApiWrapper;
    protected CompositeSubscription mCompositeSubscription;


    protected void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
        mCompositeSubscription = new CompositeSubscription();
        mApiWrapper = new ApiWrapper();
    }


    /**
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
                requestCallBack.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof APIException) {
                    APIException exception = (APIException) e;
                    AppMyAplicition.genInstance().showToast(exception.message);
                    requestCallBack.onError(exception);//业务错误
                }  else if (e instanceof SocketTimeoutException) {
                    AppMyAplicition.genInstance().showToast(e.getMessage());
                } else if (e instanceof ConnectException) {
                    AppMyAplicition.genInstance().showToast(e.getMessage());
                }else if (e instanceof HttpException) {
                    AppMyAplicition.genInstance().showToast(((HttpException) e).message());
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
