package transportServiceClasses;
import java.util.LinkedList;
import travelClasses.Travel;
import travelsection.FlightSection;
import travelsection.SeatClass;
import java.util.Scanner; 
public abstract class TransportService extends TransportAbstract implements Comparable<TransportService>{
	private LinkedList <Travel> flights;
	private String name;
	public TransportService() {
		this.name = "";
		this.flights = new LinkedList<Travel>();
	}

	@Override
	public int compareTo(TransportService o) {
		return this.name.compareTo(o.getName());
	}
	public boolean hasAppropriateName(String n) {
		if(n.isEmpty()) {
			System.out.println("Airline's name cannot be empty.");
		}
		else if(n.length() >= 6) {
			System.out.println("The name " + n + " has an invalid length of " + n.length() + ".");
			return false;
		}
		else if(n.matches(".*[0-9].*")) {
			System.out.println(n + " has numbers in the name, which is invalid.");
			return false;
		}
		return true;
	}
	public void addFlight(Travel flight) {
		flights.add(flight);
	}
	public String getName() {
		return this.name;
	}
	public LinkedList <Travel> getTravels(){
		return this.flights;
	}
	public void createTransportService(String n) {
		if(hasAppropriateName(n)){
			this.name = n;
		}
	}
	public String getType() {
		return "Generic Port";
	}
	public boolean hasDuplicateName(LinkedList <TransportService> tf) {
		for(int i = 0; i < tf.size(); i++) {
			if(compareTo(tf.get(i)) == 0) {
				System.out.println("The airline " + this.name + " already exists");
				return true;
			}
		}
		return false;
	}
	public void addFlightSection(FlightSection fs) {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).matchID(fs.getID())) {
				this.flights.get(i).addFlightSection(fs);
			}
		}
	}
	public void bookSeat(String flID, SeatClass s, int row, char cols) {
		for(int i = 0; i < flights.size(); i++) {
			if(this.flights.get(i).getID().equals(flID)) {
				flights.get(i).bookSeat(flID, s, row, cols);
				return;
			}
			
		}
		System.out.println(flID + " does not match with any of the flight's ID.");
	}
	public boolean matchingAvaliableFlight(String orig, String dest, int year, int month, int day, SeatClass s) {
		for(int i = 0; i < this.flights.size(); i++) {
			if(this.flights.get(i).matchingAvaliableFlight(orig, dest, year, month, day, s)) {
				System.out.println("Flight " + this.flights.get(i).getID() + " is avaliable");
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < this.flights.size(); i++) {
			str += this.flights.get(i).toString();
		}
		return str;
	}
	public String saveFile() {
		String str = this.name + "[";
		for(int i = 0; i < flights.size() - 1; i++) {
			str += flights.get(i).saveFile() + "],";
		}
		if(flights.size() != 0) {
			str += flights.get(flights.size()-1).saveFile();
		}
		return str;
	}
	public void changeSeatClassPrice(Scanner kb) {
		changeSeatClassPrice(kb);
	}
	
	public abstract void changeFlightSectionPrice(Scanner kb);
	
	public void findSection(Scanner kb) {
		String flID = super.findSectionID(this.flights,kb);
		boolean booked = false;
		
		for(int i = 0; i < flights.size(); i++) {
			if(booked == true) {
				break;
			}
			if(flights.get(i).getID().equals(flID)) {
				booked = setupBookSeat(flights.get(i),flID,kb);
			}
		}
	}
	public void findSectionUsingPreference(Scanner kb) {
		String flID,preference;
		int choice;
		flID = super.findSectionID(this.flights,kb);
		System.out.println("Select the preference: \n1). Window\n2). Aisle");
		do {
			System.out.print("Enter your choice: ");
			choice = kb.nextInt(); kb.nextLine();
		}while(choice != 1 && choice != 2);
		
		if(choice == 1) {
			preference = "Window";
		}
		else if(choice == 2) {
			preference = "Aisle";
		}
		else {
			System.out.println("Invalid choice was made.");
			return;
		}
		setupBookSeatPreference(flID,preference,kb);
		
	}
	private void setupBookSeatPreference(String flID, String preference, Scanner kb) {
		boolean booked = false;
		SeatClass seatClass = super.selectSeatClass(kb);
		Travel travel = super.getTravelUsingID(flID,this.flights);
		booked = travel.bookSeatWithPreference(flID, seatClass, preference);
		
	}
	private boolean setupBookSeat(Travel travel, String flID, Scanner kb) {
		int row;
		char column;
		
		System.out.println("The following flights are avaliable ---\n" + travel.toString());
		System.out.print("Enter your seat of choice (Type as row '' space '' columns)(Use 1-10 for rows and A-J for columns): ");
		String seat = kb.nextLine();
		SeatClass seatClass = super.selectSeatClass(kb);
		
		try {
			String [] temp = seat.split(" ");
			column = temp[1].trim().charAt(0);
			row = Integer.parseInt(temp[0].trim());
		}catch(Exception e) {
			System.out.println("Row and column was not properly typed.");
			return setupBookSeat(travel,flID,kb);
		}
		
		return travel.bookSeat(flID, seatClass, row, column);
		
	}

}
