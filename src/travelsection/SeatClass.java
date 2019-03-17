package travelsection;

public enum SeatClass {
	first,business,economy;
	
	public SeatClass setSeatClass(char letter) {
		if(letter == 'F') {
			return first;
		}
		else if(letter == 'B') {
			return business;
		}
		else
			return economy;
	}
	public int getPrice(SeatClass s) {
		if(s.equals(first)) {
			return 300;
		}
		else if(s.equals(business)) {
			return 200;
		}
		else if(s.equals(economy)) {
			return 100;
		}
		return 0;
	}
	public char getClassType(SeatClass s) {
		if(s.equals(first)) {
			return 'F';
		}
		else if(s.equals(business)) {
			return 'B';
		}
		else if(s.equals(economy)) {
			return 'E';
		}
		return 'U';
	}
}
