package com.xiong.dandan.wudd;

import android.os.Environment;

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
        createPathForPictures();
    }

    private void initDatas() {
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
