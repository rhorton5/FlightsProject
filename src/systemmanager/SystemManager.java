package systemmanager;
import java.util.LinkedList;
import airportClasses.Port;
import travelClasses.Travel;
import travelClasses.TravelFactory;
import travelsection.TravelSection;
import travelsection.SeatClass;
import transportServiceClasses.TransportService;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class SystemManager extends SystemAbstract{
	private LinkedList <Port> airports;
	private LinkedList <Port> cruisePorts;
	private LinkedList <TransportService> airlines;
	private LinkedList <TransportService> cruiselines;
	public SystemManager() {
		this.airports = new LinkedList<Port>();
		this.airlines = new LinkedList<TransportService>();
		this.cruisePorts = new LinkedList<Port>();
		this.cruiselines = new LinkedList<TransportService>();
	}
	public void nameAndAddPort(Scanner kb) {
		System.out.print("Enter the name of your port: ");
		String n = kb.nextLine().toUpperCase();
		createNewPort("",n);
	}
	public void nameAndAddTransport(Scanner kb) {
		System.out.print("Name your transport service: ");
		String n = kb.nextLine().toUpperCase();
		createTransport("",n);
	}
	public void changeAirlineSeatClassPrice(Scanner kb) {
		System.out.println("--- Change Airline's Seat Class Price ---");
		System.out.print("Enter the airline's name: ");
		String airlineName = kb.nextLine();
		if(!super.checkTransportService(airlineName,this.airlines)) {
			System.out.println("Airline " + airlineName + " does not exist.");
			return;
		}
		TransportService airline = super.getTransportService(airlineName, this.airlines);
		airline.changeSeatClassPrice(kb);
		System.out.println(airlineName + "'s price was successfully changed!");
	}
	public void createTravel(Scanner kb) {
		System.out.println("--- Create Travel ---");
		TransportService transport = super.getTransportServiceType(this.airlines, this.cruiselines, kb);
		setupTravel(kb,transport.getName(),transport);		
	}
	public void createTravelSection(Scanner kb) {
		System.out.println("--- Creating Travel Section ---");
		String ID;
		TransportService transport = super.getTransportServiceType(this.airlines,this.cruiselines,kb);
		System.out.print("Enter the ID: ");
		ID = kb.nextLine();
		setupSection(transport.getType(),transport.getName(),ID,kb);
	}
	public void setupSeatBooking(Scanner kb) {
		int choice = 0; 
		System.out.println("--- Book Seat ---");
		do {
			System.out.println("1). Book Seat based on seating preference");
			System.out.println("2). Book Seat using actual seat location.");
			System.out.print("Enter your choice: ");
			choice = kb.nextInt(); kb.nextLine();
		}while(choice != 1 && choice != 2);
		if(choice == 1) {
			setupBookingBySeatingPreference(kb);
		}
		else if(choice == 2) {
			setupBookingBySeatLocation(kb);
		}
		else {
			System.out.println("Choice was invalid");
		}
	}
	private void setupBookingBySeatingPreference(Scanner kb) {//*
		TransportService transport = super.getTransportServiceType(this.airlines, this.cruiselines, kb);
		if(transport != null && !transport.getName().equals("")) {
			transport.findSectionUsingPreference(kb);
		}
	}
	private void setupBookingBySeatLocation(Scanner kb) {
		TransportService transport = super.getTransportServiceType(this.airlines, this.cruiselines, kb);
		if(transport != null && !transport.getName().equals("")) {
			transport.findSection(kb);
		}
		
	}
	private LinkedList<Port> findAssociatedPortType(String type){
		if(type.equalsIgnoreCase("airline")) {
			return this.airports;
		}
		else if(type.equalsIgnoreCase("cruise line")) {
			return this.cruisePorts;
		}
		else {
			System.out.println("Type is not supported.");
			return null;
		}
	}
	private void setupTravel(Scanner kb,String name,TransportService transport) {
		String orig, dest,flightID;
		int year, month, day, hour, minutes;
		LinkedList <Port> list = findAssociatedPortType(transport.getType());
		if(list == null) {
			return;
		}
		
		System.out.println("--- Find Perferred Travel ---");
		System.out.print("List your starting location and destination (space the two out):");
		String temp = kb.nextLine().toUpperCase();
		String [] arrayTemp = temp.split(" ");
		orig = arrayTemp[0].trim();
		dest = arrayTemp[1].trim();
		
		System.out.print("Enter a flight ID: ");
		flightID = kb.nextLine().toUpperCase();
		
		System.out.print("Choose your date (use month/day/year):");
		String dates = kb.nextLine();
		String [] datesArray = dates.split("/");
		month= Integer.parseInt(datesArray[0]); day = Integer.parseInt(datesArray[1]); year = Integer.parseInt(datesArray[2]);
		System.out.print("Enter the hour this will leave (Use military time, 0-23): ");
		hour = kb.nextInt(); kb.nextLine();
		System.out.print("Enter the minute this will leave: ");
		minutes = kb.nextInt(); kb.nextLine();
		boolean exists = super.checkOriginAndDestinaton(orig, dest, list);
		if(exists == false) {
			return;
		}
		if(createTravel(transport.getType(),name,orig,dest,year,month,day,hour,minutes,flightID)) {
			setupSection(transport.getType(),transport.getName(),flightID,kb);
		}
		else {
			System.out.println("Something went wrong.  Flight/Sail failed.");
			return;
		}
		
	}
	public void changeTravelSectionPrice(Scanner kb) {
		System.out.println("--- Change Flight Section Price ---");
		System.out.print("Enter the airline's name: ");
		String airlineName = kb.nextLine();
		if(!super.checkTransportService(airlineName,this.airlines)) {
			System.out.println("Airline " + airlineName + " does not exists.");
			return;
		}
		TransportService airline = super.getTransportService(airlineName,this.airlines);
		airline.changeTravelSectionPrice(kb);
	}
	public void findPerferredFlights(Scanner kb) {
		SeatClass seatClass = SeatClass.economy;
		String orig, dest;
		int year, month, day;
		
		System.out.println("--- Find Perferred Flight ---");
		System.out.print("List your starting location and destination (space the two out):");
		String temp = kb.nextLine();
		String [] arrayTemp = temp.split(" ");
		orig = arrayTemp[0].trim();
		dest = arrayTemp[1].trim();
		
		System.out.print("Seat class you are looking for: ");
		seatClass = seatClass.setSeatClass(kb.nextLine().charAt(0));
		
		System.out.print("Choose your date (use month/day/year):");
		String dates = kb.nextLine();
		String [] datesArray = dates.split("/");
		month = Integer.parseInt(datesArray[0].trim()); day = Integer.parseInt(datesArray[1].trim()); year = Integer.parseInt(datesArray[2].trim());
		boolean exists = super.checkOriginAndDestinaton(orig, dest, this.airports);
		if(exists == false) {
			return;
		}
		findAvailableFlights(orig,dest,year,month,day,seatClass);
	}
	public void createNewPort(String type, String n) {
		if(n.isEmpty()) {
			System.out.println("Invalid parameters.");
			return;
		}
		System.out.println("--- Creating Port " + n + " ---");
		Port p = super.addPort(type,n);
		LinkedList <Port> portType = findAppropriatePortType(p.getType());
		super.addPortToList(p,portType);
		
		System.out.println();
	}
	public void createTransport(String type, String n) {
		System.out.println("--- Creating Transport Service " + n + "---");
		TransportService ts = super.addTransportService(type,n);
		LinkedList <TransportService> transportType = findAppropriateType(type);
		super.addTransportToSystem(ts, transportType);
		System.out.println();
	}
	private boolean createTravel(String type,String name, String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		System.out.println("--- Creating " + type + " " + name + " from " + orig + " to " + dest + " ---");
		LinkedList <TransportService> transportType = findAppropriateType(type);
		Travel t;
		if(super.checkTransportService(name,transportType)) {
			TravelFactory tf = new TravelFactory();
			if(type.equalsIgnoreCase("Airline")) {
				t = tf.createFlight(orig, dest, year, month, day, hour, minute, id);
			}
			else if(type.equalsIgnoreCase("Cruise Line")) {
				t = tf.createSail(orig, dest, year, month, day, hour, minute, id);
			}
			else {
				System.out.println(name + " is not supported.");
				return false;
			}
			if(t.validTravel()) {
				addTravel(type,name,t,transportType);
				return true;
			}
		}
		System.out.println(name + " has failed to be created.");
		return false;
		
	}
	private LinkedList<TransportService> findAppropriateType(String type){
		if(type.equalsIgnoreCase("Airline")) {
			return this.airlines;
		}
		else if(type.equalsIgnoreCase("Cruise Line")) {
			return this.cruiselines;
		}
		else {
			System.out.println("Type is not supported");
			return null;
		}
		
	}
	private LinkedList<Port> findAppropriatePortType(String type){
		if(type.equalsIgnoreCase("Airport")) {
			return this.airports;
		}
		else if(type.equalsIgnoreCase("Cruise Port")) {
			return this.cruisePorts;
		}
		else {
			System.out.println("Type is not supported");
			return null;
		}
	}
	private void addTravel(String type, String aname, Travel t, LinkedList<TransportService> transportType) {
		for(int i = 0; i < transportType.size(); i++) {
			if(transportType.get(i).getName().equals(aname)) {
				if(!super.isDuplicateTravel(t, transportType.get(i).getTravels()))
				transportType.get(i).addFlight(t);
				System.out.println(t.getID() + " was added to " + aname);
				return;
			}
		}
		System.out.println("Failed to add " + aname);
	}
	private void setupSection(String type,String name,String id,Scanner kb) {
		char choice;
		do {
			System.out.print("Would you like to create a section for " + id + "? (y/n): ");
			choice = kb.nextLine().toLowerCase().charAt(0);
		}while(choice != 'y' && choice != 'n');
		
		if(choice == 'y') {
			while(choice == 'y') {
				char layout;
				int price,row;
				SeatClass seatClass = SeatClass.economy;
				
				System.out.print("Select a layout (S,M,W): ");
				layout = kb.nextLine().trim().charAt(0);
				
				System.out.print("Select a Seat Class (first,economy,business): ");
				seatClass = seatClass.setSeatClass(kb.nextLine().toUpperCase().charAt(0));
				
				System.out.print("Enter a row size: ");
				row = kb.nextInt(); kb.nextLine();
				
				System.out.print("Enter a price (Type 0 to have default SeatClass price): ");
				price = kb.nextInt(); kb.nextLine();
				
				createSection(name,id,row,layout,price,seatClass,type);
				do {
					System.out.print("Would you like to create another section? (y/n): ");
					choice = kb.nextLine().toLowerCase().charAt(0);
				}while(choice != 'y' && choice != 'n');
				
			}
		}	
	}
	public void createSection(String air, String flID, int row, char layout, int price, SeatClass s, String type) {
		System.out.println("--- Creating Section for " + air + " with Seat Class " + s + " ---");
		LinkedList <TransportService> transportType = findAppropriateType(type);
		TransportService ts = super.getTransportService(air, transportType);
		TravelSection fs = new TravelSection();
		fs.createSection(s,row,layout,flID,price);
		ts.addTravelSection(fs);
		System.out.println();
		
	}
	private void findAvailableFlights(String orig, String dest, int year, int month, int day, SeatClass seatClass) {
		System.out.println("--- Searching for Avalible Flights from " + orig + " to " + dest + " ---");
		for(int i = 0; i < airlines.size(); i++) {
			this.airlines.get(i).matchingAvaliableFlight(orig, dest, year, month, day, seatClass);
		}
		System.out.println();
	}
	public void displaySystemDetails() {
		System.out.println("--- Displaying System Details ---");
		System.out.println("The following airports in the system are: ");
		for(int i = 0; i < this.airports.size(); i++) {
			System.out.println(this.airports.get(i).toString());
		}
		System.out.println();
		for(int i = 0; i < this.airlines.size(); i++) {
			System.out.println(this.airlines.get(i).toString());
		}
		System.out.println("The following cruises in the system are: ");
		for(int i = 0; i < this.cruisePorts.size(); i++) {
			System.out.println(this.cruisePorts.get(i).toString());
		}
		for(int i = 0; i < this.cruiselines.size(); i++) {
			System.out.println(this.cruiselines.get(i).toString());
		}
		
		System.out.println();
	
	}
	public void loadFile(Scanner kb) throws FileNotFoundException{
		System.out.print("Please enter the file name (include file type (i.e. .txt)): ");
		String fileName = kb.nextLine();
		loadFileToSystemManager(fileName,this,kb);
		
	}
	public void saveSystemDetails(Scanner kb) throws FileNotFoundException{
		System.out.println("Please enter a name for your file: ");
		String fileName = kb.nextLine();
		SystemFiles system = new SystemFiles();
		system.saveFile(this.airports,this.airlines,fileName);
		System.out.println("File was successfully saved!");
	}
	public void loadFileToSystemManager(String fileName, SystemManager sm,Scanner kb) throws FileNotFoundException{
		SystemFiles sf = new SystemFiles();
		String temp = sf.loadFile(fileName, kb);
		
		int i = temp.indexOf('{');
		String [] ports = temp.substring(0,i).split(",");
		String transport = temp.substring(i);
		createMultiplePorts(ports);
		
		createMultipleTransport(transport);
				
	}
	private void createMultiplePorts(String [] ports) {
		SystemFiles sf = new SystemFiles();
		for(int i = 0; i < ports.length; i++) {
			String temp = sf.trimBrackets(ports, i);
			createNewPort("Airport",temp);
		}
	}

	private void createMultipleTransport(String transport) {
		while(!transport.isEmpty()) {
			transport = loadTransportServiceInformation(transport.substring(0));
		}
	}
	private String loadTransportServiceInformation(String transport) {
		SystemFiles sf = new SystemFiles();
		String airlineName = "", output = "";
		boolean cutTransport = false;
		if(transport.length() < 10) {
			return "";
		}
		if(transport.indexOf("[") <= 6) {
			airlineName = sf.getSection(transport, "[").replaceAll("[^A-Z]", "");
			transport = sf.trimSection(transport, "[");
			createTransport("Airline",airlineName);
		}
		while(cutTransport == false) {
			output = sf.getTravel(transport, cutTransport);
			cutTransport = true;
		}
		transport = sf.getTravel(transport, cutTransport);
			if(!output.isEmpty()) {
				loadOutputInformation(output,airlineName,sf);
				return transport;
			}
		return transport;
	}
	private void loadOutputInformation(String output,String airline,SystemFiles sf) {
		if(output.equals("[]")) {
			return;
		}
		String [] travels = output.replaceAll("}", "").split("]");
		for(int i = 0; i < travels.length; i++) {
			if(travels[i].length() <= 10) {
				break;
			}
			String flightID = loadID(sf, travels, i);
			travels[i] = travels[i].substring(travels[i].indexOf("|")+1);//Doesn't get the right section without being one index after "|".
			
			
			String timeAndDate = sf.getSection(travels[i],"|");
			travels[i] = sf.trimSection(travels[i], "|");
			
			
			String travelLocations = sf.getSection(travels[i], "[");
			travels[i] = sf.trimSection(travels[i], "[");
			
			String TravelSection = travels[i].substring(1);
			
			String [] time = timeAndDate.split(",");
			int year = Integer.parseInt(time[0].trim()),month = Integer.parseInt(time[1].trim()),day = Integer.parseInt(time[2].trim()), hour = Integer.parseInt(time[3].trim()), minute = Integer.parseInt(time[4].trim());
			
			while(!travelLocations.substring(0,1).matches("^[A-Z]")) {
				travelLocations = travelLocations.substring(1);
			}
			String orig = sf.getSection(travelLocations, "|");
			String dest = travelLocations.substring(travelLocations.lastIndexOf("|")).replaceAll("[^A-Z]", "").trim();
			
			createTravel("Airline",airline, orig, dest, year, month, day, hour, minute, flightID);
			loadTravelSection(TravelSection,flightID,airline);
		
			
		}
	}
	private String loadID(SystemFiles sf, String[] travels, int i) {
		String flightID = sf.getSection(travels[i], "|").replaceAll("[^A-Z,0-9]", "");
		while (flightID.substring(0, 1).matches("[^a-zA-Z0-9]")) {
			flightID = flightID.substring(1);
		}
		return flightID;
	}

	private void loadTravelSection(String TravelSectionInfo, String flightID, String airlineName) {
		SystemFiles sf = new SystemFiles();
		String [] strParts = TravelSectionInfo.replace("[", "").split(",");
		SeatClass s = SeatClass.economy;
		for(int i = 0; i < strParts.length; i++) {
			strParts[i] = strParts[i].trim();
			char seatClass = strParts[i].charAt(0);
			strParts[i] = sf.trimString(strParts[i]);
			
			int price = Integer.parseInt(strParts[i].substring(0,strParts[i].indexOf(":")));
			strParts[i] = sf.trimString(strParts[i]);
			
			char layout = strParts[i].charAt(0);
			strParts[i] = sf.trimString(strParts[i]);
			
			int rows = Integer.parseInt(strParts[i].trim());
			
			s = s.setSeatClass(seatClass);
			createSection(airlineName,flightID,rows,layout,price,s,"Airline");
		}
	}
}
