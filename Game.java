import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
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

    JFrame f = new JFrame("TheSaggyChickGame");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    f.setIconImage((new ImageIcon("download.jpg")).getImage());

    f.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    c.gridx = 0;
    c.gridy = 0;
    f.add(new UserInfo(), c);

    c.gridx = 0;
    c.gridy = 1;
    GamePanel gamePanel = new GamePanel();
    f.add(gamePanel, c);

    c.gridx = 0;
    c.gridy = 2;
    f.add(new GameControls(f, gamePanel), c);

    f.pack();
    f.setResizable(false);
    f.setVisible(true);
  }
}