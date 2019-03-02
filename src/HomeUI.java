import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Created by Ivana on 4/10/2016.
 */
public class HomeUI extends JPanel {

  public HomeUI() {
    JTabbedPane pane = new JTabbedPane();
    JComponent tab1 = makeTextPanel("Airports");
    pane.addTab("Airports",new AirportsUI());
    pane.setMnemonicAt(0, KeyEvent.VK_1);

    JComponent tab2 = makeTextPanel("Flights");
    pane.addTab("Flights",new FlightsUI());
    pane.setMnemonicAt(1, KeyEvent.VK_2);

    JComponent tab3 = makeTextPanel("Passengers");
    pane.addTab("Passengers",new PassengersUI());
    pane.setMnemonicAt(2, KeyEvent.VK_3);

    JComponent tab4 = makeTextPanel("Planes");
    pane.addTab("Planes",new PlanesUI());
    pane.setMnemonicAt(3, KeyEvent.VK_4);

    add(pane);

  }


  protected JComponent makeTextPanel(String text) {
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel(text);
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new GridLayout(1, 1));
    panel.add(filler);
    return panel;
  }
}
