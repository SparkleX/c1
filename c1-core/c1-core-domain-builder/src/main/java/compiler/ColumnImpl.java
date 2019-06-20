package compiler;

import com.next.schema.table.Column;

public class ColumnImpl extends Column
{
	public String getJavaType()
	{
		return ColumnTypeImpl.getJavaType(this.getType());
	}
}
