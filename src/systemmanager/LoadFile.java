package systemmanager;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
public abstract class LoadFile {
	String loadFileFormat(File file) throws FileNotFoundException{
		try{
			Scanner fin = new Scanner(file);
			String temp = fin.nextLine();
			fin.close();
			return temp;
			
		}catch(Exception e) {
			System.out.println("The file was unable to turn into a scanner.");
			return "";
		}	
	}
	String getSection(String str, String section) {
		if(str.isEmpty() || section.isEmpty()) {
			return "";
		}
		return str.substring(0,str.indexOf(section)).trim();
	}
	String trimSection(String str, String section) {
		if(str.isEmpty()) {
			return "";
		}
		return str.substring(str.indexOf(section)).trim();
	}
	String [] getTime(String str) {
		return str.split(",");
	}
	
}
