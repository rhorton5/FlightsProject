package systemmanager;
import transportServiceClasses.TransportService;
import airportClasses.Port;
import java.util.Scanner;
import java.util.LinkedList;


public class SystemAbstract extends createObjects{
	public TransportService getTransportServiceType(LinkedList <TransportService> airlines, LinkedList <TransportService> cruises, Scanner kb) {
		String name;
		String choice = selectTransportType(kb);
		TransportService transport = null;
		
		
		System.out.print("Enter the name of the transport service: ");
		name = kb.nextLine().toUpperCase();
		
		if(choice.equals("airline")) {
			if(checkTransportService(name,airlines)) {
				transport = getTransportService(name, airlines);
			}
		}
		else if(choice.equals("cruise line")) {
			if(checkTransportService(name,cruises)) {
				transport = getTransportService(name,cruises);
			}
		}
		
		if(transport == null) {
			return getTransportServiceType(airlines,cruises,kb);
		}
		
		return transport;
	}
	private String selectTransportType(Scanner kb) {
		String choice;
		System.out.print("Select a transport type.");
		
		do {
			System.out.print("Type either airline or cruise line: ");
			choice = kb.nextLine().toLowerCase();
		}while(!choice.equals("airline") && !choice.equals("cruise line"));
		return choice;
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
		if(ports == null) {
			System.out.println("No ports were added.  Cannot check for matching locations.");
			return false;
		}
		if(!ports.get(0).getType().equals("Cruise Port")) {
			if(orig.equals(dest)) {
				System.out.println("Orig and dest cannot be the same.");
				return false;
			}
		}
		boolean origExists = checkLocation(orig,ports), destExists = checkLocation(dest,ports);
		return origExists && destExists;	
	}
	private boolean checkLocation(String location, LinkedList <Port> ports) {
		for(int i = 0; i < ports.size(); i++) {
			Port port = ports.get(i);
			if(port.getName().equals(location)) {
				return true;
				}
		}
		System.out.println(location + " does not exist in the system.");
		return false;
	}
		
}
