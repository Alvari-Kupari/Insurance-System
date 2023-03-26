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

  public void applyDiscount(int numPolicies) {
    // if 2 policies, reduce premium by 10%
    if (numPolicies == 2) {
      this.basePremium = (int) 0.9 * this.basePremium;

    } else if (numPolicies >= 3) {
      // if 3 or more policies, reduce premium by 20%
      this.basePremium = this.basePremium * (int) 0.8;
    }
  }
}
