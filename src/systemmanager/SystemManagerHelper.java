package systemmanager;
import java.util.Scanner;
import airportClasses.Port;
import java.util.LinkedList;
import transportServiceClasses.TransportService;

public abstract class SystemManagerHelper extends createObjects {
	public TransportService determineTransportType(Scanner kb) {
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
			if(checkAirline(name)) {
				transport = getAirline(name);
			}
		}
		else if(choice == 2) {
			if(checkShips(name)) {
				transport = getShips(name);
			}
		}
		return transport;
	}//Can I fix this up some more?  Maybe lead it to a get method via abstraction.
	private boolean checkAirport(String location,LinkedList <Port> airports) {
		for(int i = 0; i < airports.size(); i++) {
			if(airports.get(i).getName().equals(location)) {
				return true;
			}
		}
		System.out.println(location + " is not in the system currently.");
		return false;
	}
	private boolean checkAirline(String name, LinkedList<TransportService> transport) {
		for(int i = 0; i < transport.size(); i++) {
			if(transport.get(i).getName().equals(name)) {
				return true;
			}
		}
		System.out.println(name + " is not in the system currently.");
		return false;
	}
}
