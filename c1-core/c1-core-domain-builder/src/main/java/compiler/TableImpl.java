package compiler;

import com.next.schema.table.Table;

public class TableImpl  extends Table
{
	public boolean isKey(String colName)
	{
		return (super.getKey().indexOf(colName)!=-1);
	}
}
