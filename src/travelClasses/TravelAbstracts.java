package travelClasses;

import travelsection.FlightSection;
import travelsection.SeatClass;
import java.util.LinkedList;

public abstract class TravelAbstracts {
	
	public LinkedList<FlightSection> addFlightSection(FlightSection flightsection, LinkedList<FlightSection> fs) {
		if(checkForUniqueSection(flightsection,fs) && !hasDuplicateSeatClass(flightsection.getSeatClass(),fs)) {
			fs.add(flightsection);
			System.out.println("Flight Section was successfully made!");
		}
		return fs;
			
	}
	private boolean hasDuplicateSeatClass(SeatClass s,LinkedList <FlightSection> fs) {
		for(int i = 0; i < fs.size(); i++) {
			
			if(fs.get(i).getSeatClass().equals(s)) {
					System.out.println("SeatClass already exists.");
					return true;
			}
		}
		return false;
	}
	private boolean checkForUniqueSection(FlightSection flightsection,LinkedList <FlightSection> fs) {
		for(int i = 0; i < fs.size(); i++) {
			if(fs.get(i).isDuplicateFlightSection(flightsection)) {
				return false;
			}
		}
		return true;
	}
}
