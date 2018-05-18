package com.xiong.dandan.utilslibrary.file;

import android.os.Environment;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Objects;


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
