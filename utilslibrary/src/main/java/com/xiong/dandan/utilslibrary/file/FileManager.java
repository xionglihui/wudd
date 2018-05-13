package com.xiong.dandan.utilslibrary.file;

import android.os.Environment;



public class FileManager {


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
