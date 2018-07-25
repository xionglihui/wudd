package com.xionglihui.dandan.netlibrary.net.interceptor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by xionglh on 2018/7/25
 */
public class HttpRequestInterceptor implements Interceptor {


    private static final String CONTENT_TYPE_FORM="x-www-form-urlencoded";
    private static final String CONTENT_TYPE_JSON="json";
    private static final String CONTENT_FORM_DATA="form-data";

    Map<String, String> queryParamsMap = new HashMap<>();
    Map<String, String> paramsMap = new HashMap<>();
    Map<String, String> headerParamsMap = new HashMap<>();
    List<String> headerLinesList = new ArrayList<>();

    private HttpRequestInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();

        Headers.Builder headerBuilder = request.headers().newBuilder();
        if (headerParamsMap.size() > 0) {
            Iterator iterator = headerParamsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if (headerLinesList.size() > 0) {
            for (String line: headerLinesList) {
                headerBuilder.add(line);
            }
        }

        requestBuilder.headers(headerBuilder.build());


        if (queryParamsMap.size() > 0) {
            injectParamsIntoUrl(request, requestBuilder, queryParamsMap);
        }

        if (request.method().equals("POST") ) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (paramsMap.size() > 0) {
                Iterator iterator = paramsMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
                }
            }
            RequestBody requestBody = formBodyBuilder.build();
            MediaType mediaType=request.body().contentType();
            Gson gson=new Gson();
            if(mediaType!=null&&CONTENT_TYPE_FORM.equals(mediaType.subtype())) {
                String postBodyString = bodyToString(request.body());
                postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(requestBody);
                requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
            }else if(mediaType!=null&&CONTENT_TYPE_JSON.equals(mediaType.subtype())){
                String postBodyString = bodyToString(request.body());
                FormBody oldFormBody = (FormBody) requestBody;
                Map<String,Object> parasms= gson.fromJson(postBodyString, Map.class);
                for(int i=0;i<oldFormBody.size();i++){
                    String name= URLDecoder.decode(oldFormBody.encodedName(i),"utf-8");
                    String value=URLDecoder.decode(oldFormBody.encodedValue(i),"utf-8");
                    parasms.put(name,value);
                }
                String parStr= gson.toJson(parasms, HashMap.class);
                requestBuilder.post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), parStr));
            }
        } else {
            injectParamsIntoUrl(request, requestBuilder, paramsMap);
        }
        request = requestBuilder.build();
        return chain.proceed(request);
    }

    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        requestBuilder.url(httpUrlBuilder.build());
    }

    private static String bodyToString(final RequestBody request)throws IOException{
        final Buffer buffer = new Buffer();
        request.writeTo(buffer);
        return buffer.readUtf8();
    }

    public static class Builder {

        HttpRequestInterceptor interceptor;

        public Builder() {
            interceptor = new HttpRequestInterceptor();
        }

        public Builder addParam(String key, String value) {
            interceptor.paramsMap.put(key, value);
            return this;
        }

        public Builder addParamsMap(Map<String, String> paramsMap) {
            interceptor.paramsMap.putAll(paramsMap);
            return this;
        }

        public Builder addHeaderParam(String key, String value) {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }

        public Builder addHeaderLine(String headerLine) {
            int index = headerLine.indexOf(":");
            if (index == -1) {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }

        public Builder addHeaderLinesList(List<String> headerLinesList) {
            for (String headerLine: headerLinesList) {
                int index = headerLine.indexOf(":");
                if (index == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }

        public Builder addQueryParamsMap(Map<String, String> queryParamsMap) {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }

        public HttpRequestInterceptor build() {
            return interceptor;
        }

    }
}
