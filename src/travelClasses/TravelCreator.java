package travelClasses;

public abstract class TravelCreator {
	public Travel createFlight(String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		return createTravel("Flight",orig,dest,year,month,day,hour,minute,id);
	}
	public Travel createSail(String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		return createTravel("Sail",orig,dest,year,month,day,hour,minute,id);
	}
	protected abstract Travel createTravel(String type, String orig, String dest, int year, int month, int day, int hour, int minute, String id);

}
