package model;
import java.util.*;

public class Hotel {
	
	private ArrayList<Room> rooms;
	private Ledger ledger;
	
	public Hotel(){
		rooms = new ArrayList<Room>();
		ledger= new Ledger();
	}

	public Ledger getLedger() {
		return ledger;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}
	public void setRooms(Room room) {

		for (int i = 0; i < rooms.size(); i++) {

			if (rooms.get(i).equals(room)) {
				rooms.set(i, room);
			}
		}
	}

	public void addRooms(Room room) {
		if (!this.rooms.contains(room))

		{
			this.rooms.add(room);
		}

	}

	public void removeBooking(Room room) {

		for (int i = 0; i < rooms.size(); i++) {

			if (rooms.get(i).equals(room)) {
				rooms.remove(i);
			}
		}
	}
	
	public ArrayList<Room> getAllRooms() {
		return rooms;

	}
	public int getNumberOfOccupiedRooms(){
		if(rooms.size()>21){
			System.out.println("Hotel is full");
			return -1;
		}
		
			return rooms.size();
		
	}


}
