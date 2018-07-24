package com.xiong.dandan.wudd.net.api;

import com.xiong.dandan.wudd.net.request.LoginParamsRequest;
import com.xiong.dandan.wudd.net.response.CommonResponse;
import com.xiong.dandan.wudd.net.response.UserInfo;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xionglh on 2017/2/21.
 */
public interface ApiService {

    @POST("/user/loginMobile")
    Observable<CommonResponse<UserInfo>> requestLogin(@Body RequestBody requestBody);



}
