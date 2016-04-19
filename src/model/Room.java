package model;

public class Room {

	private int roomNo;
	private String roomType;
	private double roomPrice;
	private String roomSize;
	

	
	
	
	public Room(int roomNo, String roomType, String roomSize,double roomPrice) {
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.roomSize = roomSize;
	}
	
	
	public String getRoomSize() {
		return roomSize;
	}


	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}


	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	


	@Override
	public String toString() {
		return "\tNumber = " + roomNo + "\n\t\tType = " + roomType + "\n\t\tPrice = " + roomPrice + "";
	}
	
	
	
}
