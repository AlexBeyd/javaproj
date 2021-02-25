import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;


class GameControls extends JPanel {
    private JFrame parentFrame;
  
    public GameControls(JFrame frame) {
      parentFrame = frame;
      this.setLayout(new GridLayout(1, 2));
      JPanel p1 = new JPanel(new FlowLayout());
      p1.add(new JButton("Start Game"));
      this.add(p1);
      JPanel p2 = new JPanel(new FlowLayout());
      JButton quitButton = new JButton("Quit");
      quitButton.addActionListener(e -> {
        parentFrame.dispose();
      });
      p2.add(quitButton);
      this.add(p2);
    }
  }
  