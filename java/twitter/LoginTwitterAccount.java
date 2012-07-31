package com.lapatrialibre.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;

import com.lapatrialibre.exception.TweetException;
import com.lapatrialibre.xlet.XletApplication;
import com.lapatrialibre.xlet.XletLog;
 
/**
 * @author gramirez
 *
 */
public class LoginTwitterAccount {
	private static String TAG = LoginTwitterAccount.class.getSimpleName();
	
	TwitterUserInfo twitterUser = null;
	TwitterFactory factory = null;
	Twitter twitter = null;
    AccessToken accessToken = null;
    Status status = null;
	
	public LoginTwitterAccount () {
		factory = new TwitterFactory();
		twitter = factory.getInstance();
		twitterUser = new TwitterUserInfo();
	}	
	
	public void postTweet(String message) {
		XletLog.console(TAG, "post tweet to tweeter account");
		try {
			status = twitter.updateStatus(message);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	public boolean setTwitterUser(String username, String password, String path) throws TweetException {		
		//
		// lets authenticate user
		twitterUser.assignTwitterUserProps(username, password);
		twitterUser.setTokenKeyFilePath(path);
		XletLog.console(TAG, "valid user...");

		return authenticateTwitterUser(path);
	}
	
	public boolean authenticateTwitterUser(String path) {
		XletLog.console(TAG, "authenticateTwitterUser(path)");		
		boolean authenticated = false;
		
	    twitter.setOAuthConsumer(twitterUser.getTwitterconsumerkey(), twitterUser.getTwittersecretkey());   
	    
	    if(XletApplication.debugOn()) {
	    XletLog.console(TAG, "load access token info from path: " + path);
	    }
	    accessToken = AccessTokenInfo.loadAccessToken(path);
	    
	    twitter.setOAuthAccessToken(accessToken);
	    //
	    // TODO: figure out the a better method of checking if user is logged in.
	    //       right now, we see if we can get a screen name from twitter object
	    //
	    String screenName;
		try {
			screenName = twitter.getScreenName();
			XletLog.console(TAG, "screen name is " + screenName);
		    if(screenName != null) {
		    	authenticated = true;
		    }
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return authenticated;
	}
	

}