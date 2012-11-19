import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;


public class PathSample2Attrs {

	public static void main(String args []) {
		FileSystem fs = FileSystems.getDefault();
		
		Set<String> views = fs.supportedFileAttributeViews();
		System.out.println("Supported File Attributes Views: ");			
		for (String view : views) {
			System.out.println(view);
		}
		
		BasicFileAttributes attr = null;
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		
		try {
			attr = Files.readAttributes(path, BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("File size: " + attr.size());
		System.out.println("File creation time: " + attr.creationTime());
		System.out.println("File last modified time: " + attr.lastModifiedTime());
		System.out.println("is a directory?  " + attr.isDirectory());
		System.out.println("is a regular file? " + attr.isRegularFile());
		System.out.println("is a symbolic link? " + attr.isSymbolicLink());
		System.out.println("is other? " + attr.isOther());
	}
}
