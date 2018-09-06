package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.common.util.GJsonUtil;
import com.xiong.dandan.common.tools.Base64Utils;
import com.xiong.dandan.common.tools.CortyTool;
import com.xiong.dandan.wudd.Config;
import com.xiong.dandan.wudd.net.response.CommonResponse;
import com.xionglihui.dandan.netlibrary.net.RetrofitClient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * api
 * Created by xionglh on 2017/2/21.
 */
public class Api {


    private RetrofitClient retrofitClient;
    private static final String MEDIA_TYPE = "application/json;charset=utf-8";

    public static ApiService apiService;

    public Api() {
        init();
    }

    private void init(){
        retrofitClient = new RetrofitClient(Config.COMMON_URL);
        if (apiService == null)
            apiService = retrofitClient.getApiSerice(ApiService.class);
        retrofitClient.setHeads(getHttpHeader());

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
    protected RequestBody toRequestBody(Object object) {
        String json = GJsonUtil.toJson(object, Object.class);
        return RequestBody.create(MediaType.parse(MEDIA_TYPE), json);
    }

    /**
     * @param responseObservable
     * @param <T>
     * @return
     */
    protected <T> Observable applySchedulers(Observable<CommonResponse<T>> responseObservable) {
        return retrofitClient.applySchedulers(responseObservable);
    }

}



