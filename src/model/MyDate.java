package model;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class MyDate implements Serializable
{
   private int day;
   private int month;
   private int year;

   public MyDate()
   {
      GregorianCalendar currentDate = new GregorianCalendar();

      int currentDay = currentDate.get(GregorianCalendar.DATE);
      int currentMonth = currentDate.get(GregorianCalendar.MONTH) + 1;
      int currentYear = currentDate.get(GregorianCalendar.YEAR);
      day = currentDay;
      month = currentMonth;
      year = currentYear;
   }

   public MyDate(int d, int m, int y)
   {
      day = d;
      month = m;
      year = y;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public void setDay(int day)
   {
      this.day = day;
   }

   public void setMonth(int month)
   {
      this.month = month;
   }

   public int getDay()
   {
      return day;
   }

   public int getMonth()
   {
      return month;
   }

   public int getYear()
   {
      return year;
   }

   public String toString()
   {
      return  day + "/" + month + "/" + year;

   }

   public boolean isLeapYear()
   {
      if (year % 4 == 0 && year % 100 != 0)
      {
         return true;
      }
      else if (year % 400 == 0)
      {
         return true;
      }
      else
      {
         return false;
      }

   }

   public int daysInMonth()
   {
      int daysInMonth = -1;
      if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
            || month == 10 || month == 12)
      {
         daysInMonth = 31;
      }
      else if (month == 4 || month == 6 || month == 9 || month == 11)
      {
         daysInMonth = 30;

      }
      else
      {
         if (isLeapYear())
         {
            daysInMonth = 29;
         }
         else
         {
            daysInMonth = 28;

         }

      }
      return daysInMonth;
   }

   public String getAstrosign()
   {
      String Astrosign;
      if (day >= 21 && month == 3 && day <= 19 && month == 4)
      {
         Astrosign = "Aries";
      }

      else if (day >= 20 && month == 4 && day <= 20 && month == 5)
      {
         Astrosign = "Taurus";
      }
      else if (day >= 21 && month == 5 && day <= 20 && month == 6)
      {
         Astrosign = "Gemini";
      }
      else if (day >= 21 && month == 6 && day <= 22 && month == 7)
      {
         Astrosign = "Cancer";
      }
      else if (day >= 23 && month == 7 && day <= 22 && month == 8)
      {
         Astrosign = "Leo";
      }
      else if (day >= 23 && month == 8 && day <= 22 && month == 9)
      {
         Astrosign = "Virgo";
      }
      else if (day >= 23 && month == 9 && day <= 22 && month == 10)
      {
         Astrosign = "Libra";
      }
      else if (day >= 23 && month == 10 && day <= 21 && month == 11)
      {
         Astrosign = "Scorpio";
      }
      else if (day >= 22 && month == 11 && day <= 21 && month == 12)
      {
         Astrosign = "Sagittarius";
      }
      else if (day >= 22 && month == 12 && day <= 19 && month == 1)
      {
         Astrosign = "Capricorn";
      }
      else if (day >= 20 && month == 1 && day <= 18 && month == 2)
      {
         Astrosign = "Aquarius";
      }
      else if (day >= 19 && month == 2 && day <= 20 && month == 4)
      {
         Astrosign = "Aries";
      }
      else
      {
         Astrosign = "no astrosign";
      }
      return Astrosign;
   }

   public String getdayOfWeek()
   {
      int h;
      String dayOfWeek;
      if (month == 1 || month == 2)
      {
         h = (day + (13 * (month + 1) / 5) + ((year - 1) % 100)
               + (((year - 1) % 100) / 4) + ((year - 1) / 400) + (5 * ((year - 1) / 100))) % 7;
      }
      else
      {
         h = (day + (13 * (month + 1) / 5) + (year % 100) + ((year % 100) / 4)
               + (year / 400) + (5 * (year / 100))) % 7;
      }

      if (h == 0)
      {
         dayOfWeek = "Saturday";
      }
      else if (h == 1)
      {
         dayOfWeek = "Sunday";
      }
      else if (h == 2)
      {
         dayOfWeek = "Monday";
      }
      else if (h == 3)
      {
         dayOfWeek = "Tuesday";
      }
      else if (h == 4)
      {
         dayOfWeek = "Wednesday";
      }
      else if (h == 5)
      {
         dayOfWeek = "Thursday";
      }
      else
      {
         dayOfWeek = "Friday";
      }

      return dayOfWeek;
   }

   public String getMonthName()
   {

      switch (month)
      {
         case 1:
            return "januar";
         case 2:
            return "Februar";

         case 3:
            return "March";

         case 4:
            return "Avril";

         case 5:
            return "Mai";

         case 6:
            return "juni";

         case 7:
            return "july";

         case 8:
            return "AUGUST";

         case 9:
            return "September";

         case 10:
            return "October";

         case 11:
            return "November";

         case 12:
            return "December";
         default:
            return "no month";
      }
   }

   public void nextDay()
   {
      if (day < daysInMonth())
      {
         day++;
      }
      else
      {
         day = 1;

         if (month == 12)
         {
            month = 1;
            year++;
         }
         else
         {
            month++;
         }
      }
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof MyDate) )
            
      {
         return false;
      }
     
      MyDate other =(MyDate)obj;
      return day==other.day&&
             month==other.month&&
             year==other.year;
      
   }

   public MyDate copy()
   {
      return new MyDate(day, month, year);
   }

   public MyDate(MyDate obj)
   {
      day = obj.getDay();
      month = obj.getMonth();
      year = obj.getYear();
   }

   public void nextDays(int days)
   {
      for (int i = 0; i <= days; i++)
      {
         nextDay();
      }

   }

   public static MyDate today()
{
  // GregorianCalendar currentDate = new GregorianCalendar();
 //  int currentDay = currentDate.get(GregorianCalendar.DATE);
//   int currentMonth = currentDate.get(GregorianCalendar.MONTH)+1;
//   int currentYear = currentDate.get(GregorianCalendar.YEAR); 
//  MyDate d = new MyDate(currentDay, currentMonth, currentYear);
//  return d;
  
  return new MyDate();
}

   public boolean isBefore(MyDate date2)
   {
      if (year < date2.year)
      {
         return true;
      }
      else if (year > date2.getYear())
      {
         return false;
      }
      else
      {
         if (month < date2.month)
         {
            return true;

         }
         else if (month > date2.month)
         {
            return false;
         }
         else
         {
            if (day < date2.day)
            {
               return true;
            }
            else
            {
               return false;
            }
         }
      }
   }
   public boolean isBetween(MyDate date1,MyDate date2)
   {

      if(date1.isBefore(this)&&this.isBefore(date2))
      {
         return true ;
      }
      return false ;
   }
 
}
