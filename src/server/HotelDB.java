package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Booking;
import model.Guest;
import model.Hotel;
import model.MyDate;
import model.Room;

public interface HotelDB extends Remote {
    String sayHello() throws RemoteException;
    
    ArrayList<Booking> getListBookings() throws RemoteException;		
	
	ArrayList<Room> getRooms() throws RemoteException;

	ArrayList<Guest> getGuests() throws RemoteException;
	
	
	
	Hotel getHotel()throws RemoteException;

	void addBooking(Booking booking)throws RemoteException;

	void addGuest(Guest guest)throws RemoteException;

	

	ArrayList<Room> searchAvailableRooms(MyDate arrival,
			MyDate departure)throws RemoteException;
}
