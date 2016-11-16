package com.xiong.dandan.wudd.net.request;

import android.net.Uri;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.entity.BaseEntity;
import com.xiong.dandan.wudd.libs.tools.SystemLog;
import com.xiong.dandan.wudd.libs.utils.NetworkUtil;
import com.xiong.dandan.wudd.libs.utils.StrUtils;
import com.xiong.dandan.wudd.libs.utils.SystemInfoUtil;
import com.xiong.dandan.wudd.net.GsonRequest;
import com.xiong.dandan.wudd.net.exception.NetworkErrorException;
import com.xiong.dandan.wudd.net.response.BaseVolleyResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseVolleyRequest {

    public static  String SESSION_ID="";



    protected abstract int toHttpMethod();

    protected abstract String toRequestURL();

    protected abstract void toHttpRequestParams(Map<String, Object> params);

    protected abstract void toHttpRequestHeader(Map<String, String> header);

    protected abstract List<String> toUploadFilePath();


    private int mHttpMethod;
    private String mRequestUrl;
    private Map<String, Object> mContentParams;
    private Map<String, String> mHeaderParams;
    private List<String> mUploadFilesPath;

    private void initRequest() {
        mHttpMethod = toHttpMethod();
        mRequestUrl = toRequestURL();
        mUploadFilesPath = toUploadFilePath();
        mContentParams = getHttpRequestContentParams();
        if (mHttpMethod == Request.Method.DELETE || mHttpMethod == Request.Method.GET)
            mRequestUrl = toGetUrl(mRequestUrl, mContentParams);
        mHeaderParams = getHttpRequestHeaderParams();
    }

    private Map<String, Object> getHttpRequestContentParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("meVersion", SystemInfoUtil.getAppVersion(AppAplicition.genInstance()));
        params.put("meId", SystemInfoUtil.getDeviceId(AppAplicition.genInstance()));
        params.put("appSource", "android");
        params.put("channelNo", "");
        toHttpRequestParams(params);
        return params;
    }

    private Map<String, String> getHttpRequestHeaderParams() {
        Map<String, String> params = new HashMap<>();
        if(!StrUtils.isEmpty(SESSION_ID)){
            params.put("cookie",SESSION_ID);
        }
        return params;
    }

    @SuppressWarnings("unchecked")
    public void doRequest(String Tag, final Class<? extends BaseVolleyResponse> clzz,
                          Response.Listener<BaseEntity> listener, ErrorListener errorListener) {
        if (!NetworkUtil.isNetWorkAvailable(AppAplicition.genInstance())) {
            errorListener.onErrorResponse(new NetworkErrorException());
            return;
        }
        initRequest();
        @SuppressWarnings("rawtypes")
        GsonRequest request = new GsonRequest(Tag, mHttpMethod, mRequestUrl, mHeaderParams, mContentParams,
                mUploadFilesPath, clzz, listener, errorListener);
        AppAplicition.genQueue().add(request);
    }

    private String toGetUrl(String url, Map<String, Object> entity) {
        StringBuilder urlSB = new StringBuilder();
        if (entity != null && !entity.isEmpty()) {
            for (String key : entity.keySet()) {
                if (urlSB.length() != 0) {
                    urlSB.append("&");
                }
                SystemLog.D("GsonRequest",
                        "request" + JSON.toJSONString(entity.get(key)));
                urlSB.append(key + "="
                        + Uri.encode(JSON.toJSONString(entity.get(key))));
            }
        }
        if (urlSB == null || "".equals(urlSB.toString())) {
            return url;
        }
        SystemLog.D("GsonRequest", "[request url]" + urlSB);
        return url + "?" + urlSB.toString();
    }

}
