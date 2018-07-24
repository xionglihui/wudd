package com.xiong.dandan.common.util;

import java.util.Collection;

public class CollectionUtil {
	
	public static boolean isCollectionEmpty(Collection<?> c) {
		return (c == null || c.isEmpty());
	}
}
