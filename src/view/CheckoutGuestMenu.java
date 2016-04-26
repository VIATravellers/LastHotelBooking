package view;


public class CheckoutGuestMenu implements ICheckoutGuest{

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
