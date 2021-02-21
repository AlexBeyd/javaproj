
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.util.ArrayList;
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

  long timerDelay = 50L;
  int xPosition = screentWidth;

  int gameSpeed = 10;

  int columnWidth = 100;
  int betweenColumnWidth = 100;
  Color columnColor = Color.yellow;

  ArrayList<Rectangle> onScreenColumns = new ArrayList<Rectangle>();

  static Timer timer = new Timer("Timer");
  TimerTask task;

  public MyPanel() {
    setBorder(BorderFactory.createLineBorder(Color.black));

    onScreenColumns.add(new Rectangle(xPosition, 0, columnWidth, screenHeight));

    task = new TimerTask() {
      public void run() {
        xPosition = xPosition - gameSpeed;
        repaint();
      }
    };

    timer.schedule(task, 0, timerDelay);
  }

  public Dimension getPreferredSize() {
    return new Dimension(screentWidth, screenHeight);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintObstacles(g);
  }

  private void paintObstacles(Graphics g) {

    //draw all on screen columns
    for (Rectangle column : onScreenColumns){
      g.setColor(columnColor);
      g.fillRect(column.x, column.y, column.width, column.height);  
    }  

    //update columns position
    for (int index = 0; index < onScreenColumns.size(); index++){
      Rectangle column = onScreenColumns.get(index);

      if(column.x + columnWidth < 0){
        //the column is outide of visible screen - remove it from next draw cycle
        onScreenColumns.remove(index);
        index--;
      } else{
        onScreenColumns.set(index, new Rectangle(column.x - gameSpeed, column.y, column.width, column.height));
      }
    }

    //add new column if there is place available on screen
    Rectangle lastColumn = onScreenColumns.get(onScreenColumns.size() - 1);
    if(lastColumn.x + lastColumn.width + betweenColumnWidth < screentWidth){
      onScreenColumns.add(new Rectangle(screentWidth, 0 , columnWidth, screenHeight));
    }
  }
}
