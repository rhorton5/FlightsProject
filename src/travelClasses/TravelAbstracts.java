package travelClasses;

import travelsection.TravelSection;
import travelsection.SeatClass;
import java.util.LinkedList;

public abstract class TravelAbstracts {
	
	public LinkedList<TravelSection> addTravelSection(TravelSection TravelSection, LinkedList<TravelSection> fs) {
		if(checkForUniqueSection(TravelSection,fs) && !hasDuplicateSeatClass(TravelSection.getSeatClass(),fs)) {
			fs.add(TravelSection);
			System.out.println("Section was successfully made!");
		}
		return fs;
			
	}
	private boolean hasDuplicateSeatClass(SeatClass s,LinkedList <TravelSection> fs) {
		for(int i = 0; i < fs.size(); i++) {
			
			if(fs.get(i).getSeatClass().equals(s)) {
					System.out.println("SeatClass already exists.");
					return true;
			}
		}
		return false;
	}
	private boolean checkForUniqueSection(TravelSection TravelSection,LinkedList <TravelSection> fs) {
		for(int i = 0; i < fs.size(); i++) {
			if(fs.get(i).isDuplicateTravelSection(TravelSection)) {
				return false;
			}
		}
		return true;
	}
}
