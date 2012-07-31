package persistance;

public abstract class DatabaseStrategy {

	protected String server;
	protected String databasename;
	protected String user;
	protected String password;
	
	public DatabaseStrategy (String server, 
			String databaseName,
			String user,
			String password ) {
		this.server= server;
		this.databasename= databaseName;
		this.user= user;
		this.password= password;
	}
	public abstract void loadDriver() throws ClassNotFoundException;
	public abstract String generateURL();
}
