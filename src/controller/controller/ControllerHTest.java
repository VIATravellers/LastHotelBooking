package controller.controller;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Booking;
import model.Guest;
import model.MyDate;
import model.Room;

import org.junit.Test;

import server.HotelDB;
import server.Server;

public class ControllerHTest {
	@Test
	public void test() {
		HotelDB hotelDB = null;
		try {
			hotelDB = new Server();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ControllerH controller = new ControllerH(hotelDB);
		// this is a test for controller.printBooking &&
		// controller.compareDates(MyDate from,MyDate to);
		int arrivalDay = 06;
		int arrivalMonth = 06;
		int arrivalYear = 2016;
		MyDate arrivalDate = new MyDate(arrivalDay, arrivalMonth, arrivalYear);
		int departureDay = 07;
		int departureMonth = 07;
		int departureYear = 2016;
		MyDate departureDate = new MyDate(departureDay, departureMonth,
				departureYear);
		Room room = new Room(5, "Room", "Single", 110);
		Guest guest = new Guest("John", "john@happy.com", "12345678");
		Booking booking = new Booking(guest, arrivalDate, departureDate, room);
		ArrayList<Booking> bookings = controller.printBookings();
		bookings.add(booking);
		int arrivalDay2 = 06;
		int arrivalMonth2 = 06;
		int arrivalYear2 = 2016;
		MyDate arrivalDate2 = new MyDate(arrivalDay2, arrivalMonth2,
				arrivalYear2);
		int departureDay2 = 07;
		int departureMonth2 = 07;
		int departureYear2 = 2016;
		MyDate departureDate2 = new MyDate(departureDay2, departureMonth2,
				departureYear2);
		Room room2 = new Room(6, "Room", "Single", 110);
		Guest guest2 = new Guest("Tom", "Tom@happy.com", "12345789");
		Booking booking2 = new Booking(guest2, arrivalDate2, departureDate2,
				room2);
		bookings.add(booking2);
		if (controller.printBookings().size() != 2
				&& controller.compareDates(arrivalDate2, departureDate2).size() != 2) {
			fail();
		}
	}
}
