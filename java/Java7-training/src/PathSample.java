import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathSample {

	public static void main(String args []) {
		FileSystem fileSystem = FileSystems.getDefault();	
		System.out.println("File system of current JVM " + fileSystem.toString());
		
		System.out.println("Defining a Path with absolute path: ");
		Path pathAbsolute = Paths.get("C:/development/Java7-training/src/Test.txt");
		System.out.println("Test file name " + pathAbsolute.getFileName());
		System.out.println("Parent Dir " + pathAbsolute.getParent());
		System.out.println();
		
		System.out.println("Defining a Path with absolute chunked path: ");
		Path pathAbsoluteChunked = Paths.get("C:", "development", "Java7-training");
		System.out.println("Test dir name: " + pathAbsoluteChunked.getFileName());
		System.out.println("Test dir absolute chunked parent dir: " + pathAbsoluteChunked.getParent());
		System.out.println();

		System.out.println("Defining the users home directory");
		Path usersHomeDir = Paths.get(System.getProperty("user.home"));
		System.out.println("user home dir name: " + usersHomeDir.getFileName());
		System.out.println("abolute path to home dir: " + usersHomeDir.toAbsolutePath());
		System.out.println("user downloads dir parent dir: " + usersHomeDir.getParent());
		System.out.println("get the root path of user home: " + usersHomeDir.getRoot());
		System.out.println("get number of elements in user home dir: " + usersHomeDir.getNameCount());
		System.out.println();
		
		for(int i = 0; i < usersHomeDir.getNameCount(); i++) {
			System.out.println("name element [" + i + "] is: " + usersHomeDir.getName(i) );
		}
		
		String pathToString = usersHomeDir.toString();
		System.out.println("path to string " + pathToString);
		URI pathToURI = usersHomeDir.toUri();
		System.out.println("path to URI: " + pathToURI);
		
		for(Path name : usersHomeDir) {
			System.out.println(name);
		}
	}
}
