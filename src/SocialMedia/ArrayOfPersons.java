package SocialMedia;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

/**
 * Program to define an ArrayOfPersons class
 */

public class ArrayOfPersons {

   private List<Person> members;

   public ArrayOfPersons() {
      members = new ArrayList<Person>();
   }//ArrayOfPersons

   // Return a reference to the current person
   protected Person getCurrent(int position) {
      return members.get(position);
   }//getCurrent

   protected int getNoOfPersons() {
      return members.size();
   }//getNoOfPersons

   protected String getEmail(int position) {
      return members.get(position).getEmail();
   }//getEmail

   protected String getLastName(int position) {
      return members.get(position).getLastName();
   }//getLastName

   protected String getFullName(int position) {
      return members.get(position).getFullName();
   }//getFullName

   // Insert Person object into the correct index in the array - sorted by email
   protected void insert(Person newPerson) {
      members.add(newPerson);
      sortByEmail();
      System.out.println(newPerson.getFullName() + " Added");
   }//insert

   protected void removePerson(int position) {
      members.remove(position);
   }//removePerson

   // Define a Comparator
   Comparator<Person> emailCompare = new Comparator<Person>() {
      @Override
      public int compare(Person person1, Person person2) {
         return person1.getEmail().compareTo(person2.getEmail());
      }//compare
   };

   // Sort members by email using Collections Sort
   protected void sortByEmail() {
      Collections.sort(members, emailCompare);
   }//sortByEmail

   // Binary search to find index of member using email address
   // Return index position of member or number < 0 if not found
   protected int findMemberByEmail(String email) {
      return Collections.binarySearch(members, new Person(email, null, null, 
      null, "01-01-2000"), emailCompare);
   }//findMemberByEmail

   // Method to find Member(s) by their last name
   // Return a range of indices or an empty list if not found
   protected ArrayList<Integer> findByLastName(String searchName) {
      ArrayList<Integer> result = new ArrayList<Integer>();
      for (int index = 0; index < members.size(); index++) {
         if (members.get(index).getLastName().equals(searchName)) {
            result.add(index);
         }//if
      }//for
      return result;
   }//findByLastName

   // ***************
   // FRIENDS METHODS
   // ***************

   // Check if member at memberIndex has the same home as myHome
   protected Person checkFriendsByHome(int memberIndex, String myHome) {
      Person answer = null;
      if (members.get(memberIndex).getHome().equals(myHome)) {
         answer = members.get(memberIndex);
      }//if
      return answer;
   }//checkFriendByHome

   // Check if member at memberIndex has the same last name as searchName
   protected ArrayList<Person> checkFriendsByName(int memberIndex, String searchName) {
      return members.get(memberIndex).friendsBySearchName(searchName);
   }//checkFriendsByName

   // Add a friend to member at memberIndex
   protected void addFriends(int memberIndex, Person memberFriend) {
      int friendIndex;
      members.get(memberIndex).addFriend(memberFriend);
      friendIndex = findMemberByEmail(memberFriend.getEmail());
      members.get(friendIndex).addFriend(getCurrent(memberIndex));
   }//addFriends

   // *************
   // PRINT METHODS
   // *************

   protected void displayName(int index) {
      System.out.println(members.get(index).getFullName());
   }//displayName

   protected void displayMember(int index) {
      members.get(index).displayPerson();
   }//displayMember

   protected void displayEveryone() {
      for (Person aPerson : members) {
            aPerson.displayPerson();
      }//for
   }//displayEveryone
}//class

