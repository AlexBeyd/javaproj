import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayerDatabase {
    String filename = "playersdata.txt";
    String entryUserNamePrefix = "User:";
    String endtryScorePrefix = "/Score:";
    String defaultInitialScore = "0";
    File database;

    public PlayerDatabase() {
        CreateDatabase();
    }

    // check if database exist, create if not found
    public void CreateDatabase() {
        database = new File(filename);
        try {
            database.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Add new user with 0 score
    public void AddUser(String username) {
        try {
            FileWriter fw = new FileWriter(database, true);
            fw.write(entryUserNamePrefix + username + endtryScorePrefix + "0");
            fw.close();
        } catch (IOException ex) {
        }
    }

    // Find a user score by user name
    public String GetUSerScore(String username) {
        String databaseEntry = LineFromFile(username);
        // if not found - create new entry and return 0
        if (databaseEntry == null) {
            AddUser(username);
            return defaultInitialScore;
        } else {
            return GetScoreFromEntry(databaseEntry);
        }
    }

    // Update user score
    public void UpdateUserScore(String username, int score) {
        String dataToWrite = entryUserNamePrefix + username + endtryScorePrefix + score;
        String dataToReplace = LineFromFile(username);
        
        // if not found - append new entry, else, - replace old entry by new

        try {
            if ( dataToReplace == null) {
                FileWriter databaseWriter = new FileWriter(filename);
                databaseWriter.write(dataToWrite);
                databaseWriter.close();
            } else{
                ReplaceLineInFile(filename, dataToReplace, dataToWrite);
            }

        } catch (IOException ex) {
        }

    }

    String LineFromFile(String playerName) {
        try {
            final Scanner scanner = new Scanner(database);
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(entryUserNamePrefix + playerName + endtryScorePrefix)) {
                    return lineFromFile;
                }
            }
        } catch (Exception ex) {
        }

        return null;
    }

    String GetScoreFromEntry(String entry) {
        return entry.split(endtryScorePrefix)[1];
    }

    void ReplaceLineInFile(String filename, String oldLine, String newLine) {
        try {
            // Instantiating the Scanner class to read the file
            Scanner sc = new Scanner(new File(filename));
            // instantiating the StringBuffer class
            StringBuffer buffer = new StringBuffer();
            // Reading lines of the file and appending them to StringBuffer
            while (sc.hasNextLine()) {
                buffer.append(sc.nextLine() + System.lineSeparator());
            }
            String fileContents = buffer.toString();
            // closing the Scanner object
            sc.close();
            // Replacing the old line with new line
            fileContents = fileContents.replaceAll(oldLine, newLine);
            // instantiating the FileWriter class
            FileWriter writer = new FileWriter(filename);
            writer.append(fileContents);
            writer.flush();
            writer.close();
        } catch (Exception ex) {
        }
    }
}
