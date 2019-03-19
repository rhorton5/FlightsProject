package systemmanager;
import java.util.LinkedList;
import airportClasses.Port;
import travelClasses.Travel;
import travelClasses.TravelFactory;
import travelsection.FlightSection;
import travelsection.SeatClass;
import transportServiceClasses.TransportService;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class SystemManager extends SystemAbstract{
	private LinkedList <Port> airports;
	private LinkedList <Port> cruisePorts;
	private LinkedList <TransportService> airlines;
	private LinkedList <TransportService> ships;
	private File loadFile;
	public SystemManager() {
		this.airports = new LinkedList<Port>();
		this.airlines = new LinkedList<TransportService>();
		this.cruisePorts = new LinkedList<Port>();
		this.ships = new LinkedList<TransportService>();
	}
	public void nameAndAddPort(Scanner kb) {
		System.out.println("Name your port: ");
		String n = kb.nextLine();
		createNewPort("",n);
	}
	public void nameAndAddTransport(Scanner kb) {
		System.out.println("Name your transport service: ");
		String n = kb.nextLine();
		createTransport("",n);
	}
	public void changeAirlineSeatClassPrice(Scanner kb) {
		System.out.println("--- Change Airline's Seat Class Price ---");
		System.out.print("Enter the airline's name: ");
		String airlineName = kb.nextLine();
		if(!checkAirline(airlineName)) {
			System.out.println("Airline " + airlineName + " does not exist.");
			return;
		}
		TransportService airline = getAirline(airlineName);
		airline.changeSeatClassPrice(kb);
		System.out.println(airlineName + "'s price was successfully changed!");
	}
	public void createTravel(Scanner kb) {
		System.out.println("--- Create Travel ---");
		TransportService transport = super.getTransportService(this.airlines, this.ships, kb);
		setupTravel(kb,transport.getName(),transport);		
	}
	public void createTravelSection(Scanner kb) {
		System.out.println("--- Creating Travel Section ---");
		String ID;
		TransportService transport = super.getTransportService(this.airlines,this.ships,kb);
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
		TransportService transport = super.getTransportService(this.airlines, this.ships, kb);
		if(transport != null && !transport.getName().equals("")) {
			transport.findSectionUsingPreference(kb);
		}
		
	}
	private void setupBookingBySeatLocation(Scanner kb) {//*
		TransportService transport = super.getTransportService(this.airlines, this.ships, kb);
		if(transport != null && !transport.getName().equals("")) {
			transport.findSection(kb);
		}
		
	}
	private void setupTravel(Scanner kb,String name,TransportService transport) {
		String orig, dest,flightID,type;
		int year, month, day, hour, minutes;
		LinkedList <Port> list;
		
		if(transport.getType().equals("Ship")) {
			type = "Cruise Port";
			list = this.cruisePorts;
			
		}
		else {
			type = "Airport";
			list = this.airports;
		}
		
		System.out.println("--- Find Perferred Flight ---");
		System.out.println("List your starting location and destination (space the two out):");
		String temp = kb.nextLine();
		String [] arrayTemp = temp.split(" ");
		orig = arrayTemp[0].trim();
		dest = arrayTemp[1].trim();
		
		System.out.println("Enter a flight ID: ");
		flightID = kb.nextLine();
		
		System.out.println("Choose your date (use day/month/year):");
		String dates = kb.nextLine();
		String [] datesArray = dates.split("/");
		day = Integer.parseInt(datesArray[0]); month = Integer.parseInt(datesArray[1]); year = Integer.parseInt(datesArray[2]);
		System.out.print("What hour is this on: ");
		hour = kb.nextInt(); kb.nextLine();
		System.out.print("What minute is this on: ");
		minutes = kb.nextInt(); kb.nextLine();
		boolean exists = super.checkOriginAndDestinaton(orig, dest, list);
		if(exists == false) {
			return;
		}
		if(transport.getType().equals("Airline")) {
			createFlight(name,orig,dest,year,month,day,hour,minutes,flightID);
			setupSection("Airline",name,flightID,kb);
			
		}
		else if(transport.getType().equals("Ship")) {
			createSail(name,orig,dest,year,month,day,hour,minutes,flightID);
		}
		else {
			System.out.println("Something went wrong.  Flight/Sail failed.");
			return;
		}
		
	}
	public void changeFlightSectionPrice(Scanner kb) {
		System.out.println("--- Change Flight Section Price ---");
		System.out.print("Enter the airline's name: ");
		String airlineName = kb.nextLine();//Push this to a new method ^  Duplicate Code
		if(!super.checkTransportService(airlineName,this.airlines)) {
			System.out.println("Airline " + airlineName + " does not exists.");
			return;
		}
		TransportService airline = super.getTransportService(airlineName,this.airlines);
		airline.changeFlightSectionPrice(kb);
	}
	public void findPerferredFlights(Scanner kb) {//Refactor this lots! :)
		SeatClass seatClass;
		String orig, dest;
		int year, month, day;
		
		System.out.println("--- Find Perferred Flight ---");
		System.out.println("List your starting location and destination (space the two out):");
		String temp = kb.nextLine();
		String [] arrayTemp = temp.split(" ");
		orig = arrayTemp[0].trim();
		dest = arrayTemp[1].trim();
		
		System.out.println("Seat class you are looking for: ");
		seatClass = setSeatClass(kb.nextLine().charAt(0));
		
		System.out.println("Choose your date (use day/month/year):");
		String dates = kb.nextLine();
		String [] datesArray = dates.split("/");
		day = Integer.parseInt(datesArray[0]); month = Integer.parseInt(datesArray[1]); year = Integer.parseInt(datesArray[2]);
		boolean exists = super.checkOriginAndDestinaton(orig, dest, this.airports);
		if(exists == false) {
			return;
		}
		findAvailableFlights(orig,dest,year,month,day,seatClass);
	}
	public void createNewPort(String type, String n) {
		System.out.println("--- Creating Port " + n + "---");
		Port p = super.addPort(type,n);
		if(p.getType().equals("Airport")) {
			this.airports = super.addPortToList(p, this.airports);
		}
		else if(p.getType().equals("Cruise Port")){
			this.cruisePorts = super.addPortToList(p, this.cruisePorts);
		}
		else {
			System.out.println(n + "'s type is not supported on this system.");
		}
		
		System.out.println();
	}
	public void createTransport(String type, String n) {
		System.out.println("--- Creating Transport Service " + n + "---");
		TransportService ts = super.addTransportService(type,n);
		if(ts.getType().equals("Airline")) {
			this.airlines = super.addTransportToSystem(ts, this.airlines);
		}
		else if(ts.getType().equals("Ship")) {
			this.ships = super.addTransportToSystem(ts, this.ships);
		}
		else {
			System.out.println(n + "'s type is not supported on this system.");
		}
		
		System.out.println();
	}
	void createFlight(String aname, String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		System.out.println("--- Creating Flight with Airline " + aname + " from " + orig + " to " + dest + " ---");
		if(super.checkTransportService(aname,this.airlines)) {
			if(super.checkOriginAndDestinaton(orig, dest, this.airports)) {
				TravelFactory tf = new TravelFactory();
				Travel t = tf.createFlight(orig, dest, year, month, day, hour, minute, id);
				if(t.validTravel()) {
					addTravel(aname,t);
				}
			}
		}
		System.out.println();
	}//Duplicate Code
	private void createSail(String cname,String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		System.out.println("--- Creating Cruise with Cruise Port " + cname + " from " + orig + " to " + dest + " ---");
		if(super.checkTransportService(cname,this.ships)) {
			if(super.checkOriginAndDestinaton(orig, dest, this.cruisePorts)) {
				TravelFactory tf = new TravelFactory();
				Travel t = tf.createSail(orig, dest, year, month, day, hour, minute, id);
				if(t.validTravel()) {
					addSailTravel(cname,t);
				}
			}
		}
		System.out.println();
	}
	private boolean checkAirline(String aname) {
		for(int i = 0; i < airlines.size(); i++) {
			if(this.airlines.get(i).getName().equals(aname)) {
				return true;
			}
		}
		System.out.println(aname + " is not in the system currently.");
		return false;
	}
	
	private boolean checkCruisePort(String location) {
		for(int i = 0; i < cruisePorts.size(); i++) {
			if(this.cruisePorts.get(i).getName().equals(location)) {
				return true;
			}
		}
		System.out.println(location + " is not in the system currently.");
		return false;
	}
	
	private void addTravel(String aname, Travel t) {
		for(int i = 0; i < airlines.size(); i++) {
			if(this.airlines.get(i).getName().equals(aname)) {
				this.airlines.get(i).addFlight(t);
				System.out.println("Flight was added to " + aname);
				return;
			}
		}
	}
	private void addSailTravel(String cname, Travel t) {
		for(int i = 0; i < ships.size(); i++) {
			if(this.ships.get(i).getName().equals(cname)) {
				this.ships.get(i).addFlight(t);
				System.out.println("Cruise trip was added to " + cname);
				return;
			}
		}
	}
	private TransportService getAirline(String aname) {
		for(int i = 0; i < airlines.size(); i++) {
			if(this.airlines.get(i).getName().equals(aname)) {
				return this.airlines.get(i);
			}
		}
		return null;
	}
	private TransportService getShips(String cname) {
		for(int i = 0; i < ships.size(); i++) {
			if(this.ships.get(i).getName().equals(cname)) {
				return this.ships.get(i);
			}
		}
		return null;
	}
	private void setupSection(String type,String name,String id,Scanner kb) {
		System.out.print("Would you like to create a section for " + id + "? (y/n): ");
		char choice = kb.nextLine().toLowerCase().charAt(0);
		if(choice == 'y') {
			while(choice == 'y') {
				char layout;
				int price,row;
				SeatClass seatClass = SeatClass.economy;
				
				System.out.print("Select a layout(S,M,W):");
				layout = kb.nextLine().charAt(0);
				
				System.out.print("Select a Seat Class (first,economy,business): ");
				seatClass = seatClass.setSeatClass(kb.nextLine().toUpperCase().charAt(0));
				
				System.out.print("Enter a row size: ");
				row = kb.nextInt(); kb.nextLine();
				
				System.out.print("Enter a price (Type 0 to have default SeatClass price): ");
				price = kb.nextInt(); kb.nextLine();
				
				createSection(name,id,row,layout,price,seatClass,type);
				System.out.print("Would you like to create another section? (y/n): ");
				choice = kb.nextLine().toLowerCase().charAt(0);
			}
		}	
	}
	public void createSection(String air, String flID, int row, char layout, int price, SeatClass s, String type) {
		System.out.println("--- Creating Section for " + air + " with Seat Class " + s);
		if(type.equals("Airline")) {
			if(checkAirline(air)) {
				TransportService ts = getAirline(air);
				FlightSection fs = new FlightSection();
				fs.createSection(s, row, layout,flID,price);
				ts.addFlightSection(fs);
			}
		}
		else if(type.equals("Cruise Port")) {
			if(checkCruisePort(air)) {
				TransportService ts = getShips(air);
				FlightSection fs = new FlightSection();
				fs.createSection(s, row, layout, flID, price);
				ts.addFlightSection(fs);
			}
		}
		System.out.println();
		
	}
	private void findAvailableFlights(String orig, String dest, int year, int month, int day, SeatClass seatClass) {
		System.out.println("--- Searching for Avalible Flights from " + orig + " to " + dest + " ---");
		for(int i = 0; i < airlines.size(); i++) {
			this.airlines.get(i).matchingAvaliableFlight(orig, dest, year, month, day, seatClass);
		}
		System.out.println();
	}
	/*private void bookSeat(String type, String name, String fl, SeatClass s, int row, char col) {
		if(type.equals("Airline")) {
			System.out.println("--- Booking seat at airline " + name + " for Seat " + row + " " + col + " ---");
			if(checkAirline(name)) {
				TransportService ts = super.getTransportService(name,this.airlines);
				ts.bookSeat(fl,s,row,col);
			}
		}else if(type.equals("Cruise Port")) {
			System.out.println("--- Booking seat at cruise port " + name + " for Cabin " + row + " " + col + " ---");
			if(super.checkTransportService(name, this.ships)) {
				TransportService ts = super.getTransportService(name, this.ships);
				ts.bookSeat(fl,s,row,col);
			}
		}else {
			System.out.println("The type you chose is not supported.");
		}
		
		System.out.println();
	}*/
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
		for(int i = 0; i < this.ships.size(); i++) {
			System.out.println(this.ships.get(i).toString());
		}
		
		System.out.println();
	
	}
	public void loadFile(Scanner kb) throws FileNotFoundException{
		System.out.print("Please enter the file name (include file type (i.e. .txt)): ");
		String fileName = kb.nextLine();
		loadFileToSystemManager(fileName,this,kb);
		
	}
	public SeatClass setSeatClass(char letter) {
		if(letter == 'F') {
			return SeatClass.first;
		}
		else if(letter == 'B') {
			return SeatClass.business;
		}
		else
			return SeatClass.economy;
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
		System.out.println("--- Loading The File ---");
		File file = sf.getFile(fileName, kb);
		this.loadFile = file;
		Scanner fin;
		try {
			fin = new Scanner(this.loadFile);
		}
		
		catch(Exception e) {
			System.out.println("The file was unable to be turned into a scanner.");
			return;
		}
		
		String temp = fin.nextLine();
		fin.close();
		
		int i = temp.indexOf('{');
		String [] ports = temp.substring(0,i).split(",");
		String transport = temp.substring(i);
		createMultiplePorts(ports);
		
		createMultipleTransport(transport);
				
	}
	private void createMultiplePorts(String [] ports) {
		for(int i = 0; i < ports.length; i++) {
			String temp = ports[i];
			if(ports[i].contains("]")) {
				temp = temp.replace("]", "");
			}
			if(ports[i].contains("[")) {
				temp = temp.replace("[", "");
			}
			createNewPort("Airport",temp.trim());
		}
	}
	private void createMultipleTransport(String transport) {
		while(!transport.isEmpty()) {
			transport = loadTransportServiceInformation(transport.substring(0));
		}
	}
	private String loadTransportServiceInformation(String transport) {
		String airlineName = "", output = "";
		int rightBracket = 0, leftBracket = 0;
		if(transport.length() < 10) {
			return "";
		}
		if(transport.indexOf("[") <= 6) {
			airlineName = transport.substring(0,transport.indexOf("[")).replaceAll("[^A-Z]", "");
			transport = transport.substring(transport.indexOf("["));
			createTransport("Airline",airlineName);
		
		}
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
				loadOutputInformation(output,airlineName);
				return transport;
			}
		}
		return transport;
	}
	private void loadOutputInformation(String output,String airline) {
		if(output.equals("[]")) {
			return;
		}
		String [] travels = output.replaceAll("}", "").split("]");
		for(int i = 0; i < travels.length; i++) {
			if(travels[i].length() <= 10) {
				break;
			}
			String flightID = travels[i].substring(0,travels[i].indexOf("|")).replaceAll("[^A-Z,0-9]","");
			while(flightID.substring(0,1).matches("[^a-zA-Z0-9]")) {
				flightID = flightID.substring(1);
			}
			travels[i] = travels[i].substring(travels[i].indexOf("|")+1);
			
			String timeAndDate = travels[i].substring(0,travels[i].indexOf("|"));
			travels[i] = travels[i].substring(travels[i].indexOf("|"));
			
			String travelLocations = travels[i].substring(0,travels[i].indexOf('['));
			travels[i] = travels[i].substring(travels[i].indexOf("["));
			
			String flightSection = travels[i].substring(1);
			
			String [] time = timeAndDate.split(",");
			int year = Integer.parseInt(time[0].trim()),month = Integer.parseInt(time[1].trim()),day = Integer.parseInt(time[2].trim()), hour = Integer.parseInt(time[3].trim()), minute = Integer.parseInt(time[4].trim());
			
			while(!travelLocations.substring(0,1).matches("^[A-Z]")) {
				travelLocations = travelLocations.substring(1);
			}
			String orig = travelLocations.substring(0,travelLocations.indexOf("|")).trim();
			String dest = travelLocations.substring(travelLocations.lastIndexOf("|")).replaceAll("[^A-Z]", "").trim();
			
			createFlight(airline, orig, dest, year, month, day, hour, minute, flightID);
			loadFlightSection(flightSection,flightID,airline);
		
			
		}
	}
	private String trimString(String str) {
		int index = str.indexOf(":") + 1;
		String result = str.substring(index).trim();
		return result;
	}
	
	
	private void loadFlightSection(String flightSectionInfo, String flightID, String airlineName) {
		String [] strParts = flightSectionInfo.replace("[", "").split(",");
		for(int i = 0; i < strParts.length; i++) {
			strParts[i] = strParts[i].trim();
			char seatClass = strParts[i].charAt(0);
			strParts[i] = trimString(strParts[i]);
			
			int price = Integer.parseInt(strParts[i].substring(0,strParts[i].indexOf(":")));
			strParts[i] = trimString(strParts[i]);
			
			char layout = strParts[i].charAt(0);
			strParts[i] = trimString(strParts[i]);
			
			
			int rows = Integer.parseInt(strParts[i].trim());
			
			SeatClass s = setSeatClass(seatClass);
			createSection(airlineName,flightID,rows,layout,price,s,"Airline");
		}
		
		
		
	}

}
