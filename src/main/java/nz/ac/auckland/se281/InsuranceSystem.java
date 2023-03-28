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
    int profileCount = this.dataBase.size();

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

      // check if the profile is loaded. if so, make a string for the asterisks
      String stars = "";
      if (i == this.loadedProfileIndex) {
        stars = "*** ";
      }
      // initialise variables
      Profile profile = this.dataBase.get(i);
      int numPolicies = profile.policies.size();

      // next, format the word "policy" depending on how many policies they have
      String wordPolicy = "";
      switch (numPolicies) {
        case 1:
          wordPolicy = "y";
          break;

        default:
          wordPolicy = "ies";
          break;
      }

      // next print the profile
      MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          stars,
          Integer.toString(i + 1),
          profile.getName(),
          Integer.toString(profile.getAge()),
          Integer.toString(numPolicies),
          wordPolicy,
          Integer.toString(profile.getTotalPremium()));

      // next we will print the policy details of each client by looping through each policy
      for (int j = 0; j < profile.policies.size(); j++) {
        // extract the policy
        Policy policy = profile.policies.get(j);

        // use an if statement to cover the different policy types
        if (policy instanceof LifePolicy) {
          // life policy header
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              Integer.toString(policy.getSumInsured()),
              Integer.toString(policy.getPremium()),
              Integer.toString(policy.getDiscountedPremium()));

          // next cover the case for home policy
        } else if (policy instanceof HomePolicy) {
          // cast policy to home
          HomePolicy tempPolicy = (HomePolicy) policy;

          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              tempPolicy.getAddress(),
              Integer.toString(policy.getSumInsured()),
              Integer.toString(policy.getPremium()),
              Integer.toString(policy.getDiscountedPremium()));

          // now cover the car policy case
        } else if (policy instanceof CarPolicy) {
          // cast policy to car
          CarPolicy tempPolicy = (CarPolicy) policy;

          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              tempPolicy.getMakeAndModel(),
              Integer.toString(policy.getSumInsured()),
              Integer.toString(policy.getPremium()),
              Integer.toString(policy.getDiscountedPremium()));
        }
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    // first we must check if there is a loaded profile
    if (this.loadedProfileIndex != -1) {

      // if there is a loaded profile, return and print the correct error message
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(
          this.dataBase.get(this.loadedProfileIndex).getName());
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
    if (!userNameIsUnique(userName)) {
      // if it wasnt unique print the error message and exit the method
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
      return;
    }

    // create the new profile
    Profile newProfile = new Profile(userName, ageInteger);

    // add the new profile into the arraylist to store it
    this.dataBase.add(newProfile);

    // Print the message showing the new profile was successfully created
    MessageCli.PROFILE_CREATED.printMessage(userName, age);
  }

  public void loadProfile(String userName) {

    // Format the username properly
    userName = capitalizeFirstLetter(userName);

    // find if there is a matching name in the databse
    int profileRank = getIndexOfMatchingProfile(userName);

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
    MessageCli.PROFILE_UNLOADED.printMessage(this.dataBase.get(this.loadedProfileIndex).getName());

    // unload the profile
    this.loadedProfileIndex = -1;
  }

  public void deleteProfile(String userName) {

    // format the username
    userName = capitalizeFirstLetter(userName);

    // first find the profile to be deleted
    int indexOfMatchingProfile = getIndexOfMatchingProfile(userName);

    if (indexOfMatchingProfile == -1) {
      // if no profile was found, print the error message and exit
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
      return;
    }
    // next we must ensure the profile to be deleted isnt currently loaded
    if (indexOfMatchingProfile == this.loadedProfileIndex) {
      // if it is loaded, print the error message and exit
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(
          this.dataBase.get(loadedProfileIndex).getName());
      return;
    }

    // if no violations occurred, then we can delete the profile and display the deleted message
    MessageCli.PROFILE_DELETED.printMessage(dataBase.get(indexOfMatchingProfile).getName());
    this.dataBase.remove(indexOfMatchingProfile);

    // if the deleted profile was lower in the database than the loaded profile, move down
    // loadedProfileIndex
    if ((indexOfMatchingProfile < this.loadedProfileIndex) && (this.loadedProfileIndex != -1)) {
      this.loadedProfileIndex--;
    }
  }

  public void createPolicy(PolicyType type, String[] options) {
    // first ensure a profile is loaded
    if (this.loadedProfileIndex == -1) {
      // if no profile is loaded, exit
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    // extract the loaded profile beforehand to avoid code repetition
    Profile loadedProfile = dataBase.get(loadedProfileIndex);

    if (type == PolicyType.LIFE) {
      // next, we must check that if its a life policy, age <= 100 years
      if (loadedProfile.getAge() > 100) {
        // print error message and exit the method
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfile.getName());
        return;
      }

      // now ensure they dont already have a life policy
      if (loadedProfile.hasLifePolicy()) {
        // if they did, print the error message and exit
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfile.getName());
        return;
      }
    }

    // declare a variable for sum insured and policy type to save code
    int sumInsured = Integer.parseInt(options[0]);
    String policyType = "";

    // use a switch statement to cover the cases
    switch (type) {
      case LIFE:

        // declare a new life policy
        LifePolicy lifePolicy = new LifePolicy(sumInsured, loadedProfile);

        // add policy to profile
        loadedProfile.policies.add(lifePolicy);

        // update string policytype
        policyType = "life";

        // update the profile to have a life policy
        loadedProfile.gotLifePolicy();

        break;

      case HOME:

        // convert string "y" / "n" to boolean
        boolean isRental;
        if (options[2].equals("yes")) {
          // then it is a rental
          isRental = true;

        } else {
          // otherwise not a rental
          isRental = false;
        }
        // declare a new home policy
        HomePolicy homePolicy = new HomePolicy(sumInsured, options[1], isRental);

        // add policy to profile
        loadedProfile.policies.add(homePolicy);

        // update string policytype
        policyType = "home";
        break;

      case CAR:

        // convert mechnicalbreakdown string into boolean
        boolean mechanicalBreakdownCovered;

        if (options[3].equals("yes")) {
          // if yes then its covered
          mechanicalBreakdownCovered = true;

        } else {
          // otherwise not covered
          mechanicalBreakdownCovered = false;
        }
        // create new car policy
        CarPolicy carPolicy =
            new CarPolicy(
                sumInsured, loadedProfile, mechanicalBreakdownCovered, options[1], options[2]);

        // then add the policy to profile
        loadedProfile.policies.add(carPolicy);

        // update string policytype
        policyType = "car";
        break;

      default:
        break;
    }
    // inform that a new policy was successfully created
    MessageCli.NEW_POLICY_CREATED.printMessage(policyType, loadedProfile.getName());

    // after policy created, update the discount status
    loadedProfile.updateDiscount();
  }

  // **Helper methods**
  public boolean userNameIsTooShort(String userName) {

    // Check the length (if less than 3 then return true)
    if (userName.length() < 3) {
      return true;
    }

    // else return not true
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

  public boolean userNameIsUnique(String userName) {

    // loop through the database
    for (Profile profile : this.dataBase) {
      // if a matching profile was found, return false
      if (profile.getName().equals(userName)) {
        return false;
      }
    }
    // if no matching name was found, it must be unique
    return true;
  }

  public int getIndexOfMatchingProfile(String userName) {
    for (Profile profile : this.dataBase) {

      // if the profile name matches the username inputted, return the index
      if (profile.getName().equals(userName)) {
        return this.dataBase.indexOf(profile);
      }
    }
    // if the matching name wasnt found, return -1 to represent no profile found
    return -1;
  }
}
