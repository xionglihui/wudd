package com.xiong.dandan.wudd.net;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.xiong.dandan.wudd.AppAplicition;
import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.libs.GlobalConstants;


/**
 * Created by wangyy on 2015/12/1.
 */
public class ImageLoaderRequest {
    public ImageLoaderRequest(String url, ImageView image) {
        AppAplicition.genImageLoader().get(url, ImageLoader.getImageListener(image,
                        R.mipmap.ic_launcher, R.mipmap.ic_launcher),
                GlobalConstants.USER_IMAGE_SIZE_WIDTH, GlobalConstants.USER_IMAGE_SIZE_HEIGHT);
    }

    public ImageLoaderRequest(String url, ImageLoader.ImageListener listener) {
        AppAplicition.genImageLoader().get(url, listener,
                GlobalConstants.USER_IMAGE_SIZE_WIDTH, GlobalConstants.USER_IMAGE_SIZE_HEIGHT);
    }
}
