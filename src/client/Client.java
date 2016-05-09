package client;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import model.Booking;
import model.Guest;
import model.MyDate;
import model.Room;
import server.HotelDB;
import view.ClientView;
import view.CommonMenu;
import view.ScannerUserInput;

public class Client implements Serializable {

	private static ScannerUserInput scanUser;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CommonMenu commonmenu;
	private static ClientView clientview;
	private static char number;
	private static int z;

	private Client() {
		clientview = new ClientView();
		scanUser = new ScannerUserInput();
		commonmenu = new CommonMenu();
		z = 0;
		
		

	}

	public static void main(String[] args) {

		String host = "10.52.229.200";
		clientview = new ClientView();
		scanUser = new ScannerUserInput();
		commonmenu = new CommonMenu();
		
		clientview.startClient();
		boolean go = false;
		while (go == false) {

			char i = 0;
			char number = scanUser.scannerUserInputChar(i);

			if ((number < '1' || number > '3')
					&& (number > 'A' || number < 'z')) {

				commonmenu.printOut("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '1') {
				
					

					try {
						Registry registry = LocateRegistry.getRegistry(host);
						HotelDB stub = (HotelDB) registry.lookup("Hello");
						// stub.searchAvailableRooms(arrivalDate,departureDate);
						ArrayList<Booking> response = stub.getListBookings();

						// ArrayList<Room> response3 = stub.getRooms();
						commonmenu.printOut(response);

					} catch (Exception e) {
						commonmenu.printOut("Client exception: " + e.toString());
						e.printStackTrace();
					}
					clientview.startClient();
				}

				else if (number == '2') {

					commonmenu.printOut("Number: " + number);

					try {
						Registry registry = LocateRegistry.getRegistry(host);
						HotelDB stub = (HotelDB) registry.lookup("Hello");

						ArrayList<Guest> response1 = stub.getGuests();
						// stub.searchAvailableRooms(arrivalDate, departureDate)
						// ;

						commonmenu.printOut(response1);

					} catch (Exception e) {
						commonmenu.printOut("Client exception: " + e.toString());
						e.printStackTrace();
					}
					

					
					clientview.startClient();
				} else if (number == '3') {
					commonmenu.printOut("Number: " + number);

					
					commonmenu.printOut("Enter arrival day :");
					int arrivalDay = scanUser.scannerUserInputInt(z);

					commonmenu.printOut("Enter arrival month :");
					int arrivalMonth = scanUser.scannerUserInputInt(z);

					commonmenu.printOut("Enter arrival year :");
					int arrivalYear = scanUser.scannerUserInputInt(z);
					MyDate arrivalDate = new MyDate(arrivalDay, arrivalMonth,
							arrivalYear);

					commonmenu.printOut("Enter departure day :");
					int departureDay = scanUser.scannerUserInputInt(z);

					commonmenu.printOut("Enter departure month :");
					int departureMonth = scanUser.scannerUserInputInt(z);

					commonmenu.printOut("Enter departure year :");
					int departureYear = scanUser.scannerUserInputInt(z);

					MyDate departureDate = new MyDate(departureDay,
							departureMonth, departureYear);

					try {
						Registry registry = LocateRegistry.getRegistry(host);
						HotelDB stub = (HotelDB) registry.lookup("Hello");

						stub.searchAvailableRooms(arrivalDate, departureDate);
						ArrayList<Room> response2 = stub.searchAvailableRooms(
								arrivalDate, departureDate);

						commonmenu.printOut(response2);
						// System.out.println(response3);

					} catch (Exception e) {
						commonmenu.printOut("Client exception: " + e.toString());
						e.printStackTrace();
					}
				

					
					clientview.startClient();

				}
			}
		}

	}
}