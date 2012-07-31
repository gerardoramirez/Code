package com.ranchoramirez.util;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author gramirez
 */
public class FileUtil {

	/**
	 * Method to copy one file from source to destination
	 * @param source file source
	 * @param destination file destination
	 * @throws IOException
	 * @throws Exception
	 */
    public static void copy(String source, String destination) throws IOException, Exception
    {
        System.out.println("[FileIOUtil] executing copy...  ");

        File source_file = new File(source);
        File desti_file = new File(destination);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buffer;
        int byte_read;

        try
        {
            /* first check that file exists or not. */
            if (!source_file.exists() || !source_file.isFile())
            {
                throw new Exception("No source file found:" + source);
            }
            else
            {
                System.out.println("[FileUtil] source file found.  ");
            }

            /* check that the file is readable or not. */
            if (!source_file.canRead())
            {
                throw new Exception("Source file is unreadable: " + source);
            }
            else
            {
                System.out.println("[FileUtil] source file readable.  ");
            }

            /*
             * If the destination exists, make sure it is a writable file and
             * ask before overwriting it. If the destination doesn't exist, make
             * sure the directory exists and is writable.
             */
            if (desti_file.exists())
            {
                System.out.println("[FileUtil] destination file exists.  ");
                if (desti_file.isFile())
                {
                    System.out.println("[FileUtil] destination file is not a dir.  ");
                    DataInputStream in = new DataInputStream(System.in);

                    if (!desti_file.canWrite())
                    {
                        throw new Exception("Destination is unwriteable : " + destination);
                    }
                    System.out.print("File " + destination + " already exists. Overwrite?(Y/N): ");
                    System.out.flush();
                    String response = in.readLine();
                    if (!response.equals("Y") && !response.equals("y"))
                    {
                        throw new Exception("Wrong Input.");
                    }
                }
                else
                {
                    throw new Exception("Destination is not a normal file: " + destination);
                }
            }
            else
            {
                File parentdir = parent(desti_file);
                if (!parentdir.exists())
                {
                    throw new Exception("No Destination directory exist: " + destination);
                }
                if (!parentdir.canWrite())
                {
                    throw new Exception("Destination directory is unwriteable: " + destination);
                }
            }

            /*
             * Now we have checked all the things so we can copy the file now.
             */
            fis = new FileInputStream(source_file);
            fos = new FileOutputStream(desti_file);
            buffer = new byte[1024];
            while (true)
            {
                byte_read = fis.read(buffer);
                if (byte_read == -1)
                {
                    break;
                }
                fos.write(buffer, 0, byte_read);
            }
        }

        /* Finally close the stream. */
        finally
        {
            fis.close();
            fos.close();
        }

        // Access last modification date of destination file.
        System.out.print("Last modification date of destination file : ");
        System.out.println(new Date(desti_file.lastModified()));
    }

    /*
     * File.getParent() can return null when the file is specified without a
     * directory or is in the root directory. This method handles those cases.
     */
    private static File parent(File f)
    {
        String dirname = f.getParent();
        if (dirname == null)
        {
            if (f.isAbsolute())
            {
                return new File(File.separator);
            }
            else
            {
                return new File(System.getProperty("user.dir"));
            }
        }
        return new File(dirname);
    }

    public static int removeFiles(File directory, int numberToDelete) {
       int count = 0;
       File removeOldest[] = listFilesByAscendingDate(directory);

       if( numberToDelete == 1)
       {
           //
           // Delete first file
           removeOldest[0].delete();
       }
       else
       {
    	   if(numberToDelete < 0)
    		   numberToDelete = 0;
         
           for(int i = 0; i < numberToDelete; i++)
           {
           System.out.println("[FileUtil] oldest files in ascending order: " + removeOldest[i].getName());
           removeOldest[i].delete();
           count++;
           }
       }

       return count;

    }


    @SuppressWarnings("unchecked")
    public static File[] listFilesByAscendingDate(File directory) {
        if (!directory.isDirectory()) {
          return null;
        }
        File files[] = directory.listFiles();
        Arrays.sort( files, new Comparator()
        {
          public int compare(final Object o1, final Object o2) {
            return new Long(((File)o1).lastModified()).compareTo
                 (new Long(((File) o2).lastModified()));
          }
        });
        return files;
      }

      @SuppressWarnings("unchecked")
      public static File[] listFilesByDescendingDate(File folder) {
        if (!folder.isDirectory()) {
          return null;
        }
        File files[] = folder.listFiles();
        Arrays.sort( files, new Comparator()
        {
          public int compare(final Object o1, final Object o2) {
            return new Long(((File)o2).lastModified()).compareTo
                 (new Long(((File) o1).lastModified()));
          }
        });
        return files;
      }
      
      public static void cleanUpDir(String directory) {
    	  File parentDir = new File(directory);
    	  File [] dir = parentDir.listFiles();
    	  
    	  for(int g = 0; g < dir.length; g++) {
    		  dir[g].delete();
    	  }
    	  return;
      }
      
      private static String getExtension(File file) {
          String name = file.getName();
          int i = name.lastIndexOf(".");
          return (i != -1) ? name.substring(i + 1) : name; 
      }
      
}
