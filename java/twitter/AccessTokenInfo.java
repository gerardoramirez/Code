package com.lapatrialibre.twitter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.lapatrialibre.xlet.XletLog;

import twitter4j.http.AccessToken;

public abstract class AccessTokenInfo {
	
	private static String TAG = AccessTokenInfo.class.getSimpleName();
	private static int USER_ID = null;

	public static void storeAccessToken(AccessToken accessToken) {
		String filename = getFilename(USER_ID);

		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(accessToken);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static AccessToken loadAccessToken(String path) {
		XletLog.console(TAG, "read file from jar path: " + path);

		String filename = path + "/" + getFilename(USER_ID);

		AccessToken storedFile = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			storedFile = (AccessToken) in.readObject();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new AccessToken(storedFile.getToken(),
				storedFile.getTokenSecret());
	}

	public static String convertInteger(int i) {
		return Integer.toString(i);
	}

	public static String getFilename(int id) {
		return "key." + convertInteger(id);
	}

}
