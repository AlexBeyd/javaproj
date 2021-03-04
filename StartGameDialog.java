import java.awt.Dimension;
import java.awt.Point;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StartGameDialog extends JDialog {
    JTextField playerName;
    JPanel dialogPane;
    JButton closeGameButton;
    JButton playAnonymousButton;
    JButton playButton;
    int dialogAnswer = 0;

    public StartGameDialog(JFrame parentFrame) {
        setTitle("How do you want to play?");

        playerName = new JTextField("Type you name here");
        playerName.selectAll(); //select the entire text so it will be deleted once a user starts typing
        
        //cancel button will close the dialog box and do nothing
        closeGameButton = new JButton("Close Game");
        closeGameButton.addActionListener(action -> {
            dialogAnswer = 0;
            this.dispose();
        });

        playButton = new JButton("Play");
        playButton.addActionListener(action ->{
            dialogAnswer = 1;
            this.setVisible(false); //don't dispose because we will need a player name from TextField
        });

        playAnonymousButton = new JButton("Play Anonymous");
        playAnonymousButton.addActionListener(action ->{
            dialogAnswer = 2;
            this.setVisible(false);
        });

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(2, 2));

        dialogPanel.add(playerName);

        JPanel controlsPanel = new JPanel();
        controlsPanel.add(playButton);
        controlsPanel.add(playAnonymousButton);
        controlsPanel.add(closeGameButton);
        dialogPanel.add(controlsPanel);

        setContentPane(dialogPanel);

        this.setModal(true);
        this.setSize(new Dimension(400, 200));
        this.setLocation(new Point(100,100));        
    }

    public int GetAnswer() {
        this.setVisible(true);
        return dialogAnswer;
    }

    public String GetPlayerName() {
        return playerName.getText();
    }
}
