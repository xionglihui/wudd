package com.xiong.dandan.wudd;

import android.os.Environment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xiong.dandan.wudd.common.base.BaseApplication;
import com.xiong.dandan.wudd.libs.GlobalConstants;

import java.io.File;

/**
 * Created by xionglh on 2016/9/21.
 */
public class AppMyAplicition extends BaseApplication {


    private static AppMyAplicition mInstance;

    public static AppMyAplicition genInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initDatas();
//        createPathForPictures();
    }

    private void initDatas() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();//打开日志
            ARouter.openDebug();//打开调式模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        mInstance = this;
    }

    public static void createPathForPictures() {
        String paths[] = new String[]{GlobalConstants.PATH_TEMP,
                GlobalConstants.PATH_IMAGE_CACHE, GlobalConstants.PATH_LOG};
        for (int i = 0; i < paths.length; i++) {
            String path = Environment.getExternalStorageDirectory().getPath()
                    + paths[i];
            File exportDirPath = new File(path);
            if (!exportDirPath.exists()) {
                exportDirPath.mkdirs();
            }
        }
    }
}
