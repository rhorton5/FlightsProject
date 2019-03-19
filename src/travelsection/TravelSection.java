package travelsection;
import travelsection.SeatClass;
public class TravelSection{
	private SeatClass seatclass;
	private Seat seats[][];
	private String id;
	private char layout;
	private int price,row;
	
	final int maxrow = 100, minrow = 1;
	final int maxcol = 10, mincol = 1;
	final int intToCharNumber = 64;
	
	public TravelSection() {
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
		if(price <= 0) {
			this.price = this.seatclass.getPrice(s);
		}
		else {
			this.price = price;
		}
		createSeats();
		
	}
	public int layoutSize(char layout) {
		if(layout == 'S' || layout == 's') {
			return 3;
		}
		else if(layout == 'M' || layout == 'm') {
			return 4;
		}
		else if(layout == 'W' || layout == 'w') {
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
	public boolean isDuplicateTravelSection(TravelSection fs) {
		if(this.seatclass.equals(fs.getSeatClass())){
			System.out.println("Section of this Seat Class was already created.");
			return true;
	}
	return false;
	}
	public int getPrice() {
		return this.price;
	}
	public boolean bookSeat(int row, char cols) {
		int columns = (char)cols-intToCharNumber;
		if(this.seats[row-1][columns-1].seatAvaliable()) {
			this.seats[row-1][columns-1].takeSeat();
			System.out.println("Seat " + row + " " + cols + " has successfully been booked.");
			return true;
		}
		else {
			System.out.println("Seat " + row + " " + cols + " has already been booked.");
			return false;
		}
	}
	public boolean bookSeatWithPreference(String preference) {
		if(preference.equals("Window")){
			return bookWindowSeats();
		}
		else if(preference.equals("Aisle")) {
			return bookAisleSeats();
		}
		System.out.println("Invalid preference.");
		return false;
	}
	private boolean bookAisleSeats() {
		boolean booked = false;
		int row = 1;
		while(booked == false && row < this.row) {
			if(this.layout == 'S' || this.layout == 's') {
				booked = bookSeat(row,'A');
				if(booked == false) {
					booked = bookSeat(row,'B');
				}
			}
			else if(this.layout == 'M' || this.layout == 'm') {
				booked = bookSeat(row,'B');
				if(booked == false) {
					booked = bookSeat(row,'C');
				}
			}
			else if(this.layout == 'W' || this.layout == 'w') {
				booked = bookSeat(row,'B');
				int aisleSize = 0, curRow = 0;
				char [] aisleSeats = {'C','D','H','I'};
				while(booked == false && aisleSize != 0 && curRow != this.maxrow) {
					booked = bookSeat(curRow,aisleSeats[aisleSize]);
				}
			}
			else {
				System.out.println("Layout doesn't exist.");
				return false;
			}
			row++;
		}
		
		return booked;
	}
	private boolean bookWindowSeats() {
		char curColumn = 'A';
		boolean booked = false;
		int curRow = 1;
		while(booked == false && ((int)curColumn-64) < layoutSize(this.layout) && curRow < this.row) {
			int curMaxRow = this.row-curRow+1;
			booked = bookSeat(curRow,curColumn);
			
			if(booked == false) {
				bookSeat(curMaxRow,curColumn);
			}
			curColumn++;
		}
		return booked;
	}
	public boolean matchingAvaliableFlights(SeatClass s) {
		if(!s.equals(this.seatclass)) {
			return false;
		}
		for(int i = 0; this.seats.length > i; i++) {
			for(int j = 0; this.seats[i].length > j; j++) {
				if(seats[i][j].seatAvaliable()) {
					System.out.print("For the price of $" + this.price + ",");
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public String toString() {
		String str = "Section: " + this.id + "  Class: " + this.seatclass + " Price: $" + this.price + "\n";
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