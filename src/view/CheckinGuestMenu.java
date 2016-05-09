package view;

import java.io.Serializable;

import model.Booking;
import model.Hotel;
import controller.controller.ControllerH;

public class CheckinGuestMenu implements ICheckinGuest, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hotel hotel;


	@Override
	public void userInput(String s) {
		System.out.println("Enter " + s);
	}


	@Override
	public void startCheckIn() {
		System.out.println("\n");
		System.out.println("Find booking by name");
		
	}
	
	public String toString(String nationality, String passportnumber, int day, int month,
			               int year, Booking booking){
		//hotel = new Hotel();
		
		return booking.toString() + "\tNationality: " + nationality + "\n\tPassport: " 
				+ passportnumber + "\n\tBirthday:" + day + "/" + month + "/" + year;
	}
	

}
