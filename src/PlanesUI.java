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

public class PlanesUI extends JPanel {

  private JTextField planeIdField = new JTextField(7);
  private JTextField brandField = new JTextField(30);
  private JTextField capacityField = new JTextField(6);
  private JTextField typeField = new JTextField(30);


  private JButton createButton = new JButton("New");
  private JButton updateButton = new JButton("Update");
  private JButton deleteButton = new JButton("Delete");
  private JButton firstButton = new JButton("First");
  private JButton prevButton = new JButton("<<");
  private JButton nextButton = new JButton(">>");
  private JButton lastButton = new JButton("Last");

  private PlanesBean bean = new PlanesBean();

  public PlanesUI() {
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
    panel.add(new JLabel("PlaneId"), "align label");
    panel.add(planeIdField, "wrap");
    panel.add(new JLabel("Plane Brand"), "align label");
    panel.add(brandField, "wrap");
    panel.add(new JLabel("Plane Capacity"), "align label");
    panel.add(capacityField, "wrap");
    panel.add(new JLabel("Plane Type"), "align label");
    panel.add(typeField, "wrap");
    return panel;
  }

  private Plane getFieldData() {
    Plane p = new Plane();
    p.setPlaneId(Integer.parseInt(planeIdField.getText()));
    p.setBrand(brandField.getText());
    p.setCapacity(Integer.parseInt(capacityField.getText()));
    p.setType(Integer.parseInt(typeField.getText()));
    return p;
  }

  private void setFieldData(Plane p) {
    planeIdField.setText(Integer.toString(p.getPlaneId()));
    brandField.setText(p.getBrand());
    capacityField.setText(Integer.toString(p.getCapacity()));
    typeField.setText(Integer.toString(p.getType()));

  }

  private boolean isEmptyFieldData() {
    return (planeIdField.getText().trim().isEmpty()
            && brandField.getText().trim().isEmpty()
            && capacityField.getText().trim().isEmpty()
            && typeField.getText().trim().isEmpty());
  }

  private class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Plane p = getFieldData();
      switch (e.getActionCommand()) {
        case "Save":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot create an empty record");
            return;
          }
          if (bean.create(p) != null)
            JOptionPane.showMessageDialog(null,
                    "New plane created successfully.");
          createButton.setText("New");
          break;
        case "New":
          p.setPlaneId(0);
          p.setBrand("");
          p.setCapacity(0);
          p.setType(0);
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
                    null,"Plane: " + p.getPlaneId()
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
                  null,"Airport:"
                          + p.getPlaneId()
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