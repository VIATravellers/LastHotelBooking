package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import controller.controller.ControllerH;
import model.Booking;
import model.Guest;
import model.Hotel;
import model.MyDate;
import model.Room;
        
/*
 * * export CLASSPATH=/home/skr/workspace/RMIExample/bin/
	 * 
	 * Then run the rmiregistry in that same command prompt
	 * 
	 * rmiregistry 
	 * 
	 * OR don't set the CLASSPATH and run like this,
	 * 
	 * 
	 * 
	 * rmiregistry -J-Djava.rmi.server.codebase=file:"C:\Users\Othman\newworkspace\HotelRMILastUpdated\bin\\"
	 * 
	 * cd C:\Users\Othman\newworkspace\HotelRMILastUpdated\bin
	 * 
	 * Then run the server in another command prompt
	 * 
	 *
	 * 
	 * java -classpath "C:\Users\Othman\newworkspace\HotelRMILastUpdated\bin"  -Djava.rmi.server.codebase=file:"C:\Users\Othman\newworkspace\HotelRMILastUpdated\bin" server.Server 
	 *
	 * 
 */
public class Server implements HotelDB {
	
	
	public Hotel hotel;
        
    public Server() throws RemoteException {
    	hotel = new Hotel();
    }

    public String sayHello() {
        return "Hello again, world!";
    }
        
    public static void main(String args[]) {
        
    	System.setProperty("java.rmi.server.hostname","10.52.229.200");
    	
        try {
            Server obj = new Server();
            HotelDB stub = (HotelDB) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

            System.out.println("Server ready");
        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

	@Override
	public ArrayList<Booking> getListBookings() throws RemoteException {
		
		return hotel.getListBookings();
	}

	@Override
	public ArrayList<Room> getRooms() throws RemoteException {
		return hotel.getRooms();
	}

	@Override
	public ArrayList<Guest> getGuests() throws RemoteException {
		return hotel.getGuests();
	}
	
	@Override
	public Hotel getHotel() {		
		return hotel;
	}

	@Override
	public void addBooking(Booking booking) {
		hotel.setBookings(booking);
	}

	@Override
	public void addGuest(Guest guest) {
		hotel.addGuest(guest);
	}
	

	

	
	@Override
	public ArrayList<Room> searchAvailableRooms(MyDate arrivalDate, MyDate departureDate) throws RemoteException {
		
		return	hotel.getAvailableRoomsToClient(arrivalDate, departureDate);
	

	}   

	
}
