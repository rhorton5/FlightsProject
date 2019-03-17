package transportServiceClasses;
import travelClasses.Travel;
import travelsection.SeatClass;

import java.util.Scanner;
import java.util.LinkedList;
public class Airline extends TransportService{
	public Airline() {
		super();
	}
	public void createAirline(String n) {
		super.createTransportService(n);
	}
	@Override
	public String toString() {
		String str = "Airline " + super.getName() + " with the following flights:\n";
		return str + super.toString();
	}
	@Override
	public String getType() {
		return "Airline";
	}
	public void changeSeatClassPrice(Scanner kb) {
		LinkedList <Travel> travelList = super.getTravels();
		SeatClass seatClass = SeatClass.economy;
		int price;
		
		System.out.print("Enter the Seat Class you wish to change: ");
		seatClass = seatClass.setSeatClass(kb.nextLine().charAt(0));
		
		System.out.print("What is " + seatClass + "'s new price: ");
		price = kb.nextInt(); kb.nextLine();
		
		for(int i = 0; travelList.size() > i; i++) {
			travelList.get(i).changeSeatClassPrice(price,seatClass);
		}
	}
	@Override
	public void changeFlightSectionPrice(Scanner kb) {
		LinkedList <Travel> travelList = super.getTravels();
		String flightSectionID;
		int price;
		
		System.out.print("Enter the flight section's ID: ");
		flightSectionID = kb.nextLine();
		
		System.out.print("Enter " + flightSectionID + "'s new price: ");
		price = kb.nextInt(); kb.nextLine();
		
		for(int i = 0; i < travelList.size(); i++) {
			if(travelList.get(i).getID().equals(flightSectionID)) {
				travelList.get(i).changeFlightSectionPrice(price, flightSectionID);;
			}
		}
		
	}
	

}
