package view;

import java.io.Serializable;

public class FindBookingMenu implements IFindBookingMenu,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void userInput(String s) {
		System.out.println("Enter " + s);
	}

	@Override
	public void startFindBooking() {
		System.out.println("\n");
		System.out.println("Find booking by :");
		System.out.println("1. Name\n" + "2. Arrival and Departure\n"
				+ "3. Room number\n" + "4. Return\n" + "5. Exit");
		
	}
	

}
