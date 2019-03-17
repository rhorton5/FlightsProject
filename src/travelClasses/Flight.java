package travelClasses;
import java.util.LinkedList;
import travelsection.FlightSection;
import travelsection.SeatClass;
public class Flight extends Travel{
	
	Flight() {
		super();
	}
	public void createFlight(String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		super.createTravel(orig, dest, year, month, day,hour,minute, id);
	}
	@Override
	public String toString() {
		return "Flight: " + super.toString();
	}
	@Override
	public String getType() {
		return "Flight";
	}
	public void changeSeatClassPrice(int price, SeatClass seatClass) {
		LinkedList <FlightSection> flightSect = super.getFlightSection();
		
		for(int i = 0; i < flightSect.size(); i++) {
			if(flightSect.get(i).getSeatClass().equals(seatClass)) {
				flightSect.get(i).changePrice(price);
			}
		}
		
	}
	@Override
	public void changeFlightSectionPrice(int price, String flightSectionID) {
		LinkedList <FlightSection> flightSect = super.getFlightSection();
		
		for(int i = 0; i < flightSect.size(); i++) {
			flightSect.get(i).changePrice(price);
			
		}
		
		
	}
	

}
