package com.xiong.dandan.utilslibrary.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;


public class SystemInfoUtil {

	/**
	 * 
	 * @param context
	 * @return 返回当前的版本号
	 */
	public static String getAppVersion(Context context) {
		PackageManager pagePackageManager = context.getPackageManager();
		PackageInfo packageInfo = null;
		try {
			packageInfo = pagePackageManager.getPackageInfo(
					context.getPackageName(), 0);
			String version = packageInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 获取设备好唯一标识
	 * @param context
	 * @return
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		return deviceId;
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
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return channel;
	}

	/**
	 * 获取系统的版本号
	 *
	 * @return
	 */
	public static String getSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获取当前ip地址
	 *
	 * @param context
	 * @return
	 */
	public static String getLocalIpAddress(Context context) {
		try {
			WifiManager wifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int i = wifiInfo.getIpAddress();
			return SlinUtil.int2ip(i);
		} catch (Exception ex) {
			return " 获取IP出错鸟!!!!请保证是WIFI,或者请重新打开网络!\n" + ex.getMessage();
		}
	}



}
