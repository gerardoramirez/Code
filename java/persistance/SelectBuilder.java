package persistance;


import java.util.ArrayList;
import java.util.Iterator;



public class SelectBuilder extends SQLBuilder
{
    private String table;
    private String category;
    private String searchValue;
        
    public ArrayList<String> columns  = new ArrayList<String>();
    
    public void addColumns(String column) 
    {        
       columns.add(column);
    }

    @Override
    public String getCommand()
    {
        // TODO Auto-generated method stub
        return "SELECT ";
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    public void setSearchValue(String searchValue)
    {
        this.searchValue = searchValue;
    }
    
    @Override
    public String getCriteria()
    {
          return " WHERE " + category + " = " + "\'" + searchValue + "\'";
    }
    public void setTable(String table) {
        this.table = table;
    }

    @Override
    public String getTable()
    {
        return table;
    }

    @Override
    public String getWhat()
    {    
        StringBuffer buffer = new StringBuffer();
        String delimiter = ",";
        for(Iterator i=this.columns.iterator();i.hasNext();) 
        {
            String oneItem = (String)i.next();
            //System.out.println("name: " + oneItem);
            if(oneItem.indexOf(delimiter) < 0)
            {
                if(buffer.length() > 0)
                {
                    buffer.append(delimiter);
                }
                buffer.append(oneItem);
            }
        }
        return buffer.toString();
    }
    
    public String searchSQLStatementwithWhereClause()
    {
        return getCommand() + getWhat() + " FROM " + table +  getCriteria();
    }
    public String searchSQLStatement()
    {
        return getCommand() + getWhat() + " FROM " + table;
    }

}
