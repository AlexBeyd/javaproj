
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
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

  ArrayList<GameColumn> onScreenColumns = new ArrayList<GameColumn>();

  static Timer timer = new Timer("Timer");
  TimerTask task;

  public MyPanel() {
    setBorder(BorderFactory.createLineBorder(Color.black));

    onScreenColumns.add(new GameColumn(xPosition, 0, columnWidth, screenHeight, -1));

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
    for (GameColumn gameColumn : onScreenColumns){
      g.setColor(columnColor);

      for(Rectangle column : gameColumn.GetWalls()){
        g.fillRect(column.x, column.y, column.width, column.height);  
      }
    }  

    //update columns position
    for (int index = 0; index < onScreenColumns.size(); index++){
      GameColumn gameColumn = onScreenColumns.get(index);

      if(gameColumn.GetWalls().get(0).x + columnWidth < 0){
        //the column is outide of visible screen - remove it from next draw cycle
        onScreenColumns.remove(index);
        index--;
      } else{
        onScreenColumns.get(index).UpdateX(onScreenColumns.get(index).GetWalls().get(0).x - gameSpeed); 
      }
    }

    //add new column if there is place available on screen
    Rectangle lastColumn = onScreenColumns.get(onScreenColumns.size() - 1).GetWalls().get(0);
    if(lastColumn.x + lastColumn.width + betweenColumnWidth < screentWidth){
      onScreenColumns.add(new GameColumn(screentWidth, 0 , columnWidth, screenHeight, -1));
    }
  }
}

class GameColumn {
  
  private ArrayList<Rectangle> Walls = new ArrayList<Rectangle>();

  public int PassageYCoord;
  

  public GameColumn(int x, int y, int width, int height, int passageCoord){
    int passageHeight = height / 10;
    
    Random numGen = new Random();
    if(passageCoord == -1) PassageYCoord = numGen.nextInt(height);
    else PassageYCoord = passageCoord;

    if(PassageYCoord + passageHeight > height){
      //passage is on bottom of column
      PassageYCoord = height - passageHeight;
      Walls.add(new Rectangle(x, y, width, height - PassageYCoord));
    } else if(PassageYCoord == 0){
      //passage is on top of column
      Walls.add(new Rectangle(x, y + passageHeight, width, height - passageHeight));
    } else{
      //any other case
      Walls.add(new Rectangle(x, y, width, height - PassageYCoord));
      Walls.add(new Rectangle(x, PassageYCoord + passageHeight, width, height - PassageYCoord - passageHeight));
    }
  }

  public void UpdateX(int newX){
    for(int index = 0; index < Walls.size(); index++)
    {
      Walls.set(index, new Rectangle(newX, Walls.get(index).y, Walls.get(index).width, Walls.get(index).height));
    }
  }

  public ArrayList<Rectangle> GetWalls(){
    return Walls;
  }
}
