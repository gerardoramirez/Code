package com.ranchoramirez.util;

import java.util.Hashtable;

public class Utils {
    
    private static Hashtable extension_map = new Hashtable();

    private static void setSuffix(String ext, String ct) {
	extension_map.put(ext, ct);
    }

    static {
	setSuffix("", "content/unknown");
	setSuffix(".uu", "application/octet-stream");
	setSuffix(".saveme", "application/octet-stream");
	setSuffix(".dump", "application/octet-stream");
	setSuffix(".hqx", "application/octet-stream");
	setSuffix(".arc", "application/octet-stream");
	setSuffix(".o", "application/octet-stream");
	setSuffix(".a", "application/octet-stream");
	setSuffix(".bin", "application/octet-stream");
	setSuffix(".exe", "application/octet-stream");
	setSuffix(".z", "application/octet-stream");
	setSuffix(".gz", "application/octet-stream");
	setSuffix(".oda", "application/oda");
	setSuffix(".pdf", "application/pdf");
	setSuffix(".eps", "application/postscript");
	setSuffix(".ai", "application/postscript");
	setSuffix(".ps", "application/postscript");
	setSuffix(".rtf", "application/rtf");
	setSuffix(".dvi", "application/x-dvi");
	setSuffix(".hdf", "application/x-hdf");
	setSuffix(".latex", "application/x-latex");
	setSuffix(".cdf", "application/x-netcdf");
	setSuffix(".nc", "application/x-netcdf");
	setSuffix(".tex", "application/x-tex");
	setSuffix(".texinfo", "application/x-texinfo");
	setSuffix(".texi", "application/x-texinfo");
	setSuffix(".t", "application/x-troff");
	setSuffix(".tr", "application/x-troff");
	setSuffix(".roff", "application/x-troff");
	setSuffix(".man", "application/x-troff-man");
	setSuffix(".me", "application/x-troff-me");
	setSuffix(".ms", "application/x-troff-ms");
	setSuffix(".src", "application/x-wais-source");
	setSuffix(".wsrc", "application/x-wais-source");
	setSuffix(".zip", "application/zip");
	setSuffix(".bcpio", "application/x-bcpio");
	setSuffix(".cpio", "application/x-cpio");
	setSuffix(".gtar", "application/x-gtar");
	setSuffix(".shar", "application/x-shar");
	setSuffix(".sh", "application/x-shar");
	setSuffix(".sv4cpio", "application/x-sv4cpio");
	setSuffix(".sv4crc", "application/x-sv4crc");
	setSuffix(".tar", "application/x-tar");
	setSuffix(".ustar", "application/x-ustar");
	setSuffix(".snd", "audio/basic");
	setSuffix(".au", "audio/basic");
	setSuffix(".aifc", "audio/x-aiff");
	setSuffix(".aif", "audio/x-aiff");
	setSuffix(".aiff", "audio/x-aiff");
	setSuffix(".wav", "audio/x-wav");
	setSuffix(".gif", "image/gif");
	setSuffix(".ief", "image/ief");
	setSuffix(".jfif", "image/jpeg");
	setSuffix(".jfif-tbnl", "image/jpeg");
	setSuffix(".jpe", "image/jpeg");
	setSuffix(".jpg", "image/jpeg");
	setSuffix(".jpeg", "image/jpeg");
	setSuffix(".tif", "image/tiff");
	setSuffix(".tiff", "image/tiff");
	setSuffix(".ras", "image/x-cmu-rast");
	setSuffix(".pnm", "image/x-portable-anymap");
	setSuffix(".pbm", "image/x-portable-bitmap");
	setSuffix(".pgm", "image/x-portable-graymap");
	setSuffix(".ppm", "image/x-portable-pixmap");
	setSuffix(".rgb", "image/x-rgb");
	setSuffix(".xbm", "image/x-xbitmap");
	setSuffix(".xpm", "image/x-xpixmap");
	setSuffix(".xwd", "image/x-xwindowdump");
	setSuffix(".htm", "text/html");
	setSuffix(".html", "text/html");
	setSuffix(".text", "text/plain");
	setSuffix(".c", "text/plain");
	setSuffix(".cc", "text/plain");
	setSuffix(".c++", "text/plain");
	setSuffix(".h", "text/plain");
	setSuffix(".pl", "text/plain");
	setSuffix(".txt", "text/plain");
	setSuffix(".java", "text/plain");
	setSuffix(".rtx", "application/rtf");
	setSuffix(".tsv", "texyt/tab-separated-values");
	setSuffix(".etx", "text/x-setext");
	setSuffix(".mpg", "video/mpeg");
	setSuffix(".mpe", "video/mpeg");
	setSuffix(".mpeg", "video/mpeg");
	setSuffix(".mov", "video/quicktime");
	setSuffix(".qt", "video/quicktime");
	setSuffix(".avi", "application/x-troff-msvideo");
	setSuffix(".movie", "video/x-sgi-movie");
	setSuffix(".mv", "video/x-sgi-movie");
	setSuffix(".mime", "message/rfc822");
    }

    /**
     * A useful utility routine that tries to guess the content-type
     * of an object based upon its extension.
     */
    public static String guessContentTypeFromName(String fname) {
	String ext = "";
	int i = fname.lastIndexOf('#');

	if (i != -1)
	    fname = fname.substring(0, i - 1);
	i = fname.lastIndexOf('.');
	i = Math.max(i, fname.lastIndexOf('/'));
	i = Math.max(i, fname.lastIndexOf('?'));

	if (i != -1 && fname.charAt(i) == '.') {
	    ext = fname.substring(i).toLowerCase();
	}
	return (String) extension_map.get(ext);
    }

//    public static MimeType getMimeType(String filename) {
//	try {
//	    return new MimeType(guessContentTypeFromName(filename));
//	} catch (MimeTypeFormatException ex) {
//	    return null;
//	}
//    }
}