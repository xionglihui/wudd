package com.xionglihui.dandan.netlibrary.net;

import com.xionglihui.dandan.netlibrary.net.exception.NetException;

/**
 * Created by xionglh on 2017/2/22.
 */
public abstract class RequestCallBack<T> implements  IRequestCallBack<T> {

    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(NetException apiexception) {

    }
}
