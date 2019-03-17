package transportServiceClasses;

import java.util.Scanner;

public class Ship extends TransportService {
	public Ship() {
		super();
	}
	public void createShip(String n) {
		super.createTransportService(n);
	}
	@Override
	public String toString() {
		String str = "Ship " + super.getName() + " with the following ships:\n";
		return str + super.toString();
	}
	@Override
	public String getType() {
		return "Ship";
	}
	@Override
	public void changeFlightSectionPrice(Scanner kb) {
		
	}
}
