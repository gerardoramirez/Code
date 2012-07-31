package persistance;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateBuilder extends SQLBuilder {
    public String table;
    public String columnToModify;
    
    //attributes used to modify
    public String id;
    public String value;
    
    
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getColumnToModify()
    {
        return columnToModify;
    }
    public void setColumnToModify(String columnToModify)
    {
        this.columnToModify = columnToModify;
    }
   
    private Map columnsAndData = new HashMap();
    
    public void addColumnAndData(String columnName, Object value) 
    {
        if(value != null)
        {
            columnsAndData.put(columnName,value);
        }
    }
	@Override
	public String getCommand() {
		// TODO Auto-generated method stub
		return "UPDATE ";
	}


	public String getCriteriaWithValue() {
		// TODO Auto-generated method stub
		return getCriteria() + getColumnToModify() + " = " + value;
	}
	public String getCriteriaWithId() {
        // TODO Auto-generated method stub
        return getCriteria() + getColumnToModify() + " = " + id;
    }
	
    @Override
    public String getCriteria()
    {
        // TODO Auto-generated method stub
        return " WHERE ";
    }
	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return table;
	}
	public void setTable(String table) {
	    this.table = table;
	}

    @Override
    public String getWhat() {
  
        StringBuffer columnPlusValue = new StringBuffer();
        StringBuffer what = new StringBuffer();
        
        String columnName = null;
        
        Iterator iter = columnsAndData.keySet().iterator();
        columnPlusValue.append(" SET ");
        
        while(iter.hasNext())
        {
            columnName = (String)iter.next();
            //append the name of the column
            columnPlusValue.append(columnName);
            columnPlusValue.append('=');
            //append the value 
            columnPlusValue.append(columnsAndData.get(columnName));
            if(iter.hasNext())
            {
                columnPlusValue.append(',');
            }
        }
        
        return columnPlusValue.toString();
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String updateSQLStatement() {
    	return getCommand() + getTable() + getWhat() + getCriteriaWithValue();
    }

}
