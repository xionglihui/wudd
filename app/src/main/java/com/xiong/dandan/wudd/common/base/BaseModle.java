package com.xiong.dandan.wudd.common.base;

import com.xiong.dandan.common.util.GJsonUtil;
import com.xiong.dandan.common.util.SharedPreferencesUtil;
import com.xiong.dandan.wudd.AppMyAplicition;
;

import rx.Observable;

/**
 * Created by xionglh on 2017/2/23.
 */
public class BaseModle<T> implements IBaseModle<T> {

    @Override
    public void savaDataInfo(T t) {
        String s = GJsonUtil.objToJson(t);
        SharedPreferencesUtil.saveStringValue(AppMyAplicition.genInstance(), BaseModle.this.getClass().getName(), s);

    }

    @Override
    public Observable<T> getDataInfo(final Class<?> cl) {
        String cacheInfoStr = SharedPreferencesUtil.getStringValue(AppMyAplicition.genInstance(), BaseModle.this.getClass().getName());
        return Observable.just(cacheInfoStr).map(t->GJsonUtil.toObject(t, cl));
    }


}
