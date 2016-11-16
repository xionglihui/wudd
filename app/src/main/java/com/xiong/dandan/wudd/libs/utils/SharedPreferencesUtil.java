/**
 * 
 */
package com.xiong.dandan.wudd.libs.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;


/**
 * @author xionglh
 * @version CommonMethod.java 2014年10月5日 下午3:58:01 v1.0.0 xionglihui
 */
public class SharedPreferencesUtil {




	/**
	 * 通过SharedPreferences保存本地数据
	 * 
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveStringValue(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(
				SharedPreferencesKey.SHARE_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 通过SharedPreferences获取本地数据
	 * 
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getStringValue(Context context, String key) {
		SharedPreferences settings = context.getSharedPreferences(
				SharedPreferencesKey.SHARE_FILE_NAME, Context.MODE_PRIVATE);
		return settings.getString(key, "");
	}
	/**
	 * 
	 * @param context
	 * @param keys
	 */
	public static void cleanStringValue(Context context, String... keys) {
		for (String key : keys) {
			SharedPreferences settings = context.getSharedPreferences(
					SharedPreferencesKey.SHARE_FILE_NAME, Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = settings.edit();
			if (settings.contains(key)) {
				editor.remove(key).commit();
			}
		}
	}


	/**
	 * sdcard是否可读写
	 * 
	 * @return ture or false
	 */
	public static boolean IsCanUseSdCard() {
		try {
			return Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
