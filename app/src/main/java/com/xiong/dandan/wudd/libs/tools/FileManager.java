package com.xiong.dandan.wudd.libs.tools;

import android.os.Environment;

import com.xiong.dandan.wudd.libs.GlobalConstants;


public class FileManager {

	public static String getSaveFilePath() {
		return getRootFilePath() + GlobalConstants.PATH_IMAGE_CACHE;
	}

	public static boolean hasSDCard() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}

	public static String getRootFilePath() {
		if (hasSDCard()) {
			return Environment.getExternalStorageDirectory().getPath();// filePath:/sdcard/
		} else {
			return Environment.getDataDirectory().getAbsolutePath() + "/data"; // filePath:																		// /data/data/
		}
	}
}
