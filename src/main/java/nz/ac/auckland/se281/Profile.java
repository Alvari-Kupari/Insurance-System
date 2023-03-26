package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String name;
  private int age;
  private int totalPremium;
  ArrayList<Policy> Policies = new ArrayList<>();

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
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
    // return the total running premium
    return totalPremium;
  }
}
