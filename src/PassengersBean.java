import com.sun.rowset.JdbcRowSetImpl;

import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;

/**
 * Created by Ivana on 4/10/2016.
 */
public class PassengersBean {
  static final String JDBC_DRIVER =
          "com.mysql.jdbc.Driver";
  static final String DB_URL =
          "jdbc:mysql://localhost:3306/final_project";
  static final String DB_USER = "root";
  static final String DB_PASS = "root";
  private JdbcRowSet rowSet = null;
  public PassengersBean() {
    try {
      Class.forName(JDBC_DRIVER);
      rowSet = new JdbcRowSetImpl();
      rowSet.setUrl(DB_URL);
      rowSet.setUsername(DB_USER);
      rowSet.setPassword(DB_PASS);
      rowSet.setCommand("SELECT * FROM Passenger");
      rowSet.execute();
    }
    catch (SQLException | ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
  }
  public Passenger create(Passenger p) {
    try {
      rowSet.moveToInsertRow();
      rowSet.updateInt("PassengerID", p.getPassengerId());
      rowSet.updateString("FirstName", p.getFirstName());
      rowSet.updateString("MiddleName", p.getMiddleName());
      rowSet.updateString("LastName", p.getLastName());
      Date newDate = new Date(p.getDateOfBirth().getTime());
      rowSet.updateDate("DOB", newDate);
      rowSet.updateString("Seat", p.getSeat());
      rowSet.updateString("Flight", p.getFlight());
      rowSet.insertRow();
      rowSet.moveToCurrentRow();
    } catch (SQLException ex) {
      try {
        rowSet.rollback();
        p = null;
      } catch (SQLException e) {

      }
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Passenger update(Passenger p) {
    try {
      rowSet.updateInt("passengerId", p.getPassengerId());
      rowSet.updateString("firstName", p.getFirstName());
      rowSet.updateString("middleName", p.getMiddleName());
      rowSet.updateString("lastName", p.getLastName());
      Date newDate = new Date(p.getDateOfBirth().getTime());
      rowSet.updateDate("DOB", newDate);
      rowSet.updateString("seat", p.getSeat());
      rowSet.updateString("Flight", p.getFlight());
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
    return p;
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

  public Passenger moveFirst() {
    Passenger p = new Passenger();
    try {
      rowSet.first();
      p.setPassengerId(rowSet.getInt("PassengerID"));
      p.setFirstName(rowSet.getString("FirstName"));
      p.setMiddleName(rowSet.getString("MiddleName"));
      p.setLastName(rowSet.getString("LastName"));
      p.setDateOfBirth(rowSet.getDate("DOB"));
      p.setFlight(rowSet.getString("Flight"));
      p.setSeat(rowSet.getString("Seat"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Passenger moveLast() {
    Passenger p = new Passenger();
    try {
      rowSet.last();
      p.setPassengerId(rowSet.getInt("PassengerID"));
      p.setFirstName(rowSet.getString("FirstName"));
      p.setMiddleName(rowSet.getString("MiddleName"));
      p.setLastName(rowSet.getString("LastName"));
      p.setDateOfBirth(rowSet.getDate("DOB"));
      p.setFlight(rowSet.getString("Flight"));
      p.setSeat(rowSet.getString("Seat"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Passenger moveNext() {
    Passenger p = new Passenger();
    try {
      if (rowSet.next() == false) {
        rowSet.previous();
      }
      p.setPassengerId(rowSet.getInt("PassengerID"));
      p.setFirstName(rowSet.getString("FirstName"));
      p.setMiddleName(rowSet.getString("MiddleName"));
      p.setLastName(rowSet.getString("LastName"));
      p.setDateOfBirth(rowSet.getDate("DOB"));
      p.setFlight(rowSet.getString("Flight"));
      p.setSeat(rowSet.getString("Seat"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Passenger movePrevious() {
    Passenger p = new Passenger();
    try {
      if (rowSet.previous() == false)
        rowSet.next();
      p.setPassengerId(rowSet.getInt("PassengerID"));
      p.setFirstName(rowSet.getString("FirstName"));
      p.setMiddleName(rowSet.getString("MiddleName"));
      p.setLastName(rowSet.getString("LastName"));
      p.setDateOfBirth(rowSet.getDate("DOB"));
      p.setFlight(rowSet.getString("Flight"));
      p.setSeat(rowSet.getString("Seat"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Passenger getCurrent() {
    Passenger p = new Passenger();
    try {
      rowSet.moveToCurrentRow();
      p.setPassengerId(rowSet.getInt("PassengerId"));
      p.setFirstName(rowSet.getString("FirstName"));
      p.setMiddleName(rowSet.getString("MiddleName"));
      p.setLastName(rowSet.getString("LastName"));
      p.setDateOfBirth(rowSet.getDate("DOB"));
      p.setFlight(rowSet.getString("Flight"));
      p.setSeat(rowSet.getString("Seat"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }
}
