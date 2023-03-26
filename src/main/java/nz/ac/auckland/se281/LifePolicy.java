package nz.ac.auckland.se281;

public class LifePolicy extends Policy {

  public LifePolicy(int sumInsured, Profile profile) {
    // add the required fields
    super(sumInsured);
    this.basePremium = (int) 0.01 + profile.getAge() / 10000;
  }
}
