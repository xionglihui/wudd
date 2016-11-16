package com.xiong.dandan.wudd;

import android.os.Environment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.xiong.dandan.wudd.common.base.BaseActivity;
import com.xiong.dandan.wudd.common.base.BaseApplication;
import com.xiong.dandan.wudd.libs.GlobalConstants;
import com.xiong.dandan.wudd.net.VolleyImageCache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xionglh on 2016/9/21.
 */
public class AppAplicition extends BaseApplication {


    private static AppAplicition mInstance;
    private static RequestQueue mQueue;
    private static ImageLoader mImageLoader;
    public List<BaseActivity> mTotalActivity=new ArrayList<>();

    public static AppAplicition genInstance() {
        return mInstance;
    }

    public static RequestQueue genQueue() {
        return mQueue;
    }

    public static ImageLoader genImageLoader() {
        return mImageLoader;
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
        VolleyImageCache lruImageCache = VolleyImageCache.genInstance();
        mImageLoader = new ImageLoader(mQueue, lruImageCache);
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
