package com.xiong.dandan.common.tools;

import com.google.common.io.BaseEncoding;

public class Base64Utils {

	public static byte[] decode(String str) {
		return BaseEncoding.base64().decode(str);
	}

	public static String encode(byte[] bytes) {
		return BaseEncoding.base64().encode(bytes);
	}
}
