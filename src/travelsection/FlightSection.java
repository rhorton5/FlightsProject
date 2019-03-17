package travelsection;
import travelsection.SeatClass;
public class FlightSection{
	private SeatClass seatclass;
	private Seat seats[][];
	private String id;
	private char layout;
	private int price,row;
	
	final int maxrow = 100, minrow = 1;
	final int maxcol = 10, mincol = 1;
	final int intToCharNumber = 64;
	
	public FlightSection() {
		this.seats = new Seat[1][1];
		createSeats();
	}
	private void createSeats() {
		for(int i = 0; i < this.seats.length; i++) {
			for(int j = 0; j < this.seats[i].length; j++) {
				this.seats[i][j] = new Seat(i,j);
			}
		}
	}
	public void createSection(SeatClass s, int row, char layout, String flID, int price) {
		if(minrow > row || row > maxrow) {
			System.out.println("Row has an invalid size.");
			return;
		}
		int column = layoutSize(layout);
		if(column == -1) {
			return;
		}
		this.seatclass = s;
		this.seats = new Seat[row][column];
		this.id = flID;
		this.row  = row;
		this.layout = layout;
		if(price == 0) {
			this.price = this.seatclass.getPrice(s);
		}
		this.price = price;
		createSeats();
		
	}
	public int layoutSize(char layout) {
		if(layout == 'S') {
			return 3;
		}
		else if(layout == 'M') {
			return 4;
		}
		else if(layout == 'W') {
			return 10;
		}
		System.out.println("The column size is invalid");
		return -1;
	}
	public boolean validSection() {
		if(this.seatclass != null) {
			return true;
		}
		return false;
	}
	public String getID() {
		return this.id;
	}
	public SeatClass getSeatClass() {
		return this.seatclass;
	}
	public void changePrice(int newPrice) {
		System.out.println(this.id + " has changed from $" + this.price + " to $" + newPrice);
		this.price = newPrice;
	}
	public boolean isDuplicateFlightSection(FlightSection fs) {
		if(this.seatclass.equals(fs.getSeatClass())){
			System.out.println("This Flight Section was already created.");
			return true;
	}
	return false;
	}
	public int getPrice() {
		return this.price;
	}
	public void bookSeat(int row, char cols) {
		int columns = (char)cols-intToCharNumber;
		if(this.seats[row-1][columns-1].seatAvaliable()) {
			this.seats[row-1][columns-1].takeSeat();
			System.out.println("Seat " + row + " " + cols + " has successfully been booked.");
		}
		else {
			System.out.println("Seat " + row + " " + cols + " has already been booked.");
		}
	}
	public boolean matchingAvaliableFlights(SeatClass s) {
		if(!s.equals(this.seatclass)) {
			return false;
		}
		for(int i = 0; this.seats.length > i; i++) {
			for(int j = 0; this.seats[i].length > j; j++) {
				if(seats[i][j].seatAvaliable()) {
					int temp = j + intToCharNumber + 1;
					char letter = (char)temp;
					System.out.println("Seat " + i + " " + letter + " " + " is avaliable for the requested flight for " + "$" + this.price + "!");
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String toString() {
		String str = "Section: " + this.id + "  Class: " + this.seatclass + "\n";
		str += "Seat Row: " + this.seats.length + " Seat Columns: " + this.seats[0].length + "\n";
		str += "The following seats have been taken: \n";
		for(int i = 0; i < this.seats.length; i++) {
			for(int j = 0; j < this.seats[i].length; j++) {
				str += this.seats[i][j].toString();
			}
		}
		return str + "\n";
		
	}
	public String saveFile() {
		String str = this.seatclass.getClassType(this.seatclass) + ": " + this.price + ": " + this.layout + ": " + this.row;
		return str;
	}

}