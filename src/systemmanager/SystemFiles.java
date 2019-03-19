package systemmanager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import airportClasses.Port;
import transportServiceClasses.TransportService;
import travelsection.SeatClass;

import java.util.LinkedList;
import java.util.Scanner;
public class SystemFiles {
	
	File getFile(String fileName, Scanner kb) {
		File file = new File(fileName);
		if(!file.exists()) {
			do {
				System.out.println("The file was not found. Please enter a name for a file: ");
				try {
					file = new File(kb.nextLine());
				}catch(Exception e) {
					System.out.println("An error occured, please try again.");
				}
			}while(file.exists());
		}
		return file;
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
		PrintWriter pw = getPrintWriter(new File(fileName));
		if(pw == null) {
			return;
		}
		String str = formatString(ports,transport,pw);
		
		System.out.println(str);
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
