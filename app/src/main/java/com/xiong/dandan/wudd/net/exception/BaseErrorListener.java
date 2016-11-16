package com.xiong.dandan.wudd.net.exception;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.xiong.dandan.wudd.libs.tools.SystemLog;


/**
 * Created by wangyy on 2015/8/13.
 */
public abstract class BaseErrorListener implements Response.ErrorListener {
    private static final String LOG_TAG = BaseErrorListener.class.getSimpleName();

    @Override
    public void onErrorResponse(VolleyError arg0) {
        if (arg0 instanceof AuthFailureError) {
            SystemLog.D(LOG_TAG, "[HttpResponse error]" + "[AuthFailureError]" + arg0.getMessage());
            authFailureError();
            return;
        } else if (arg0 instanceof TimeoutError) {
            SystemLog.D(LOG_TAG, "[HttpResponse error]" + "[TimeoutError]" + arg0.getMessage());
            arg0 = new NetworkErrorException();
        } else if (arg0 instanceof NoConnectionError) {
            SystemLog.D(LOG_TAG, "[HttpResponse error]" + "[NoConnectionError]" + arg0.getMessage());
            arg0 = new NetworkErrorException();
        } else if (arg0 instanceof NetworkError) {
            SystemLog.D(LOG_TAG, "[HttpResponse error]" + "[NetworkError]" + arg0.getMessage());
            arg0 = new NetworkErrorException();
        } else if(arg0 instanceof ServerError){
            SystemLog.D(LOG_TAG, "[HttpResponse error]" + "[NetworkError]" + arg0.getMessage());
            arg0 = new ServerErrorException();
        }
        onErrorResponseListener(arg0);
    }

    public abstract void authFailureError() ;

    public abstract void onErrorResponseListener(VolleyError arg0);

}