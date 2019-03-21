package systemmanager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import airportClasses.Port;
import transportServiceClasses.TransportService;

import java.util.LinkedList;
import java.util.Scanner;
public class SystemFiles extends LoadFile{
	private String directory,altDirectory;
	public SystemFiles() {
		this.directory = new File("").getAbsolutePath() + "\\src\\savedFiles\\";
		this.altDirectory = new File("").getAbsolutePath() + "\\savedFiles\\";//Some IDE adds src folder and others don't as the Absolute Path.
	}
	
	String loadFile(String fileName, Scanner kb) throws FileNotFoundException{
		File file = new File(this.directory + fileName);
		if(!file.exists()) {
			file = new File(this.altDirectory + fileName);
			if(file.exists()) {
				return super.loadFileFormat(file);
			}
			do {
				System.out.print("The file was not found.\nPlease enter a name for a file: ");
				try {
					file = new File(kb.nextLine());
				}catch(Exception e) {
					System.out.println("An error occured, please try again.");
				}
			}while(!file.exists());
		}
		return super.loadFileFormat(file);
	}
	private PrintWriter getPrintWriter(File file) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(file);
			return pw;
		}
		catch(Exception e) {
			System.out.println("Saving file failed: PrintWriter failed to write.");
			return null;
		}
	}
	void saveFile(LinkedList <Port> ports, LinkedList <TransportService> transport, String fileName) {
		if(ports.size() == 0) {
			System.out.println("There is nothing to save.  Please make sure a port is added before saving your file.");
			return;
		}
		File file = new File(this.directory + fileName);
		PrintWriter pw = getPrintWriter(file);
		if(pw == null) {
			return;
		}
		String str = formatString(ports,transport,pw);
		pw.println(str);
		pw.close();
	}
	private String formatString(LinkedList <Port> ports, LinkedList <TransportService> transport, PrintWriter pw) {
		String str = formatPort(ports,pw);
		str = formatTransportService(transport,str,pw);
		return str;
	}
	private String formatPort(LinkedList <Port> ports,PrintWriter pw) {
		String str = "[";
		
		for(int i = 0; i < ports.size()-1; i++) {
			str += ports.get(i).getName() + ", ";
		}
		str += ports.get(ports.size()-1) + "] {";
		return str;
	}
	private String formatTransportService(LinkedList <TransportService>transport, String str, PrintWriter pw) {
		for(int i = 0; i < transport.size()-1; i++) {
			str +=transport.get(i).saveFile() + "]],";
		}
		if(transport.size() != 0) {
			str += transport.get(transport.size() - 1).saveFile() + "]]}";
		}
		return str;
	}
	
	
}
