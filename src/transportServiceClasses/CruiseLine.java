package transportServiceClasses;

import java.util.LinkedList;
import java.util.Scanner;

import travelClasses.Travel;
import travelsection.SeatClass;

public class CruiseLine extends TransportService {
	public CruiseLine() {
		super();
	}
	public void createCruiseLine(String n) {
		super.createTransportService(n);
	}
	@Override
	public String toString() {
		String str = "CruiseLine " + super.getName() + " with the following CruiseLines:\n";
		return str + super.toString();
	}
	@Override
	public String getType() {
		return "Cruise Line";
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
	public void changeTravelSectionPrice(Scanner kb) {
		LinkedList <Travel> travelList = super.getTravels();
		String TravelSectionID;
		int price;
		
		System.out.print("Enter the cruise's section's ID: ");
		TravelSectionID = kb.nextLine();
		
		System.out.print("Enter " + TravelSectionID + "'s new price: ");
		price = kb.nextInt(); kb.nextLine();
		
		for(int i = 0; i < travelList.size(); i++) {
			if(travelList.get(i).getID().equals(TravelSectionID)) {
				travelList.get(i).changeTravelSectionPrice(price, TravelSectionID);;
			}
		}
		
	}
}
