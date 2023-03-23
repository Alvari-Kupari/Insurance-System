package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Initialising the database using an arraylist
  ArrayList<Profile> dataBase = new ArrayList<>();

  public InsuranceSystem() {}

  public void printDatabase() {

    // create a variable to store the number of profiles
    int profileCount = dataBase.size();

    // Display the correct message if there are 0 profiles
    if (profileCount == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    }

    // next account for the case where there is 1 profile
    else if (profileCount == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
    }

    // Finally, if there is more than 1 profile, display the correct message
    else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(profileCount), "s", ":");
    }

    // now loop through the database and display each name and age ranked
    for (int i = 0; i < profileCount; i++) {
      System.out.println(
          " "
              + (i + 1)
              + ": "
              + dataBase.get(i).getName()
              + ", "
              + Integer.toString(dataBase.get(i).getAge()));
    }
  }

  public void createNewProfile(String userName, String age) {

    // Format the username to title case
    userName = capitalizeFirstLetter(userName);

    // Check if the string age doesnt contain only digits (IE is not a number).
    if (!(age.matches("[0-9]+"))) {

      // if the string age was invalid, print the error message for invalid age, then return
      MessageCli.INVALID_AGE.printMessage(age, userName);
      return;
    }

    // Next, the age must be converted into an integer
    int ageInteger = Integer.parseInt(age);

    // Next we must ensure the username is valid (more than 2 characters)
    if (userNameIsTooShort(userName)) {
      // Print error message for username too short
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      return;
    }

    // Next ensure the username is unique
    for (int i = 0; i < dataBase.size(); i++) {
      Profile temporaryProfile = dataBase.get(i);

      if ((temporaryProfile.getName()).equals(userName)) {
        // if it wasnt unique then print the correct error message and exit the method
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        return;
      }
    }

    // create the new profile
    Profile newProfile = new Profile(userName, ageInteger);

    // add the new profile into the arraylist to store it
    dataBase.add(newProfile);

    // Print the message showing the new profile was successfully created
    MessageCli.PROFILE_CREATED.printMessage(userName, age);
  }

  public void loadProfile(String userName) {
    //Format the username properly
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }

  // Helper methods
  public boolean userNameIsTooShort(String userName) {

    // Check the length (if less than 3 then return true)
    if (userName.length() < 3) {
      return true;
    }

    // else return false
    return false;
  }

  public String capitalizeFirstLetter(String string) {

    // first create a string of everything but the first letter
    String everythingButFirstLetter = string.substring(1);

    // convert the previous string to lower case
    String unCapitalized = everythingButFirstLetter.toLowerCase();

    // isolate the first letter
    String firstLetter = string.substring(0, 1);

    // convert the first letter to uppercase
    String firstLetterCapitalized = firstLetter.toUpperCase();

    // return the two strings mashed together
    return firstLetterCapitalized + unCapitalized;
  }

}
