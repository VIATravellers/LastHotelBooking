package view;

import java.io.Serializable;


public class CheckoutGuestMenu implements ICheckoutGuest ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void userInput(String s) {
		System.out.println("Enter " + s);
	}

	@Override
	public void startCheckOut() {
		System.out.println("\n");
		System.out.println("Find booking by name");
		
	}
	

}
