package nz.ac.auckland.se281;

public class Profile {
  private String name;
  private int age;
  private boolean isLoaded;

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
    this.isLoaded = false;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public boolean isLoaded() {
    return this.isLoaded;
  }

  public void load() {
    this.isLoaded = true;
  }

  public void unLoad() {
    this.isLoaded = false;
  }
}
