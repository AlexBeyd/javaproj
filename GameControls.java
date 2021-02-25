import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;


class GameControls extends JPanel {
    private JFrame parentFrame;
  
    public GameControls(JFrame frame, GamePanel gamePanel) {
      parentFrame = frame;
      this.setLayout(new GridLayout(1, 2));

      //start game button
      JPanel p1 = new JPanel(new FlowLayout());
      JButton startButton = new JButton("Start Game");
      p1.add(startButton);
      startButton.addActionListener(l->{
        gamePanel.StartGame();
      });
      this.add(p1);

      //quit game button
      JPanel p2 = new JPanel(new FlowLayout());
      JButton quitButton = new JButton("Quit");
      quitButton.addActionListener(e -> {
        parentFrame.dispose();
      });
      p2.add(quitButton);
      this.add(p2);
    }
  }
  