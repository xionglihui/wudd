package com.xiong.dandan.wudd.libs.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

	private static final String LOG_TAG = FileUtil.class.getSimpleName();

	/**
	 * Write text to filer.
	 * 
	 * @param content
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public static boolean write2File(String content, String dir, String fileName) {
		return write2File(content, dir, fileName, false);
	}

	/**
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName) {
		File targetFile = new File(fileName);
		StringBuilder readedStr = new StringBuilder();

		InputStream in = null;
		BufferedReader br = null;
		try {
			if (!targetFile.exists()) {
				targetFile.createNewFile();
				return readedStr.toString();
			} else {
				in = new BufferedInputStream(new FileInputStream(targetFile));
				br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String tmp;

				while ((tmp = br.readLine()) != null) {
					readedStr.append(tmp);
				}
				br.close();
				in.close();

				return readedStr.toString();
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, "readFile", e);
			return readedStr.toString();
		}
	}

	/**
	 * Retrieves last modified time.
	 * 
	 * @param path
	 * @return
	 */
	public static long getLastModifiedTime(String path) {
		File file = new File(path);
		return file.lastModified();
	}

	/**
	 * Check whether file exists.
	 * 
	 * @param path
	 * @return
	 */
	public static boolean exists(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * file copy
	 * @return
	 */
	public static boolean fileCpy(String srcPath, String dstPath,
			boolean rewrite) {
		File srcFile = null;
		File dstFile = null;
		FileInputStream fosfrom = null;
		FileOutputStream fosto = null;
		try {
			srcFile = new File(srcPath);
			dstFile = new File(dstPath);
			if (!srcFile.exists()) {
				return false;
			}
			if (!srcFile.isFile()) {
				return false;
			}
			if (!srcFile.canRead()) {
				return false;
			}
			if (!dstFile.getParentFile().exists()) {
				dstFile.getParentFile().mkdirs();
			}
			if (dstFile.exists() && rewrite) {
				dstFile.delete();
			}
			fosfrom = new FileInputStream(srcFile);
			fosto = new FileOutputStream(dstFile, false);
			byte bt[] = new byte[1024];
			int c;

			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c);
			}
			return true;
		} catch (Exception e) {
			Log.e(LOG_TAG, "fileCpy", e);
			return false;
		} finally {
			if (fosfrom != null) {
				try {
					fosfrom.close();
				} catch (IOException e) {
					Log.e(LOG_TAG, "fileCpy.IOException.1", e);
				}
			}
			if (fosto != null) {
				try {
					fosto.close();
				} catch (IOException e) {
					Log.e(LOG_TAG, "fileCpy.IOException.2", e);
				}
			}
		}
	}

	/**
	 * Rename file.
	 * 
	 * @param srcPath
	 * @param dstPath
	 * @return
	 */
	public static boolean renameToFile(String srcPath, String dstPath) {
		File srcFile = null;
		File dstFile = null;
		try {
			srcFile = new File(srcPath);
			dstFile = new File(dstPath);
			return srcFile.renameTo(dstFile);
		} catch (Exception e) {
			Log.e(LOG_TAG, "Exception", e);
			return false;
		}

	}

	/**
	 * Create file.
	 * 
	 * @param dir
	 *            directory
	 * @param fileName
	 *            file name
	 */
	public static boolean createFile(String dir, String fileName) {
		if (StrUtils.isEmpty(dir) || StrUtils.isEmpty(fileName)) {
			return false;
		}
		try {
			// create directory
			createDir(dir);
			return createFile(StrUtils.trimRight(dir, "/") + "/"
					+ StrUtils.trimLeft(fileName, "/"));
		} catch (Exception e) {
			Log.e(LOG_TAG, "createFile", e);
			return false;
		}
	}

	/**
	 * Create directory.
	 * 
	 * @param dir
	 */
	public static void createDir(String dir) {
		if (StrUtils.isEmpty(dir)) {
			return;
		}
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * Create file.
	 * 
	 * @param path
	 *            the whole file.
	 */
	public static boolean createFile(String path) {
		if (StrUtils.isEmpty(path)) {
			return false;
		}
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.createNewFile();
			}
			return true;
		} catch (Exception e) {
			Log.e(LOG_TAG, "createFile", e);
			return false;
		}
	}

	/**
	 * Create file.
	 * 
	 * @param path
	 *            the whole file.
	 */
	public static void clearFileInDir(String path) {
		if (StrUtils.isEmpty(path)) {
			return;
		}
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {

			File[] files = dir.listFiles();
			if (files != null && files.length > 0) {
				for (File file : files) {
					file.delete();
				}
			}
		}
	}

	/**
	 * Delete file.
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}

	/**
	 * Uncompress the zip file to folder
	 * 
	 * @param zipFileString
	 * @param outPathString
	 * @throws Exception
	 */
	public static void unZipFolder(String zipFileString, String outPathString)
			throws Exception {
		Log.v(LOG_TAG, "UnZipFolder(zipFileString, String)");
		unZipFolder(zipFileString, outPathString);
	}

	/**
	 * Uncompress the zip file to folder
	 * 
	 * @param inputStream
	 *            input stream
	 * @param outPathString
	 *            folder uncompressed
	 * @throws Exception
	 */
	public static void unZipFolder(InputStream inputStream, String outPathString)
			throws Exception {
		if (inputStream == null || StrUtils.isEmpty(outPathString)) {
			return;
		}
		Log.v(LOG_TAG, "UnZipFolder(InputStream, String)");
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(
				inputStream);
		java.util.zip.ZipEntry zipEntry;
		String szName = "";

		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();

			if (zipEntry.isDirectory()) {

				// get the folder name of the widget
				szName = szName.substring(0, szName.length() - 1);
				File folder = new File(outPathString
						+ File.separator + szName);
				folder.mkdirs();

			} else {

				File file = new File(outPathString
						+ File.separator + szName);
				file.createNewFile();
				// get the output stream of the file
				FileOutputStream out = new FileOutputStream(
						file, false);
				int len;
				byte[] buffer = new byte[1024];
				// read (len) bytes into buffer
				while ((len = inZip.read(buffer)) != -1) {
					// write (len) byte from buffer at the position 0
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}// end of while

		inZip.close();

	}

	/**
	 * Write text to filer.
	 * 
	 * @param content
	 * @param dir
	 * @param fileName
	 *            mode
	 * @return
	 */
	public static boolean write2File(String content, String dir,
			String fileName, boolean appendMode) {
		if (StrUtils.isEmpty(dir) || StrUtils.isEmpty(fileName)) {
			return false;
		}
		String path = StrUtils.trimRight(dir, "/") + "/"
				+ StrUtils.trimLeft(fileName, "/");
		if (!createFile(dir, fileName)) {
			return false;
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(path, appendMode);
			bw = new BufferedWriter(fw);

			if (appendMode) {
				bw.append(StrUtils.notNullStr(content));
			} else {
				bw.write(StrUtils.notNullStr(content));
			}

			bw.flush();
			bw.close();
			fw.close();
			return true;
		} catch (IOException e) {
			Log.e(LOG_TAG, "createFile.1", e);
			return false;
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				Log.e(LOG_TAG, "createFile.2", e);
			}
		}
	}

	/**
	 * Check whether SDCard is available to access.
	 * 
	 * @return true is SDCard is available or false
	 */
	public static boolean isSDCardAvailableToAccess() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}

	public static boolean close(Closeable c, String name) {

		name = name == null ? "Untyped" : name;

		try {
			if (c != null) {
				c.close();
			}
		} catch (IOException e) {
			Log.e(LOG_TAG, "Issue when closing " + name, e);
			return false;
		}

		return true;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param fileName
	 * @return 文件后缀名
	 */
	public static String getFileType(String fileName) {
		if (fileName != null) {
			int typeIndex = fileName.lastIndexOf(".");
			if (typeIndex != -1) {
				String fileType = fileName.substring(typeIndex + 1)
						.toLowerCase();
				return fileType;
			}
		}
		return "";
	}

	/**
	 * 根据后缀名判断是否是图片文件
	 * 
	 * @param type
	 * @return 是否是图片结果true or false
	 */
	public static boolean isImage(String type) {
		if (type != null
				&& (type.equals("jpg") || type.equals("gif")
						|| type.equals("png") || type.equals("jpeg")
						|| type.equals("bmp") || type.equals("wbmp")
						|| type.equals("ico") || type.equals("jpe"))) {
			return true;
		}
		return false;
	}
}
