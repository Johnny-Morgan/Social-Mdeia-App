package SocialMedia;
import java.util.Scanner;

/**
 * Program to create a Social Network System
 */

public class SocialNetwork {

   // Populate Members and some friends
   protected static ArrayOfPersons addMembers(ArrayOfPersons myArray) {

      Person person1 = new Person("j.smith@hotmail.com", "Smith", "John",
         "Rathcoole", "Dublin","Football", "30-10-1990");
      Person person2 = new Person("d.jones@hotmail.com", "Jones", "Denise",
         "Clondalkin","Dublin", "Netball","25-04-2000");
      Person person3 = new Person("m.young@google.com", "Young", "Mary", "Blackrock",
         "Dublin","Painting", "06-05-1997");
      Person person4 = new Person("c.robinson@btinternet.com", "Robinson", "Ciara", 
         "Dundalk", "Louth","Piano", "24-06-2005");
      Person person5 = new Person("j.agnew@gmail.com", "Agnew", "James", "Blackrock",
         "Dublin","Football", "15-09-2000");
      Person person6 = new Person("a.jones@ulster.ac.uk", "Jones", "Alex", 
         "Blackrock", "Dublin","Guitar", "01-07-1995");
      Person person7 = new Person("b.jones@gmail.com", "Jones", "Bobby", 
         "Bray", "Wicklow","Hiking", "01-07-1995");
      Person person8 = new Person("b.kelly@ulster.ac.uk", "Kelly", "Bronagh",
         "Dundalk", "Louth","Poker", "01-07-1995");

      // Add members
      // Maintaining order by email
      myArray.insert(person1);
      myArray.insert(person2);
      myArray.insert(person3);
      myArray.insert(person4);
      myArray.insert(person5);
      myArray.insert(person6);
      myArray.insert(person7);
      myArray.insert(person8);

      myArray.addFriends(4, person7);
      myArray.addFriends(4, person8);
      myArray.addFriends(0, person4);

      return myArray;
   }//addMembers


   public static void main(String[] args) {

      Scanner keyboard = new Scanner(System.in);

      final String ADMINLOGIN = "ADMIN";
      char choice;
      int index;
      String memberLogin, firstName, lastName, email, home, county, hobby, dateOfBirth;
      Person newPerson;

      ArrayOfPersons mySocialMembers = new ArrayOfPersons();

      System.out.println("CASE STUDY COMPLETED");
      System.out.println("********************");

      addMembers(mySocialMembers);

      do {
         System.out.println("\n**** SOCIAL NETWORK ****");
         System.out.println("1 - Login");
         System.out.println("2 - Register New User");
         System.out.println("0 - Exit\n");

         System.out.print("Enter your choice: ");
         choice = keyboard.nextLine().charAt(0);

         switch (choice) {
            case '1':
               System.out.print("\nEnter your email: ");
               memberLogin = keyboard.nextLine();
               if (memberLogin.equals(ADMINLOGIN)) {
                  Administrator.adminMenu(mySocialMembers);
               }//if
               else {
                  index = mySocialMembers.findMemberByEmail((memberLogin));
                  if (index < 0) {
                     System.out.println("\nMember not found\n");
                  }//if
                  else {
                     Login.userMenu(mySocialMembers, index, ADMINLOGIN);
                  }//else
               }//else
               break;
            case '2':
               System.out.println("\nEnter the following details:");
               System.out.print("Email address:              ");
               email = keyboard.nextLine();
               System.out.print("First name:                 ");
               firstName = keyboard.nextLine();
               System.out.print("Last name:                  ");
               lastName = keyboard.nextLine();
               System.out.print("Home town location:         ");
               home = keyboard.nextLine();
               System.out.print("Home county:              ");
               county = keyboard.nextLine();
               System.out.print("Your hobby:                 ");
               hobby = keyboard.nextLine();
               do {
                  do {
                     System.out.print("Date of Birth (DD-MM-YYYY): ");
                     dateOfBirth = keyboard.nextLine();
                  } while (!Methods.checkDateFormat(dateOfBirth));
               } while (!Methods.checkProperBirthday
                    (Methods.convertDate(dateOfBirth)));

               // Create new member and insert into array in alphabetical 
               // order of email address
               newPerson = new Person(email, lastName, firstName, home, county, hobby, dateOfBirth);
               mySocialMembers.insert(newPerson);
               break;
            case '0':
               System.out.println("You have chosen to exit the Social Network");
               break;
            default:
               System.out.println("Invalid choice\n");
         }//switch
      } while (choice != '0');
   }//main
}//class


