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
    JButton cancelButton;
    JButton playAnonymusButton;
    JButton playButton;

    public StartGameDialog(JFrame parentFrame) {
        setTitle("How do you want to play?");

        playerName = new JTextField("Type you name here");
        playerName.selectAll(); //select the entire text so it will be deleted once a user starts typing

        //cancel button will close the dialog box and do nothing
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(action -> {
            this.dispose();
        });

        playButton = new JButton("Play");
        playButton.addActionListener(action ->{
            
        });

        playAnonymusButton = new JButton("Play Anonymus");

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(2, 2));

        dialogPanel.add(playerName);


        JPanel controlsPanel = new JPanel();
        controlsPanel.add(playAnonymusButton);
        controlsPanel.add(cancelButton);
        dialogPanel.add(controlsPanel);

        setContentPane(dialogPanel);

        this.setModal(true);
        this.setSize(new Dimension(400, 200));
        this.setLocation(new Point(100,100));

        this.setVisible(true);
    }
}
