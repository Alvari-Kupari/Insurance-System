package nz.ac.auckland.se281;

public class HomePolicy extends Policy {
  private String address;
  private boolean isRental;

  public HomePolicy(int sumInsured, String address, boolean isRental) {
    super(sumInsured);
    this.address = address;
    this.isRental = isRental;

    // if the home is rented, base premium is 2% of sum insured
    if (isRental) {
      this.basePremium = (int) (0.02 * sumInsured);

    } else {
      // base premium is 1% of sum insured if house isnt rented
      this.basePremium = (int) (0.01 * sumInsured);
    }
  }

  public String getAddress() {
    return this.address;
  }
}
