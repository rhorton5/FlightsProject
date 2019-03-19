package transportServiceClasses;

public class TransportServiceFactory extends TransportServiceCreator{
	@Override
	protected TransportService createTransportService(String type, String n) {
		if(type.equals("Airline")) {
			Airline al = new Airline();
			al.createAirline(n);
			return al;
		}
		else if(type.equals("Cruise Line")){
			CruiseLine sp = new CruiseLine();
			sp.createCruiseLine(n);
			return sp;
		}
		return null;
	}

}
