package controller.controller;

import java.util.ArrayList;

import model.Booking;

public interface InterControllerH {
	
	public void start();
	public void createBooking();
	public void searchAvailabeRoom();
	public void findBooking();
	public void deleteBooking();
	public void exit();
	public void printAvailableRooms();
	public void compareDates();
	public void checkIn();
	public void checkOut();
	public void getPrice(Booking booking);
	public void setBooking(Booking booking);
	
	

}
