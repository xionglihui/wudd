package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.wudd.AppMyAplicition;
import com.xiong.dandan.wudd.Config;
import com.xiong.dandan.wudd.libs.tools.CortyTool;
import com.xiong.dandan.wudd.libs.utils.Base64Utils;
import com.xiong.dandan.wudd.net.response.CommonResponse;

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
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * api
 * Created by xionglh on 2017/2/21.
 */
public abstract class Api {
    /***
     * 请求公告部分
     */
    private static final String BASE_URL = Config.COMMON_URL;

    // 消息头
    private static final String HEADER_X_HB_Client_Type = "X-HB-Client-Type";

    private static final String FROM_ANDROID = "ayb-android";

    private static ApiService service;

    private static Retrofit retrofit;


    public static ApiService getService() {
        if (service == null) {
            service = getRetrofit().create(ApiService.class);
        }
        return service;
    }


    private static Map<String, String> getHttpHeader() {
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
     * 拦截器  给所有的请求添加消息头
     */
    private static Interceptor mInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            Map<String, String> heads = getHttpHeader();
            for (String key : heads.keySet()) {
                builder.addHeader(key, heads.get(key));
            }
            Request request = builder.addHeader(HEADER_X_HB_Client_Type, FROM_ANDROID).build();
            return chain.proceed(request);
        }
    };


    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            // log拦截器  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 请求的缓存
            File cacheFile = new File(AppMyAplicition.genInstance().getCacheDir(), "cache");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).addInterceptor(mInterceptor).cache(cache).build();
            retrofit = new Retrofit.Builder().client(client).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return retrofit;
    }

    /**
     * 对 Observable<T> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     * protected <T> Observable.Transformer<Response<T>, T> applySchedulers()
     *
     * @param responseObservable
     * @param <T>
     * @return
     */
    protected <T> Observable applySchedulers(Observable<CommonResponse<T>> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<CommonResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(CommonResponse<T> tResponse) {
                        return flatResponse(tResponse);
                    }
                })
                ;
    }

    /**
     * 对网络接口返回的Response进行分割操作 对于jasn 解析错误以及返回的 响应实体为空的情况
     *
     * @param response
     * @return
     */
    public <T> Observable<T> flatResponse(final CommonResponse<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response != null && !subscriber.isUnsubscribed()) {
                    CommonResponse<T>.Result<T> result = response.getResult();
                    if (!result.isError()||result.isSuccess()) {
                        subscriber.onNext(result.getData());
                    } else {
                        AppMyAplicition.genInstance().showToast(result.getMessage());
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException("0", "解析json错误或者服务器返回空的json"));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

            }
        });
    }

    /**
     *
     */
    public static class APIException extends Exception {
        public String code;
        public String message;

        public APIException(String code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }

    }


}



