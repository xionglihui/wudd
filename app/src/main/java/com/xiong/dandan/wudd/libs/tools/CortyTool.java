/**
 * 
 */
package com.xiong.dandan.wudd.libs.tools;

import android.os.Environment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author xionglh
 * 
 * @version CortyTool.java Created by xionglh on 2015年4月8日 上午11:05:23 v1.0.3
 * 
 */
public class CortyTool {
	
	/**
	 * 将byte数组转换为表示16进制值的字符串，如：byte[]{8,18}转换为：0813， 和public static byte[]
	 * hexStrToByteArr(String strIn) 互为可逆的转换过程
	 * 
	 * @param arrB
	 *            需要转换的byte数组
	 * @return 转换后的字符串
	 */
	public static String byteArrToHexStr(byte[] arrB) {
		int iLen = arrB.length;
		// 每个byte(8位)用两个(16进制)字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while (intTmp < 0) {// 把负数转换为正数
				intTmp = intTmp + 256;
			}
			if (intTmp < 16) {// 小于0F的数需要在前面补0
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
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
	/**
	 * 将表示16进制值的字符串转换为byte数组，和public static String byteArrToHexStr(byte[] arrB)
	 * 互为可逆的转换过程
	 * 
	 * @param strIn
	 *            需要转换的字符串
	 * @return 转换后的byte数组
	 */
	public static byte[] hexStrToByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		// 两个(16进制)字符表示一个字节(8位)，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * MD5加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptMD5(String data) {

		MessageDigest mdInst = null;
		try {
			mdInst = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		mdInst.update(data.getBytes());// 使用指定的字节更新摘要
		byte[] md = mdInst.digest();// 获得密文
		return byteArrToHexStr(md);
	}

}
