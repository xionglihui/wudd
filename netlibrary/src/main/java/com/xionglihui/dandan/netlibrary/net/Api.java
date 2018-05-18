package com.xionglihui.dandan.netlibrary.net;


import android.app.Application;

import java.io.File;
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

/**
 * api
 * Created by xionglh on 2017/2/21.
 */
public abstract class Api {

    private static Retrofit retrofit;
    public abstract String getHttpUrl();
    public abstract Map<String, String> getHttpHeader();

    public  Retrofit getRetrofit() {
        if (retrofit == null) {
            //  打印所有的log
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).addInterceptor(new HeadInterceptor(getHttpHeader())).build();
            retrofit = new Retrofit.Builder().client(client).baseUrl(getHttpUrl()).addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return retrofit;
    }


}



