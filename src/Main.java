import javax.swing.*;

/**
 * Created by Ivana on 4/10/2016.
 */
public class Main {
  public static void main(String[] args) {
    try {
      // Set System L&F
      UIManager.setLookAndFeel(
              UIManager.getSystemLookAndFeelClassName());
    }
    catch (UnsupportedLookAndFeelException e) {
      // handle exception
    }
    catch (ClassNotFoundException e) {
      // handle exception
    }
    catch (InstantiationException e) {
      // handle exception
    }
    catch (IllegalAccessException e) {
      // handle exception
    }

    new AirportsUI(); //Create and show the GUI.
  }
}
