package com.xiong.dandan.wudd.common.base;

import rx.Observable;

/**
 * Created by xionglh on 2017/2/23.
 */
public interface IBaseModle<T> {
    void savaDataInfo(T dataInfo);
    Observable<T> getDataInfo(Class<?> cl);
}
