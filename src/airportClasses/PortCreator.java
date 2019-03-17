package airportClasses;

public abstract class PortCreator {
	public Port createAirport(String n) {
		return createPort("Airport",n);
	}
	public Port createCruisePort(String n) {
		return createPort("Cruise Port",n);
	}
	abstract Port createPort(String type,String n);

}
