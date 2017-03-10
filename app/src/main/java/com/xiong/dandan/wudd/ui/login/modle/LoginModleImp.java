package com.xiong.dandan.wudd.ui.login.modle;

import com.xiong.dandan.wudd.common.base.BaseModle;
import com.xiong.dandan.wudd.net.response.UserInfo;

/**
 * Created by xionglh on 2017/1/4.
 */
public class LoginModleImp<T> extends BaseModle<T> {

    @Override
    public void savaDataInfo(T t) {
        super.savaDataInfo(t);
        UserInfo userInfo = (UserInfo) t;
    }
}
