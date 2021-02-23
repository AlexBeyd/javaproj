
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.*;

public class Game {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  private static void createAndShowGUI() {
    JFrame f = new JFrame("Swing Paint Demo");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    f.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    f.add(new JTextField("User name"), c);

    c.gridx = 1;
    c.gridy = 0;
    f.add(new JTextField("User score"), c);

    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 2;
    f.add(new GamePanel(), c);

    c.gridx = 0;
    c.gridy = 2;
    f.add(new JButton("Start Game"), c);

    f.pack();
    f.setResizable(false);
    f.setVisible(true);
  }
}
