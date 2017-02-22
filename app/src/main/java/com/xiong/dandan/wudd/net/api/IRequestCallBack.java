package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.wudd.net.response.HttpExceptionBean;

/**
 * 请求回调
 * Created by xionglh on 2017/2/22.
 */
public interface IRequestCallBack<T> {

    void onCompleted();
    void onError(HttpExceptionBean mHttpExceptionBean);
    void onNext(T t);
}
