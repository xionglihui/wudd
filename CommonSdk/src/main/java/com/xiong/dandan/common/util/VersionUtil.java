/**
 *
 */
package com.xiong.dandan.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * @author xionglh
 * @version VersionUtil.java Created by xionglh on 2015年7月10日 下午2:08:34 v1.0.0
 */
public class VersionUtil {

    static int version = Build.VERSION.SDK_INT;

    public static int getSdkVersion() {
        return version;
    }

    @TargetApi(8)
    public static boolean sdkVersion8() {
        if (getSdkVersion() >= 8) {
            return true;
        }
        return false;
    }

    @TargetApi(11)
    public static boolean sdkVersion11() {
        if (getSdkVersion() >= 11) {
            return true;
        }
        return false;
    }

    @TargetApi(17)
    public static boolean sdkVersion17() {
        if (getSdkVersion() >= 17) {
            return true;
        }
        return false;
    }

    /**
     * 获取渠道
     *
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        String channel = "";
        try {
            channel = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA).metaData.getString("UMENG_CHANNEL");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (channel.equals("吉融"))//特殊渠道包
            return "PD900610388";
        return channel;
    }
}
