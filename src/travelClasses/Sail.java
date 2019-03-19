package travelClasses;

public class Sail extends Travel {

	public Sail() {
		super();
	}
	@Override
	public String toString() {
		return "Sail: " + super.toString();
	}
	@Override
	public String getType() {
		return "Sail";
	}
	
	public void createSail(String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		super.createTravel(orig, dest, year, month, day, hour, minute, id);
	}

}
