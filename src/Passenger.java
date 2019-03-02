import java.util.Date;

/**
 * Created by Ivana on 4/9/2016.
 */
public class Passenger {
  private String firstName;
  private String middleName;
  private String lastName;
  private Date dateOfBirth;
  private String flight;
  private String seat;
  private int passengerId;

  public Passenger() {

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getFlight() {
    return flight;
  }

  public void setFlight(String flight) {
    this.flight = flight;
  }

  public String getSeat() {
    return seat;
  }

  public void setSeat(String seat) {
    this.seat = seat;
  }

  public int getPassengerId() {
    return passengerId;
  }

  public void setPassengerId(int passangerId) {
    this.passengerId = passangerId;
  }
}
