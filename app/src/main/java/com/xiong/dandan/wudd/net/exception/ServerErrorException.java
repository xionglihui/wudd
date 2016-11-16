package com.xiong.dandan.wudd.net.exception;

import com.android.volley.VolleyError;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.R;


/**
 * 服务器异常 或 Json解析错误
 * @author  Created by wangyy on 2015/8/13.
 */
public class ServerErrorException extends VolleyError {

    public ServerErrorException() {
        super(AppAplicition.genInstance().getResources().getString(R.string.exception_json_error));
    }
}