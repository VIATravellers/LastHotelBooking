package model;


public class Booking
{

   private Guest guest;
   private MyDate arrivalDate, departureDate;

   private Room room;

   public Booking(Guest name, MyDate arrivalDate2, MyDate departureDate2,
         Room room)
   {

      this.guest = name;
      this.arrivalDate = arrivalDate2;
      this.departureDate = departureDate2;
      this.room = room;

   }

   public Guest getGuest()
   {
      return guest;
   }

   public void setGuest(Guest guest)
   {
      this.guest = guest;
   }

   public MyDate getArrivalDate()
   {
      return arrivalDate;
   }

   public void setArrivalDate(MyDate arrivalDate)
   {
      this.arrivalDate = arrivalDate;
   }

   public MyDate getDepartureDate()
   {
      return departureDate;
   }

   public void setDepartureDate(MyDate departureDate)
   {
      this.departureDate = departureDate;
   }

   public Room getRoom()
   {
      return room;
   }

  

   @Override
   public String toString()
   {

      return "Booking  \n\tGuest name = " + guest.getName() + "\n\tArrival date = "
            + arrivalDate + "\n\tDeparture date = " + departureDate + ",\n\tRoom: "
            + room.toString() + "\n";
   }

}
