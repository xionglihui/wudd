package com.xiong.dandan.utilslibrary.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xiong.dandan.utilslibrary.tools.SystemLog;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Json数据解析
 * 
 * @author xionglh
 * @version GJsonUtil.java 2014年10月1日 上午8:39:04 v1.0.0 xionglihui
 */
public class GJsonUtil {

	private static Gson gson = new Gson();
	private static Gson expGson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();

	/**
	 * json转化成 java bean
	 * 
	 * @param json
	 * @return java bean of beanClass
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T toObject(String json, Type type) {
		if (json == null) {
			return null;
		}
		if (type == null) {
			return null;
		}
		Gson gsonMap = new Gson();
		T object;
		try {
			object = (T) gsonMap.fromJson(json, type);
		} catch (RuntimeException e) {
			throw e;
		}
		return object;
	}

	/**
	 * json转化成 java bean
	 * 
	 * @param json
	 * @param beanClass
	 * @return java bean of beanClass
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T toObject(String json, Class beanClass) {
		if (json == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}

		@SuppressWarnings("unused")
		T object;
		try {
			// json = URLDecoder.decode(json, "UTF-8");
			return (T) gson.fromJson(json, beanClass);
		} catch (Exception e) {
			e.printStackTrace();
			SystemLog.E("GsonUtil", e.getMessage());
		}
		return null;
	}

	/**
	 * java bean 转json
	 * 
	 * @param object
	 *            java bean
	 * @param beanClass
	 *            需要转化成json的bean对象的class
	 * @return json string
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(Object object, Class beanClass) {
		if (object == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}
		return gson.toJson(object, beanClass);
	}

	/**
	 * java bean 转json 排除属性
	 * 
	 * @param object
	 *            java bean
	 * @param beanClass
	 *            需要转化成json的bean对象的class
	 * @return json string
	 */
	@SuppressWarnings("rawtypes")
	public static String toJsonExp(Object object, Class beanClass) {
		if (object == null) {
			return null;
		}
		if (beanClass == null) {
			return null;
		}
		return expGson.toJson(object, beanClass);
	}

	/**
	 * Map转json数据
	 * 
	 * @param entity
	 *            HashMap
	 * @return json strings
	 */
	public static String mapToJson(Map<String, String> entity) {
		if (entity == null)
			return null;
		return gson.toJson(entity, HashMap.class);
	}

	/**
	 * Map转json数据
	 * 
	 * @param obj
	 *            HashMap
	 * @return json strings
	 */
	public static String objToJson(Object obj) {
		if (obj == null)
			return null;
		return gson.toJson(obj);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json) {
		return gson.fromJson(json, Map.class);
	}
}
