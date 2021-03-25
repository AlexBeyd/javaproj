import java.io.File;
import java.io.IOException;

public class PlayerDatabase {
    String filename = "playersdata.txt";
    File database;

    public PlayerDatabase() {
        CreateDatabase();
    }

    // check if database exist, create if not found
    public void CreateDatabase(){
        database = new File(filename);
        try {
            database.createNewFile();
        } catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Add new user with 0 score
    public void AddUser(String username) {

    }

    // Find a user score by user name
    public int GetUSerScore(String username) {

    }

    // Update user score
    public void UpdateUserScore(String username, int score) {
        
    }
}
