package airportClasses;

import java.util.LinkedList;

public interface PortAbstractHelper {
	public Port addPort(String n);
	public Port createPort(String type,String n);
	public LinkedList <Port> addPortToList(Port air, LinkedList <Port> list);
	
}
