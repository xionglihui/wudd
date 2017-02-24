package com.xiong.dandan.wudd.net.api;

/**
 * Created by xionglh on 2017/2/22.
 */
public abstract class RequestCallBack<T> implements  IRequestCallBack<T> {

    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(APIException apiexception) {

    }
}
