import com.sun.java.swing.plaf.motif.MotifTextUI;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * Created by Ivana on 4/10/2016.
 */
public class AppMain {


  public static void main(String[] args) {

    BufferedImage image = null;
    try{
      image = ImageIO.read(new File("plane.png"));
    } catch(Exception e){e.printStackTrace();}
    try
    {
      UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
    }
    catch(Exception e){
    }
    JFrame f=new JFrame();
    f.setIconImage(image);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(new HomeUI(), BorderLayout.CENTER);
    Color backColor = new Color(179, 217, 255);
      f.setSize(700, 550);
      f.setVisible(true);
    }

}
