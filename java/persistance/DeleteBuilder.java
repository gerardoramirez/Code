package persistance;

public class DeleteBuilder extends SQLBuilder{
	
	private String category;
	private String searchValue;
	private String table;

	
	public String getCommand() {
		
		return "DELETE ";
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	    
	public String getCriteria()
	{ 
	   return " WHERE " + category + " = " + "\'" + searchValue + "\'";
	}

    public void setTable(String table) {
        this.table = table;
    }

    
    public String getTable()
    {
        return table;
    }
	
	public String getWhat() {
		return "";
	}
	
	public String searchSQLStatementwithWhereClause() {
		return getCommand() + getWhat() + " FROM " + table + getCriteria();
	}

	public String searchSQLStatement() {
		return getCommand() + getWhat() + " FROM " + table;
	}


}
