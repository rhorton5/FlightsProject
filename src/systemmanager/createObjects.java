package systemmanager;
import airportClasses.Port;
import airportClasses.PortAbstractHelper;
import airportClasses.PortFactory;
import transportServiceClasses.TransportServiceHelper;
import transportServiceClasses.TransportService;
import transportServiceClasses.TransportServiceFactory;
import java.util.LinkedList;
import java.util.Scanner;
public abstract class createObjects implements TransportServiceHelper{
	private Scanner kb = new Scanner(System.in);
	public Port addPort(String type,String n) {
		
		String choice = type;
		while(!choice.toLowerCase().equals("airport") && !choice.toLowerCase().equals("cruise port")){
			System.out.println("What type is " + n + "?");
			System.out.print("Type Airport or Cruise Port: ");
			choice = kb.nextLine();
		}
		switch(choice.toLowerCase()) {
		case "airport": return createPort("Airport",n);
		case "cruise port": return createPort("Cruise Port",n);
		}
		return createPort("Not Identified",n);
	}
	
	public Port createPort(String type,String n) {
		PortFactory portfactory = new PortFactory();
		if(type.equalsIgnoreCase("Airport")) {
			return portfactory.createAirport(n);
		}
		else if(type.equalsIgnoreCase("Cruise Port")) {
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
	
	public TransportService addTransportService(String type, String n) {
		return createTransportService(type,n);
	}
	
	public TransportService createTransportService(String type, String n) {
		TransportServiceFactory tsf = new TransportServiceFactory();
		String choice = type;
		while(!choice.toLowerCase().equals("airline") && !choice.toLowerCase().equals("ship")){
			System.out.println("What type is " + n + "?");
			System.out.print("Type airline or ship");//Extract to a single method.  Duplicate Code
			choice = kb.nextLine();
		}
		switch(choice.toLowerCase()) {
		case "airline": return tsf.createAirline(n);
		case "ship": return tsf.createShip(n);
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
