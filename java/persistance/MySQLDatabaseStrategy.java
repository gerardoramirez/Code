package persistance;


public class MySQLDatabaseStrategy extends DatabaseStrategy {

	public MySQLDatabaseStrategy(String server, String databaseName, String user,
			String password) {
		super(server, databaseName, user, password);
	}

	
	public String generateURL() {
		// returns url to access the MySQL database server
		return "jdbc:mysql://" + server + "/" + databasename;
	}

	
	public void loadDriver() throws ClassNotFoundException {
		// loads mysql driver
		Class.forName("com.mysql.jdbc.Driver");
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	

	
}
