package transportServiceClasses;

public abstract class TransportServiceCreator {
	public TransportService createAirline(String n) {
		return createTransportService("Airline",n);
	}
	public TransportService createCruiseLine(String n) {
		return createTransportService("Cruise Line",n);
	}
	protected abstract TransportService createTransportService(String type, String n);
	

}
