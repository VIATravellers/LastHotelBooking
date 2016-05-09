package controller.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Booking;
import model.Hotel;
import model.MyDate;
import model.Room;

public interface InterControllerH {
	
	public void start();
	public void createBooking();
	public void searchAvailabeRoom();
	public Booking findBooking();
	public void deleteBooking();
	public void exit();
	public void printAvailableRooms() throws RemoteException;
	public ArrayList<Room> compareDates(MyDate from ,MyDate to);
	public void checkIn();
	public void checkOut();
	public void getPrice(Booking booking);
	public void setBooking(Booking booking);
	public void checkZeroInt(int i);
	public void checkZeroString(String s);
	public ArrayList<Booking> returnBookings();
	public Hotel getHotel();
	
	
	

}
