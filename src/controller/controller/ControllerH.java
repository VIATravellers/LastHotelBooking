package controller.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Booking;
import model.Guest;
import model.Hotel;
import model.MyDate;
import model.Room;
import server.HotelDB;
import server.Server;
import view.CheckinGuestMenu;
import view.CheckoutGuestMenu;
import view.CommonMenu;
import view.CreateBookingMenu;
import view.DeleteBookingMenu;
import view.ExitProgramMenu;
import view.FindBookingMenu;
import view.MainMenu;
import view.ScannerUserInput;

public class ControllerH implements InterControllerH {

	private Scanner scanner = new Scanner(System.in);

	private MyDate tempArr;
	private MyDate tempDep;
	private MyDate desiredArrival;
	private MyDate desiredDeparture;
	private Hotel hotel;
	private ArrayList<Booking> bookings;
	private ArrayList<Room> availableRooms;
	private ArrayList<Room> occupied;
	private MainMenu menu;
	private CheckinGuestMenu checkinMenu;
	private CheckoutGuestMenu checkoutMenu;
	private CreateBookingMenu createBookingMenu;
	private FindBookingMenu findBookingMenu;
	private DeleteBookingMenu deleteBookingMenu;
	private ExitProgramMenu exitProgramMenu;
	private CommonMenu commonmenu;
	private HotelDB hotelDB;
	private Server server;
	private boolean correct;
	

	private ScannerUserInput scanUser;

	private int emptyRooms;

	private int[] checkRoomNo;

	private int percent;

	public ControllerH(HotelDB hotelDB1) {

		try {
			bookings = hotelDB1.getListBookings();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		correct = false;
		occupied = new ArrayList<>();
		menu = new MainMenu();
		checkinMenu = new CheckinGuestMenu();
		checkoutMenu = new CheckoutGuestMenu();
		createBookingMenu = new CreateBookingMenu();
		findBookingMenu = new FindBookingMenu();
		deleteBookingMenu = new DeleteBookingMenu();
		exitProgramMenu = new ExitProgramMenu();
		commonmenu = new CommonMenu();
		scanUser = new ScannerUserInput();
		hotelDB = hotelDB1;
		
		checkRoomNo = new int [100];
		try {
			hotel = hotelDB.getHotel();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availableRooms = new ArrayList<>();
	}

	@Override
	public void start() {

		menu.startMenu();

		boolean go = false;
		while (go == false) {

			char i = 0;
			char number = scanUser.scannerUserInputChar(i);

			if ((number < '1' || number >= '7')
					&& (number > 'A' || number < 'z')) {

				commonmenu
						.printOut("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '6') {// now the exit will be with 6 instead of 4
									// u get it daniel ??
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

				} else if (number == '4') { // this is new (press 4 for checkIn)
					commonmenu.printOut("Number: " + number);

					checkIn();
					go = true;
				} else if (number == '5') { // this is new (press 5 for
											// checkOut)
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

		checkoutMenu.userInput("guest name : ");
		String s = "";
		String name = scanUser.scannerUserInputString(s);
		checkZeroString(name);
		checkoutMenu.userInput("passportNo :");
		String passportNo = null;
		while(correct !=true){

			
		passportNo = scanUser.scannerUserInputString(s);
		if(passportNo.length()<7 || passportNo.length()>7){
			commonmenu.printOut("Incorrect passport format, try again");
		}
		else{
			correct = true;
		}
		}
		correct = false;
		checkZeroString(passportNo);
		
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
			commonmenu.printOut("Do you want a discount ? :");
			String response =scanUser.scannerUserInputString(s);
			
			if(response.equalsIgnoreCase("NO"))
			{

			    getPrice(result);
			  
			}
			else if(response.equalsIgnoreCase("YES"))
			{
				
				commonmenu.printOut("Enter discount percent value : ");
			    percent = scanUser.scannerUserInputInt2(percent) ;
				double roomPrice2 = result.getRoom().getRoomPrice()-(percent * result.getRoom().getRoomPrice()/100) ;
				result.getRoom().setRoomPrice(roomPrice2); 
				getPrice(result);
			
			}
			else
			{
				commonmenu.printOut("Wrong code !");
			}
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
		start() ;

	}

	// this is the checkIn (new)
	public void checkIn() {

		checkinMenu = new CheckinGuestMenu();
		checkinMenu.startCheckIn();
		checkinMenu.userInput("guest name :");
		String s = "";
		String name = scanUser.scannerUserInputString(s);
		checkZeroString(name);
		MyDate today = new MyDate();
		Booking result = null;
		for (int i = 0; i < bookings.size(); i++) {
			if (bookings.get(i).getGuest().getName().equalsIgnoreCase(name)
					) {
				result = bookings.get(i);
				commonmenu.printOut(bookings.get(i).toString());
				setBooking(bookings.get(i));

			}
		}

	}

	public void setBooking(Booking booking) {
		commonmenu.printOut("Edit booking :");
		checkinMenu.userInput("nationality :");
		String s = "";
		String nationality = scanUser.scannerUserInputString(s);
		checkZeroString(nationality);
		checkinMenu.userInput("day of birthday :");
		int i = 0;
		int birthday =0;
		while(correct !=true){
			birthday = scanUser.scannerUserInputInt(i);
			if(birthday<1 || birthday>31){
				commonmenu.printOut("Incorrect day, try again");
			}
			else{
				correct = true;
			}
		}
		correct = false;
		checkZeroInt(birthday);
		checkinMenu.userInput("month of birthday :");
		int birthmonth = 0;
		while(correct !=true){
			birthmonth = scanUser.scannerUserInputInt(i);
			if(birthmonth<1 || birthmonth>12){
				commonmenu.printOut("Incorrect month, try again");
			}
			else{
				correct = true;
			}
		}
		correct = false;
		checkZeroInt(birthmonth);
		checkinMenu.userInput("year of birthday :");
		int birthyear = 0;
		while(correct !=true){
			birthyear = scanUser.scannerUserInputInt(i);
			if(birthyear<1940 || birthyear>1998){
				commonmenu.printOut("Incorrect year, try again");
			}
			else{
				correct = true;
			}
		}
		correct = false;
		
		checkZeroInt(birthyear);
		MyDate birthdate = new MyDate(birthday, birthmonth, birthyear);
		checkinMenu.userInput("passportNo : ");
		String passportNo =null;
		while(correct !=true){

			
			passportNo = scanUser.scannerUserInputString(s);
			if(passportNo.length()<7 || passportNo.length()>7){
				commonmenu.printOut("Incorrect passport format, try again");
			}
			else{
				correct = true;
			}
		}
		correct = false;
		
		checkZeroString(passportNo);
		Guest guest = new Guest(booking.getGuest().getName(), nationality,
				birthdate, booking.getGuest().getTelephoneNo(), booking
						.getGuest().getEmail(), passportNo);
		booking.setGuest(guest);
		System.out.println(checkinMenu.toString(nationality, passportNo, birthday, birthmonth, birthyear, booking));
   
		start();
	}

	@Override
	public void createBooking() {

		searchAvailabeRoom();
		createBookingMenu = new CreateBookingMenu();

		createBookingMenu.userInput(" room number");

		int i = 0;
		int roomNo = 0;
		  while(correct !=true){

				
			  roomNo = scanUser.scannerUserInputInt(i);
			  checkZeroInt(roomNo);
				if(roomNo<1 || roomNo>25){
					commonmenu.printOut("Incorrect room number, try again");
				}
				
				correct =true;
			}
		 
	          correct = false;
				
	
		

		Room room = hotel.getRoomByRoomNo(roomNo);

		createBookingMenu.userInput(" name");

		String s = "";
		String name = scanUser.scannerUserInputString(s);
		checkZeroString(name);

		createBookingMenu.userInput(" email");
		String email = null;
	
		email = scanUser.scannerUserInputString(s);

		checkZeroString(email);

		createBookingMenu.userInput("phone number");
		String telephoneNo = null;
          while(correct !=true){

			
			telephoneNo = scanUser.scannerUserInputString(s);
			checkZeroString(telephoneNo);
			if(telephoneNo.length()<8 || telephoneNo.length()>15){
				commonmenu.printOut("Incorrect telephone number format, try again");
			}
			else{
				correct = true;
			}
		}
          correct = false;
	
		

		Guest guest = new Guest(name, email, telephoneNo);

		Booking booking = new Booking(guest, desiredArrival, desiredDeparture,
				room);
		
		
		try {
			hotelDB.addBooking(booking);
			hotelDB.addGuest(guest);
			bookings.add(booking);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		commonmenu.printOut(booking.toString());

		start();
	}

	@Override
	public Booking findBooking() {

	
		findBookingMenu = new FindBookingMenu();
		findBookingMenu.startFindBooking();
		boolean go = false;
		while (go == false) {

			char c = 0;

char number = scanUser.scannerUserInputChar(c);

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

					String s = "";
					String na = scanUser.scannerUserInputString(s);
					checkZeroString(na);
					/*try {
						bookings = hotelDB.getListBookings();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
*/
					
					int size = bookings.size();
					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getGuest().getName()
								.equalsIgnoreCase(na)) {
							commonmenu.printOut(bookings.get(i).toString());
						 

						}
						else{
							if(size==0){
							commonmenu.printOut("Name does not exist in the database, try again");
						
							go = true;
							
							
							}
						}
						size--;
					}
					start();
					

				} else if (number == '2') {
					// Arrival date

					findBookingMenu.userInput("arrival day");
					int i = 0;
					int arrival1 = 0;
					
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>31){
								commonmenu.printOut("Incorrect day, try again");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int aday = arrival1;

					findBookingMenu.userInput("arrival month");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>12){
								commonmenu.printOut("Incorrect month, try again");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int amonth = arrival1;

					findBookingMenu.userInput("arrival year");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if( arrival1<2016|| arrival1>2020){
								commonmenu.printOut("Incorrect year, try again");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
				
					int ayear = arrival1;

					// Departure date
					findBookingMenu.userInput("departure day");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>31){
								commonmenu.printOut("Incorrect day, try again, departure is before or same day as"
										+ "arrival, try again ");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int dday = arrival1;

					findBookingMenu.userInput("departure month");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>12) {
								commonmenu.printOut("Incorrect month, try again, departure month is before"
										+ "arrival, try again ");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
				
					int dmonth = arrival1;

					findBookingMenu.userInput("departure year");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1>2020 || arrival1<ayear ||(arrival1==ayear && amonth>dmonth)||
									(arrival1==ayear && amonth==dmonth && aday>=dday)){
								commonmenu.printOut("Incorrect month, try again, departure month is before"
										+ "arrival, try again ");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int dyear = arrival1;

					tempArr = new MyDate(aday, amonth, ayear);
					tempDep = new MyDate(dday, dmonth, dyear);
				
					int size = bookings.size();
					
					for (int j = 0; j < bookings.size(); j++) {
						

						if (bookings.get(j).getArrivalDate().getDay() == tempArr
								.getDay()
								&& bookings.get(j).getArrivalDate().getMonth() == tempArr
										.getMonth()
								&& bookings.get(j).getArrivalDate().getYear() == tempArr
										.getYear()
								&& bookings.get(j).getDepartureDate().getDay() == tempDep
										.getDay()
								&& bookings.get(j).getDepartureDate()
										.getMonth() == tempDep.getMonth()
								&& bookings.get(j).getDepartureDate().getYear() == tempDep
										.getYear())
							

						{
							commonmenu.printOut(bookings.get(j).toString());
							return bookings.get(j) ;
							

						}
						else{
							if(size==0){
		
							go = true;
							
							}
						}
						size--;
					}
					
					

					start();
				}
				

				else if (number == '3') {
					findBookingMenu.userInput("room number");

					int i = 0;
					int na1 = 0;
					while(correct !=true){
						na1 = scanUser.scannerUserInputInt(i);
						checkZeroInt(na1);
						if(na1<1 || na1>25){
							commonmenu.printOut("Room number is incorrect");;
						}
						else{
							
							correct = true;
						}
					}
					correct = false;
					

					 
					int size = bookings.size();
					for (int j = 0; j < bookings.size(); j++) {
						
						if (bookings.get(j).getRoom().getRoomNo() == na1) {
							commonmenu.printOut(bookings.get(j).toString());
							return bookings.get(j) ;
						
							
						}
						else{
							if(size==0){
								go = true;
								
								
							}
							
						}
						size--;
					}

					start();
				}
			}

		}
		return null ;
		
	}

	@Override
	public void deleteBooking() {
		
		deleteBookingMenu = new DeleteBookingMenu();
		deleteBookingMenu.startDeleteBooking();
		boolean go = false;
		while (go == false) {

			char c = 0;
			char number = scanUser.scannerUserInputChar(c);

			if ((number < '1' || number > '5')
					&& (number > 'A' || number < 'z')) {

				commonmenu
						.printOut("Wrong character or incorrect number, try again");

			}

			else {
				if (number == '5') {
					exit();
				} else if (number == '4') {
					start();
				}

				else if (number == '1') {
					deleteBookingMenu.userInput("name");
					String s = "";
					String na = scanUser.scannerUserInputString(s);
					checkZeroString(na);
				
					int size = bookings.size();
					for (int i = 0; i < bookings.size(); i++) {

						if (bookings.get(i).getGuest().getName()
								.equalsIgnoreCase(na)) {
							commonmenu.printOut(bookings.get(i).toString());
						

						}
						else{
							if(size==0){
							go = true;
							
							}
						}
						 size--;

					}
					start();

				} else if (number == '2') {
					findBookingMenu.userInput("arrival day");
					int i = 0;
					int arrival1 = 0;
					
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>31){
								commonmenu.printOut("Incorrect day, try again");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int aday = arrival1;

					findBookingMenu.userInput("arrival month");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>12){
								commonmenu.printOut("Incorrect month, try again");;
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int amonth = arrival1;

					findBookingMenu.userInput("arrival year");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if( arrival1<2016|| arrival1>2020){
								commonmenu.printOut("Incorrect year, try again");;
							}
							else{
								correct = true;
							}
						}
					 correct = false;
					
					int ayear = arrival1;

					// Departure date
					findBookingMenu.userInput("departure day");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>31 ){
								System.out.println("Departure is before or same day as"
										+ "arrival, try again ");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
			
					int dday = arrival1;

					findBookingMenu.userInput("departure month");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1<1 || arrival1>12 ){
								System.out.println("Departure month is before "
										+ "arrival, try again ");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
				
					int dmonth = arrival1;

					findBookingMenu.userInput("departure year");
					 while(correct !=true){

							
						 arrival1 = scanUser.scannerUserInputInt(i);
						 checkZeroInt(arrival1);
							if(arrival1>2020 || arrival1<ayear ||(arrival1==ayear && amonth>dmonth)||
									(arrival1==ayear && amonth==dmonth && aday>=dday) ){
								System.out.println("Departure year is before "
										+ "arrival, try again ");
							}
							else{
								correct = true;
							}
						}
					 correct = false;
				
					int dyear = arrival1;


					tempArr = new MyDate(aday, amonth, ayear);
					tempDep = new MyDate(dday, dmonth, dyear);
					int size = bookings.size();
					
					
						
					for (int s = 0; s < bookings.size(); s++) {

						if (bookings.get(s).getArrivalDate().getDay() == tempArr
								.getDay()
								&& bookings.get(s).getArrivalDate().getMonth() == tempArr
										.getMonth()
								&& bookings.get(s).getArrivalDate().getYear() == tempArr
										.getYear()
								&& bookings.get(s).getDepartureDate().getDay() == tempDep
										.getDay()
								&& bookings.get(s).getDepartureDate()
										.getMonth() == tempDep.getMonth()
								&& bookings.get(s).getDepartureDate().getYear() == tempDep
										.getYear())
							

						{
							commonmenu.printOut(bookings.get(s).toString());
							bookings.remove(s);

						}
						else{
							if(size==0){
		
							go = true;
							
							}
						}
						size--;
					}
					
					

					start();
				}
				

				else if (number == '3') {
					deleteBookingMenu.userInput("room number");
					scanner.nextInt();
					int m = 0;
					int na = 0;
					while(correct !=true){
						na = scanUser.scannerUserInputInt(m);
						checkZeroInt(na);
						if(na<1 || na>25){
							commonmenu.printOut("Incorrect room number, try again");
						}
						else{
							correct = true;
						}
					}
					correct = false;
					
					int size = bookings.size();
					for (int i = 0; i < bookings.size(); i++) {
						if (bookings.get(i).getRoom().getRoomNo() == na) {
							commonmenu.printOut(bookings.get(i).toString());
							
						}
							else{
								if(size==0){
									go = true;
									
									
								}
								
							}
							size--;
						}

						start();
					}
				}

			}
			
		}

	@Override
	public void searchAvailabeRoom() {
		availableRooms = new ArrayList<>();
		int m = 0;
		menu = new MainMenu();
		// Arrival date

		findBookingMenu.userInput("arrival day");
		int i = 0;
		int arrival1 = 0;
		
		 while(correct !=true){

				
			 arrival1 = scanUser.scannerUserInputInt(i);
			 checkZeroInt(arrival1);
				if(arrival1<1 || arrival1>31){
					commonmenu.printOut("Incorrect day, try again");
				}
				else{
					correct = true;
				}
			}
		 correct = false;
		
		int aday = arrival1;

		findBookingMenu.userInput("arrival month");
		 while(correct !=true){

				
			 arrival1 = scanUser.scannerUserInputInt(i);
			 checkZeroInt(arrival1);
				if(arrival1<1 || arrival1>12){
					commonmenu.printOut("Incorrect month, try again");
				}
				else{
					correct = true;
				}
			}
		 correct = false;
	
		int amonth = arrival1;

		findBookingMenu.userInput("arrival year");
		 while(correct !=true){

				
			 arrival1 = scanUser.scannerUserInputInt(i);
			 checkZeroInt(arrival1);
				if(arrival1<2016|| arrival1>2020 ){
					commonmenu.printOut("Incorrect year, try again");
				}
				else{
					correct = true;
				}
			}
		 correct = false;
		
		int ayear = arrival1;

		// Departure date
		findBookingMenu.userInput("departure day");
		 while(correct !=true){

				
			 arrival1 = scanUser.scannerUserInputInt(i);
			 checkZeroInt(arrival1);
				if(arrival1<1 || arrival1>31 ){
					System.out.println("Incorrect day or departure is before or same day as "
							+ "arrival, try again ");
				}
				else{
					correct = true;
				}
			}
		 correct = false;
	
		int dday = arrival1;

		findBookingMenu.userInput("departure month");
		 while(correct !=true){

				
			 arrival1 = scanUser.scannerUserInputInt(i);
			 checkZeroInt(arrival1);
				if(arrival1<1 || arrival1>12 ){
					System.out.println("Incorrect month or departure month is before "
							+ "arrival, try again ");
				}
				else{
					correct = true;
				}
			}
		 correct = false;
	
		int dmonth = arrival1;

		findBookingMenu.userInput("departure year");
		 while(correct !=true){

				
			 arrival1 = scanUser.scannerUserInputInt(i);
			 checkZeroInt(arrival1);
				if(arrival1>2020 || arrival1<ayear ||(arrival1==ayear && amonth>dmonth)||
						(arrival1==ayear && amonth==dmonth && aday>=dday)){
					System.out.println("Incorrect year or departure year is before "
							+ "arrival, try again ");
				}
				else{
					correct = true;
				}
			}
		 correct = false;
	
		int dyear = arrival1;


		desiredArrival = new MyDate(aday, amonth, ayear);
		desiredDeparture = new MyDate(dday, dmonth, dyear);

		compareDates(desiredArrival,desiredDeparture);
		ArrayList<Room> roomDB = new ArrayList<>();

		roomDB = hotel.getRooms();
		
		for (int k = 0; k < roomDB.size(); k++) {
			if (!occupied.contains(roomDB.get(k))) {
				availableRooms.add(roomDB.get(k));
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
	public ArrayList<Room> compareDates(MyDate desiredArrival,MyDate desiredDeparture) {
		
		for (int i = 0; i < bookings.size(); i++) {
			

			if ((desiredArrival.isBetween(bookings.get(i).getArrivalDate(),
					bookings.get(i).getDepartureDate()))
					|| (desiredDeparture.isBetween(bookings.get(i).getArrivalDate(),
							bookings.get(i).getDepartureDate()))
					|| ((desiredDeparture.equals(bookings.get(i).getDepartureDate())) && (desiredArrival
							.equals(bookings.get(i).getArrivalDate())))
					|| ((bookings.get(i).getArrivalDate().isBetween(desiredArrival,
							desiredDeparture)) && (bookings.get(i).getDepartureDate()
							.isBetween(desiredArrival, desiredDeparture)))
					|| ((desiredArrival.isBetween(bookings.get(i).getArrivalDate(),
							bookings.get(i).getDepartureDate())) && (desiredDeparture
							.isBetween(bookings.get(i).getArrivalDate(),
									bookings.get(i).getDepartureDate())))) {

				if (!occupied.contains(bookings.get(i).getRoom())) {
					occupied.add(bookings.get(i).getRoom());

				}
			}

		}
		return occupied ;

	}

	@Override
	public void checkZeroInt(int i) {
		if (i == 0) {
			start();
		}

	}

	@Override
	public void checkZeroString(String s) {
		if (s.equals("0")) {
			start();
		}

	}

	public ArrayList<Booking> printBookings() {
		return bookings;
	}

	@Override
	public ArrayList<Booking> returnBookings() {
		ArrayList<Booking> book = new ArrayList<Booking>();
		for (int i = 0; i < bookings.size(); i++) {
			book.add(bookings.get(i));
		}
		return book;
	}

	public ArrayList<Room> getAvailableRoomS() {
		return availableRooms;
	}

	@Override
	public Hotel getHotel() {
		// TODO Auto-generated method stub
		return hotel;
	}

}
