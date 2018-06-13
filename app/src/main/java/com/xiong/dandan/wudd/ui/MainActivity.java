package com.xiong.dandan.wudd.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.common.base.BaseActivity;
import com.xiong.routerlibrary.url.ARouterPageUrl;

/**
 * Created by xionglh on 2016/9/21.
 */

@Route(path = ARouterPageUrl.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        ARouter.getInstance().build("/login/LoginActivity").navigation();
    }

    @Override
    public void onTrimMemory(int level) {
        switch (level) {
            case TRIM_MEMORY_RUNNING_CRITICAL:
                break;
        }
        super.onTrimMemory(level);
    }
}
