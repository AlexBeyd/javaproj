
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
  int betweenColumnWidth = columnWidth * 2;
  Color columnColor = Color.lightGray;

  ArrayList<GameColumn> onScreenColumns = new ArrayList<>();

  static Timer timer = new Timer("Timer");
  TimerTask task;

  public MyPanel() {
    setBorder(BorderFactory.createLineBorder(Color.black));

    onScreenColumns.add(new GameColumn(xPosition, 0, columnWidth, screenHeight));

    task = new TimerTask() {
      public void run() {
        xPosition = xPosition - gameSpeed;
        repaint();
      }
    };

    timer.schedule(task, 0, timerDelay);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(screentWidth, screenHeight);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    paintObstacles(g);
  }

  private void paintObstacles(Graphics g) {

    //draw all on screen columns
    for (GameColumn gameColumn : onScreenColumns){
      g.setColor(columnColor);

      for(Rectangle column : gameColumn.getWalls()){
        g.fillRect(column.x, column.y, column.width, column.height);  
      }
    }  

    //update columns position
    int index = 0;
    while(index < onScreenColumns.size())
    {
      GameColumn gameColumn = onScreenColumns.get(index);

      if(gameColumn.getWalls().get(0).x + columnWidth < 0){
        //the column is outide of visible screen - remove it from next draw cycle
        onScreenColumns.remove(index);
        index--;
      } else{
        onScreenColumns.get(index).updateX(onScreenColumns.get(index).getWalls().get(0).x - gameSpeed); 
      }

      index++;
    }

    //add new column if there is place available on screen
    Rectangle lastColumn = onScreenColumns.get(onScreenColumns.size() - 1).getWalls().get(0);
    if(lastColumn.x + lastColumn.width + betweenColumnWidth < screentWidth){
      onScreenColumns.add(new GameColumn(screentWidth, 0 , columnWidth, screenHeight));
    }
  }
}

class GameColumn {
  
  private ArrayList<Rectangle> walls = new ArrayList<>();

  public int PassageYCoord;  

  public GameColumn(int x, int y, int width, int height){
    int passageHeight = height / 10;
    
    Random numGen = new Random();
    PassageYCoord = numGen.nextInt(height);

    if(PassageYCoord + passageHeight >= height){
      //passage is on bottom of column
      PassageYCoord = height - passageHeight;
      walls.add(new Rectangle(x, y, width, PassageYCoord));
      System.out.println("case 1: PassageYCoord = " + PassageYCoord);
    } else if(PassageYCoord == 0){
      //passage is on top of column
      walls.add(new Rectangle(x, y + passageHeight, width, height - passageHeight));
      System.out.println("Case 2");
    } else{
      //any other case
      walls.add(new Rectangle(x, y, width, PassageYCoord));
      walls.add(new Rectangle(x, PassageYCoord + passageHeight, width, height - PassageYCoord - passageHeight));
      System.out.println("Case 3, Passage coord = " + PassageYCoord);
    }
  }

  public void updateX(int newX){
    for(int index = 0; index < walls.size(); index++)
    {
      Rectangle item = walls.get(index);
      walls.set(index, new Rectangle(newX, item.y, item.width, item.height));
    }
  }

  public ArrayList<Rectangle> getWalls(){
    return walls;
  }
}
