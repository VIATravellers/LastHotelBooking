package db;

import java.util.ArrayList;

import model.Room;

public class RoomDB {
	
	//ArrayList of all the rooms in the hotel
	private ArrayList<Room> allRoom;
	
	
	/**
	 * Creates and fills up the array list with all the rooms
	 */
	public RoomDB(){
		allRoom = new ArrayList<>();
		this.set();
	}
	
	
	/**
	 * Sets up all the rooms in the hotel
	 */
	public void set(){
		
		//Suites setup
		Room one = new Room(1,"Suite","One",220);
		Room two = new Room(2,"Suite","One",220);
		Room three = new Room(3,"Suite","Two",340);
		Room four = new Room(4,"Suite","Three",450);

		//Rooms setup
		Room room1 = new Room(5,"Room","Single",110);
		Room room2 = new Room(6,"Room","Single",110);
		Room room3 = new Room(7,"Room","Single",110);
		
		Room room4 = new Room(8,"Room","Twin",170);
		Room room5 = new Room(9,"Room","Twin",170);
		Room room6 = new Room(10,"Room","Twin",170);
		Room room7 = new Room(11,"Room","Twin",170);
		Room room8 = new Room(12,"Room","Twin",170);
		Room room9 = new Room(13,"Room","Twin",170);
		Room room10 = new Room(14,"Room","KingSized",170);
		Room room11 = new Room(15,"Room","KingSized",170);
		Room room12 = new Room(16,"Room","KingSized",170);
		Room room13 = new Room(17,"Room","KingSized",170);
		Room room14 = new Room(18,"Room","KingSized",170);
		Room room15 = new Room(19,"Room","KingSized",170);
		Room room16 = new Room(20,"Room","KingSized",170);
		Room room17 = new Room(21,"Room","KingSized",170);
		Room room18 = new Room(22,"Room","KingSized",170);
		Room room19 = new Room(23,"Room","KingSized",170);
		Room room20 = new Room(24,"Room","KingSized",170);
		Room room21 = new Room(25,"Room","KingSized",170);
		
		allRoom.add(one);
		allRoom.add(two);
		allRoom.add(three);
		allRoom.add(four);
		
		allRoom.add(room1);
		allRoom.add(room2);
		allRoom.add(room3);
		allRoom.add(room4);
		allRoom.add(room5);
		allRoom.add(room6);
		allRoom.add(room7);
		allRoom.add(room8);
		allRoom.add(room9);
		allRoom.add(room10);
		allRoom.add(room11);
		allRoom.add(room12);
		allRoom.add(room13);
		allRoom.add(room14);
		allRoom.add(room15);
		allRoom.add(room16);
		allRoom.add(room17);
		allRoom.add(room18);
		allRoom.add(room19);
		allRoom.add(room20);
		allRoom.add(room21);
		
		
	}
	public ArrayList<Room> getRooms(){
		
			return allRoom;
		}
	
	public Room getRoomByRoomNo(int RoomNo)
	{
	   for(int i =0 ;i<allRoom.size();i++)
	   {
	      if(allRoom.get(i).getRoomNo()==RoomNo)
	      {
	         return allRoom.get(i);
	      }
	   }
      return null;
	}
		
	
	public String toString(){
		String str = "";
		for(int i = 0;i<allRoom.size();i++){
			str += allRoom.get(i) + "\n";
		}
		return str;
	}

}
