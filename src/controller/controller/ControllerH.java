package controller.controller;



import java.util.ArrayList;
import java.util.Scanner;

import db.RoomDB;
import model.Booking;
import model.Guest;
import model.MyDate;
import model.Room;

public class ControllerH implements InterControllerH {
	// this is my second commit
	private Scanner scanner = new Scanner(System.in);

	private MyDate tempArr;
	private MyDate tempDep;
	private MyDate desiredArrival;
	private MyDate desiredDeparture;
	private RoomDB rooms;
	private ArrayList<Booking> bookings;
	private ArrayList<Room> availableRooms;
	private ArrayList<Room> occupied;

	public ControllerH() {
		new MyDate(0, 0, 0);
		new MyDate(0, 0, 0);
		new ArrayList<>();
		new ArrayList<>();
		new ArrayList<>();
		new ArrayList<>();
		new MyDate(0, 0, 0);
		rooms = new RoomDB();
		bookings = new ArrayList<>();
		occupied = new ArrayList<>();

	}

	@Override
	public void start() {

		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Enter number of interaction, you want to do!!");
		System.out.println(" ");

		// Menu
		System.out.println("1. Create Booking\n" + "2. Find Booking\n"
				+ "3. Delete Booking\n" + "4. CheckIn\n" + "5.CheckOut\n"+ "6.Exit");
		boolean go = false;
		while (go == false) {

			char number = scanner.next().charAt(0);

			if ((number < '1' || number > '4')
					&& (number > 'A' || number < 'z')) {

				System.out
						.println("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '6') {// now the exit will be with 6 instead of 4 u get it daniel ??
					exit();
				} else if (number == '1') {

					System.out.println("Number: " + number);

					createBooking();
					go = true;
				} else if (number == '2') {
					System.out.println("Number: " + number);

					findBooking();
					go = true;

				} else if (number == '3') {
					System.out.println("Number: " + number);

					deleteBooking();
					go = true;

				}else if (number == '4') {    //this is new (press 4 for checkIn)
					System.out.println("Number: " + number);

					checkIn() ;
					go = true;
				} else if (number == '5') {  // this is new (press 5 for checkOut)
					System.out.println("Number: " + number);

					checkOut();
					go = true;
				}
			}

		}

	}
	// this new (checkOut)
	public void checkOut() {
		System.out.println("\n");
		System.out.println("Find booking by name");
		System.out.println("Enter guest_name :");
		String name = scanner.nextLine();
		System.out.println("Enter passportNo :");
		scanner.nextLine();
		String passportNo = scanner.nextLine();

		Booking result = null;
		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).getGuest().getName().equalsIgnoreCase(name)
					&& bookings.get(i).getGuest().getPassportNo()
							.equals(passportNo)) {
				result = bookings.get(i);

			}
		}
		if (result == null) {
			System.out.println("No booking founded!");
		} else {
			System.out.println(result.toString());
			System.out.println("Press Enter to get the price :");
			String enter = scanner.nextLine();
			getPrice(result);
		}

	}

	public void getPrice(Booking booking) {

		MyDate dd = booking.getDepartureDate().copy();

		MyDate ad = booking.getArrivalDate().copy();

		int count = 1;
		while (!(ad.getDay() == dd.getDay() && ad.getMonth() == dd.getMonth() && ad
				.getYear() == dd.getYear())) {

			ad.nextDay();
			count++;
		}
		double pricePerDay = booking.getRoom().getRoomPrice();

		double costRoom = pricePerDay * count;
		System.out.println("Total price : " + costRoom);

	}

	//this is the checkIn (new)
	public void checkIn() {
		System.out.println("\n");
		System.out.println("Find booking by name");
		System.out.println("Enter guest_name :");
		String name = scanner.nextLine();
		MyDate today = new MyDate();
		Booking result = null ;
		for(int i =0 ;i<bookings.size();i++)
		{
			if(bookings.get(i).getGuest().getName().equalsIgnoreCase(name)&&
					bookings.get(i).getArrivalDate().equals(today))
			{
				result = bookings.get(i) ;
				System.out.println(bookings.get(i).toString());
				setBooking(bookings.get(i));
				
			}
		}
		
		
	}

	public void setBooking(Booking booking) {
		System.out.println("Edit booking :");
		System.out.println("Enter nationality :");
		String nationality = scanner.nextLine();
		System.out.println("Enter day of birthday :");
		int birthday = scanner.nextInt();
		System.out.println("Enter month of birthday :");
		int birthmonth = scanner.nextInt();
		System.out.println("Enter year of birthday :");
		int birthyear = scanner.nextInt();
		MyDate birthdate = new MyDate(birthday, birthmonth, birthyear);
		System.out.println("Enter passportNo : ");
		String passportNo = scanner.nextLine() ;
		Guest guest = new Guest(booking.getGuest().getName(), nationality,
				birthdate, booking.getGuest().getTelephoneNo(), booking.getGuest().getEmail(),
				passportNo) ;
		booking.setGuest(guest);
		
		
	}

	@Override
	public void createBooking() {

		searchAvailabeRoom();

		System.out.println("Enter room number");
		int roomNo = scanner.nextInt();
		Room room = rooms.getRoomByRoomNo(roomNo);

		// roomsNo.add(counter,roomNo);

		System.out.println("Enter name");
		scanner.nextLine();
		String name = scanner.nextLine();

		// new

		System.out.println("Enter email");
		// scanner.nextLine();
		String email = scanner.nextLine();

		System.out.println("Enter phone number");
		// scanner.nextLine();
		String telephoneNo = scanner.nextLine();

		Guest guest = new Guest(name, email, telephoneNo);
		// customerName.add(counter,name);

		Booking booking = new Booking(guest, desiredArrival, desiredDeparture,
				room);
		bookings.add(booking);
		System.out.println(booking.toString());
		// counter++;
		start();

	}

	@Override
	public void findBooking() {
		System.out.println("\n");
		System.out.println("Find booking by :");
		System.out.println("1. Name\n" + "2. Arrival and Departure\n"
				+ "3. Room number\n" + "4. Return\n" + "5. Exit");
		boolean go = false;
		while (go == false) {

			char number = scanner.next().charAt(0);

			if ((number < '1' || number > '5')
					&& (number > 'A' || number < 'z')) {

				System.out
						.println("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '5') {
					exit();
				} else if (number == '4') {
					start();
				}

				else if (number == '1') {
					System.out.println("Enter name");
					scanner.nextLine();
					String na = scanner.nextLine();

					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getGuest().getName()
								.equalsIgnoreCase(na)) {
							System.out.println(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				} else if (number == '2') {
					// Arrival date
					scanner.nextLine();
					System.out.println("Enter arrival day");
					int arrival1 = scanner.nextInt();
					int aday = arrival1;

					scanner.nextLine();
					System.out.println("Enter arrival month");
					arrival1 = scanner.nextInt();
					int amonth = arrival1;

					scanner.nextLine();
					System.out.println("Enter arrival year");
					arrival1 = scanner.nextInt();
					int ayear = arrival1;

					// Departure date
					scanner.nextLine();
					System.out.println("Enter departure day");
					arrival1 = scanner.nextInt();
					int dday = arrival1;

					scanner.nextLine();
					System.out.println("Enter departure month");
					arrival1 = scanner.nextInt();
					int dmonth = arrival1;

					scanner.nextLine();
					System.out.println("Enter departure year");
					arrival1 = scanner.nextInt();
					int dyear = arrival1;

					scanner.nextLine();

					tempArr = new MyDate(aday, amonth, ayear);
					tempDep = new MyDate(dday, dmonth, dyear);

					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getArrivalDate().getDay() == tempArr
								.getDay()
								&& bookings.get(i).getArrivalDate().getMonth() == tempArr
										.getMonth()
								&& bookings.get(i).getArrivalDate().getYear() == tempArr
										.getYear()
								&& bookings.get(i).getDepartureDate().getDay() == tempDep
										.getDay()
								&& bookings.get(i).getDepartureDate()
										.getMonth() == tempDep.getMonth()
								&& bookings.get(i).getDepartureDate().getYear() == tempDep
										.getYear())
							;

						{
							System.out.println(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				}

				else if (number == '3') {
					System.out.println("Enter room number");

					int na = scanner.nextInt();

					for (int i = 0; i < bookings.size(); i++) {
						if (bookings.get(i).getRoom().getRoomNo() == na) {
							System.out.println(bookings.get(i).toString());
							go = true;
							start();

						}
					}

				}
			}

		}

	}

	@Override
	public void deleteBooking() {
		System.out.println("\n");
		System.out.println("Find booking by :");
		System.out.println("1. Name\n" + "2. Arrival and Departure\n"
				+ "3. Room number\n" + "4. Return\n" + "5. Exit");
		boolean go = false;
		while (go == false) {

			char number = scanner.next().charAt(0);

			if ((number < '1' || number > '5')
					&& (number > 'A' || number < 'z')) {

				System.out
						.println("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '5') {
					exit();
				} else if (number == '4') {
					start();
				}

				else if (number == '1') {
					System.out.println("Enter name");
					scanner.nextLine();
					String na = scanner.nextLine();

					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getGuest().getName()
								.equalsIgnoreCase(na)) {
							System.out.println(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				} else if (number == '2') {
					// Arrival date
					scanner.nextLine();
					System.out.println("Enter arrival day");
					int arrival1 = scanner.nextInt();
					int aday = arrival1;

					scanner.nextLine();
					System.out.println("Enter arrival month");
					arrival1 = scanner.nextInt();
					int amonth = arrival1;

					scanner.nextLine();
					System.out.println("Enter arrival year");
					arrival1 = scanner.nextInt();
					int ayear = arrival1;

					// Departure date
					scanner.nextLine();
					System.out.println("Enter departure day");
					arrival1 = scanner.nextInt();
					int dday = arrival1;

					scanner.nextLine();
					System.out.println("Enter departure month");
					arrival1 = scanner.nextInt();
					int dmonth = arrival1;

					scanner.nextLine();
					System.out.println("Enter departure year");
					arrival1 = scanner.nextInt();
					int dyear = arrival1;

					scanner.nextLine();

					tempArr = new MyDate(aday, amonth, ayear);
					tempDep = new MyDate(dday, dmonth, dyear);

					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getArrivalDate().getDay() == tempArr
								.getDay()
								&& bookings.get(i).getArrivalDate().getMonth() == tempArr
										.getMonth()
								&& bookings.get(i).getArrivalDate().getYear() == tempArr
										.getYear()
								&& bookings.get(i).getDepartureDate().getDay() == tempDep
										.getDay()
								&& bookings.get(i).getDepartureDate()
										.getMonth() == tempDep.getMonth()
								&& bookings.get(i).getDepartureDate().getYear() == tempDep
										.getYear())
							;

						{
							System.out.println(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				}

				else if (number == '3') {
					System.out.println("Enter room number");
					scanner.nextInt();
					int na = scanner.nextInt();

					for (int i = 0; i < bookings.size(); i++) {
						if (bookings.get(i).getRoom().getRoomNo() == na) {
							System.out.println(bookings.get(i).toString());
							go = true;
							start();

						}
					}

				}
			}

		}

	}

	@Override
	public void searchAvailabeRoom() {

		// Arrival date
		scanner.nextLine();
		System.out.println("Enter arrival day");
		int arrival1 = scanner.nextInt();
		int aday = arrival1;

		scanner.nextLine();
		System.out.println("Enter arrival month");
		arrival1 = scanner.nextInt();
		int amonth = arrival1;

		scanner.nextLine();
		System.out.println("Enter arrival year");
		arrival1 = scanner.nextInt();
		int ayear = arrival1;

		// Departure date
		scanner.nextLine();
		System.out.println("Enter departure day");
		arrival1 = scanner.nextInt();
		int dday = arrival1;

		scanner.nextLine();
		System.out.println("Enter departure month");
		arrival1 = scanner.nextInt();
		int dmonth = arrival1;

		scanner.nextLine();
		System.out.println("Enter departure year");
		arrival1 = scanner.nextInt();
		int dyear = arrival1;

		scanner.nextLine();

		desiredArrival = new MyDate(aday, amonth, ayear);
		desiredDeparture = new MyDate(dday, dmonth, dyear);

		compareDates();
		ArrayList<Room> roomDB = new ArrayList<>();
		availableRooms = new ArrayList<>();
		roomDB = rooms.getRooms();

		for (int i = 0; i < roomDB.size(); i++) {
			if (!occupied.contains(roomDB.get(i))) {
				availableRooms.add(roomDB.get(i));
			}
		}
		printAvailableRooms();
	}

	public void printAvailableRooms() {
		System.out.println("Available rooms : ");
		for (int i = 0; i < availableRooms.size(); i++) {
			System.out.println(availableRooms.get(i));
		}
		System.out.println();

	}

	@Override
	public void exit() {
		// End program
		System.out.println("You have end the program");
		System.exit(0);
	}

	@Override
	public void compareDates() {
		for (int i = 0; i < bookings.size(); i++) {
			Booking temp = bookings.get(i);

			if ((desiredArrival.isBetween(temp.getArrivalDate(),
					temp.getDepartureDate()))
					|| (desiredDeparture.isBetween(temp.getArrivalDate(),
							temp.getDepartureDate()))
					|| ((desiredDeparture.equals(temp.getDepartureDate())) && (desiredArrival
							.equals(temp.getArrivalDate())))
					|| ((temp.getArrivalDate().isBetween(desiredArrival,
							desiredDeparture)) && (temp.getDepartureDate()
							.isBetween(desiredArrival, desiredDeparture)))
					|| ((desiredArrival.isBetween(temp.getArrivalDate(),
							temp.getDepartureDate())) && (desiredDeparture
							.isBetween(temp.getArrivalDate(),
									temp.getDepartureDate())))) {

				if (!occupied.contains(temp.getRoom())) {
					occupied.add(temp.getRoom());
					// System.out.println("Occupied" +
					// temp.getRoom().getRoomNumber());

				}
			}

		}

	}

	

}