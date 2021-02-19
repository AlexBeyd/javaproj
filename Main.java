
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }

  private static void createAndShowGUI() {
    System.out.println("Created GUI on EDT? " + SwingUtilities.isEventDispatchThread());
    JFrame f = new JFrame("Swing Paint Demo");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(new MyPanel());
    f.pack();
    f.setVisible(true);
  }
}

class MyPanel extends JPanel {

  int screenHeight = 700;
  int screentWidth = 1000;

  public MyPanel() {
    setBorder(BorderFactory.createLineBorder(Color.black));
  }

  public Dimension getPreferredSize() {
    return new Dimension(screentWidth, screenHeight);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintObstacles(g);
  }

  private void paintObstacles(Graphics g) {
    g.drawRect(-10, 0, 100, screenHeight);
  }
}
