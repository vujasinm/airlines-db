import com.sun.org.apache.xpath.internal.operations.String;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class FlightsUI extends JPanel {

  private JTextField flightNoField = new JTextField(7);
  private JTextField origAirportField = new JTextField(30);
  private JTextField destAirportField = new JTextField(30);
  private JTextField depTimeField = new JTextField(30);
  private JTextField arrTimeField = new JTextField(30);
  private JTextField planeIdField = new JTextField(30);

  private JButton createButton = new JButton("New");
  private JButton updateButton = new JButton("Update");
  private JButton deleteButton = new JButton("Delete");
  private JButton firstButton = new JButton("First");
  private JButton prevButton = new JButton("<<");
  private JButton nextButton = new JButton(">>");
  private JButton lastButton = new JButton("Last");

  private FlightsBean bean = new FlightsBean();

  public FlightsUI() {
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
    panel.add(new JLabel("FlightNo"), "align label");
    panel.add(flightNoField, "wrap");
    panel.add(new JLabel("Origin Airport"), "align label");
    panel.add(origAirportField, "wrap");
    //aNameField.setEnabled(false);
    panel.add(new JLabel("Destination Airport"), "align label");
    panel.add(destAirportField, "wrap");

    panel.add(new JLabel("Departure Time (YYYY-MM-DD HH:MM)"), "align label");
    panel.add(depTimeField, "wrap");
    panel.add(new JLabel("Arrival time (YYYY-MM-DD HH:MM)"), "align label");
    panel.add(arrTimeField, "wrap");
    panel.add(new JLabel("PlaneId"), "align label");
    panel.add(planeIdField, "wrap");
    return panel;
  }

  private Flights getFieldData() {
    Flights f = new Flights();
    f.setPlaneId(Integer.parseInt(planeIdField.getText()));
    f.setOriginAirport(origAirportField.getText());
    f.setFlightNo(flightNoField.getText());
    f.setDepartureTime(depTimeField.getText());
    f.setArrivalTime(arrTimeField.getText());
    f.setDestinationAirport(destAirportField.getText());
    return f;
  }

  private void setFieldData(Flights f) {
    flightNoField.setText(f.getFlightNo());
    origAirportField.setText(f.getOriginAirport());
    destAirportField.setText(f.getDestinationAirport());
    depTimeField.setText(f.getDepartureTime().toString());
    arrTimeField.setText(f.getArrivalTime().toString());
    planeIdField.setText(Integer.toString(f.getPlaneId()));

  }

  private boolean isEmptyFieldData() {
    return (flightNoField.getText().trim().isEmpty()
            && origAirportField.getText().trim().isEmpty()
            && destAirportField.getText().trim().isEmpty()
            && depTimeField.getText().trim().isEmpty()
            && arrTimeField.getText().trim().isEmpty()
            && planeIdField.getText().trim().isEmpty());
  }

  private class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Flights f = getFieldData();
      switch (e.getActionCommand()) {
        case "Save":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot create an empty record");
            return;
          }
          if (bean.create(f) != null)
            JOptionPane.showMessageDialog(null,
                    "New flight created successfully.");
          createButton.setText("New");
          break;
        case "New":
          f.setArrivalTime("YYYY-MM-DD HH-MM");
          f.setDepartureTime("YYYY-MM-DD HH-MM");
          f.setDestinationAirport("");
          f.setFlightNo("");
          f.setOriginAirport("");
          f.setPlaneId(new Random()
                  .nextInt(Integer.MAX_VALUE) + 1);
          setFieldData(f);
          createButton.setText("Save");
          break;
        case "Update":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot update an empty record");
            return;
          }
          if (bean.update(f) != null)
            JOptionPane.showMessageDialog(
                    null,"Flight: " + f.getFlightNo()
                            + " is updated successfully");
          break;
        case "Delete":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot delete an empty record");
            return;
          }
          f = bean.getCurrent();
          bean.delete();
          JOptionPane.showMessageDialog(
                  null,"Flight:"
                          + f.getFlightNo()
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
