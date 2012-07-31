/**
 * 
 */
package com.lapatrialibre.twitter;

import java.util.Properties;

import com.lapatrialibre.util.XletUtils;
import com.lapatrialibre.xlet.XletLog;


/**
 * @author developer
 *
 */
public class TwitterUserInfo {
	private static final String TAG = TwitterUserInfo.class.getSimpleName();
	private static String PROP_NAME = "twitter.properties";
	private static String PROP_LOCATION = "/com/lapatrialibre/twitter/";
	
	private String username;
    private String password;
    private String twitterconsumerkey;
    private String twittersecretkey;
    
    private String tokenKeyFilePath;
      
    public String getTokenKeyFilePath() {
		return tokenKeyFilePath;
	}
	public void setTokenKeyFilePath(String tokenKeyFile) {
		this.tokenKeyFilePath = tokenKeyFile;
	}
	public String getUsername() {
		return username;
	}
	private void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	public String getTwitterconsumerkey() {
		return twitterconsumerkey;
	}
	private void setTwitterconsumerkey(String twitterconsumerkey) {
		this.twitterconsumerkey = twitterconsumerkey;
	}
	public String getTwittersecretkey() {
		return twittersecretkey;
	}
	private void setTwittersecretkey(String twittersecretkey) {
		this.twittersecretkey = twittersecretkey;
	}
	
	public void assignTwitterUserProps(String username,  String password) {
		System.out.println("[TwitterUserInfo] loading properties...");

		setTwittersecretkey("");
		setTwitterconsumerkey("");
		setUsername(username);
		setPassword(password);
		
	}
	
  
    
}
