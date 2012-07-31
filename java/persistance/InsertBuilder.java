package persistance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InsertBuilder extends SQLBuilder {
	
	private String table;
	private Map columnsAndData = new HashMap();
	
	public void addColumnAndData(String columnName, Object value) 
	{
		if(value != null)
		{
			columnsAndData.put(columnName,value);
		}
	}
	public void setTable(String table) 
	{
		this.table= table;
	}
	
	public String getCommand() 
	{
		// TODO Auto-generated method stub
		return "INSERT INTO ";
	}

	
	public String getCriteria() 
	{
		// 
		// never used but since its apart of SQLBuilder we will always
		// return an empty String
		//
		return "";
	}

	@Override
	public String getTable() 
	{
		return table;
	}

	@Override
	public String getWhat() {
		StringBuffer columns = new StringBuffer();
		StringBuffer values = new StringBuffer();
		StringBuffer what = new StringBuffer();
		
		String columnName = null;
		
		Iterator iter = columnsAndData.keySet().iterator();
		while(iter.hasNext())
		{
			columnName = (String)iter.next();
			columns.append(columnName);
			values.append(columnsAndData.get(columnName));
			if(iter.hasNext())
			{
				columns.append(',');
				values.append(',');
			}
		}
		what.append(" (");
		what.append(columns);
		what.append(" ) VALUES (");
		what.append(values);
		what.append(") ");
		
		return what.toString();
	}
	public String insertSQLStatement() {
		return getCommand() + getTable() + getWhat();
	}

	
}
