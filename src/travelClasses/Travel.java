package travelClasses;
import java.util.LinkedList;
import travelsection.FlightSection;
import travelsection.SeatClass;
public abstract class Travel extends TravelAbstracts implements Comparable <Travel>{
	private LinkedList<FlightSection> fs;
	private String orig, dest, id;
	private int day, month, year;
	public Travel() {
		this.orig = "";
		this.dest = "";
		this.id = "";
		this.day = 0;
		this.month = 0;
		this.year = 0;
		this.fs = new LinkedList<FlightSection>();
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
	protected LinkedList<FlightSection> getFlightSection(){
		return this.fs;
	}
	public abstract void changeSeatClassPrice(int price, SeatClass seatClass);
	public abstract void changeFlightSectionPrice(int price, String flightSectionID);
	public abstract String getType();
	public boolean hasAppropriateDates(int day, int month, int year) {
		int maxDay = 32, minDay = 1, maxMonth = 12, minMonth = 1, minYear = 2019;
		boolean validFlight = true;
		
		if(day > maxDay || minDay > day) {
			System.out.print("The day of the flight is invalid. ");
			validFlight = false;
		}
		if(month >= maxMonth || month < minMonth) {
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
	public void addFlightSection(FlightSection flightsection) {
		super.addFlightSection(flightsection,fs);
	}
	public String getID() {
		return this.id;
	}
	public void bookSeat(String flID, SeatClass s, int row, char col) {
		for(int i = 0; i < fs.size(); i++) {
			if(this.fs.get(i).getID().equals(flID) && this.fs.get(i).getSeatClass().equals(s)) {
				this.fs.get(i).bookSeat(row, col);
			}
		}
	}
	public boolean matchingAvaliableFlight(String orig, String dest, int year, int month, int day, SeatClass s) {
		if(this.year != year || this.month != month || this.day != day) {
			return false;
		}
		if(this.orig.equals(orig) && this.dest.equals(dest)) {
			for(int i = 0; i < fs.size(); i++) {
				if(this.fs.get(i).matchingAvaliableFlights(s)) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String toString() {
		String str = this.orig + " to " + this.dest + ".  On the date: " + this.month + " "+ this.day + " " + this.year + ".\n";
		for(int i = 0; i < this.fs.size(); i++) {
			str += this.fs.get(i).toString();
		}
		return str;
	}
	public String saveFile() {//Hour and minutes are currently placeholders.
		String str = "";
		str = this.id + "|" + this.year + ", " + this.month + ", " + this.day + ", " + 10 + ", " + 30 + " | " + this.orig + " | " + this.dest  + " [";
		for(int i = 0; i < this.fs.size()-1; i++) {
			 str += this.fs.get(i).saveFile() + ", "; 
		}
		if(fs.size() != 0) {
			str += fs.get(fs.size() - 1).saveFile();
		}
		return str;
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
}
