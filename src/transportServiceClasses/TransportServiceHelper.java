package transportServiceClasses;

import java.util.LinkedList;

public interface TransportServiceHelper {
	public TransportService addTransportService(String n);
	public TransportService createTransportService(String n);
	public LinkedList <TransportService> addTransportToSystem(TransportService air, LinkedList<TransportService> list);
}
