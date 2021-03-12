import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseMotionListener {

  JFrame parentFrame;

  int screenHeight = 700;
  int screentWidth = 1000;

  long timerDelay = 50L;
  int xPosition = screentWidth;

  int gameSpeed = 10;

  int currentCursorX = -1;
  int currentCursorY = -1;
  int cursorWidth = 10;
  int cursonHeight = 10;

  int columnWidth = 100;
  int betweenColumnWidth = columnWidth * 2;
  Color columnColor = Color.lightGray;

  ArrayList<GameColumn> onScreenColumns = new ArrayList<>();

  Timer timer;
  TimerTask task;

  public GamePanel(JFrame parent) {
    parentFrame = parent;

    setBorder(BorderFactory.createLineBorder(Color.black));

    onScreenColumns.add(new GameColumn(xPosition, 0, columnWidth, screenHeight));    

    addMouseMotionListener(this);
  }

  public void StartGame() {
    timer = new Timer("Timer");
    task = new TimerTask() {
      public void run() {
        xPosition = xPosition - gameSpeed;
        repaint();

        UpdateColumnsPostitions();
      }
    };

    timer.schedule(task, 0, timerDelay);
  }

  private void StopGame() {
    timer.cancel();
    task.cancel();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(screentWidth, screenHeight);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    drawCursor(g);

    paintObstacles(g);
  }

  public void mouseMoved(MouseEvent e) {

    // erase old curson
    if (currentCursorX != -1 && currentCursorY != -1)
      repaint(currentCursorX, currentCursorY, cursorWidth, cursonHeight);

    // update coordinates for cursor new position
    currentCursorX = e.getX();
    currentCursorY = e.getY();

    // paint curson on new position
    repaint(currentCursorX, currentCursorY, cursorWidth, cursonHeight);

    if (isColumnHit()) {
      StopGame();
      JOptionPane.showMessageDialog(parentFrame, "Focus!");

      //reset all columns to initial state
      xPosition = screentWidth;
      onScreenColumns = new ArrayList<>();
      onScreenColumns.add(new GameColumn(xPosition, 0, columnWidth, screenHeight));

      //clear game screen
      repaint();
    }
  }

  private void paintObstacles(Graphics g) {
    // draw all on screen columns
    for (GameColumn gameColumn : onScreenColumns) {
      g.setColor(columnColor);

      for (Rectangle column : gameColumn.getWalls()) {
        g.fillRect(column.x, column.y, column.width, column.height);
      }
    }
  }

  private void UpdateColumnsPostitions() {
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

  private void drawCursor(Graphics g) {
    g.drawRect(currentCursorX, currentCursorY, cursorWidth, cursonHeight);
  }

  private boolean isColumnHit() {
    
    for (GameColumn gameColumn : onScreenColumns) {
      for (Rectangle column : gameColumn.getWalls()) {
        if (currentCursorX < column.x + column.width && currentCursorX + cursorWidth > column.x
            && currentCursorY < column.y + column.height && currentCursorY + cursonHeight > column.y)
          return true;
      }
    }

    return false;
  }
}