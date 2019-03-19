package travelClasses;
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
	

}
