import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Created by Ivana on 4/10/2016.
 */
public class PassengersUI extends JPanel{

  private JTextField passengerIdField = new JTextField(5);
  private JTextField firstNameField = new JTextField(7);
  private JTextField middleNameField = new JTextField(30);
  private JTextField lastNameField = new JTextField(30);
  private JTextField dateOfBirthField = new JTextField(30);
  private JTextField flightField = new JTextField(5);
  private JTextField seatField = new JTextField(5);

  private JButton createButton = new JButton("New");
  private JButton updateButton = new JButton("Update");
  private JButton deleteButton = new JButton("Delete");
  private JButton firstButton = new JButton("First");
  private JButton prevButton = new JButton("<<");
  private JButton nextButton = new JButton(">>");
  private JButton lastButton = new JButton("Last");

  private PassengersBean bean = new PassengersBean();

  public PassengersUI() {
    setBorder(new TitledBorder
            (new EtchedBorder()," "));
    setLayout(new BorderLayout(50, 50));
    add(initFields(), BorderLayout.NORTH);
    add(initButtonsFirstRow(), BorderLayout.CENTER);
    add(initButtonsSecondRow(), BorderLayout.SOUTH);
    setFieldData(bean.moveFirst());
  }

  private JPanel initButtonsFirstRow() {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
    panel.add(createButton);
    panel.add(updateButton);
    panel.add(deleteButton);

    createButton.addActionListener(new ButtonHandler());
    updateButton.addActionListener(new ButtonHandler());
    deleteButton.addActionListener(new ButtonHandler());

    return panel;
  }

  private JPanel initButtonsSecondRow() {
    JPanel panel = new JPanel();
    panel.add(firstButton);
    panel.add(prevButton);
    panel.add(nextButton);
    panel.add(lastButton);
    firstButton.addActionListener(new ButtonHandler());
    prevButton.addActionListener(new ButtonHandler());
    nextButton.addActionListener(new ButtonHandler());
    lastButton.addActionListener(new ButtonHandler());

    return panel;
  }

  private JPanel initFields() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout());
    panel.add(new JLabel("PassengerId"), "align label");
    panel.add(passengerIdField, "wrap");
    panel.add(new JLabel("First Name"), "align label");
    panel.add(firstNameField, "wrap");
    panel.add(new JLabel("Middle Name"), "align label");
    panel.add(middleNameField, "wrap");
    panel.add(new JLabel("Last Name"), "align label");
    panel.add(lastNameField, "wrap");
    panel.add(new JLabel("DOB  (YYYY-MM-DD)"), "align label");
    panel.add(dateOfBirthField, "wrap");
    panel.add(new JLabel("Flight"), "align label");
    panel.add(flightField, "wrap");
    panel.add(new JLabel("Seat"), "align label");
    panel.add(seatField, "wrap");
    return panel;
  }

  private Passenger getFieldData() {
    Passenger p = new Passenger();
    p.setPassengerId(Integer.parseInt(passengerIdField.getText()));
    p.setFirstName(firstNameField.getText());
    p.setMiddleName(middleNameField.getText());
    p.setLastName(lastNameField.getText());
    p.setDateOfBirth(Date.valueOf(dateOfBirthField.getText()));
    p.setFlight(flightField.getText());
    p.setSeat(seatField.getText());
    return p;
  }

  private void setFieldData(Passenger p) {
    passengerIdField.setText(Integer.toString(p.getPassengerId()));
    firstNameField.setText(p.getFirstName());
    middleNameField.setText(p.getMiddleName());
    lastNameField.setText(p.getLastName());
    dateOfBirthField.setText(p.getDateOfBirth().toString());
    flightField.setText(p.getFlight());
    seatField.setText(p.getSeat());
  }

  private boolean isEmptyFieldData() {
    return (passengerIdField.getText().trim().isEmpty()
            && firstNameField.getText().trim().isEmpty()
            && middleNameField.getText().trim().isEmpty()
            && lastNameField.getText().trim().isEmpty()
            && dateOfBirthField.getText().trim().isEmpty()
            && flightField.getText().trim().isEmpty())
            && seatField.getText().trim().isEmpty();
  }

  private class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Passenger p = getFieldData();
      switch (e.getActionCommand()) {
        case "Save":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot create an empty record");
            return;
          }
          if (bean.create(p) != null)
            JOptionPane.showMessageDialog(null,
                    "New passenger created successfully.");
          createButton.setText("New");
          break;
        case "New":
          p.setPassengerId(new Random()
                  .nextInt(Integer.MAX_VALUE) + 1);
          p.setFirstName("");
          p.setMiddleName("");
          p.setLastName("");
          p.setDateOfBirth(new Date(0));
          p.setFlight("");
          p.setSeat("");
          setFieldData(p);
          createButton.setText("Save");
          break;
        case "Update":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot update an empty record");
            return;
          }
          if (bean.update(p) != null)
            JOptionPane.showMessageDialog(
                    null,"Passenger: " + p.getPassengerId()
                            + " is updated successfully");
          break;
        case "Delete":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot delete an empty record");
            return;
          }
          p = bean.getCurrent();
          bean.delete();
          JOptionPane.showMessageDialog(
                  null,"Passenger:"
                          + p.getPassengerId()
                          + " is deleted successfully");
          break;
        case "First":
          setFieldData(bean.moveFirst()); break;
        case "<<":
          setFieldData(bean.movePrevious()); break;
        case ">>":
          setFieldData(bean.moveNext()); break;
        case "Last":
          setFieldData(bean.moveLast()); break;
        default:
          JOptionPane.showMessageDialog(null,
                  "invalid command");
      }
    }
  }
}
