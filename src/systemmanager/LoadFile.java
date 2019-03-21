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
	String getAirlineName(String transport) {
		if(transport.indexOf("[") <= 6) {
			return transport.substring(0,transport.indexOf("[")).replaceAll("[^A-Z]", "");
		
		}
		return "";
	}
	String getTravel(String transport,boolean cutTransport) {
		int rightBracket = 0, leftBracket = 0;
		String output = "";
		while(!transport.isEmpty()) {
			if(transport.charAt(0) == '[') {
				rightBracket++;
			}
			else if(transport.charAt(0) == ']') {
				leftBracket++;
			}
			
			output += transport.charAt(0);
			transport = transport.substring(1);
			if(output.equals("[]")) {
				return "";
			}
			if(rightBracket == leftBracket || transport.isEmpty()) {
				if(cutTransport == true) {
					return transport;
				}
				else {
					return output;
				}
			}
		}
		return transport;
	}
	String trimBrackets(String[] ports, int i) {
		String temp = ports[i];
		if (ports[i].contains("]")) {
			temp = temp.replace("]", "");
		}
		if (ports[i].contains("[")) {
			temp = temp.replace("[", "");
		}
		return temp.trim();
	}
	String trimString(String str) {
		int index = str.indexOf(":") + 1;
		String result = str.substring(index).trim();
		return result;
	}
}
