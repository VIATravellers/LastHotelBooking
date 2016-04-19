package controller.controller;

import java.util.Scanner;

import db.RoomDB;

public class MainProgram {

	public static void main(String[] args) {

		Scanner scanner= new Scanner(System.in);
		
		
		ControllerH controller = new ControllerH();
		controller.start();
		
		//System.out.println(room.toString());

	}

}
