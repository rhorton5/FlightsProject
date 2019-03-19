package systemmanager;
import transportServiceClasses.TransportService;
import airportClasses.Port;
import java.util.Scanner;
import java.util.LinkedList;


public class SystemAbstract extends createObjects{
	public TransportService getTransportService(LinkedList <TransportService> airlines, LinkedList <TransportService> cruises, Scanner kb) {
		String name;
		TransportService transport = null;
		System.out.println("Select a transport type\n1). Flights\n2). Cruises");
		int choice = 0;
		do {
			choice = kb.nextInt(); kb.nextLine();
		}while(choice != 1 && choice != 2);
		
		System.out.print("Enter the name of the transport service: ");
		name = kb.nextLine();
		if(choice == 1) {
			if(checkTransportService(name,airlines)) {
				transport = getTransportService(name, airlines);
			}
		}
		else if(choice == 2) {
			if(checkTransportService(name,cruises)) {
				transport = getTransportService(name,cruises);
			}
		}
		
		if(transport == null) {
			throw new IllegalArgumentException("Invalid type was found.  This error should not occur.");
		}
		
		return transport;
	}

	public boolean checkTransportService(String name, LinkedList<TransportService> list) {
		for(int i = 0; i < list.size(); i++) {
			TransportService cur = list.get(i);
			if(cur.getName().equals(name)) {
				return true;
			}
		}
		System.out.println(name + " is not in the system.");
		return false;
	}

	public TransportService getTransportService(String name, LinkedList<TransportService> list) {
		if(list == null || name.isEmpty()) {
			System.out.println("Invalid parameters.");
			return null;
		}
		
		for(int i = 0; i < list.size(); i++) {
			TransportService cur = list.get(i);
			if(cur.getName().equals(name)) {
				return cur;
			}
		}
		return null;
	}
	public boolean checkOriginAndDestinaton(String orig, String dest,LinkedList <Port> ports) {
		if(!ports.get(0).getType().equals("CruisePort")) {
			if(orig.equals(dest)) {
				System.out.println("Orig and dest cannot be the same.");
			}
		}
		
		boolean origExists = false, destExists = false;
		for(int i = 0; i < ports.size() && origExists != true || destExists != true; i++) {
			Port port = ports.get(i);
			if(port.getName().equals(orig)) {
				origExists = true;
			}
			else if(port.getName().equals(dest)) {
				destExists = true;
			}
		}
		return origExists && destExists;	
	}
		
}
