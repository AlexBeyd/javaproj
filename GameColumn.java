import java.util.ArrayList;
import java.util.Random;
import java.awt.*;


public class GameColumn {
  
    private ArrayList<Rectangle> walls = new ArrayList<>();
  
    public int PassageYCoord;  
  
    public GameColumn(int x, int y, int width, int height){
      int passageHeight = height / 10;
      
      Random numGen = new Random();
      PassageYCoord = numGen.nextInt(height); //create random Y coordinate of passage for the column
  
      //create a column with passage
      if(PassageYCoord + passageHeight >= height){
        //passage is on bottom of column
        PassageYCoord = height - passageHeight;
        walls.add(new Rectangle(x, y, width, PassageYCoord));
      } else if(PassageYCoord == 0){
        //passage is on top of column
        walls.add(new Rectangle(x, y + passageHeight, width, height - passageHeight));
      } else{
        //any other case
        walls.add(new Rectangle(x, y, width, PassageYCoord));
        walls.add(new Rectangle(x, PassageYCoord + passageHeight, width, height - PassageYCoord - passageHeight));
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