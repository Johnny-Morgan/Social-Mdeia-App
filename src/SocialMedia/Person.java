package SocialMedia;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Program to define a Person class
 */

public class Person {

   private String email;
   private String lastName;
   private String firstName;
   private String home;
   private String hobby;
   private LocalDate dateOfBirth;

   private ArrayList<Person> friends;

   public Person(String myEmail, String last, String first, String newHome, String myHobby, String myDOB) {
      email = myEmail;
      lastName = last;
      firstName = first;
      home = newHome;
      hobby = myHobby;
      dateOfBirth = Methods.convertDate(myDOB);
      friends = new ArrayList<Person>();
   }//Person

   protected String getEmail() {
      return email;
   }//getEmail

   protected void setEmail(String newEmail) {
      email = newEmail;
   }//setEmail

   protected String getLastName() {
      return lastName;
   }//getLastName

   protected void setLastName(String newLastName) {
      lastName = newLastName;
   }//setLastName

   protected String getFirstName() {
      return firstName;
   }//getFirstName

   protected String getFullName() {
      return firstName + " " + lastName;
   }//getFullName

   protected String getHome() {
      return home;
   }//getHome

   protected void setHome(String newHome) {
      home = newHome;
   }//setHome

   public String getHobby() {
      return hobby;
   }

   public void setHobby(String myHobby) {
      hobby = myHobby;
   }

   protected LocalDate getDateOfBirth() {
      return dateOfBirth;
   }//getDateOfBirth

   protected void setDateOfBirth(String date) { dateOfBirth = Methods.convertDate(date); }//setDateOfBirth
   // ***************
   // FRIENDS METHODS
   // ***************
   protected ArrayList<Person> getFriends() {
      return friends;
   }//getFriends

   protected void addFriend(Person newFriend) {
      friends.add(newFriend);
   }//addFriend

   protected void removeFriend(Person aFriend) {
      friends.remove(aFriend);
   }//addFriend

   // Method to check if a Person is already a friend
   protected boolean alreadyAFriend(Person aPerson) {
      boolean answer = false;
      if (friends != null) {
         answer = friends.contains(aPerson);
      }//if
      return answer;
   }//alreadyAFriend

   // Method to return references to any Persons with same last name as searchName
   protected ArrayList<Person> friendsBySearchName(String searchName) {
      ArrayList<Person> result = new ArrayList<Person>();
      for (Person aFriend : friends) {
         if (aFriend.lastName.equals(searchName)) {
            result.add(aFriend);
         }//if
      }//for
      return result;
   }//friendsBySearchName

   // *************
   // PRINT METHODS
   // *************
   protected void displayFriends() {
      if (friends.size() == 0) {
         System.out.println("No friends");
      }//if
      else {
         System.out.println("You have " + friends.size() + " friends:");
         for (Person friend : friends) {
            System.out.print("\t" + friend.getFullName());
            if (Methods.calculateBirthday(friend.getDateOfBirth()) < 7) {
               System.out.print("  ** BIRTHDAY SOON **");
            }//if
            System.out.println();
         }//for
      }//else
   }//displayFriends

   protected void displayPerson() {
      System.out.println("\nFull Name:     " + getFullName());
      System.out.println("Email:         " + email);
      System.out.println("Home:          " + home);
      System.out.println("Hobby:         " + hobby);
      System.out.println("Date Of Birth: " + Methods.dateToString(dateOfBirth));
      displayFriends();
   }//displayPerson

}//class


