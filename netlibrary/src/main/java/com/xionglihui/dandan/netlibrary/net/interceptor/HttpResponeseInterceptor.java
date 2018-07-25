package com.xionglihui.dandan.netlibrary.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by xionglh on 2018/7/25
 */
public class HttpResponeseInterceptor implements Interceptor {

    public HttpResponeseInterceptor() {
        super();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            final StringBuffer cookieBuffer = new StringBuffer();
            Observable.from(originalResponse.headers("Set-Cookie"))
                    .map(s -> {
                        String[] cookieArray = s.split(";");
                        return cookieArray[0];
                    }).subscribe(s -> {
                cookieBuffer.append(s).append(";");
            });
        }
        return originalResponse;
    }
}