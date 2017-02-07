package com.xiong.dandan.wudd.common.base;

import java.lang.ref.WeakReference;

/**
 * Created by xionglh on 2017/2/7.
 */
public abstract class BaseMVPPersenter<T> {

    protected WeakReference<T> mViewRef;

    protected void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
