package com.ranchoramirez.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.Vector;

public class StringUtil {
	

	 private static final char[] HEX_DIGITS =
	  {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	  /**
	   * Convenience call for {@link #toHexString(byte[], String, int)}, where
	   * <code>sep = null; lineLen = Integer.MAX_VALUE</code>.
	   * @param buf
	   */
	  public static String toHexString(byte[] buf) {
	    return toHexString(buf, null, Integer.MAX_VALUE);
	  }

	  /**
	   * Get a text representation of a byte[] as hexadecimal String, where each
	   * pair of hexadecimal digits corresponds to consecutive bytes in the array.
	   * @param buf input data
	   * @param sep separate every pair of hexadecimal digits with this separator, or
	   * null if no separation is needed.
	   * @param lineLen break the output String into lines containing output for lineLen
	   * bytes.
	   */
	  public static String toHexString(byte[] buf, String sep, int lineLen) {
	    if (buf == null) return null;
	    if (lineLen <= 0) lineLen = Integer.MAX_VALUE;
	    StringBuffer res = new StringBuffer(buf.length * 2);
	    for (int i = 0; i < buf.length; i++) {
	      int b = buf[i];
	      res.append(HEX_DIGITS[(b >> 4) & 0xf]);
	      res.append(HEX_DIGITS[b & 0xf]);
	      if (i > 0 && (i % lineLen) == 0) res.append('\n');
	      else if (sep != null && i < lineLen - 1) res.append(sep); 
	    }
	    return res.toString();
	  }
	  
	  /**
	   * Convert a String containing consecutive (no inside whitespace) hexadecimal
	   * digits into a corresponding byte array. If the number of digits is not even,
	   * a '0' will be appended in the front of the String prior to conversion.
	   * Leading and trailing whitespace is ignored.
	   * @param text input text
	   * @return converted byte array, or null if unable to convert
	   */
	  public static byte[] fromHexString(String text) {
	    text = text.trim();
	    if (text.length() % 2 != 0) text = "0" + text;
	    int resLen = text.length() / 2;
	    int loNibble, hiNibble;
	    byte[] res = new byte[resLen];
	    for (int i = 0; i < resLen; i++) {
	      int j = i << 1;
	      hiNibble = charToNibble(text.charAt(j));
	      loNibble = charToNibble(text.charAt(j + 1));
	      if (loNibble == -1 || hiNibble == -1) return null;
	      res[i] = (byte)(hiNibble << 4 | loNibble);
	    }
	    return res;
	  }
	  
	  private static final int charToNibble(char c) {
	    if (c >= '0' && c <= '9') {
	      return c - '0';
	    } else if (c >= 'a' && c <= 'f') {
	      return 0xa + (c - 'a');
	    } else if (c >= 'A' && c <= 'F') {
	      return 0xA + (c - 'A');
	    } else {
	      return -1;
	    }
	  }

    /**
     *
     * Get a hex value of a char.
     * @param c Char.
     */
    public static String getHexChar(int c) {
        return (c < 16 ? "%0" : "%") + Integer.toHexString(c).toUpperCase();
    }
    
    /**
     * 
     * Verifies whether a given string is empty (length = 0 or null or == blank).
     * 
     * @param str String.
     * @return Empty (true).
     */
    public static final boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || (str.equals(""));
    }
    
    public static final String lineSeparator =
        System.getProperty("line.separator", "\n");
      public static final String lineSeparatorStr = cleanString(lineSeparator);

      // Ensure that escape sequences are passed through properly.
      public static String cleanString(String str)
      {
        if (str == null) {
    		return null;
    	} else
        {
          char[]       charArray = str.toCharArray();
          StringBuffer sBuf      = new StringBuffer();

          for (char element : charArray) {
    		switch (element)
            {
              case '\"' : sBuf.append("\\\"");
                          break;
              case '\\' : sBuf.append("\\\\");
                          break;
              case '\n' : sBuf.append("\\n");
                          break;
              case '\r' : sBuf.append("\\r");
                          break;
              default   : sBuf.append(element);
                          break;
            }
    	}

          return sBuf.toString();
        }
      }

      /*
        This method will return the correct name for a class object representing
        a primitive, a single instance of a class, as well as n-dimensional arrays
        of primitives or instances. This logic is needed to handle the string returned
        from Class.getName(). If the class object represents a single instance (or
        a primitive), Class.getName() returns the fully-qualified name of the class
        and no further work is needed. However, if the class object represents an
        array (of n dimensions), Class.getName() returns a Descriptor (the Descriptor
        grammar is defined in section 4.3 of the Java VM Spec). This method will
        parse the Descriptor if necessary.
      */
      public static String getClassName(Class<?> targetClass)
      {
        String className = targetClass.getName();

        return targetClass.isArray() ? parseDescriptor(className) : className;
      }

      /*
        See the comment above for getClassName(targetClass)...
      */
      private static String parseDescriptor(String className)
      {
        char[] classNameChars = className.toCharArray();
        int    arrayDim       = 0;
        int    i              = 0;

        while (classNameChars[i] == '[')
        {
          arrayDim++;
          i++;
        }

        StringBuffer classNameBuf = new StringBuffer();

        switch (classNameChars[i++])
        {
          case 'B' : classNameBuf.append("byte");
                     break;
          case 'C' : classNameBuf.append("char");
                     break;
          case 'D' : classNameBuf.append("double");
                     break;
          case 'F' : classNameBuf.append("float");
                     break;
          case 'I' : classNameBuf.append("int");
                     break;
          case 'J' : classNameBuf.append("long");
                     break;
          case 'S' : classNameBuf.append("short");
                     break;
          case 'Z' : classNameBuf.append("boolean");
                     break;
          case 'L' : classNameBuf.append(classNameChars,
                                         i, classNameChars.length - i - 1);
                     break;
        }

        for (i = 0; i < arrayDim; i++) {
    		classNameBuf.append("[]");
    	}

        return classNameBuf.toString();
      }

      /*
      @param contextURL the context in which to attempt to resolve the spec.
      Effectively a document base.
      */
      public static URL getURL(URL contextURL, String spec)
        throws MalformedURLException
      {
        try
        {
          return new URL(contextURL, spec);
        }
        catch (MalformedURLException e)
        {
          File tempFile = new File(spec);
          if (contextURL == null ||
              (contextURL != null && tempFile.isAbsolute()))
          {
            return tempFile.toURI().toURL();
          }

          // only reach here if the contextURL !null, spec is relative path and
          // MalformedURLException thrown
          throw e;
        }
      }

      /*
        Returns an InputStream for reading from the specified resource, if the
        resource points to a stream.
      */
      public static InputStream getContentAsInputStream(URL url)
        throws SecurityException,
               IllegalArgumentException,
               IOException
      {
        if (url == null)
        {
          throw new IllegalArgumentException("URL cannot be null.");
        }

        try
        {
          InputStream content = url.openStream();

          if (content == null)
          {
            throw new IllegalArgumentException("No content.");
          }
            return content;
          }
        catch (SecurityException e)
        {
          throw new SecurityException("Your JVM's SecurityManager has " +
                                      "disallowed this.");
        }
        catch (FileNotFoundException e)
        {
          throw new FileNotFoundException("This file was not found: " + url);
        }
      }

      public static List<String> parseNMTokens(String nmTokens)
      {
        StringTokenizer strTok = new StringTokenizer(nmTokens, " ");
        List<String> tokens = new Vector<String>();

        while (strTok.hasMoreTokens())
        {
          tokens.add(strTok.nextToken());
        }

        return tokens;
      }

      public static String getNMTokens(List<String> list)
      {
        if (list != null)
        {
          StringBuffer strBuf = new StringBuffer();
          int size = list.size();

          for (int i = 0; i < size; i++)
          {
            String token = list.get(i);

            strBuf.append((i > 0 ? " " : "") + token);
          }

          return strBuf.toString();
        }
        else
        {
          return null;
        }
      }
      
      /**
       * Create a query String using the given parameters.
       * <pre>
       * Map<String,String> map = new LinkedHashMap<String,String>();
       * map.put("k1", "value1");
       * map.put("k2", "value2");
       * 
       * String s = StringUtils.createQueryString(params);
       * 
       * // => "?k1=value1&k2=value2"
       * </pre>
       * @param params
       * @return Query String
       */
      public static String createQueryString(Map<String,String> params) {
          if(null == params || params.isEmpty()) {
              return "";
          }
          StringBuilder sb = new StringBuilder();
          if (!params.isEmpty()) {
              sb.append("?");
              try {

                  for (Iterator<Entry<String, String>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
                      final Entry<String, String> entry = iterator.next();
                      sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                      sb.append("=");
                      sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                      if (iterator.hasNext()) {
                          sb.append("&");
                      }
                  }
              } catch (UnsupportedEncodingException ue) {
                  // ignore - UTF-8 is mandatory
              }
          }
          return sb.toString();
      }
}
