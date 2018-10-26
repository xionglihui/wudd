package com.xionglihui.dandan.netlibrary.net;

import android.util.LruCache;

import com.xionglihui.dandan.netlibrary.BuildConfig;
import com.xionglihui.dandan.netlibrary.net.exception.NetError;
import com.xionglihui.dandan.netlibrary.net.exception.NetException;
import com.xionglihui.dandan.netlibrary.net.interceptor.HttpRequestInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xionglihui on 2018/5/19.
 */
public class RetrofitClient {

    private static Retrofit retrofit;

    private String httpUrl;

    private Map<String, String> mHeadsMap = new HashMap<>();

    private Map<String, String> mQureyMap = new HashMap<>();

    public RetrofitClient(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public void setHeads(Map<String, String> headsMap) {
        this.mHeadsMap = headsMap;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    private static LruCache<String, Object> mLruApiCache = new LruCache<>(Integer.MAX_VALUE);


    private Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG)
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        //add common  parameter
        HttpRequestInterceptor.Builder httpCommonIntercepoter = new HttpRequestInterceptor.Builder();
        httpCommonIntercepoter.addHeaderParamsMap(mHeadsMap);
        httpCommonIntercepoter.addParamsMap(mQureyMap);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).addInterceptor(interceptor).addInterceptor(httpCommonIntercepoter.build()).build();
        retrofit = new Retrofit.Builder().client(client).baseUrl(httpUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return retrofit;
    }

    public <T> T getApiSerice(Class<T> classSerice) {
        Object o = mLruApiCache.get(classSerice.getName());
        T t;
        if (o != null) {
            t = (T) o;
        } else {
            t = getRetrofit().create(classSerice);
            mLruApiCache.put(classSerice.getName(), t);
        }
        return t;
    }


    public void romveLruCache() {
        mLruApiCache.evictAll();
    }



    public <T> Observable applySchedulers(Observable<T> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap((T t) -> flatResponse(t));
    }

    /**
     * 对网络接口返回的Response进行分割操作 对于jasn 解析错误以及返回的 响应实体为空的情况
     *
     * @param response
     * @return
     */
    public <T> Observable<T> flatResponse(final T response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response != null && !subscriber.isUnsubscribed()) {
                    subscriber.onNext(response);
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new NetException(NetError.SYSTEM_ERROR));
                    }
                    return;
                }
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

            }

        });
    }
}



