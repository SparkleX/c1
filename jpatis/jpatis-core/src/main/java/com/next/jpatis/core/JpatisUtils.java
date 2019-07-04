package com.next.jpatis.core;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class JpatisUtils {
	public static void initWithDefault(Object o) {
		if(o==null) {
			return;
		}
		Field[] fields = FieldUtils.getAllFields(o.getClass());
		for(Field field:fields) {
			DefaultValue aDefault = field.getAnnotation(DefaultValue.class);
			if(aDefault==null) {
				continue;
			}
			String str = aDefault.value();
			Object value = ConvertUtils.convert(str, field.getType());
			try {
				FieldUtils.writeField(field,  o, value, true);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
