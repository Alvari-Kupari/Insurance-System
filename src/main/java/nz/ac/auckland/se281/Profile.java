package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String name;
  private int age;
  private boolean discounted;
  ArrayList<Policy> Policies = new ArrayList<>();
  int numLifePolicies;

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
    this.discounted = false;
    this.numLifePolicies = 0;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public int getTotalPremium() {
    // intialise premium running total
    int totalPremium = 0;
    for (int i = 0; i < this.Policies.size(); i++) {
      // increase the total premium for each policy
      totalPremium += this.Policies.get(i).getPremium();
    }
    // return the total running premium.
    return totalPremium;
  }

  public boolean isDiscounted() {
    // return whether this profile has a discount applied
    return this.discounted;
  }

  public int getNumOfLifePolicies() {
    // returns the number of life policies
    return this.numLifePolicies;
  }
}
