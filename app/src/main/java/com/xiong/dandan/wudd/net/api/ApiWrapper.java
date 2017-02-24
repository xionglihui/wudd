package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.wudd.net.request.LoginParamsRequest;
import com.xiong.dandan.wudd.net.response.CommonResponse;
import com.xiong.dandan.wudd.net.response.UserInfo;

import rx.Observable;

/**
 * Created by xionglh on 2017/2/22.
 */
public class ApiWrapper extends Api {

    @SuppressWarnings("unchecked")
    public Observable<CommonResponse<UserInfo>> getLoginUserInfo(LoginParamsRequest loginParamsRequest) {
        return applySchedulers(getService().requestLogin(loginParamsRequest));
    }

    @SuppressWarnings("unchecked")
    public Observable<CommonResponse<UserInfo>> getHeadToken(String token) {
        return applySchedulers(getService().requestHeadToken(token));
    }

    @SuppressWarnings("unchecked")
    public Observable<CommonResponse<UserInfo>> getGetInfo(String custid) {
        return applySchedulers(getService().requestGet(custid));
    }
}
