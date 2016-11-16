package com.xiong.dandan.wudd.net;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by wangyy on 2015/7/16.
 */
public class VolleyImageCache implements ImageLoader.ImageCache {

    private static LruCache<String, Bitmap> mMemoryCache;
    private static VolleyImageCache mVolleyImageCache = new VolleyImageCache();

    private VolleyImageCache() {
        // Get the Max available memory
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    public static VolleyImageCache genInstance() {
        return mVolleyImageCache;
    }

    @Override
    public Bitmap getBitmap(String arg0) {
        return mMemoryCache.get(arg0);
    }

    @Override
    public void putBitmap(String arg0, Bitmap arg1) {
        if (getBitmap(arg0) == null) {
            mMemoryCache.put(arg0, arg1);
        }
    }
}

