package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String name;
  private int age;
  ArrayList<Policy> Policies = new ArrayList<>();
  private boolean hasLifePolicy;

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
    this.hasLifePolicy = false;
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
      totalPremium += this.Policies.get(i).getDiscountedPremium();
    }
    // return the total running premium.
    return totalPremium;
  }

  public boolean hasLifePolicy() {
    // returns the number of life policies
    return this.hasLifePolicy;
  }

  public void gotLifePolicy() {
    this.hasLifePolicy = true;
  }

  public void updateDiscount() {
    // initialise a variable representing the number of policies
    int numPolicies = this.Policies.size();

    // loop through the arraylist of policies and apply the discount for each
    for (int i = 0; i < numPolicies; i++) {
      this.Policies.get(i).applyDiscount(numPolicies);
    }
  }
}
