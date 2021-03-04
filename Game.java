import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

    // display user info panel
    c.gridx = 0;
    c.gridy = 0;
    UserInfo userInfo = new UserInfo();
    f.add(userInfo, c);

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

    // show start game modal dialog box
    StartGameDialog startGameDialog = new StartGameDialog(f);

    switch (startGameDialog.GetAnswer()) {
      case 0: //cancel
        break;
      case 1: //play with player name
        userInfo.SetUserName(startGameDialog.GetPlayerName());
        userInfo.SetUserScore("0000");  //replace by reading latest score for the player from database
        break;
      case 2: //play anonymously
        userInfo.SetUserName("Anonymous");
        userInfo.SetUserScore("******");
        break;
      default:
    }
  }
}