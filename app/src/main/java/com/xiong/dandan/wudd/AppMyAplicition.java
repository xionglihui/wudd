package com.xiong.dandan.wudd;

import android.os.Environment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.xiong.dandan.wudd.common.base.BaseActivity;
import com.xiong.dandan.wudd.common.base.BaseApplication;
import com.xiong.dandan.wudd.libs.GlobalConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xionglh on 2016/9/21.
 */
public class AppMyAplicition extends BaseApplication {


    private static AppMyAplicition mInstance;
    private static RequestQueue mQueue;
    public List<BaseActivity> mTotalActivity=new ArrayList<>();

    public static AppMyAplicition genInstance() {
        return mInstance;
    }

    public static RequestQueue genQueue() {
        return mQueue;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initDatas();
        createPathForPictures();
    }

    private void initDatas() {
        mInstance = this;
        mQueue = Volley.newRequestQueue(this);
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
