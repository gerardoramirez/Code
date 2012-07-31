package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InsertNewCompany {

	private static Log _log = LogFactory.getLog(InsertNewCompany.class);

	public static boolean insertNewCompany(String newCompanyName) {
		System.out.println("[InsertNewCompany] adding a new company to available companies table..." + newCompanyName); // NOI18N
		boolean success = false;
	
		String server = ResourceBundle.getBundle("persistance.database").getString("server"); // NOI18N
		String database = ResourceBundle.getBundle("persistance.database").getString("database.name"); // NOI18N
		String username = ResourceBundle.getBundle("persistance.database").getString("username"); // NOI18N
		String password = ResourceBundle.getBundle("persistance.database").getString("password"); // NOI18N

		InsertBuilder insertNewComp = new InsertBuilder();

		insertNewComp.setTable("company");
		insertNewComp.addColumnAndData("company_name", ("\'" + newCompanyName + "\'"));

		String query = insertNewComp.insertSQLStatement(); // NOI18N
		System.out.println("[InsertNewCompany] the query is: " + query);

		MySQLDatabaseStrategy db = new MySQLDatabaseStrategy(server, database,username, password);
		String url = db.generateURL(); // NOI18N

		Connection con = null;
		Statement select = null;

		try {
			db.loadDriver();
			System.out.println("[InsertNewCompany] driver loaded");
		} catch (Exception e) {
			System.out.println("[InsertNewCompany] failed to load driver");
		}
		try {
			con = DriverManager.getConnection(url, db.getUser(), db.getPassword());
			select = con.createStatement();
			int insert = select.executeUpdate(query);
			if (insert == 1) {
				System.out.println("[InsertNewCompany] "
						+ "company was inserted into company table");
				success = true;
			} else {
				System.out.println("[InsertNewCompany] "
						+ "company was not inserted.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (select != null) {
					System.out
							.println("[InsertNewCompany] closing statement");
					select.close();
				}
			} catch (SQLException sqle) {
				_log.error(sqle);
			}
			try {
				if (con != null) {
					System.out
							.println("[InsertNewCompany] closing connection");
					con.close();
				}
			} catch (SQLException sqle) {
				_log.error(sqle);
			}

		}

		return success;
	}

}
