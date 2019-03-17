package travelClasses;

public class TravelFactory extends TravelCreator{
	@Override
	protected Travel createTravel(String type, String orig, String dest, int year, int month, int day, int hour, int minute, String id) {
		if(type.equals("Flight")) {
			Flight f = new Flight();
			f.createFlight(orig,dest,year,month,day,hour, minute, id);
			return f;
		}
		else if(type.equals("Sail")) {
			Sail s = new Sail();
			s.createSail(orig, dest, year, month, day, hour, minute, id);
			return s;
		}
		else {
			Flight f = new Flight();
			f.createTravel(orig, dest, year, month, day, hour, minute, id);
			return f;
		}
		
	}

}
