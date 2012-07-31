package persistance;

public abstract class SQLBuilder {
	
	public abstract String getCommand();
	public abstract String getTable();
	public abstract String getWhat();
	public abstract String getCriteria();

}
