package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Initialising the database using an arraylist
  ArrayList<Profile> dataBase = new ArrayList<>();

  public InsuranceSystem() {}

  public void printDatabase() {}

  public void createNewProfile(String userName, String age) {

    // Format the username to title case
    String userNameFormatted = capitalizeFirstLetter(userName);

    // Check if the string age doesnt contain only digits (IE is not a number).
    if (!(age.matches("[0-9]+"))) {

      // if the string age was invalid, print the error message for invalid age, then return
      MessageCli.INVALID_AGE.printMessage(age, userNameFormatted);
      return;
    }

    // Next, the age must be converted into an integer
    int age_integer = Integer.parseInt(age);

    // Next we must ensure the username is valid (more than 2 characters)
    if (userNameIsTooShort(userName)) {
      // Print error message for username too short
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userNameFormatted);
      return;
    }

    // Next ensure the username is unique
    for (int i = 0; i < dataBase.size(); i++) {
      Profile temporaryProfile = dataBase.get(i);

      if ((temporaryProfile.name).equals(userNameFormatted)) {
        // if it wasnt unique then print the correct error message and exit the method
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userNameFormatted);
        return;
      }
    }

    // create the new profile
    Profile newProfile = new Profile(userNameFormatted, age_integer);

    // add the new profile into the arraylist to store it
    dataBase.add(newProfile);

    // Print the message showing the new profile was successfully created
    MessageCli.PROFILE_CREATED.printMessage(userNameFormatted, age);
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
  public boolean userNameIsTooShort(String userName) {

    // Check the length (if less than 3 then return true)
    if (userName.length() < 3) {
      return true;
    }

    // else return false
    return false;
  }

  public String capitalizeFirstLetter(String string) {
    String everythingButFirstLetter = string.substring(1);
    String unCapitalized = everythingButFirstLetter.toLowerCase();
    String firstLetter = string.substring(0, 1);
    String firstLetterCapitalized = firstLetter.toUpperCase();

    return firstLetterCapitalized + unCapitalized;
  }
}
