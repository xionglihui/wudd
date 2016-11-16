package com.xiong.dandan.wudd.libs.tools;

import android.annotation.SuppressLint;
import android.util.Log;

import com.xiong.dandan.wudd.Config;
import com.xiong.dandan.wudd.libs.GlobalConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemLog {
	public static final int LEVEL_VERBOSE = 0X001;
	public static final int LEVEL_DEBUG = 0X002;
	public static final int LEVEL_INFO = 0X003;
	public static final int LEVEL_WARNING = 0X004;
	public static final int LEVEL_ERROR = 0X005;

	public static void V(String tag, String msg) {
		log(LEVEL_VERBOSE, tag, msg);
	}

	public static void D(String tag, String msg) {
		log(LEVEL_DEBUG, tag, msg);
	}

	public static void I(String tag, String msg) {
		log(LEVEL_INFO, tag, msg);
	}

	public static void W(String tag, String msg) {
		log(LEVEL_WARNING, tag, msg);
	}

	public static void E(String tag, String msg) {
		log(LEVEL_ERROR, tag, msg);
	}

	private static void log(int logLevel, String logTag, String logMessage) {
		if (Config.isPrduction) {
			if (logLevel < Config.SDCARD_SMALLESET_LEVEL)
				return;
			LocalLog localLog = new LocalLog(logLevel, logTag, logMessage);
			saveVisionExceptionLog(localLog);
		} else {
			switch (logLevel) {
			case LEVEL_VERBOSE:
				Log.v(logTag, logMessage);
				break;
			case LEVEL_DEBUG:
				Log.d(logTag, logMessage);
				break;
			case LEVEL_INFO:
				Log.i(logTag, logMessage);
				break;
			case LEVEL_WARNING:
				Log.w(logTag, logMessage);
				break;
			case LEVEL_ERROR:
				Log.e(logTag, logMessage);
				break;
			default:
				// empty here
				break;
			}
		}
	}

	@SuppressLint("SimpleDateFormat")
	public static void saveVisionExceptionLog(LocalLog localLog) {
		// Create directory
		File logdir = new File(GlobalConstants.LOG_FILE_DIR);
		if (!logdir.isDirectory()) {
			logdir.mkdirs();
		}
		// Create file
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		File logFile = new File(logdir, date + "_" + GlobalConstants.LOG_FILE_NAME);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Save exception log
		try {
			PrintWriter pw = new PrintWriter(
					new FileOutputStream(logFile, true), true);
			pw.write(localLog.toString() + "\n\r");
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static String logLevelToString(int level) {
		String ret = "I";
		switch (level) {
		case LEVEL_VERBOSE:
			ret = "VERBOSE";
			break;
		case LEVEL_DEBUG:
			ret = "DEBUG";
			break;
		case LEVEL_INFO:
			ret = "INFO";
			break;
		case LEVEL_WARNING:
			ret = "WARNING";
			break;
		case LEVEL_ERROR:
			ret = "ERROR";
			break;
		}
		return ret;
	}

	private static class LocalLog {
		public Date mLogTime;
		public int mLogLevel;
		public String mLogTag;
		public String mLogMessage;

		public LocalLog(int logLevel, String logTag, String logMessage) {
			mLogLevel = logLevel;
			mLogTag = logTag;
			mLogMessage = logMessage;
			mLogTime = new Date();
		}

		@SuppressLint("SimpleDateFormat")
		@Override
		public String toString() {
			SimpleDateFormat format1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String date = format1.format(mLogTime);
			return "[" + date + "] [" + logLevelToString(mLogLevel) + "] ["
					+ mLogTag + "] " + " " + mLogMessage;
		}
	}

}
