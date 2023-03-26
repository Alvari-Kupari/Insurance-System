package nz.ac.auckland.se281;

public abstract class Policy {
  protected int sumInsured;
  protected int basePremium;

  public Policy(int sumInsured) {
    this.sumInsured = sumInsured;
  }

  public void discount(int numPolicies) {
    // if there are 2 polciies, reduce basePremium by 10%
  }

  public int getPremium() {
    return this.basePremium;
  }
}
