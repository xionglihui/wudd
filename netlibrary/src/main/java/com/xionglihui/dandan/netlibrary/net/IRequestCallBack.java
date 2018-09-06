package com.xionglihui.dandan.netlibrary.net;

import com.xionglihui.dandan.netlibrary.net.exception.NetException;

/**
 * 请求回调
 * Created by xionglh on 2017/2/22.
 */
public interface IRequestCallBack<T> {

    void onCompleted();
    void onError(NetException apiexception);
    void onNext(T t);
}
