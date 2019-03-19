package menu;

import java.util.Scanner;
import java.io.FileNotFoundException;
import systemmanager.SystemManager;

public class MainMenu {
	public static void mainMenu() throws FileNotFoundException{
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
								+ "\n7). Find avaliable seats on desired airline or cruise line"
								+ "\n8). Change price of a Seat Class of a desired airline or cruise line"
								+ "\n9). Change price of a section of a desired airline or cruise line"
								+ "\n10). Book a seat using seat preference or seat's location"
								+ "\n11). Display all objects in system"
								+ "\n0). Quit");
			System.out.print("Please enter your choice: ");
			choice = kb.nextInt(); kb.nextLine();
			switch(choice) {
			case 1: sm.loadFile(kb); break;
			case 2: sm.saveSystemDetails(kb); break;
			case 3: sm.nameAndAddPort(kb); break;
			case 4: sm.nameAndAddTransport(kb); break;
			case 5: sm.createTravel(kb); break;
			case 6: sm.createTravelSection(kb); break;
			case 7: sm.findPerferredFlights(kb); break;
			case 8: sm.changeAirlineSeatClassPrice(kb); break;
			case 9: sm.changeTravelSectionPrice(kb); break;
			case 10: sm.setupSeatBooking(kb); break;
			case 11:sm.displaySystemDetails(); break;
			}
			
			
			
		}while(choice != 0);
		System.out.println("Goodbye!");
	}
}
