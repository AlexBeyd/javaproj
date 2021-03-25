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

    PlayerDatabase database = new PlayerDatabase();

    JFrame mainFrame = new JFrame("TheSaggyChickGame");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainFrame.setIconImage((new ImageIcon("download.jpg")).getImage());

    mainFrame.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;

    // display user info panel
    c.gridx = 0;
    c.gridy = 0;
    UserInfo userInfo = new UserInfo(database);
    mainFrame.add(userInfo, c);

    c.gridx = 0;
    c.gridy = 1;
    GamePanel gamePanel = new GamePanel(mainFrame, userInfo);
    mainFrame.add(gamePanel, c);

    c.gridx = 0;
    c.gridy = 2;
    mainFrame.add(new GameControls(mainFrame, gamePanel), c);

    mainFrame.pack();
    mainFrame.setResizable(false);
    mainFrame.setVisible(true);

    // show start game modal dialog box
    StartGameDialog startGameDialog = new StartGameDialog(mainFrame);

    switch (startGameDialog.GetAnswer()) {
      case 0: // cancel
        mainFrame.dispose();
        break;
      case 1: // play with player name
        userInfo.SetUserName(startGameDialog.GetPlayerName());
        userInfo.SetUserScore("0000"); // replace by reading latest score for the player from database
        break;
      case 2: // play anonymously
        userInfo.SetUserName("Anonymous");
        userInfo.SetUserScore("******");
        break;
      default:
    }
  }
}