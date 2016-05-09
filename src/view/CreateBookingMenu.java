package view;

import java.io.Serializable;

public class CreateBookingMenu  implements ICreateBookingMenu,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void userInput(String s) {
		
		System.out.println("Enter " + s);
	}

}
