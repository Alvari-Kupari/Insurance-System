package nz.ac.auckland.se281;

public class Profile {
  private String name;
  private int age;

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
}
