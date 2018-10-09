package SocialMedia;
import java.util.Scanner;

/**
 * Display the menu options for the Administrator
 */

public class Administrator {

   static Scanner keyboard = new Scanner(System.in);

   static protected void adminMenu(ArrayOfPersons socialMembers) {
      char adminChoice, response;
      int removeIndex;
      String memberEmail;

      do {
         System.out.println("\n**** ADMINISTRATOR MENU ****");
         System.out.println("1 - List All Users");
         System.out.println("2 - Remove a User");
         System.out.println("0 - Return to Main Menu\n");

         System.out.print("Enter your choice: ");
         adminChoice = keyboard.nextLine().charAt(0);

         switch (adminChoice) {

            case '1':
               socialMembers.displayEveryone();
               break;

            case '2':
               System.out.print("\nEnter the email of the member to be deleted (case sensitive): ");

               memberEmail = keyboard.nextLine();
               removeIndex = socialMembers.findMemberByEmail(memberEmail);

               socialMembers.displayName(removeIndex);
               System.out.print("\tDo you want to remove this member (Y/N)? ");
               response = keyboard.nextLine().toUpperCase().charAt(0);
               if (response == 'Y') {
                  // Remove member from friends lists
                  for (int index = 0; index < socialMembers.getNoOfPersons(); index++) {
                     if (socialMembers.getCurrent(index).getFriends().
                           contains(socialMembers.getCurrent(removeIndex))) {
                           socialMembers.getCurrent(index).getFriends().
                              remove(socialMembers.getCurrent(removeIndex));
                     }//if
                  }//for
                  // Remove member
                  System.out.println("\t" + socialMembers.getCurrent(removeIndex).getFullName() + " removed\n");
                  socialMembers.removePerson(removeIndex);
               }//if
               break;

            case '0':
               break;

            default:
               System.out.println("Invalid Choice");
         }//switch

      } while (adminChoice != '0');

   }//adminMenu

}//class

