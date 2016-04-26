package view;

public class CheckinGuestMenu implements ICheckinGuest {

	@Override
	public void userInput(String s) {
		System.out.println("Enter " + s);
	}


	@Override
	public void startCheckIn() {
		System.out.println("\n");
		System.out.println("Find booking by name");
		
	}
	

}
