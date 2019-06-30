package com.next.c1.reflect;

import org.apache.commons.lang3.reflect.FieldUtils;

public class FieldUtilsEx {
	static public Object readField(Object data, String col) {
		try {
			return FieldUtils.readField(data, col, true);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeField(Object target, String fieldName, Object value) {
		try {
			FieldUtils.writeField(target, fieldName, value, true);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
