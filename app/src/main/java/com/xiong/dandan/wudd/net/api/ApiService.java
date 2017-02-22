package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.wudd.net.request.LoginParamsRequest;
import com.xiong.dandan.wudd.net.response.CommonResponse;
import com.xiong.dandan.wudd.net.response.UserInfo;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xionglh on 2017/2/21.
 */
public interface ApiService {

    @POST("/user/loginMobile")
    Observable<CommonResponse<UserInfo>> requestLogin(@Body LoginParamsRequest mLoginParams);

}
