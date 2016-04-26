package view;

public class DeleteBookingMenu implements IDeleteBooking {


	@Override
	public void userInput(String s) {
		System.out.println("Enter " + s);
	}

	@Override
	public void startDeleteBooking() {
		System.out.println("\n");
		System.out.println("Find booking by :");
		System.out.println("1. Name\n" + "2. Arrival and Departure\n"
				+ "3. Room number\n" + "4. Return\n" + "5. Exit");
		
	}

}
