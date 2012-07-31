/**
 * 
 */
package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author gramirez
 *
 */
public class SelectCompany
{
    private static Log _log = LogFactory.getLog(SelectCompany.class);

    public static String getCompany(String searchValue)
    {
    	//
    	//todo:trim the search value to a fixed length 
    	//
        System.out.println("[SelectCompany] executing search for company...");
        String companyID = "not found";
        
		String server = ResourceBundle.getBundle("persistance.database").getString("server"); // NOI18N
		String database = ResourceBundle.getBundle("persistance.database").getString("database.name"); // NOI18N
		String username = ResourceBundle.getBundle("persistance.database").getString("username"); // NOI18N
		String password = ResourceBundle.getBundle("persistance.database").getString("password"); // NOI18N

        SelectBuilder selectCompany = new SelectBuilder();

        selectCompany.addColumns("company_name");
        selectCompany.addColumns("company_id");
        selectCompany.setTable("company");

        String query = selectCompany.searchSQLStatement();
        System.out.println("[SelectCompany] the query is: " + query);

        MySQLDatabaseStrategy db = new MySQLDatabaseStrategy(server, database, username, password);
        String url = db.generateURL();
        System.out.println("database url: " + db.generateURL());

        Connection con = null;
        ResultSet result = null;
        Statement select = null;

        try
        {
            db.loadDriver();
            System.out.println("[SelectCompany] driver loaded");
        }
        catch (Exception e)
        {
            System.out.println("[SelectCompany] failed to load driver");
        }
        try
        {
            con = DriverManager.getConnection(url, db.getUser(), db.getPassword());
			select = con.createStatement();

			if (select.executeQuery(query) != null) {
				System.out.println("[SelectCompany] resultset is " + select.executeQuery(query));
				result = select.executeQuery(query);
				while (result.next()) {
					if (result.getString("company_name").equals(searchValue))
					{
						//System.out.println("[SelectCompany] located companyID");
						companyID = result.getString("company_id");
						break;
					}
					else
					{
						//System.out.println("[SelectCompany] could not locate companyID");
						companyID = "not found";
					}
				}
			} 
        }
        catch (Exception e)
        {
        	
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (result != null)
                {
                    System.out.println("[SelectCompany] closing resultSet");
                    result.close();
                }
            }
            catch (SQLException sqle)
            {
                _log.error(sqle);
            }
            try
            {
                if (select != null)
                {
                    System.out.println("[SelectCompany] closing statement");
                    select.close();
                }
            }
            catch (SQLException sqle)
            {
                _log.error(sqle);
            }
            try
            {
                if (con != null)
                {
                    System.out.println("[SelectCompany] closing connection");
                    con.close();
                }
            }
            catch (SQLException sqle)
            {
                _log.error(sqle);
            }

        }
        System.out.println("[SelectCompany] companyID " + companyID);
        return companyID;

    }

    public static void main(String args[]) {
    	getCompany("Ricoh");
    }
}
//jdbc:mysql://localhost:3306/javabase