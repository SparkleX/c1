package com.next.jpatis.ddl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.next.jpatis.core.JpaUtils;

public class DdlUtil {
	public static String createTable(Class<?> clazz) {
		String tableName = JpaUtils.getTableName(clazz);
		List<Field> fields = JpaUtils.getFields(clazz);
		StringBuilder fieldList = new StringBuilder();
		for(Field f:fields)	{
			fieldList.append("\"").append(JpaUtils.getFieldName(f)).append("\" ").append(getDbType(f.getType())).append(",");
		}
		
		String sql = String.format("create table %s(%s primary key(\"id\"))", tableName, fieldList.toString());
		return sql;
		
	}

	private static String getDbType(Class<?> type) {
		if(type.equals(Integer.class)) {
			return "int";
		}
		if(type.equals(String.class)) {
			return "nvarchar(255)";
		}
		
		if(type.equals(BigDecimal.class)) {
			return "decimal(19,6)";
		}
		if(type.equals(Timestamp.class)) {
			return "timestamp";
		}
		if(type.equals(LocalDateTime.class)) {
			return "datetime";
		}		
		
		throw new RuntimeException(String.format("invalid type %s",type.getName()));
	}
}
