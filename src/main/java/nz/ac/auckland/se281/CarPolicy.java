package nz.ac.auckland.se281;

public class CarPolicy extends Policy {
  // initialise fields
  private String makeAndModel;
  private String licensePlate;
  private boolean mechanicalBreakdownCovered;

  public CarPolicy(
      int sumInsured,
      Profile profile,
      boolean mechanicalBreakdownCovered,
      String makeAndModel,
      String licensePlate) {
    // add the required fields
    super(sumInsured);
    this.mechanicalBreakdownCovered = mechanicalBreakdownCovered;
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;

    if (profile.getAge() < 25) {
      // if the client is under 25, the premium is 15% of sum insured
      this.basePremium = (int) 0.15 * sumInsured;
    } else {
      // otherwise it is 10%
      this.basePremium = (int) 0.10 * sumInsured;
    }
    // if mechanical breakdown is included, add 80
    if (mechanicalBreakdownCovered) {
      this.basePremium += 80;
    }
  }
}
