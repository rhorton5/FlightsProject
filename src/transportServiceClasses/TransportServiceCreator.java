package transportServiceClasses;

public abstract class TransportServiceCreator {
	public TransportService createAirline(String n) {
		return createTransportService("Airline",n);
	}
	public TransportService createShip(String n) {
		return createTransportService("Ship",n);
	}
	protected abstract TransportService createTransportService(String type, String n);
	

}
