package com.xiong.dandan.wudd.common.base;

import android.os.Bundle;

/**
 * Created by xionglh on 2017/2/7.
 */
public abstract class BaseCommonActivity<V extends IBaseView , T extends BaseCommonPersenter<V>> extends BaseTitleBarActivity {

    protected T mPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPersenter = createPersenter();
        mPersenter.attachView((V)this);
    }

    protected abstract T createPersenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPersenter.detachView();
    }
}
