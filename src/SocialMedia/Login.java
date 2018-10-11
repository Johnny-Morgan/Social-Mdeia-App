package SocialMedia;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Display menu options for a member once they are logged in
 */

public class Login {

   static Scanner keyboard = new Scanner(System.in);

   protected static void userMenu(ArrayOfPersons myArray, int index, 
      String administrator) {

      int currentIndex = index, days;
      char choice;
      Person currentMember = myArray.getCurrent(index);

      do {
         myArray.displayMember(currentIndex);
         days = Methods.calculateBirthday(currentMember.getDateOfBirth());
         System.out.println(days + " days until your birthday\n");

         System.out.println("\n**** USER MENU ****");
         System.out.println("1 - Edit Details");
         System.out.println("2 - Suggest Friends");
         System.out.println("3 - Search for another user");
         System.out.println("4 - Delete Friends");
         System.out.println("5 - Delete your own account");

         System.out.println("0 - Return to Main Menu\n");

         System.out.print("Enter your choice: ");
         choice = keyboard.nextLine().charAt(0);

         switch (choice) {
            case '1':
               currentIndex = editMenu(myArray, currentIndex, administrator);
               break;

            case '2':
               checkFriends(myArray, currentIndex);
               break;

            case '3':
               findMember(myArray, currentIndex);
               break;

            case '4':
               removeFriends(myArray, currentIndex);
               break;

            case '5':
               if (deleteMe(myArray, currentIndex)) {
                  choice = '0';
               }//if
               break;

            case '0':
               break;

            default:
               System.out.println("Invalid choice\n");
         }//switch
      } while (choice != '0');
   }//userMenu

   // OPtION 1 - Method to edit a member's details
   protected static int editMenu(ArrayOfPersons myArray, int index, String administrator) {
      int location = index;
      char editChoice;
      Person currentMember = myArray.getCurrent(location);
      String newData;

      do {
         System.out.println("Your current details are as follows:");
         currentMember.displayPerson();
         System.out.println("Do you want to change?");
         System.out.println("1 - First Name");
         System.out.println("2 - Last Name");
         System.out.println("3 - Email");
         System.out.println("4 - Home Location");
         System.out.println("5 - Date Of Birth");
         System.out.println("6 - Hobby");
         System.out.println("0 - Return to Main Menu\n");

         System.out.print("Enter your choice: ");
         editChoice = keyboard.nextLine().charAt(0);

         switch (editChoice) {
            case '1':
               System.out.println("\nYour first name is: " + 
                  currentMember.getFirstName());
               System.out.print("\nEnter your new first name: ");
               newData = keyboard.nextLine();
               //  currentMember.setFirstName(newData);
               break;
            case '2':
               System.out.println("\nYour last name is: " + 
                  currentMember.getLastName());
               System.out.print("\nEnter your new last name: ");
               newData = keyboard.nextLine();
               currentMember.setLastName(newData);
               break;
            case '3':
               System.out.println("\nYour current email address is: " + 
                  currentMember.getEmail());
               System.out.print("\nEnter your new email address: ");
               newData = keyboard.nextLine();
               if ((myArray.findMemberByEmail(newData) < 0) || 
                  (newData.equals(administrator))) {
                  System.out.println("\nEmail already exists");
               }//if
               else {
                  currentMember.setEmail(newData);
                  // Re-sort the members into alphabetical order of email address
                  myArray.sortByEmail();
                  location = myArray.findMemberByEmail(newData);
               }//else
               break;
            case '4':
               System.out.println("\nYour home location is: " + 
                  currentMember.getHome());
               System.out.print("\nEnter your new home location: ");
               newData = keyboard.nextLine();
               currentMember.setHome(newData);
               break;
            case '5':
               System.out.println("\nYour Date of Birth is: " + 
                  Methods.dateToString(currentMember.getDateOfBirth()));
               do {
                  do {
                     System.out.print("\nEnter your new Date of Birth (DD-MM-YYYY): ");
                     newData = keyboard.nextLine();
                  } while (!Methods.checkDateFormat(newData));
               } while (!Methods.checkProperBirthday(Methods.convertDate(newData)));
               currentMember.setDateOfBirth(newData);
               break;
            case '6':
               System.out.println("\nYour hobby is: " +
                       currentMember.getHobby());
               System.out.print("\nEnter your new hobby: ");
               newData = keyboard.nextLine();
               currentMember.setHobby(newData);
               break;
            case '0':
               break;
            default:
               System.out.println("Invalid choice\n");
         }//switch
      } while (editChoice != '0');
      return location;
   }//editMenu


   // OPTION 2 - Check for and add possible friends based on home location
   protected static void checkFriends(ArrayOfPersons myArray, int location) {
      Person currentMember = myArray.getCurrent(location), possibleFriend;
      ArrayList<Person> friendsOfFriends = new ArrayList<Person>();
      char response;
      int noOfFriends = 0;

      System.out.println("\nSuggested friends based on home location:");
      for (int index = 0; index < myArray.getNoOfPersons(); index++) {
         possibleFriend = myArray.checkFriendsByHome(index, currentMember.getHome());
         if ((possibleFriend != null) && (index != location)) {
            // If not already a friend ask if they are to be added
            if (!currentMember.alreadyAFriend(possibleFriend)) {
               myArray.displayName(index);
               System.out.print("\tDo you want to add this person as a friend (Y/N)? ");
               response = keyboard.nextLine().toUpperCase().charAt(0);
               if (response == 'Y') {
                  myArray.addFriends(location, possibleFriend);
                  System.out.println("\t" + myArray.getFullName(index) + " added as a Friend");
                  noOfFriends++;
               }//if
            }//if
         }//if
      }//for

      //Check for and add possible friends based on hobby
      System.out.println("\nSuggested friends based on hobby:");
      for (int index = 0; index < myArray.getNoOfPersons(); index++) {
         possibleFriend = myArray.checkFriendsByHobby(index, currentMember.getHobby());
         if ((possibleFriend != null) && (index != location)) {
            // If not already a friend ask if they are to be added
            if (!currentMember.alreadyAFriend(possibleFriend)) {
               myArray.displayName(index);
               System.out.print("\tYou share an interest in " + currentMember.getHobby() + "\n");
               System.out.print("\tDo you want to add this person as a friend (Y/N)? ");
               response = keyboard.nextLine().toUpperCase().charAt(0);
               if (response == 'Y') {
                  myArray.addFriends(location, possibleFriend);
                  System.out.println("\t" + myArray.getFullName(index) + " added as a Friend");
                  noOfFriends++;
               }//if
            }//if
         }//if
      }//for

      //Check for and add possible friends based on friends of friends
      System.out.println("\nSuggested friends based on Friends of Friends:");
      for (Person aFriend : currentMember.getFriends()) {
         for (Person friend : aFriend.getFriends()) {
            if ((!currentMember.getFriends().contains(friend)) && 
               (!currentMember.getEmail().equals(friend.getEmail()))
                    && (!friendsOfFriends.contains(friend))) {
               friendsOfFriends.add(friend);
            }//if
         }//for
      }//for
      for (Person aFriend : friendsOfFriends) {
         System.out.println(aFriend.getFullName());
         System.out.print("\tDo you want to add this person as a friend (Y/N)? ");
         response = keyboard.nextLine().toUpperCase().charAt(0);
         if (response == 'Y') {
            currentMember.getFriends().add(aFriend);
            System.out.println("\t" + aFriend.getFullName() + " added as a Friend");
            noOfFriends++;
         }//if
      }//for


      if (noOfFriends == 0) {
         System.out.println("\nNo new friends added");
      }//if
      else {
         System.out.println("\n" + noOfFriends + " new friends added");
      }//else
   }//checkFriends


   // OPTION 3 - Find member(s) with a particular last name
   protected static void findMember(ArrayOfPersons myArray, int location) {
      String answer;
      char response;
      Person currentMember = myArray.getCurrent(location);
      ArrayList<Integer> searchLastNameResult;

      System.out.print("\nEnter the last name you are looking for (case sensitive): ");
      answer = keyboard.nextLine();
      searchLastNameResult = myArray.findByLastName(answer);

      // Check if the result contains the current member
      if (searchLastNameResult.contains(location)) {
         searchLastNameResult.remove(searchLastNameResult.indexOf(location));
      }//if

      // No last name found
      if (searchLastNameResult.size() == 0) {
         System.out.println("No-one with this surname is registered");
      }//if
      else {
         for (Integer member : searchLastNameResult) {
            myArray.displayName(member);
            // Check if already a friend
            if (currentMember.getFriends().contains(myArray.getCurrent(member))) {
               System.out.println("\tAlready a friend");
            }//if
            else {
               System.out.print("\tDo you want to add this person as a friend (Y/N)? ");
               response = keyboard.nextLine().toUpperCase().charAt(0);
               if (response == 'Y') {
                  currentMember.addFriend(myArray.getCurrent(member));
                  System.out.println("\t" + myArray.getCurrent(member).
                     getFullName() + " added as a Friend\n");
               }//if
            }//else
         }//for
      }//else
      System.out.println("\nPRESS ANY KEY TO CONTINUE");
      keyboard.nextLine();
   }//findMember


   // OPTION 4 - Remove a friend
   protected static void removeFriends(ArrayOfPersons myArray, int location) {
      Person currentMember = myArray.getCurrent(location);
      String answer;
      ArrayList<Person> searchLastNameResult;
      char response;

      System.out.print("\nEnter the last name of the friend you wish to remove (case sensitive): ");
      answer = keyboard.nextLine();

      // Find any member with this surname
      searchLastNameResult = myArray.checkFriendsByName(location, answer);

      // Check if no friends with last name found
      if (searchLastNameResult.size() == 0) {
         System.out.println("No-one with this last name is a friend");
      }//if
      else {
         for (Person friend : searchLastNameResult) {
            System.out.print("\tDo you wish to delete " + friend.getFullName() + 
               " from your list of friends (Y/N)? ");
            response = keyboard.nextLine().toUpperCase().charAt(0);
            if (response == 'Y') {
               // Remove from list of friends
               currentMember.removeFriend(friend);
               // Remove from friend's list of friends
               friend.removeFriend(currentMember);
               System.out.println("\t" + friend.getFullName() + 
                  " has been removed as a Friend\n");
            }//if
         }//for
      }//else
   }//removeFriends


   // OPTION 5 Remove member from system and remove from all friends lists
   protected static boolean deleteMe(ArrayOfPersons myArray, int location) {
      Person currentMember = myArray.getCurrent(location);
      char response;
      boolean deleted = false;

      System.out.print("\nAre you sure you want to remove yourself from this Social Network site (Y/N)?: ");
      response = keyboard.nextLine().toUpperCase().charAt(0);
      if (response == 'Y') {
         // Remove member from friends lists
         for (int index = 0; index < myArray.getNoOfPersons(); index++) {
            if (myArray.getCurrent(index).getFriends().contains(currentMember)) {
               myArray.getCurrent(index).getFriends().remove(currentMember);
            }//if
         }//for
         // Remove member
         myArray.removePerson(location);
         deleted = true;
      }//if
      return deleted;
   }//deleteMe

}//class


