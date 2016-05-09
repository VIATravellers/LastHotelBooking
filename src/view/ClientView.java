package view;

public class ClientView implements IClientView{

	@Override
	public void startClient() {
	
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Enter number of interaction, you want to do!!");
		System.out.println(" ");

		System.out.println("1. See bookings \n" + "2. See guests\n"
				+ "3. See available rooms\n" );

	}

}
