package nz.ac.auckland.se281;

public abstract class Policy {
  protected int sumInsured;
  protected int basePremium;
  protected int discountedPremium;

  public Policy(int sumInsured) {
    this.sumInsured = sumInsured;
  }

  public int getPremium() {
    return this.basePremium;
  }

  public int getDiscountedPremium() {
    return this.discountedPremium;
  }

  public int getSumInsured() {
    return this.sumInsured;
  }

  public void applyDiscount(int numPolicies) {

    // if 2 policies, reduce premium by 10%
    if (numPolicies == 2) {
      this.discountedPremium = (int) (0.9 * this.basePremium);

    } else if (numPolicies >= 3) {
      // if 3 or more policies, reduce premium by 20%
      this.discountedPremium = (int) (this.basePremium * 0.8);

    } else {
      // otherwise set no discount
      this.discountedPremium = this.basePremium;
    }
  }
}
