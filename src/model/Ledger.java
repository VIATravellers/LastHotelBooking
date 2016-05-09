package model;

import java.io.Serializable;
import java.util.*;

public class Ledger implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Booking> bookings;

	public Ledger() {
		this.bookings = new ArrayList<Booking>();
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Booking booking) {

		for (int i = 0; i < bookings.size(); i++) {

			if (bookings.get(i).equals(booking)) {
				bookings.set(i, booking);
			}
		}
	}

	public void addBookings(Booking booking) {
		if (!this.bookings.contains(booking))

		{
			this.bookings.add(booking);
		}

	}

	public void removeBooking(Booking booking) {

		for (int i = 0; i < bookings.size(); i++) {

			if (bookings.get(i).equals(booking)) {
				bookings.remove(i);
			}
		}
	}

	public ArrayList<Booking> getAllBooking() {
		return bookings;

	}

	@Override
	public String toString() {

		return "Ledger [Booking=" + bookings + "]";
	}

}
