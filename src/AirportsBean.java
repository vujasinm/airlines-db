import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;

/**
 * Created by Ivana on 4/10/2016.
 */
public class AirportsBean {
  static final String JDBC_DRIVER =
          "com.mysql.jdbc.Driver";
  static final String DB_URL =
          "jdbc:mysql://localhost:3306/final_project";
  static final String DB_USER = "root";
  static final String DB_PASS = "root";
  private JdbcRowSet rowSet = null;
  public AirportsBean() {
    try {
      Class.forName(JDBC_DRIVER);
      rowSet = new JdbcRowSetImpl();
      rowSet.setUrl(DB_URL);
      rowSet.setUsername(DB_USER);
      rowSet.setPassword(DB_PASS);
      rowSet.setCommand("SELECT * FROM Airport");
      rowSet.execute();
    }
      catch (SQLException | ClassNotFoundException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());

        ex.printStackTrace();
      }
    }
  public Airport create(Airport a) {
    try {
      rowSet.moveToInsertRow();
      rowSet.updateString("airportName", a.getAirportName());
      rowSet.updateString("city", a.getCity());
      rowSet.updateString("country", a.getCountry());
      rowSet.insertRow();
      rowSet.moveToCurrentRow();
    } catch (SQLException ex) {
      try {
        rowSet.rollback();
        a = null;
      } catch (SQLException e) {

      }
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return a;
  }

  public Airport update(Airport a) {
    try {
      rowSet.updateString("AirportName", a.getAirportName());
      rowSet.updateString("City", a.getCity());
      rowSet.updateString("Country", a.getCountry());
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
    return a;
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

  public Airport moveFirst() {
    Airport a = new Airport();
    try {
      rowSet.first();
      a.setAirportName(rowSet.getString("AirportName"));
      a.setCity(rowSet.getString("City"));
      a.setCountry(rowSet.getString("Country"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return a;
  }

  public Airport moveLast() {
    Airport a = new Airport();
    try {
      rowSet.last();
      a.setAirportName(rowSet.getString("AirportName"));
      a.setCity(rowSet.getString("City"));
      a.setCountry(rowSet.getString("Country"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return a;
  }

  public Airport moveNext() {
    Airport a = new Airport();
    try {
      if (rowSet.next() == false) {
        rowSet.previous();
      }
        a.setAirportName(rowSet.getString("AirportName"));
        a.setCity(rowSet.getString("City"));
        a.setCountry(rowSet.getString("Country"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return a;
  }

  public Airport movePrevious() {
    Airport a = new Airport();
    try {
      if (rowSet.previous() == false)
        rowSet.next();
      a.setAirportName(rowSet.getString("AirportName"));
      a.setCity(rowSet.getString("City"));
      a.setCountry(rowSet.getString("Country"));;

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());
      ex.printStackTrace();

    }
    return a;
  }

  public Airport getCurrent() {
    Airport a = new Airport();
    try {
      rowSet.moveToCurrentRow();
      a.setAirportName(rowSet.getString("AirportName"));
      a.setCity(rowSet.getString("City"));
      a.setCountry(rowSet.getString("Country"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());
      ex.printStackTrace();

    }
    return a;
  }
}

