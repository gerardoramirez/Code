package com.ranchoramirez.utilities.args;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.ranchoramirez.utilities.args.ArgsException.ErrorCode;

public class StringArgumentMarshaler implements ArgumentMarshaler {
	private String stringValue = "";
	
	@Override
	public void set(Iterator<String> currentArgument) throws ArgsException {
		try {
			stringValue = currentArgument.next();
		} catch (NoSuchElementException e) {
			throw new ArgsException(ErrorCode.MISSING_STRING);
		}
	}
	
	public static String getValue(ArgumentMarshaler am) {
		if(am != null && am instanceof StringArgumentMarshaler)
			return ((StringArgumentMarshaler) am).stringValue;
		else
			return "";
	}

}
