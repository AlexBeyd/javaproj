import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.io.Console;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseMotionListener {

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

  public GamePanel() {
    setBorder(BorderFactory.createLineBorder(Color.black));

    onScreenColumns.add(new GameColumn(xPosition, 0, columnWidth, screenHeight));

    task = new TimerTask() {
      public void run() {
        xPosition = xPosition - gameSpeed;
        repaint();
      }
    };

    addMouseMotionListener(this);
  }

  public void StartGame() {
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

  public void mouseMoved(MouseEvent e) {
    System.out.println("Hello");
  }

  private void paintObstacles(Graphics g) {

    // draw all on screen columns
    for (GameColumn gameColumn : onScreenColumns) {
      g.setColor(columnColor);

      for (Rectangle column : gameColumn.getWalls()) {
        g.fillRect(column.x, column.y, column.width, column.height);
      }
    }

    // update columns position
    int index = 0;
    while (index < onScreenColumns.size()) {
      GameColumn gameColumn = onScreenColumns.get(index);

      if (gameColumn.getWalls().get(0).x + columnWidth < 0) {
        // the column is outide of visible screen - remove it from next draw cycle
        onScreenColumns.remove(index);
        index--;
      } else {
        onScreenColumns.get(index).updateX(onScreenColumns.get(index).getWalls().get(0).x - gameSpeed);
      }

      index++;
    }

    // add new column if there is place available on screen
    Rectangle lastColumn = onScreenColumns.get(onScreenColumns.size() - 1).getWalls().get(0);
    if (lastColumn.x + lastColumn.width + betweenColumnWidth < screentWidth) {
      onScreenColumns.add(new GameColumn(screentWidth, 0, columnWidth, screenHeight));
    }
  }
}