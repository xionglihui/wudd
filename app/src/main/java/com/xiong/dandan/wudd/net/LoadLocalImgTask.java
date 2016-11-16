/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiong.dandan.wudd.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.xiong.dandan.wudd.R;
import com.xiong.dandan.wudd.libs.utils.StrUtils;

import java.io.IOException;

public class LoadLocalImgTask extends AsyncTask<Void, Void, Bitmap> {

    private ImageView photoView;
    private String firstPath;
    private String secondPath;
    private int width;
    private int height;
    private Context context;
    private String truePath;
    private ICallback callback;

    public interface ICallback{
        void callback(Bitmap bitmap);
    }

    public LoadLocalImgTask(Context context, String firstPath, String secondPath,
                            ImageView photoView, int width, int height, ICallback callback) {
        this.context = context;
        this.firstPath = firstPath;
        truePath = firstPath;
        this.secondPath = secondPath;
        this.photoView = photoView;
        this.width = width;
        this.height = height;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        Bitmap bitmap = null;
        bitmap = getBitmapFromPath(firstPath);
        if (bitmap == null && !StrUtils.isEmpty(secondPath)) {
            bitmap = getBitmapFromPath(secondPath);
            truePath = secondPath;
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (result != null)
            VolleyImageCache.genInstance().putBitmap(truePath, result);
        else
            result = BitmapFactory.decodeResource(context.getResources(),
                    R.mipmap.icon_default_expression);
        if(photoView != null) {
            photoView.setVisibility(View.VISIBLE);
            photoView.setImageBitmap(result);
        }
        if(callback != null){
            callback.callback(result);
        }
    }

    private Bitmap getBitmapFromPath(String path) {
        try {
            Bitmap bitmap = null;
            if (width == height && width == 0) {
                bitmap = BitmapFactory.decodeFile(path);
            } else {
                bitmap = decodeScaleImage(path, width, height);
            }
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap decodeScaleImage(String var0, int var1, int var2) {
        BitmapFactory.Options var3 = getBitmapOptions(var0);
        int var4 = calculateInSampleSize(var3, var1, var2);
        var3.inSampleSize = var4;
        var3.inJustDecodeBounds = false;
        Bitmap var5 = BitmapFactory.decodeFile(var0, var3);
        int var6 = readPictureDegree(var0);
        Bitmap var7 = null;
        if (var5 != null && var6 != 0) {
            var7 = rotaingImageView(var6, var5);
            var5.recycle();
            var5 = null;
            return var7;
        } else {
            return var5;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options var0, int var1, int var2) {
        int var3 = var0.outHeight;
        int var4 = var0.outWidth;
        int var5 = 1;
        if (var3 > var2 || var4 > var1) {
            int var6 = Math.round((float) var3 / (float) var2);
            int var7 = Math.round((float) var4 / (float) var1);
            var5 = var6 > var7 ? var6 : var7;
        }

        return var5;
    }

    public static BitmapFactory.Options getBitmapOptions(String var0) {
        BitmapFactory.Options var1 = new BitmapFactory.Options();
        var1.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(var0, var1);
        return var1;
    }


    public static int readPictureDegree(String var0) {
        short var1 = 0;

        try {
            ExifInterface var2 = new ExifInterface(var0);
            int var3 = var2.getAttributeInt("Orientation", 1);
            switch (var3) {
                case 3:
                    var1 = 180;
                case 4:
                case 5:
                case 7:
                default:
                    break;
                case 6:
                    var1 = 90;
                    break;
                case 8:
                    var1 = 270;
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return var1;
    }

    public static Bitmap rotaingImageView(int var0, Bitmap var1) {
        Matrix var2 = new Matrix();
        var2.postRotate((float) var0);
        Bitmap var3 = Bitmap.createBitmap(var1, 0, 0, var1.getWidth(), var1.getHeight(), var2, true);
        return var3;
    }
}
