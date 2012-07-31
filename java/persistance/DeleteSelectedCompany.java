package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DeleteSelectedCompany {
	private static Log _log = LogFactory.getLog(DeleteSelectedCompany.class);
	
	public static boolean removeRange(String rangeID)
	{
		 System.out.println("[DeleteSelectedRange] executing removal of newly assigned range..."); //NOI18N
		 boolean success = false;
		 
	     String server = ResourceBundle.getBundle("persistance.database").getString("server"); //NOI18N
	     String database = ResourceBundle.getBundle("persistance.database").getString("database.name"); //NOI18N
	     String username = ResourceBundle.getBundle("persistance.database").getString("username"); //NOI18N
	     String password = ResourceBundle.getBundle("persistance.database").getString("password"); //NOI18N
	    
	     DeleteBuilder deleteRange = new DeleteBuilder();
	     
	     deleteRange.setTable("available_productID_ranges");
	     deleteRange.setCategory("P_Id");
	     deleteRange.setSearchValue(rangeID);
	     
	     String query = deleteRange.searchSQLStatementwithWhereClause(); //NOI18N
	     System.out.println("[DeleteSelectedRange] the query is: " + query);
	     
	     MySQLDatabaseStrategy db = new MySQLDatabaseStrategy(server, database, username, password);
	     String url = db.generateURL(); //NOI18N
	 
	     Connection con = null;
	     Statement select = null;
	     
	     try
	        {
	            db.loadDriver();
	            System.out.println("[DeleteSelectedRange] driver loaded"); 
	        }
	        catch (Exception e)
	        {
	            System.out.println("[DeleteSelectedRange] failed to load driver");
	        }
	        try
	        {
	            con = DriverManager.getConnection(url, db.getUser(), db.getPassword());
	            select = con.createStatement();
	            int delete = select.executeUpdate(query);
	            if(delete==1)
	            {
	            	System.out.println("[DeleteSelectedRange] " + "Range removed from available list");
	            	success = true;
	            }
	            else
	            {
	            	System.out.println("[DeleteSelectedRange] " + "Range was not removed.");
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
	                if (select != null)
	                {
	                    System.out.println("[DeleteSelectedRange] closing statement");
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
	                    System.out.println("[DeleteSelectedRange] closing connection");
	                    con.close();
	                }
	            }
	            catch (SQLException sqle)
	            {
	                _log.error(sqle);
	            }

	        }
	     
	     return success;
	}
	

}
