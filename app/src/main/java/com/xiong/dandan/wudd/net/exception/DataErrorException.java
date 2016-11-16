package com.xiong.dandan.wudd.net.exception;

import com.android.volley.VolleyError;

/**
 * 数据错误，code 不为0
 * @author Created by wangyy on 2015/8/13.
 */
public class DataErrorException extends VolleyError {

    public DataErrorException(String message) {
        super(message);
    }
}