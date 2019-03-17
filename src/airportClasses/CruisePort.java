package airportClasses;

public class CruisePort extends Port {
	public CruisePort() {}
	@Override
	public int compareTo(Port o) {
		return super.getName().compareTo(o.getName());
	}
	public void createCruisePort(String n) {
		super.createPort(n);
	}

	@Override
	public boolean hasAppropriateName(String n) {
		if(n.length() != 3) {
			System.out.println("The cruise port name " + n + " has an invalid length of " + n.length() + ".");
			return false;
		}
		else if(n.matches(".*[0-9].*")) {
			System.out.println(n + " has numbers in the name, which is invalid.");
			return false;
		}
		return true;
	}
	@Override
	public String getType() {
		return "Cruise Port";
	}
}
