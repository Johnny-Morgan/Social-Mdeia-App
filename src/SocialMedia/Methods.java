package SocialMedia;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.temporal.ChronoUnit;
import java.time.Period;

/**
 * Class to contain commonly used methods to manipulate dates
 */

public class Methods {

   // Method to check for the proper date format
   protected static boolean checkDateFormat(String theDate) {

      boolean proper = false, properFormat = false, properNumbers = false;
      int day, month, year;

      // Check proper formatting, day-month-year
      if (theDate.contains("-")) {
         String[] parts = theDate.split("-");

         if ((parts.length == 3) && (parts[2].length() == 4)) {
            properFormat = true;
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);

            if (year >= 0) {
               // Check proper number of days in a month
               if ((month >= 1) && (month <= 12)) {
                  switch (month) {
                     case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                        if ((day >= 1) && (day <= 31)) {
                           properNumbers = true;
                        }// if
                        break;
                     case 2:
                        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                           if ((day >= 1) && (day <= 29)) {
                              properNumbers = true;
                           }//if
                        }//if leap
                        else {
                           if ((day >= 1) && (day <= 28)) {
                              properNumbers = true;
                           }//if
                        }//else not leap
                        break;
                     case 4: case 6: case 9: case 11:
                        if ((day >= 1) && (day <= 30)) {
                           properNumbers = true;
                        }//if
                        break;
                  }//switch
               }//if valid month
            }//if valid year
         }//if
      }//if

      if (properFormat) {
         if (properNumbers) {
            proper = true;
         }//if
         else {
            System.out.println("\tInvalid Date!");
         }//else
      }//if
      else {
         System.out.println("\t Invalid date format!");
      }//else

      return proper;
   }//checkDateFormat

   // Method to check if the user is inputting proper year of birth
   protected static boolean checkProperBirthday(LocalDate theBirthday) {
      final int OLDAGE = 123;
      boolean properBirthday = false;
      if (theBirthday.isBefore(LocalDate.now()) && (Period.between(theBirthday, LocalDate.now()).getYears() < OLDAGE)) {
         properBirthday = true;
      }//if
      else {
         System.out.println("\tInvalid year!");
      }//else
      return properBirthday;
   }//checkProperBirthday

   // Method to convert LocalDate to String format dd-MM-yyyy
   protected static String dateToString(LocalDate theDate) {
      String date = "";
      int day = theDate.getDayOfMonth();
      int month = theDate.getMonthValue();

      if (day < 10) {
         date = date.concat("0");
      }//if
      date = date.concat(Integer.toString(day).concat("-"));
      if (month < 10) {
         date = date.concat("0");
      }//if
      date = date.concat(Integer.toString(month).concat("-"));
      return date.concat(Integer.toString(theDate.getYear()));
   }//dateToString

   // Method to convert String date to LocalDate
   protected static LocalDate convertDate(String stringDate) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.ENGLISH);
      return LocalDate.parse(stringDate, formatter);
   }//convertDate

   // Method to calculate days to the next birthday
   protected static int calculateBirthday(LocalDate pBirthday) {
      // Returns a copy of pBirthday but changes its year to today's year
      LocalDate nextBirthday = pBirthday.withYear(LocalDate.now().getYear());
      // If birthday has already happened this year it has to change it by +1
      if (nextBirthday.isBefore(LocalDate.now()) || nextBirthday.isEqual(LocalDate.now())) {
         nextBirthday = nextBirthday.plusYears(1);
      }//if
      // Calculate and return the day difference
      return (int)(ChronoUnit.DAYS.between(LocalDate.now(), nextBirthday));
   }//calculateBirthday

}//class