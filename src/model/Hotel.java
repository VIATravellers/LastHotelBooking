package model;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;

import controller.controller.ControllerH;
import server.HotelDB;
import server.Server;
import view.MainMenu;

public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ControllerH cont;

	private ArrayList<Room> allRoom;
	private ArrayList<Booking> book;
	private ArrayList<Guest> guest;
	private ArrayList<Room> availableRoomsToClient;

	private ArrayList<Room> occupied2;

	private HotelDB  hotelDB ;

	public Hotel() throws RemoteException {

		allRoom = new ArrayList<>();
		this.set();
		
	    book = new ArrayList<Booking>(); // i tried to make it book=cont.print it give me Memoryerror
		
		guest = new ArrayList<Guest>();
		occupied2 = new ArrayList<Room>();
		availableRoomsToClient = new ArrayList<Room>();

	}

	public void set() {

		// Suites setup
		Room one = new Room(1, "Suite", "One", 220);
		Room two = new Room(2, "Suite", "One", 220);
		Room three = new Room(3, "Suite", "Two", 340);
		Room four = new Room(4, "Suite", "Three", 450);

		// Rooms setup
		Room room1 = new Room(5, "Room", "Single", 110);
		Room room2 = new Room(6, "Room", "Single", 110);
		Room room3 = new Room(7, "Room", "Single", 110);

		Room room4 = new Room(8, "Room", "Twin", 170);
		Room room5 = new Room(9, "Room", "Twin", 170);
		Room room6 = new Room(10, "Room", "Twin", 170);
		Room room7 = new Room(11, "Room", "Twin", 170);
		Room room8 = new Room(12, "Room", "Twin", 170);
		Room room9 = new Room(13, "Room", "Twin", 170);
		Room room10 = new Room(14, "Room", "KingSized", 170);
		Room room11 = new Room(15, "Room", "KingSized", 170);
		Room room12 = new Room(16, "Room", "KingSized", 170);
		Room room13 = new Room(17, "Room", "KingSized", 170);
		Room room14 = new Room(18, "Room", "KingSized", 170);
		Room room15 = new Room(19, "Room", "KingSized", 170);
		Room room16 = new Room(20, "Room", "KingSized", 170);
		Room room17 = new Room(21, "Room", "KingSized", 170);
		Room room18 = new Room(22, "Room", "KingSized", 170);
		Room room19 = new Room(23, "Room", "KingSized", 170);
		Room room20 = new Room(24, "Room", "KingSized", 170);
		Room room21 = new Room(25, "Room", "KingSized", 170);

		allRoom.add(one);
		allRoom.add(two);
		allRoom.add(three);
		allRoom.add(four);

		allRoom.add(room1);
		allRoom.add(room2);
		allRoom.add(room3);
		allRoom.add(room4);
		allRoom.add(room5);
		allRoom.add(room6);
		allRoom.add(room7);
		allRoom.add(room8);
		allRoom.add(room9);
		allRoom.add(room10);
		allRoom.add(room11);
		allRoom.add(room12);
		allRoom.add(room13);
		allRoom.add(room14);
		allRoom.add(room15);
		allRoom.add(room16);
		allRoom.add(room17);
		allRoom.add(room18);
		allRoom.add(room19);
		allRoom.add(room20);
		allRoom.add(room21);

	}

	public ArrayList<Room> getRooms() {

		return allRoom;
	}

	public Room getRoomByRoomNo(int RoomNo) {
		for (int i = 0; i < allRoom.size(); i++) {
			if (allRoom.get(i).getRoomNo() == RoomNo) {
				return allRoom.get(i);
			}
		}
		return null;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < allRoom.size(); i++) {
			str += allRoom.get(i) + "\n";
		}
		return str;
	}

	public void setBookings(Booking booking) {
		book.add(booking);
	}

	public ArrayList<Booking> getListBookings() {
		return book;
	}

	public ArrayList<Guest> getGuests() {
		return guest;
	}

	public void addGuest(Guest guest) {
		this.guest.add(guest);
	}

	public ArrayList<Room> getAvailableRoomsToClient(MyDate date1, MyDate date2)
			throws RemoteException { // we
		// use
		// it
		// here
		// return cont.getAvailableRoomS(date1, date2);
       
		for (int i = 0; i < book.size(); i++) {

			if ((date1.isBetween(book.get(i).getArrivalDate(), book.get(i)
					.getDepartureDate()))
					|| (date2.isBetween(book.get(i).getArrivalDate(),
							book.get(i).getDepartureDate()))
					|| ((date2.equals(book.get(i).getDepartureDate())) && (date1
							.equals(book.get(i).getArrivalDate())))
					|| ((book.get(i).getArrivalDate().isBetween(date1, date2)) && (book
							.get(i).getDepartureDate().isBetween(date1, date2)))
					|| ((date1.isBetween(book.get(i).getArrivalDate(), book
							.get(i).getDepartureDate())) && (date2.isBetween(
							book.get(i).getArrivalDate(), book.get(i)
									.getDepartureDate())))) {

				if (!occupied2.contains(book.get(i).getRoom())) {
					occupied2.add(book.get(i).getRoom());

				}
			}

		}

		// availableRoomstoclient=allRoom ;

		// (occupied2.contains(availableRoomstoclient.get(i)))
		// availableRoomstoclient.remove(i);
		ArrayList<Room> roomDB = new ArrayList<Room>();
		roomDB = allRoom;
		for (int k = 0; k < roomDB.size(); k++) {
			if (!occupied2.contains(roomDB.get(k))) {
				availableRoomsToClient.add(roomDB.get(k));
			}
		}

		return availableRoomsToClient;
		

	}

}
