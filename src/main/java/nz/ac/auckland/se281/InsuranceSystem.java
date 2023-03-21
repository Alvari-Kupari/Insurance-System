package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Initialising the database using an arraylist
  ArrayList<Profile> dataBase = new ArrayList<>();

  public InsuranceSystem() {}

  public void printDatabase() {
    // TODO: Complete this method.
  }

  public void createNewProfile(String userName, String age) {

    // Format the username to title case by first converting the whole string to lowercase
    userName.toLowerCase();

    // then change the first letter to uppercase
    String userNameFormatted = userName.substring(0, 1).toUpperCase() + userName.substring(1);

    // Check if the string age doesnt contain only digits (IE is not a number).
    if (!(age.matches("[0-9]+"))) {

      // if the string age was invalid, print the error message for invalid age, then return
      MessageCli.INVALID_AGE.printMessage(age, userNameFormatted);
      return;
    }

    // Next, the age must be converted into an integer
    int age_integer = Integer.parseInt(age);

    // Next we must ensure the username is valid (more than 2 characters)
    if (userNameIsTooSHort(userName)) {
      // Print error message for username too short
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userNameFormatted);
      return;
    }

    // Next ensure the username is unique
    int a = 0;
    for (int i = 0; i < dataBase.size(); i++) {
      Profile temporaryProfile = dataBase.get(i);

      if (temporaryProfile.name == userNameFormatted) {
        // if it wasnt unique then print the correct error message and exit the method
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userNameFormatted);
        a = 1;
        return;
      }
    }

    // create the new profile
    Profile newProfile = new Profile(userNameFormatted, age_integer);

    // add the new profile into the arraylist to store it
    dataBase.add(newProfile);

    // Print the message showing the new profile was successfully created
    MessageCli.PROFILE_CREATED.printMessage(userNameFormatted, age);
    if (a == 1) {
      System.out.println("doggies");
    }
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
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
  public boolean userNameIsTooSHort(String userName) {

    // Check the length (if less than 3 then return true)
    if (userName.length() < 3) {
      return true;
    }

    // else return false
    return false;
  }
}
