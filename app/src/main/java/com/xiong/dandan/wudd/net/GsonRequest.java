package com.xiong.dandan.wudd.net;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.entity.BaseEntity;
import com.xiong.dandan.wudd.libs.tools.SystemLog;
import com.xiong.dandan.wudd.libs.utils.CollectionUtil;
import com.xiong.dandan.wudd.libs.utils.SharedPreferencesKey;
import com.xiong.dandan.wudd.libs.utils.SharedPreferencesUtil;
import com.xiong.dandan.wudd.libs.utils.StrUtils;
import com.xiong.dandan.wudd.net.exception.DataErrorException;
import com.xiong.dandan.wudd.net.exception.ServerErrorException;
import com.xiong.dandan.wudd.net.request.BaseVolleyRequest;
import com.xiong.dandan.wudd.net.response.BaseVolleyResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GsonRequest extends Request<BaseEntity> {
    private static final String LOG_TAG = GsonRequest.class.getSimpleName();

    private final Listener<BaseEntity> mListener;
    private Map<String, Object> mParams;
    private Class<? extends BaseVolleyResponse> mClzz;
    private Map<String, String> mHeaderParams;
    private List<String> mFilePath;

    private MultipartEntity mReqEntity;

    public GsonRequest(String Tag, int method, String url, Map<String, String> headers, Map<String, Object> params,
                       List<String> files, Class<? extends BaseVolleyResponse> clzz,
                       Listener<BaseEntity> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mParams = params;
        mHeaderParams = headers;
        mFilePath = files;
        mClzz = clzz;
        mReqEntity = new MultipartEntity();

        setShouldCache(false);
        setTag(Tag);
        setRetryPolicy(
                // 超时时间、retry次数、超时时间乘数 例如第一次5秒  第二次超时想要设置10秒， 乘数就为2 默认为1 可以是float
                new DefaultRetryPolicy(20000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        );
        if (!CollectionUtil.isCollectionEmpty(mFilePath)) {
            // upload files
            buildMultipartEntity();
        }
    }

    @Override
    protected Response<BaseEntity> parseNetworkResponse(NetworkResponse response) {
        // this will be called in child thread. Not in ui thread
        Map<String, String> responseHeaders = response.headers;
        String rawCookies = responseHeaders.get("Set-Cookie");
        if (!StrUtils.isEmpty(rawCookies)) {
            BaseVolleyRequest.SESSION_ID = rawCookies.substring(0, rawCookies.indexOf(";"));
            SharedPreferencesUtil.saveStringValue(AppAplicition.genInstance(), SharedPreferencesKey.LOGIN_SESSION_ID,BaseVolleyRequest.SESSION_ID);
            SystemLog.D(LOG_TAG,"session_id"+BaseVolleyRequest.SESSION_ID);
        }
        try {
            int status = response.statusCode;
            SystemLog.D(LOG_TAG, "[HttpResponse Status]" + status);
            if (status >= 200 && status < 300) { // 200 ~ 300 are success
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                if (StrUtils.isEmpty(jsonString)) {
                    return Response.error(new ParseError(new Exception("Json error")));
                } else {
                    SystemLog.D(LOG_TAG, "[doRequest]" + jsonString);
                        // json to object
                        final BaseVolleyResponse localResponse = JSON.parseObject(jsonString, mClzz);
                        if (localResponse == null) {
                            return Response.error(new ServerErrorException());
                        } else {
                            if (!localResponse.getCode().equals("0")) {
                                if((localResponse.getCode().equals("-99")||localResponse.getCode().equals("-2"))&&StrUtils.isEmpty(localResponse.getMsg()))
                                    return Response.error(new AuthFailureError(localResponse.getMsg()));
                                return Response.error(new DataErrorException(localResponse.getMsg()));
                            } else {
                                // save info local. like save data to database.
                                BaseEntity entity = localResponse.saveInfo(localResponse);
                                return Response.success(entity, HttpHeaderParser.parseCacheHeaders(response));
                            }
                        }
//                    else {
//                        // 返回结果为list
//                        final BaseVolleyResponse localResponse = JSON.parseObject(jsonString, mClzz);
//                        if (localResponse == null) {
//                            return Response.error(new ServerErrorException());
//                        } else {
//                            // save info local. like save data to database.
//                            BaseEntity entity = localResponse.saveInfo(localResponse);
//                            return Response.success(entity, HttpHeaderParser.parseCacheHeaders(response));
//                        }
//                    }
                }
            }
        } catch (Exception e) {
            return Response.error(new ServerErrorException());
        }
        return Response.error(new ServerErrorException());
    }

    @Override
    protected void deliverResponse(BaseEntity response) {
        mListener.onResponse(response);
    }

    @Override
    public String getBodyContentType() {
        if (!CollectionUtil.isCollectionEmpty(mFilePath)) {
//            return mReqEntity.getContentType().getValue();
        }
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (!CollectionUtil.isCollectionEmpty(mFilePath)) {
            // upload files
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                mReqEntity.writeTo(bos);
            } catch (IOException e) {
                VolleyLog.e("IOException writing to ByteArrayOutputStream");
            }
            return bos.toByteArray();
        }

        // other
        if (mParams != null && !mParams.isEmpty()) {
            SystemLog.D("mParams", mParams.toString());
            String ss = JSON.toJSONString(mParams);
            return ss.getBytes();
        }
        return super.getBody();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaderParams;
    }

    private void buildMultipartEntity() {
        try {
            for (String filepath : mFilePath) {
                File file = new File(filepath);
                FileBody filePart = new FileBody(file);
                mReqEntity.addPart(file.getName(), filePart);
            }
            if (mParams != null && !mParams.isEmpty()) {
                Iterator<String> keySet = mParams.keySet().iterator();
                while (keySet.hasNext()) {
                    String key = keySet.next();
                    String ss = JSON.toJSONString(mParams.get(key));
                    mReqEntity.addPart(key, new StringBody(ss));
                }
            }
        } catch (UnsupportedEncodingException e) {

        }
    }
}
