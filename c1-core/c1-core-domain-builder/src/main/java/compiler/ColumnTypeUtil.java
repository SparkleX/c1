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
			return "Integer";
		case DECIMAL: 
			return "BigDecimal";
		case DATE: 
			return "Date";
		}
		throw new RuntimeException(type.value());
	}
}
