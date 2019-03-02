import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class AirportsUI extends JPanel {

  private JTextField aNameField = new JTextField(30);
  private JTextField cityField = new JTextField(30);
  private JTextField countryField = new JTextField(30);


  private JButton createButton = new JButton("New");
  private JButton updateButton = new JButton("Update");
  private JButton deleteButton = new JButton("Delete");
  private JButton firstButton = new JButton("First");
  private JButton prevButton = new JButton("<<");
  private JButton nextButton = new JButton(">>");
  private JButton lastButton = new JButton("Last");

  private AirportsBean bean = new AirportsBean();

  public AirportsUI() {
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
    panel.add(new JLabel("Airport Name"), "align label");
    panel.add(aNameField, "wrap");
    panel.add(new JLabel("City"), "align label");
    panel.add(cityField, "wrap");
    panel.add(new JLabel("Country"), "align label");
    panel.add(countryField, "wrap");
    return panel;
  }

  private Airport getFieldData() {
    Airport a = new Airport();
    a.setAirportName(aNameField.getText());
    a.setCity(cityField.getText());
    a.setCountry(countryField.getText());
    return a;
  }

  private void setFieldData(Airport a) {
    aNameField.setText(String.valueOf(a.getAirportName()));
    cityField.setText((a.getCity()));
    countryField.setText(a.getCountry());

  }

  private boolean isEmptyFieldData() {
    return (aNameField.getText().trim().isEmpty()
            && cityField.getText().trim().isEmpty()
            && countryField.getText().trim().isEmpty());
  }

  private class ButtonHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Airport a = getFieldData();
      switch (e.getActionCommand()) {
        case "Save":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot create an empty record");
            return;
          }
          if (bean.create(a) != null)
            JOptionPane.showMessageDialog(null,
                    "New airport created successfully.");
          createButton.setText("New");
          break;
        case "New":
          a.setAirportName("");
          a.setCity("");
          a.setCountry("");
          setFieldData(a);
          createButton.setText("Save");
          break;
        case "Update":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot update an empty record");
            return;
          }
          if (bean.update(a) != null)
            JOptionPane.showMessageDialog(
                    null,"Airport:" + String.valueOf(a.getAirportName()
                            + " is updated successfully"));
          break;
        case "Delete":
          if (isEmptyFieldData()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot delete an empty record");
            return;
          }
          a = bean.getCurrent();
          bean.delete();
          JOptionPane.showMessageDialog(
                  null,"Airport:"
                          + String.valueOf(a.getAirportName()
                          + " is deleted successfully"));
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
