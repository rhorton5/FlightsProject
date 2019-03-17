import systemmanager.SystemManager;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class partTwoTester {

	public static void main(String[] args) throws FileNotFoundException{
		SystemManager sm = new SystemManager();
		Scanner kb = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("--- Main Menu ---"
								+ "\n1). Load a file to System Manager"
								+ "\n2). Save transport information to a file"
								+ "\n3). Add port to System Manager"
								+ "\n4). Add transport service to System Manager"
								+ "\n5). Add flight or cruise trips to System Manager"
								+ "\n6). Add flight sections or cabin sections to System Manager"
								+ "\n7). Find avaliable seats using seating preference"
								+ "\n8). Change price of a Seat Class of a desired airline"
								+ "\n9). Change price of a flight section of a desired airline"
								+ "\n10). Display all objects in system"
								+ "\n0). Quit");
			System.out.print("Please enter your choice: ");
			choice = kb.nextInt(); kb.nextLine();
			switch(choice) {
			case 1: sm.loadFile(kb); break;
			case 2: sm.saveSystemDetails(kb); break;
			case 3: sm.nameAndAddPort(kb); break;
			case 4: sm.nameAndAddTransport(kb); break;
			case 5: sm.createTravel(kb); break;
			case 6: 
			case 7: sm.findPerferredFlights(kb); break;
			case 8: sm.changeAirlineSeatClassPrice(kb); break;
			case 9: sm.changeFlightSectionPrice(kb); break;
			case 10: sm.displaySystemDetails(); break;
			}
		}while(choice != 0);
		System.out.println("Goodbye!");
	}

}
