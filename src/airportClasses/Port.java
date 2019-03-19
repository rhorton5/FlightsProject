package airportClasses;

import java.util.LinkedList;

public abstract class Port implements Comparable <Port>{
	private String name;
	public Port() {
		this.name = "";
	}
	public String getName() {
		return this.name;
	}
	public abstract boolean hasAppropriateName(String n);
	public void createPort(String n) {
		if(hasAppropriateName(n)) {
			this.name = n;
		}
	}
	public boolean duplicateCheck(LinkedList <Port> port){
		if(!this.name.isEmpty()){
			for(int i = 0; i < port.size(); i++) {
				if(port.get(i).compareTo(this) == 0) {
					System.out.println(this.name + " is already in the system.");
					return true;
				}
			}
		}
		return false;
	}
	protected boolean isValid() {
		return !this.name.isEmpty();
	}
	@Override
	public String toString() {
		return this.name;
	}
	public String getType() {
		return "Generic Port";
	}
	public LinkedList<Port> addPortToList(LinkedList<Port> list) {
		if (!duplicateCheck(list) && !getName().isEmpty()) {
			list.add(this);
			System.out.println(getName() + " was successfully added!");
		}
		return list;
	}
}
