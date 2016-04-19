package controller.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import db.RoomDB;
import model.Booking;
import model.Guest;
import model.Hotel;
import model.MyDate;
import model.Room;

public class ControllerH implements InterControllerH
{

   private Scanner scanner = new Scanner(System.in);

   private MyDate departureDate;
   private MyDate arrivalDate;
   private ArrayList<Integer> departure;
   private ArrayList<Integer> arrival;
   private ArrayList<Integer> roomsNo;
   private int counter;
   private Hotel hotel;
   private ArrayList<String> customerName;
   private MyDate date;
   private MyDate desiredArrival;
   private MyDate desiredDeparture;
   private RoomDB rooms;
   private ArrayList<Booking> bookings;

   private ArrayList<Room> occupied;
   private ArrayList<Room> availableRooms;

   public ControllerH()
   {
      departureDate = new MyDate(0, 0, 0);
      arrivalDate = new MyDate(0, 0, 0);
      departure = new ArrayList<>();
      arrival = new ArrayList<>();
      counter = 0;
      roomsNo = new ArrayList<>();
      customerName = new ArrayList<>();
      date = new MyDate(0, 0, 0);
      rooms = new RoomDB();
      bookings = new ArrayList<>();
      occupied = new ArrayList<>();
      availableRooms = new ArrayList<>();
   }

   @Override
   public void start()
   {

      System.out.println("Enter number of interaction, you want to do!!");
      System.out.println(" ");

      // Menu
      System.out.println("1. Create Booking\n" + "2. Find Booking\n"
            + "3. Delete Booking\n" + "4. Exit");
      boolean go = false;
      while (go == false)
      {

         char number = scanner.next().charAt(0);

         if ((number < '1' || number > '4') && (number > 'A' || number < 'z'))
         {

            System.out
                  .println("Wrong character or incorrect number, try again");

         }

         else
         {
            if (number == '4')
            {
               exit();
            }
            else if (number == '1')
            {

               System.out.println("Number: " + number);

               createBooking();
               go = true;
            }
            else if (number == '2')
            {
               System.out.println("Number: " + number);

               findBooking();
               go = true;

            }
            else if (number == '3')
            {
               System.out.println("Number: " + number);

               deleteBooking();
               go = true;

            }
         }

      }

   }

   @Override
   public void createBooking()
   {

      searchAvailabeRoom();

      System.out.println("Enter room number");
      int roomNo = scanner.nextInt();
      Room room = rooms.getRoomByRoomNo(roomNo);

      // roomsNo.add(counter,roomNo);

      System.out.println("Enter name");
      scanner.nextLine();
      String name = scanner.nextLine();

      // new

      System.out.println("Enter email");
      // scanner.nextLine();
      String email = scanner.nextLine();

      System.out.println("Enter phone number");
      // scanner.nextLine();
      String telephoneNo = scanner.nextLine();

      Guest guest = new Guest(name, email, telephoneNo);
      // customerName.add(counter,name);

      Booking booking = new Booking(guest, desiredArrival, desiredDeparture,
            room);
      bookings.add(booking);
      System.out.println(booking.toString());
      // counter++;
      start();

   }

   @Override
   public void findBooking()
   {
      System.out.println("\n");
      System.out.println("Find booking by :");
      System.out.println("1. Name\n" + "2. Arrival and Departure\n"
            + "3. Room number\n" + "4. Return\n" + "5. Exit");
      boolean go = false;
      while (go == false)
      {

         char number = scanner.next().charAt(0);

         if ((number < '1' || number > '5') && (number > 'A' || number < 'z'))
         {

            System.out
                  .println("Wrong character or incorrect number, try again");

         }

         else
         {
            if (number == '5')
            {
               exit();
            }
            else if (number == '4')
            {
               start();
            }

            else if (number == '1')
            {
               System.out.println("Enter name");
               String na = scanner.nextLine();
               scanner.nextLine();

               for (int i = 0; i < roomsNo.size(); i++)
               {
                  String n = customerName.get(i);
                  if (n.equals(na))
                  {
                     System.out.println("Name: " + customerName.get(i)
                           + "\n Room number: " + roomsNo.get(i)
                           + "\n Arrival: " + arrival.get(i) + "\n Departure: "
                           + departure.get(i));
                  }
               }
               go = true;
            }
            else if (number == '2')
            {
               System.out.println("Enter arriva  time");
               String na = scanner.nextLine();
               scanner.nextLine();
               System.out.println("Enter departure time");
               String na1 = scanner.nextLine();

               go = true;

            }

            else if (number == '3')
            {
               System.out.println("Enter room number");

               int na = scanner.nextInt();
               scanner.nextLine();

               for (int i = 0; i < roomsNo.size(); i++)
               {
                  int n = roomsNo.get(i);
                  if (n == na)
                  {
                     System.out.println("Name: " + customerName.get(i)
                           + "\n Room number: " + roomsNo.get(i)
                           + "\n Arrival: " + arrival.get(i) + "\n Departure: "
                           + departure.get(i));
                  }
               }
               go = true;

            }
         }

      }

   }

   @Override
   public void deleteBooking()
   {
      System.out.println("\n");
      System.out.println("Find booking by :");
      System.out.println("1. Name\n" + "2. Arrival and Departure\n"
            + "3. Room number\n" + "4. Return\n" + "5. Exit");
      boolean go = false;
      while (go == false)
      {

         char number = scanner.next().charAt(0);

         if ((number < '1' || number > '6') && (number > 'A' || number < 'z'))
         {

            System.out
                  .println("Wrong character or incorrect number, try again");

         }

         else
         {
            if (number == '5')
            {
               exit();
            }
            else if (number == '4')
            {
               start();
            }

            else if (number == '1')
            {
               System.out.println("Enter name");
               String na = scanner.nextLine();
               scanner.nextLine();

               for (int i = 0; i < roomsNo.size(); i++)
               {
                  String n = customerName.get(i);
                  if (n.equals(na))
                  {
                     System.out.println("Name: " + customerName.get(i)
                           + "\n Room number: " + roomsNo.get(i)
                           + "\n Arrival: " + arrival.get(i) + "\n Departure: "
                           + departure.get(i));
                  }
                  else
                  {
                     System.out.println("Name not found");
                  }
                  System.out.println("Do you want to delete booking?\n"
                        + "1 = Yes\n" + "2 = No\n");
                  int choice = scanner.nextInt();
                  if (choice == 1)
                  {
                     System.out.println("Booking deleted!");
                     roomsNo.remove(i);
                  }

                  start();

               }
               go = true;
            }
            else if (number == '2')
            {
               System.out
                     .println("Enter arrival time and departure time??????");
               String na = scanner.nextLine();
               scanner.nextLine();
               System.out.println("Enter departure time");
               String na1 = scanner.nextLine();
               go = true;

            }

            else if (number == '3')
            {
               System.out.println("Enter room number");
               int na = scanner.nextInt();
               scanner.nextLine();
               if (na < 0 || na > 25)
               {
                  System.out.println("Wrong room number, try again");
               }

               for (int i = 0; i < roomsNo.size(); i++)
               {
                  int n = roomsNo.get(i);
                  if (n == na)
                  {
                     System.out.println("Name: " + customerName.get(i)
                           + "\n Room number: " + roomsNo.get(i)
                           + "\n Arrival: " + arrival.get(i) + "\n Departure: "
                           + departure.get(i));
                  }
                  System.out.println("Do you want to delete booking?\n"
                        + "1 = Yes\n" + "2 = No\n");
                  int choice = scanner.nextInt();
                  if (choice == 1)
                  {
                     System.out.println("Booking deleted!");
                     roomsNo.remove(i);
                  }
                  else
                  {
                     start();
                  }
               }
               go = true;

            }
         }

      }

   }

   @Override
   public void searchAvailabeRoom()
   {

      // Arrival date
      scanner.nextLine();
      System.out.println("Enter arrival day");
      int arrival1 = scanner.nextInt();
      int aday = arrival1;

      scanner.nextLine();
      System.out.println("Enter arrival month");
      arrival1 = scanner.nextInt();
      int amonth = arrival1;

      scanner.nextLine();
      System.out.println("Enter arrival year");
      arrival1 = scanner.nextInt();
      int ayear = arrival1;

      // Departure date
      scanner.nextLine();
      System.out.println("Enter departure day");
      arrival1 = scanner.nextInt();
      int dday = arrival1;

      scanner.nextLine();
      System.out.println("Enter departure month");
      arrival1 = scanner.nextInt();
      int dmonth = arrival1;

      scanner.nextLine();
      System.out.println("Enter departure year");
      arrival1 = scanner.nextInt();
      int dyear = arrival1;

      scanner.nextLine();

      desiredArrival = new MyDate(aday, amonth, ayear);
      desiredDeparture = new MyDate(dday, dmonth, dyear);

      compareDates();
      ArrayList<Room> roomDB = new ArrayList<>();
      roomDB = rooms.getRooms();

      for (int i = 0; i < roomDB.size(); i++)
      {
         if (!occupied.contains(roomDB.get(i)))
         {
            availableRooms.add(roomDB.get(i));
         }
      }
      printAvailableRooms();
   }

   public void printAvailableRooms()
   {
      System.out.println("Available rooms : ");
      for (int i = 0; i < availableRooms.size(); i++)
      {
         System.out.println(availableRooms.get(i));
      }
      System.out.println();

   }

   @Override
   public void exit()
   {
      // End program
      System.out.println("You have end the program");
      System.exit(0);
   }

   @Override
   public void compareDates()
   {
      for (int i = 0; i < bookings.size(); i++)
      {
         Booking temp = bookings.get(i);

         if ((desiredArrival.isBetween(temp.getArrivalDate(),
               temp.getDepartureDate()))
               || (desiredDeparture.isBetween(temp.getArrivalDate(),
                     temp.getDepartureDate()))
               || ((desiredDeparture.equals(temp.getDepartureDate())) && (desiredArrival
                     .equals(temp.getArrivalDate())))
               || ((temp.getArrivalDate().isBetween(desiredArrival,
                     desiredDeparture)) && (temp.getDepartureDate().isBetween(
                     desiredArrival, desiredDeparture)))
               || ((desiredArrival.isBetween(temp.getArrivalDate(),
                     temp.getDepartureDate())) && (desiredDeparture.isBetween(
                     temp.getArrivalDate(), temp.getDepartureDate()))))
         {

            if (!occupied.contains(temp.getRoom()))
            {
               occupied.add(temp.getRoom());
               // System.out.println("Occupied" +
               // temp.getRoom().getRoomNumber());

            }
         }

      }

   }

}
