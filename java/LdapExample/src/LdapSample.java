import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LdapSample {
	
	public static void main(String[] args) {
	    try {
	        Hashtable<String, String> env = new Hashtable<String, String>();
	 
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); 
			env.put(Context.PROVIDER_URL,"ldap://64.54.42.95:389"); //replace with your server URL/IP
			 
	        // This line is optional based on environment. You can try with comment or without comment also.
	        env.put(Context.SECURITY_AUTHENTICATION, "Simple");
	        env.put(Context.SECURITY_PRINCIPAL,"boyt"); // the user name.	 
	        env.put(Context.SECURITY_CREDENTIALS, "PAssword123"); //the password.
	 
	        DirContext ctx = new InitialDirContext(env);
	        ctx.close();
	 
	    } catch(Exception e) {
	        System.out.println("Error authenticating user:");
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	        return;
	    }
	    //if no exception, the user is already authenticated.
	    System.out.println("OK, successfully authenticating user");
	    }
}
