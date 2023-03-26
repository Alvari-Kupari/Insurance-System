package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Initialising the database using an arraylist
  ArrayList<Profile> dataBase = new ArrayList<>();

  // initialise the index of the loaded profile
  private int loadedProfileIndex;

  public InsuranceSystem() {
    // -1 = no loaded profile, otherwise it indicates the index of loaded profile
    this.loadedProfileIndex = -1;
  }

  public void printDatabase() {

    // create a variable to store the number of profiles
    int profileCount = dataBase.size();

    // Display the correct message if there are 0 profiles
    if (profileCount == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");

      // next account for the case where there is 1 profile
    } else if (profileCount == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");

      // Finally, if there is more than 1 profile, display the correct message
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(profileCount), "s", ":");
    }

    // now loop through the database and display each name and age ranked
    for (int i = 0; i < profileCount; i++) {

      // if the current profile is the loaded profile, use the loaded profile message
      if (i == this.loadedProfileIndex) {
        MessageCli.PRINT_DB_PROFILE_HEADER_SHORT.printMessage(
            "*** ",
            Integer.toString(i + 1),
            dataBase.get(i).getName(),
            Integer.toString(dataBase.get(i).getAge()));

        // otherwise, use the normal formatting for a profile
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
            Integer.toString(i + 1),
            dataBase.get(i).getName(),
            Integer.toString(dataBase.get(i).getAge()));
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    // first we must check if there is a loaded profile
    if (this.loadedProfileIndex != -1) {

      // if there is a loaded profile, return and print the correct error message
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(
          dataBase.get(this.loadedProfileIndex).getName());
      return;
    }

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
    if (!userNameIsUnique(dataBase, userName)) {
      // if it wasnt unique print the error message and exit the method
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
      return;
    }

    // create the new profile
    Profile newProfile = new Profile(userName, ageInteger);

    // add the new profile into the arraylist to store it
    dataBase.add(newProfile);

    // Print the message showing the new profile was successfully created
    MessageCli.PROFILE_CREATED.printMessage(userName, age);
  }

  public void loadProfile(String userName) {

    // Format the username properly
    userName = capitalizeFirstLetter(userName);

    // find if there is a matching name in the databse
    int profileRank = getIndexOfMatchingProfile(dataBase, userName);

    if (profileRank == -1) {

      // if no profile was found, then print the appropriate error message and exit the method
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
      return;
    }

    // load the profile and display the load message
    this.loadedProfileIndex = profileRank;
    MessageCli.PROFILE_LOADED.printMessage(userName);
  }

  public void unloadProfile() {
    // if there is no loaded profile, display the error message and exit the method
    if (this.loadedProfileIndex == -1) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
      return;
    }

    // print the unloaded message
    MessageCli.PROFILE_UNLOADED.printMessage(dataBase.get(this.loadedProfileIndex).getName());

    // unload the profile
    this.loadedProfileIndex = -1;
  }

  public void deleteProfile(String userName) {

    // format the username
    userName = capitalizeFirstLetter(userName);

    // first find the profile to be deleted
    int indexOfMatchingProfile = getIndexOfMatchingProfile(dataBase, userName);

    if (indexOfMatchingProfile == -1) {
      // if no profile was found, print the error message and exit
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
      return;
    }
    // next we must ensure the profile to be deleted isnt currently loaded
    if (indexOfMatchingProfile == this.loadedProfileIndex) {
      // if it is loaded, print the error message and exit
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(
          dataBase.get(loadedProfileIndex).getName());
      return;
    }

    // if no violations occurred, then we can delete the profile and display the deleted message
    MessageCli.PROFILE_DELETED.printMessage(dataBase.get(indexOfMatchingProfile).getName());
    dataBase.remove(indexOfMatchingProfile);

    // if the deleted profile was lower in the database than the loaded profile, move down
    // loadedProfileIndex
    if ((indexOfMatchingProfile < this.loadedProfileIndex) && (this.loadedProfileIndex != -1)) {
      this.loadedProfileIndex--;
    }
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

  public boolean userNameIsUnique(ArrayList dataBase, String userName) {

    // loop through the database
    for (int i = 0; i < dataBase.size(); i++) {
      Profile temporaryProfile = (Profile) dataBase.get(i);

      if ((temporaryProfile.getName()).equals(userName)) {
        // if it wasnt unique then return 0

        return false;
      }
    }
    // otherwise the username is unique
    return true;
  }

  public int getIndexOfMatchingProfile(ArrayList dataBase, String userName) {
    for (int i = 0; i < dataBase.size(); i++) {
      Profile temporaryProfile = (Profile) dataBase.get(i);

      // if the temporary profile name matches the username inputted, return the index
      if (temporaryProfile.getName().equals(userName)) {
        return i;
      }
    }
    // if the matching name wasnt found, return -1 to represent no profile found
    return -1;
  }
}
