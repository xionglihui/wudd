package com.xiong.dandan.wudd.net;

import com.android.volley.Response;
import com.xiong.dandan.wudd.common.base.BaseActivity;
import com.xiong.dandan.wudd.entity.BaseEntity;


/**
 * 成功监听
 * Created by xionglh on 2016/8/30.
 */
public class CommSuccessListener implements Response.Listener<BaseEntity> {

    private BaseActivity  mBaseActivity;

    public CommSuccessListener(BaseActivity baseActivity){
        this.mBaseActivity=baseActivity;
    }

    @Override
    public void onResponse(BaseEntity baseEntity) {
        if(mBaseActivity!=null&&mBaseActivity.mProgressDialog!=null)
            mBaseActivity.dismissProgressDialog();

    }
}
