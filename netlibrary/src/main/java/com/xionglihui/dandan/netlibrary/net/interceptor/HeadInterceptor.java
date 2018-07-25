package com.xionglihui.dandan.netlibrary.net.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加消息头
 * Created by xionglihui on 2018/5/18.
 */
public class HeadInterceptor implements Interceptor {


    // 消息头
    private static final String HEADER_X_HB_Client_Type = "X-HB-Client-Type";

    private static final String FROM_ANDROID = "ayb-android";
    private Map<String, String> mHeadInterceptors;

    public HeadInterceptor(Map<String, String> headInterceptors) {
        this.mHeadInterceptors = headInterceptors;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (mHeadInterceptors != null)
            for (String key : mHeadInterceptors.keySet()) {
                builder.addHeader(key, mHeadInterceptors.get(key));
            }
        Request request = builder.addHeader(HEADER_X_HB_Client_Type, FROM_ANDROID).build();
        return chain.proceed(request);
    }
}
