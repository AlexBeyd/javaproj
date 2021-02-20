
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
  long timerDelay = 1000L;
  int xPosition = 0;

  static Timer timer = new Timer("Timer");
  TimerTask task;

  public MyPanel() {
    setBorder(BorderFactory.createLineBorder(Color.black));

     task = new TimerTask() {
      public void run() {
        xPosition = xPosition + 10;
        //g.drawRect(x, 0, 100, screenHeight);  
        //System.out.println("I'm timer task. Time is " + new Date().getTime());
        //repaint(100, 0, 100, screenHeight);
        repaint();
      }
    };
   
    timer.schedule(task,0, timerDelay );
  }

  public Dimension getPreferredSize() {
    return new Dimension(screentWidth, screenHeight);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintObstacles(g);
  }

  private void paintObstacles(Graphics g) {
    g.drawRect(xPosition, 0, 100, screenHeight);    
  }
}
