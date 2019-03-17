package transportServiceClasses;
import java.util.Scanner;
import travelClasses.Travel;
import travelsection.SeatClass;
import java.util.LinkedList;
public abstract class TransportAbstract {

	public String findSectionID(LinkedList <Travel> flights, Scanner kb) {
		String flID;
		System.out.println("Select a section listed below:");
		for(int i = 0; i < flights.size(); i++) {
			System.out.println(flights.get(i).getID() + " || " + flights.get(i).printOrigToDest());
		}
		do {
			System.out.print("Enter your choice: ");
			flID = kb.nextLine();
		}while(!checkID(flights,flID));
		return flID;
	}
	private boolean checkID(LinkedList <Travel> travels, String flID) {
		for(int i = 0; i < travels.size(); i++) {
			if(travels.get(i).getID().equals(flID)) {
				return true;
			}
		}
		return false;
	}
	public SeatClass selectSeatClass(Scanner kb) {
		SeatClass seatClass = SeatClass.economy;
		System.out.println("Select a seat class (first,business,economic): ");
		return seatClass = seatClass.setSeatClass(kb.nextLine().toUpperCase().charAt(0));
	}
	public Travel getTravelUsingID(String ID, LinkedList <Travel> travelList) {
		for(int i = 0; i < travelList.size(); i++) {
			Travel temp = travelList.get(i);
			if(temp.getID().equals(ID)) {
				return temp;
			}
		}
		System.out.println("ID does not match");
		return null;
	}

}
