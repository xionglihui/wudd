package com.xiong.dandan.wudd.net.api;

/**
 * 请求回调
 * Created by xionglh on 2017/2/22.
 */
public interface IRequestCallBack<T> {

    void onCompleted();
    void onError(APIException apiexception);
    void onNext(T t);
}
