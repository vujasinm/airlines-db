import java.sql.Time;

/**
 * Created by Ivana on 4/9/2016.
 */
public class Flights {
  private String flightNo;
  private int planeId;
  private String originAirport;
  private String destinationAirport;
  private String departureTime;
  private String arrivalTime;

  public Flights() {

  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public int getPlaneId() {
    return planeId;
  }

  public void setPlaneId(int planeId) {
    this.planeId = planeId;
  }

  public String getOriginAirport() {
    return originAirport;
  }

  public void setOriginAirport(String originAirport) {
    this.originAirport = originAirport;
  }

  public String getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(String destinationAirport) {
    this.destinationAirport = destinationAirport;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

}