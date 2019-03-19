package airportClasses;
public class PortFactory extends PortCreator{

	@Override 
	public Port createPort(String type, String n){
		if(type.equalsIgnoreCase("Airport")) {
			Airport air = new Airport();
			air.createAirport(n);
			return air;
		}
		else if(type.equalsIgnoreCase("Cruise Port")) {
			CruisePort cruise = new CruisePort();
			cruise.createCruisePort(n);
			return cruise;
		}
		else {
			throw new IllegalArgumentException("The program returned an invalid type for createPort.");
		}
		
	}
}
