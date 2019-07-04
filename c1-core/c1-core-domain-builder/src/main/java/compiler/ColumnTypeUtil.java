package compiler;

import com.next.c1.schema.table.DbType;

public class ColumnTypeUtil 
{
	static public String getJavaType(DbType type)
	{
		switch(type)
		{
		case IDENTITY:
		case INTEGER:
			return "Integer";
		case TEXT:
		case ALPHA_NUMERIC: 
			return "String";
		case DECIMAL: 
			return "BigDecimal";
		case DATE: 
			return "LocalDateTime";
		}
		throw new RuntimeException(type.value());
	}
	static public String normalizeValidValue(String value){
		String rt = value.replaceAll("\\s", "");
		return rt;
	}	
}
