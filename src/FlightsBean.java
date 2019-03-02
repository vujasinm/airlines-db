import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;

/**
 * Created by Ivana on 4/10/2016.
 */
public class FlightsBean {
  static final String JDBC_DRIVER =
          "com.mysql.jdbc.Driver";
  static final String DB_URL =
          "jdbc:mysql://localhost:3306/final_project";
  static final String DB_USER = "root";
  static final String DB_PASS = "root";
  private JdbcRowSet rowSet = null;
  public FlightsBean() {
    try {
      Class.forName(JDBC_DRIVER);
      rowSet = new JdbcRowSetImpl();
      rowSet.setUrl(DB_URL);
      rowSet.setUsername(DB_USER);
      rowSet.setPassword(DB_PASS);
      rowSet.setCommand("SELECT * FROM Flights");
      rowSet.execute();
    }
    catch (SQLException | ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
  }
  public Flights create(Flights f) {
    try {
      rowSet.moveToInsertRow();
      rowSet.updateString("DestinationAirport", f.getDestinationAirport());
      rowSet.updateString("FlightNo", f.getFlightNo());
      rowSet.updateString("OriginAirport", f.getOriginAirport());
      rowSet.updateString("ArrivalTime", f.getArrivalTime());
      rowSet.updateString("DepartureTime", f.getDepartureTime());
      rowSet.updateInt("PlaneID", f.getPlaneId());
      rowSet.insertRow();
      rowSet.moveToCurrentRow();
    } catch (SQLException ex) {
      try {
        rowSet.rollback();
        f = null;
      } catch (SQLException e) {

      }
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }

  public Flights update(Flights f) {
    try {
      rowSet.updateString("DestinationAirport", f.getDestinationAirport());
      rowSet.updateString("FlightNo", f.getFlightNo());
      rowSet.updateString("OriginAirport", f.getOriginAirport());
      rowSet.updateString("ArrivalTime", f.getArrivalTime());
      rowSet.updateString("DepartureTime", f.getDepartureTime());
      rowSet.updateInt("PlaneID", f.getPlaneId());
      rowSet.updateRow();
      rowSet.moveToCurrentRow();
    } catch (SQLException ex) {
      try {
        rowSet.rollback();
      } catch (SQLException e) {

      }
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }

  public void delete() {
    try {
      rowSet.moveToCurrentRow();
      rowSet.deleteRow();
    } catch (SQLException ex) {
      try {
        rowSet.rollback();
      } catch (SQLException e) { }
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }

  }

  public Flights moveFirst() {
    Flights f = new Flights();
    try {
      rowSet.first();
      f.setDestinationAirport(rowSet.getString("DestinationAirport"));
      f.setArrivalTime(rowSet.getString("ArrivalTime"));
      f.setDepartureTime(rowSet.getString("DepartureTime"));
      f.setFlightNo(rowSet.getString("FlightNo"));
      f.setOriginAirport(rowSet.getString("OriginAirport"));
      f.setPlaneId(rowSet.getInt("PlaneID"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }

  public Flights moveLast() {
    Flights f = new Flights();
    try {
      rowSet.last();
      f.setDestinationAirport(rowSet.getString("DestinationAirport"));
      f.setArrivalTime(rowSet.getString("ArrivalTime"));
      f.setDepartureTime(rowSet.getString("DepartureTime"));
      f.setFlightNo(rowSet.getString("FlightNo"));
      f.setOriginAirport(rowSet.getString("OriginAirport"));
      f.setPlaneId(rowSet.getInt("PlaneID"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }

  public Flights moveNext() {
    Flights f = new Flights();
    try {
      if (rowSet.next() == false) {
        rowSet.previous();
      }
      f.setDestinationAirport(rowSet.getString("DestinationAirport"));
      f.setArrivalTime(rowSet.getString("ArrivalTime"));
      f.setDepartureTime(rowSet.getString("DepartureTime"));
      f.setFlightNo(rowSet.getString("FlightNo"));
      f.setOriginAirport(rowSet.getString("OriginAirport"));
      f.setPlaneId(rowSet.getInt("PlaneID"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }

  public Flights movePrevious() {
    Flights f = new Flights();
    try {
      if (rowSet.previous() == false)
        rowSet.next();
      f.setDestinationAirport(rowSet.getString("DestinationAirport"));
      f.setArrivalTime(rowSet.getString("ArrivalTime"));
      f.setDepartureTime(rowSet.getString("DepartureTime"));
      f.setFlightNo(rowSet.getString("FlightNo"));
      f.setOriginAirport(rowSet.getString("OriginAirport"));
      f.setPlaneId(rowSet.getInt("PlaneID"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }

  public Flights getCurrent() {
    Flights f = new Flights();
    try {
      rowSet.moveToCurrentRow();
      f.setDestinationAirport(rowSet.getString("DestinationAirport"));
      f.setArrivalTime(rowSet.getString("ArrivalTime"));
      f.setDepartureTime(rowSet.getString("DepartureTime"));
      f.setFlightNo(rowSet.getString("FlightNo"));
      f.setOriginAirport(rowSet.getString("OriginAirport"));
      f.setPlaneId(rowSet.getInt("PlaneID"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return f;
  }
}