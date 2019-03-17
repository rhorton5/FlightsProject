package travelsection;

public class Seat {
	private boolean isAvaliable;
	private int row, column;
	final int intToCharacterNumber = 64;
	public Seat(int row, int column) {
		this.isAvaliable = true;
		this.row = row+1;
		this.column = column+1;
	}
	public boolean seatAvaliable() {
		return this.isAvaliable;
	}
	public void takeSeat() {
		this.isAvaliable = false;
	}
	@Override
	public String toString() {
		if(this.isAvaliable == false) {
			int temp = this.column + intToCharacterNumber;
			char columnLetter = (char)temp;
			return "Seat " + this.row + " " + columnLetter + "\n";//int to char.
		}
		return "";
	}
	public boolean isWindowSeat(int maxRow) {
		if(this.row == 1) {
			return true;
		}
		else if(maxRow == this.row) {
			return true;
		}
		return false;
	
	}
	public boolean isAisleSeat(char layout) {
		int aisleCol1, aisleCol2, aisleCol3, aisleCol4;
		if(layout == 'S') {
			aisleCol1 = 1; aisleCol2 = 2;
			return this.column == aisleCol1 || this.column == aisleCol2;
		}
		else if(layout == 'M') {
			aisleCol1 = 2; aisleCol2 = 3;
			return this.column == aisleCol1 || this.column == aisleCol2;
		}
		else {
			aisleCol1 = 3; aisleCol2 = 4; aisleCol3 = 7; aisleCol4 = 8;
			return this.column == aisleCol1 || this.column == aisleCol2 || this.column == aisleCol3 || this.column == aisleCol4;
		}
		
	}

}
