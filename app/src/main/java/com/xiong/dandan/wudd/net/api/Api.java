package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.utilslibrary.security.Base64Utils;
import com.xiong.dandan.utilslibrary.security.CortyTool;
import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.Config;
import com.xiong.dandan.wudd.R;

import com.xiong.dandan.wudd.net.response.CommonResponse;
import com.xionglihui.dandan.netlibrary.net.WuddNet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * api
 * Created by xionglh on 2017/2/21.
 */
public class Api {


    public WuddNet wuddNet;

    protected static ApiService apiService;

    public Api() {
        init();
    }

    public void init(){
        wuddNet = new WuddNet(Config.COMMON_URL);
        if (apiService == null)
            apiService = wuddNet.getApiSerice(ApiService.class);
        wuddNet.setHeads(getHttpHeader());

    }

    private  Map<String, String> getHttpHeader() {
        Map<String, String> header = new HashMap<>();
        String encodedAuth = "Basic " + Base64Utils.encode((Config.APP_NAME + ":" + Config.APP_PWD).getBytes());
        String service = "slb";
        String serviceTime = String.valueOf((new Date()).getTime());
        String sign = service + serviceTime + Config.APP_SOURCE + Config.APP_SEEDS;
        header.put("AUTHORIZATION", encodedAuth);
        header.put("accept", "application/json");
        header.put("Connection", "Keep-Alive");
        header.put("appSource", Config.APP_SOURCE);
        header.put("service", service);
        header.put("serviceTime", serviceTime);
        header.put("magicnum", "0xSL001");
        header.put("sign", CortyTool.encryptMD5(sign));
        header.put("Accept-Encoding", "gzip");
        return header;
    }


    /**
     * @param responseObservable
     * @param <T>
     * @return
     */
    protected <T> Observable applySchedulers(Observable<CommonResponse<T>> responseObservable) {
        return wuddNet.applySchedulers(responseObservable);
    }
//
//    /**
//     * 对网络接口返回的Response进行分割操作 对于jasn 解析错误以及返回的 响应实体为空的情况
//     *
//     * @param response
//     * @return
//     */
//    public <T> Observable<T> flatResponse(final CommonResponse<T> response) {
//        return Observable.create(new Observable.OnSubscribe<T>() {
//            @Override
//            public void call(Subscriber<? super T> subscriber) {
//                if (response != null && !subscriber.isUnsubscribed()) {
//                    CommonResponse<T>.Result<T> result = response.getResult();
//                    if (!result.isError() || result.isSuccess()) {
//                        subscriber.onNext(result.getData());
//                    } else {
//                        subscriber.onError(new APIException(result.getCode(),result.getMessage()));//业务错误
//                    }
//                } else {
//                    if (!subscriber.isUnsubscribed()) {
//                        subscriber.onError(new APIException("0",AppMyAplicition.genInstance().getString(R.string.exception_json_error)));
//                    }
//                    return;
//                }
//                if (!subscriber.isUnsubscribed()) {
//                    subscriber.onCompleted();
//                }
//
//            }
//
//        });
//    }


}



