package travelClasses;
import java.util.LinkedList;
import java.util.Scanner;
import travelsection.TravelSection;
import travelsection.SeatClass;
public abstract class Travel extends TravelAbstracts implements Comparable <Travel>{
	private LinkedList<TravelSection> ts;
	private String orig, dest, id;
	private int day, month, year;
	public Travel() {
		this.orig = "";
		this.dest = "";
		this.id = "";
		this.day = 0;
		this.month = 0;
		this.year = 0;
		this.ts = new LinkedList<TravelSection>();
	}
	public boolean hasAppropriateID(String ID) {
		if(!ID.matches(".*[0-9].*") && !ID.matches("[a-zA-Z]+")) {
			System.out.println(ID + " is invalid.  It is not alphanumeric.");
		}
		return true;
	}
	public boolean matchID(String ID) {
		return this.id.equals(ID);
	}
	protected LinkedList<TravelSection> getTravelSection(){
		return this.ts;
	}
	public void changeSeatClassPrice(int price, SeatClass seatClass) {
		LinkedList <TravelSection> flightSect = getTravelSection();
		
		for(int i = 0; i < flightSect.size(); i++) {
			if(flightSect.get(i).getSeatClass().equals(seatClass)) {
				flightSect.get(i).changePrice(price);
			}
		}
		
	}
	public void changeTravelSectionPrice(int price, String TravelSectionID) {
		LinkedList <TravelSection> flightSect = getTravelSection();
		
		for(int i = 0; i < flightSect.size(); i++) {
			flightSect.get(i).changePrice(price);
			
		}
	}
	public abstract String getType();
	public boolean hasAppropriateDates(int day, int month, int year) {
		int maxDay = 32, minDay = 1, maxMonth = 12, minMonth = 1, minYear = 2019;
		boolean validFlight = true;
		
		if(day > maxDay || minDay > day) {
			System.out.print("The day of the flight is invalid. ");
			validFlight = false;
		}
		if(month > maxMonth || month < minMonth) {
			System.out.print("The month of the flight is invalid. ");
			validFlight = false;
		}
		if(year < minYear) {
			System.out.println("The year of the flight is invalid. ");
			validFlight = false;
		}
		return validFlight;
	}
	public void createTravel(String orig, String dest, int year, int month, int day, int hour, int minute, String ID) {
		if(hasAppropriateDates(day,month,year) && hasAppropriateID(ID) && hasAppropriateTime(hour,minute)) {
			this.orig = orig;
			this.dest = dest;
			this.id = ID;
			this.day = day;
			this.month = month;
			this.year = year;
		}
	}
	public boolean hasAppropriateTime(int hour, int minute) {
		boolean res = true;
		int minTime = 0, maxHour = 24, maxMinute = 59;//60 minutes counts as 0.
		if(hour < minTime || hour > maxHour) {
			System.out.println("The hour is invalid.");
			res = false;
		}
		if(minute < minTime || minute > maxMinute) {
			System.out.println("The minute is invalid.");
			res = false;
		}
		return res;
	}
	public boolean validTravel() {
		return !this.orig.isEmpty();
	}
	public void addTravelSection(TravelSection TravelSection) {
		super.addTravelSection(TravelSection,ts);
	}
	public String getID() {
		return this.id;
	}
	public boolean bookSeat(String flID, SeatClass s, int row, char col) {
		for(int i = 0; i < ts.size(); i++) {
			if(this.ts.get(i).getID().equals(flID) && this.ts.get(i).getSeatClass().equals(s)) {
				return this.ts.get(i).bookSeat(row, col);
			}
		}
		System.out.println(flID + " was not found.");
		return false;
	}
	public boolean bookSeatWithPreference(String flID, SeatClass s, String preference) {
		for(int i = 0; i < ts.size(); i++) {
			if(this.ts.get(i).getID().equals(flID) && this.ts.get(i).getSeatClass().equals(s)) {
				return this.ts.get(i).bookSeatWithPreference(preference);
			}
		}
		System.out.println(flID + " was not found.");
		return false;
	}
	public boolean matchingAvaliableFlight(String orig, String dest, int year, int month, int day, SeatClass s) {
		if(this.year != year || this.month != month || this.day != day) {
			return false;
		}
		if(this.orig.equals(orig) && this.dest.equals(dest)) {
			for(int i = 0; i < ts.size(); i++) {
				if(this.ts.get(i).matchingAvaliableFlights(s)) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String toString() {
		String str = this.orig + " to " + this.dest + ".  On the date: " + this.month + " "+ this.day + " " + this.year + ".\n";
		for(int i = 0; i < this.ts.size(); i++) {
			str += this.ts.get(i).toString();
		}
		return str;
	}
	public String saveFile() {//Hour and minutes are currently placeholders.
		String str = "";
		str = this.id + "|" + this.year + ", " + this.month + ", " + this.day + ", " + 10 + ", " + 30 + " | " + this.orig + " | " + this.dest  + " [";
		for(int i = 0; i < this.ts.size()-1; i++) {
			 str += this.ts.get(i).saveFile() + ", "; 
		}
		if(ts.size() != 0) {
			str += ts.get(ts.size() - 1).saveFile();
		}
		return str;
	}
	public String printOrigToDest() {
		return "From: " + this.orig + " to " + this.dest;
	}
	@Override
	public int compareTo(Travel o) {
		int res = this.orig.compareTo(o.orig);
		if(res != 0) {
			return res;
		}
		res = this.dest.compareTo(o.dest);
		if(res != 0) {
			return res;
		}
		res = this.id.compareTo(o.id);
		if(res != 0) {
			return res;
		}
		res = o.day-this.day;
		if(res != 0) {
			return res;
		}
		res = o.month-this.month;
		if(res != 0) {
			return res;
		}
		return o.year-this.year;
	}
	public String typeColumnAndRow(Scanner kb) {
		System.out.println("The following flights are avaliable ---\n" + toString());
		System.out.print(
				"Enter your seat of choice (Type as row '' space '' columns)(Use 1-10 for rows and A-J for columns): ");
		String seat = kb.nextLine();
		return seat;
	}
}
