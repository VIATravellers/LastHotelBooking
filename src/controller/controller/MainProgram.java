package controller.controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import server.HotelDB;

public class MainProgram {
	
 
	public static void main(String[] args) throws RemoteException,
			NotBoundException {

		// Get rmi reference to hotelDB
		String host = "10.52.229.200";
		Registry registry = LocateRegistry.getRegistry(host);
		HotelDB stub = (HotelDB) registry.lookup("Hello");

		ControllerH controller = new ControllerH(stub);
		controller.start();
	}

}
