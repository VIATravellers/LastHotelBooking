package controller.controller;



import java.util.ArrayList;
import java.util.Scanner;

import view.*;
import db.RoomDB;
import model.*;


public class ControllerH implements InterControllerH {

	private Scanner scanner = new Scanner(System.in);

	private MyDate tempArr;
	private MyDate tempDep;
	private MyDate desiredArrival;
	private MyDate desiredDeparture;
	private RoomDB rooms;
	private ArrayList<Booking> bookings;
	private ArrayList<Room> availableRooms;
	private ArrayList<Room> occupied;
    private MainMenu menu ;
    private CheckinGuestMenu checkinMenu ;
    private CheckoutGuestMenu checkoutMenu ;
    private CreateBookingMenu createBookingMenu ;
    private FindBookingMenu findBookingMenu ;
    private DeleteBookingMenu deleteBookingMenu ;
    private ExitProgramMenu exitProgramMenu ;
    private CommonMenu commonmenu ;


    
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
		menu = new MainMenu();
		checkinMenu = new CheckinGuestMenu();
		checkoutMenu = new CheckoutGuestMenu();
		createBookingMenu = new CreateBookingMenu();
		findBookingMenu = new FindBookingMenu(); 
		deleteBookingMenu = new DeleteBookingMenu();
		exitProgramMenu = new ExitProgramMenu();
		commonmenu=new CommonMenu();
		
	}

	@Override
	public void start() {
	
		menu.startMenu();
		

		boolean go = false;
		while (go == false) {

			char number = scanner.next().charAt(0);

			if ((number < '1' || number >= '7')
					&& (number > 'A' || number < 'z')) {

				commonmenu.printOut("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '6') {// now the exit will be with 6 instead of 4 u get it daniel ??
					exit();
				} else if (number == '1') {

					commonmenu.printOut("Number: " + number);

					createBooking();
					go = true;
				} else if (number == '2') {
					commonmenu.printOut("Number: " + number);

					findBooking();
					go = true;

				} else if (number == '3') {
					commonmenu.printOut("Number: " + number);

					deleteBooking();
					go = true;

				}else if (number == '4') {    //this is new (press 4 for checkIn)
					commonmenu.printOut("Number: " + number);

					checkIn() ;
					go = true;
				} else if (number == '5') {  // this is new (press 5 for checkOut)
					commonmenu.printOut("Number: " + number);

					checkOut();
					go = true;
				}
			}

		}

	}
	// this new (checkOut)
	public void checkOut() {
		checkoutMenu = new CheckoutGuestMenu();
		
		checkoutMenu.startCheckOut();
		scanner.nextLine();
		
		checkoutMenu.userInput("guest name : ");
		String name = scanner.nextLine();
		checkoutMenu.userInput("passportNo :");
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
			commonmenu.printOut("No booking founded!");
		} else {
			commonmenu.printOut(result.toString());
			commonmenu.printOut("Press Enter to get the price :");
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
		commonmenu.printOut("Total price : " + costRoom);

	}

	//this is the checkIn (new)
	public void checkIn() {
		
		checkinMenu = new CheckinGuestMenu();
		checkinMenu.startCheckIn();
		checkinMenu.userInput("guest name :");
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
		commonmenu.printOut("Edit booking :");
		checkinMenu.userInput("nationality :");
		String nationality = scanner.nextLine();
		checkinMenu.userInput("day of birthday :");
		int birthday = scanner.nextInt();
		checkinMenu.userInput("month of birthday :");
		int birthmonth = scanner.nextInt();
		checkinMenu.userInput("year of birthday :");
		int birthyear = scanner.nextInt();
		MyDate birthdate = new MyDate(birthday, birthmonth, birthyear);
		checkinMenu.userInput("passportNo : ");
		String passportNo = scanner.nextLine() ;
		Guest guest = new Guest(booking.getGuest().getName(), nationality,
				birthdate, booking.getGuest().getTelephoneNo(), booking.getGuest().getEmail(),
				passportNo) ;
		booking.setGuest(guest);
		
		
	}

	@Override
	public void createBooking() {

		searchAvailabeRoom();
		createBookingMenu = new CreateBookingMenu();
		
		createBookingMenu.userInput(" room number");
		int roomNo = scanner.nextInt();
		Room room = rooms.getRoomByRoomNo(roomNo);


		createBookingMenu.userInput(" name");
		scanner.nextLine();
		String name = scanner.nextLine();

		createBookingMenu.userInput(" email");
		String email = scanner.nextLine();

		createBookingMenu.userInput("phone number");
		String telephoneNo = scanner.nextLine();

		Guest guest = new Guest(name, email, telephoneNo);

		Booking booking = new Booking(guest, desiredArrival, desiredDeparture,
				room);
		bookings.add(booking);
		commonmenu.printOut(booking.toString());
		start();

	}

	@Override
	public void findBooking() {
		
		findBookingMenu = new FindBookingMenu();
		findBookingMenu.startFindBooking();
		boolean go = false;
		while (go == false) {

			char number = scanner.next().charAt(0);

			if ((number < '1' || number > '5')
					&& (number > 'A' || number < 'z')) {

				commonmenu.printOut("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '5') {
					exit();
				} else if (number == '4') {
					start();
				}

				else if (number == '1') {
					findBookingMenu.userInput(" name");
					scanner.nextLine();
					String na = scanner.nextLine();

					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getGuest().getName()
								.equalsIgnoreCase(na)) {
							commonmenu.printOut(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				} else if (number == '2') {
					// Arrival date
					scanner.nextLine();
					findBookingMenu.userInput("arrival day");
					int arrival1 = scanner.nextInt();
					int aday = arrival1;

					scanner.nextLine();
					findBookingMenu.userInput("arrival month");
					arrival1 = scanner.nextInt();
					int amonth = arrival1;

					scanner.nextLine();
					findBookingMenu.userInput("arrival year");
					arrival1 = scanner.nextInt();
					int ayear = arrival1;

					// Departure date
					scanner.nextLine();
					findBookingMenu.userInput("departure day");
					arrival1 = scanner.nextInt();
					int dday = arrival1;

					scanner.nextLine();
					findBookingMenu.userInput("departure month");
					arrival1 = scanner.nextInt();
					int dmonth = arrival1;

					scanner.nextLine();
					findBookingMenu.userInput("departure year");
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
							commonmenu.printOut(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				}

				else if (number == '3') {
					findBookingMenu.userInput("room number");

					int na = scanner.nextInt();

					for (int i = 0; i < bookings.size(); i++) {
						if (bookings.get(i).getRoom().getRoomNo() == na) {
							commonmenu.printOut(bookings.get(i).toString());
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
		deleteBookingMenu = new DeleteBookingMenu();
		deleteBookingMenu.startDeleteBooking();
		boolean go = false;
		while (go == false) {

			char number = scanner.next().charAt(0);

			if ((number < '1' || number > '5')
					&& (number > 'A' || number < 'z')) {

				commonmenu.printOut("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '5') {
					exit();
				} else if (number == '4') {
					start();
				}

				else if (number == '1') {
					deleteBookingMenu.userInput("name");
					scanner.nextLine();
					String na = scanner.nextLine();

					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getGuest().getName()
								.equalsIgnoreCase(na)) {
							commonmenu.printOut(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				} else if (number == '2') {
					// Arrival date
					scanner.nextLine();
					deleteBookingMenu.userInput("arrival day");
					int arrival1 = scanner.nextInt();
					int aday = arrival1;

					scanner.nextLine();
					deleteBookingMenu.userInput("arrival month");
					arrival1 = scanner.nextInt();
					int amonth = arrival1;

					scanner.nextLine();
					deleteBookingMenu.userInput("arrival year");
					arrival1 = scanner.nextInt();
					int ayear = arrival1;

					// Departure date
					scanner.nextLine();
					deleteBookingMenu.userInput("departure day");
					arrival1 = scanner.nextInt();
					int dday = arrival1;

					scanner.nextLine();
					deleteBookingMenu.userInput("departure month");
					arrival1 = scanner.nextInt();
					int dmonth = arrival1;

					scanner.nextLine();
					deleteBookingMenu.userInput("departure year");
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
							commonmenu.printOut(bookings.get(i).toString());
							go = true;
							start();

						}

					}

				}

				else if (number == '3') {
					deleteBookingMenu.userInput("room number");
					scanner.nextInt();
					int na = scanner.nextInt();

					for (int i = 0; i < bookings.size(); i++) {
						if (bookings.get(i).getRoom().getRoomNo() == na) {
							commonmenu.printOut(bookings.get(i).toString());
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
		menu = new MainMenu();
		// Arrival date
		
		menu.userInput(" arrival date");
		int arrival1 = scanner.nextInt();
		int aday = arrival1;

		scanner.nextLine();
		menu.userInput("arrival month");
		arrival1 = scanner.nextInt();
		int amonth = arrival1;

		scanner.nextLine();
		menu.userInput("arrival year");
		arrival1 = scanner.nextInt();
		int ayear = arrival1;

		// Departure date
		scanner.nextLine();
		menu.userInput("departure day");
		arrival1 = scanner.nextInt();
		int dday = arrival1;

		scanner.nextLine();
		menu.userInput("departure month");
		arrival1 = scanner.nextInt();
		int dmonth = arrival1;

		scanner.nextLine();
		menu.userInput("departure year");
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
		commonmenu.printOut("Available rooms : ");
		for (int i = 0; i < availableRooms.size(); i++) {
			commonmenu.printOut(availableRooms.get(i));
		}
		

	}

	@Override
	public void exit() {
		// End program
		exitProgramMenu = new ExitProgramMenu();
		exitProgramMenu.exitMenu();
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
					

				}
			}

		}

	}

	@Override
	public void returntoMainMenu() {
		
		commonmenu.printOut("Press ESC key to return to the main start Menu");
		
		
	}

	

}