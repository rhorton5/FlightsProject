package systemmanager;
import airportClasses.Port;
import airportClasses.PortAbstractHelper;
import airportClasses.PortFactory;
import transportServiceClasses.TransportServiceHelper;
import transportServiceClasses.TransportService;
import transportServiceClasses.TransportServiceFactory;
import java.util.LinkedList;
import java.util.Scanner;
public abstract class createObjects implements PortAbstractHelper, TransportServiceHelper{
	private Scanner kb = new Scanner(System.in);
	public Port addPort(String n) {
		System.out.println("What type is " + n + "?");
		System.out.println("1). Airport\n2). Cruise Port");
		int choice;
		do {
			choice = kb.nextInt(); kb.nextLine();
		}while(choice != 1 && choice != 2);
		switch(choice) {
		case 1: return createPort("Airport",n);
		case 2: return createPort("Cruise Port",n);
		}
		return createPort("Not Identified",n);
	}
	
	public Port createPort(String type,String n) {
		PortFactory portfactory = new PortFactory();
		if(type.equals("Airport")) {
			return portfactory.createAirport(n);
		}
		else if(type.equals("Cruise Port")) {
			return portfactory.createPort("Cruise Port", n);
		}
			return portfactory.createPort("Airport", n);
	}
	
	public LinkedList <Port> addPortToList(Port air, LinkedList <Port> list) {
		if(!air.duplicateCheck(list) && !air.getName().isEmpty()) {
			list.add(air);
			System.out.println(air.getName() + " was successfully added!");
		}
		return list;
	}
	
	public TransportService addTransportService(String n) {
		return createTransportService(n);
	}
	
	public TransportService createTransportService(String n) {
		TransportServiceFactory tsf = new TransportServiceFactory();
		int choice;
		do {
			System.out.println("What type is " + n + "?");
			System.out.println("\n1). Airline\n2). Ship");//Extract to a single method.  Duplicate Code
			choice = kb.nextInt(); kb.nextLine();
		}while(choice != 1 && choice != 2);
		switch(choice) {
		case 1: return tsf.createAirline(n);
		case 2: return tsf.createShip(n);
		}
		return tsf.createAirline(n);
	}
	
	public LinkedList <TransportService> addTransportToSystem(TransportService air, LinkedList<TransportService> list){
		if(!air.getName().isEmpty() && !air.hasDuplicateName(list)) {
			list.add(air);
			System.out.println(air.getName() + " was successfully added!");
		}
		return list;
	}
	public TransportService createFlightSection(Scanner kb, TransportService transport) {
		
		return transport;
	}

}
