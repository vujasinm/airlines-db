import com.sun.rowset.JdbcRowSetImpl;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.swing.*;

/**
 * Created by Ivana on 4/10/2016.
 */
public class PlanesBean {
  static final String JDBC_DRIVER =
          "com.mysql.jdbc.Driver";
  static final String DB_URL =
          "jdbc:mysql://localhost:3306/final_project";
  static final String DB_USER = "root";
  static final String DB_PASS = "root";
  private JdbcRowSet rowSet = null;
  public PlanesBean() {
    try {
      Class.forName(JDBC_DRIVER);
      rowSet = new JdbcRowSetImpl();
      rowSet.setUrl(DB_URL);
      rowSet.setUsername(DB_USER);
      rowSet.setPassword(DB_PASS);
      rowSet.setCommand("SELECT * FROM Plane");
      rowSet.execute();
    }
    catch (SQLException | ClassNotFoundException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
 }
  public Plane create(Plane p) {
    try {
      rowSet.moveToInsertRow();
      rowSet.updateInt("PlaneID", p.getPlaneId());
      rowSet.updateString("Brand", p.getBrand());
      rowSet.updateInt("Capacity", p.getCapacity());
      rowSet.updateInt("Type", p.getType());
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

  public Plane update(Plane p) {
    try {
      rowSet.updateInt("PlaneID", p.getPlaneId());
      rowSet.updateString("Brand", p.getBrand());
      rowSet.updateInt("Capacity", p.getCapacity());
      rowSet.updateInt("Type", p.getType());
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

  public Plane moveFirst() {
    Plane p = new Plane();
    try {
      rowSet.first();
      p.setPlaneId(rowSet.getInt("PlaneID"));
      p.setBrand(rowSet.getString("Brand"));
      p.setCapacity(rowSet.getInt("Capacity"));
      p.setType(rowSet.getInt("Type"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Plane moveLast() {
    Plane p = new Plane();
    try {
      rowSet.last();
      p.setPlaneId(rowSet.getInt("PlaneID"));
      p.setBrand(rowSet.getString("Brand"));
      p.setCapacity(rowSet.getInt("Capacity"));
      p.setType(rowSet.getInt("Type"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Plane moveNext() {
    Plane p = new Plane();
    try {
      if (rowSet.next() == false) {
        rowSet.previous();
      }
      p.setPlaneId(rowSet.getInt("PlaneID"));
      p.setBrand(rowSet.getString("Brand"));
      p.setCapacity(rowSet.getInt("Capacity"));
      p.setType(rowSet.getInt("Type"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Plane movePrevious() {
    Plane p = new Plane();
    try {
      if (rowSet.previous() == false)
        rowSet.next();
      p.setPlaneId(rowSet.getInt("PlaneID"));
      p.setBrand(rowSet.getString("Brand"));
      p.setCapacity(rowSet.getInt("Capacity"));
      p.setType(rowSet.getInt("Type"));

    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }

  public Plane getCurrent() {
    Plane p = new Plane();
    try {
      rowSet.moveToCurrentRow();
      p.setPlaneId(rowSet.getInt("PlaneID"));
      p.setBrand(rowSet.getString("Brand"));
      p.setCapacity(rowSet.getInt("Capacity"));
      p.setType(rowSet.getInt("Type"));
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());

      ex.printStackTrace();
    }
    return p;
  }
}
