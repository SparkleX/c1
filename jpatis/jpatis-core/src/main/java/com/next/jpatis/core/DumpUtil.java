package com.next.jpatis.core;

import java.lang.reflect.Field;
import java.util.List;

public class DumpUtil {
	private static final int columnLength = 10;

	public static void show(List<?> list) {
		try {
			StringBuilder sb = new StringBuilder();
			if (list.size() == 0) {
				sb.append("<EMPTY>\n");
			} else {
				List<Field> fields = JpaUtils.getFields(list.get(0).getClass());
				String line = new String(new char[(columnLength + 1) * fields.size() + 1]).replace("\0", "-");
				sb.append(line);
				sb.append("\r\n");
				showHeader(sb, fields);
				sb.append(line);
				sb.append("\r\n");
				showData(sb, fields, list);
				sb.append(line);
				sb.append("\r\n");
			}
			System.out.println(sb.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void showData(StringBuilder sb, List<Field> fields, List<?> list)
			throws IllegalArgumentException, IllegalAccessException {
		for (Object o : list) {
			sb.append("|");
			for (Field field : fields) {
				Object value = field.get(o);
				sb.append(String.format("%-10s|", value.toString()));
			}
			sb.append("\r\n");
		}
	}

	private static void showHeader(StringBuilder sb, List<Field> fields) {
		sb.append("|");
		for (Field field : fields) {
			String name = JpaUtils.getFieldName(field);
			sb.append(String.format("%-10s|", name));
		}
		sb.append("\r\n");

	}
}
