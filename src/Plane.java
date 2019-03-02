/**
 * Created by Ivana on 4/9/2016.
 */
public class Plane {
  private int PlaneId;
  private String brand;
  private int type;
  private int capacity;

  public Plane() {
  }

  public int getPlaneId() {
    return PlaneId;
  }

  public void setPlaneId(int planeId) {
    PlaneId = planeId;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
