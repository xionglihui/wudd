package com.xiong.dandan.wudd.net.exception;

import com.android.volley.VolleyError;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.R;

/**
 * Created by wangyy on 2015/9/16.
 */
public class NetworkErrorException extends VolleyError {

    public NetworkErrorException() {
        super(AppAplicition.genInstance().getResources().getString(R.string.exception_netword_error));
    }
}