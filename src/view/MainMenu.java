package view;

import java.io.Serializable;

public class MainMenu implements IMainMenu,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Override
	public void startMenu() {
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Enter number of interaction, you want to do!!");
		System.out.println(" ");

		System.out.println("1. Create Booking\n" + "2. Find Booking\n"
				+ "3. Delete Booking\n" + "4. CheckIn\n" + "5. CheckOut\n"+ "6. Exit");
		
		
		
	}



	@Override
	public void userInput(String s) {
	
		
		 System.out.println("Enter " + s);
		
	}
	
}
