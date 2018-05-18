package com.xionglihui.dandan.netlibrary.net;

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
