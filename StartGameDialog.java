import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartGameDialog extends JDialog {
    JTextField playerName;
    JPanel dialogPane;
    JButton cancelButton;

    public StartGameDialog(JFrame parentFrame) {
        setTitle("How do you want to play?");

        playerName = new JTextField("Type you name here");

        //cancel button will close the dialog box and do nothing
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(action -> {
            this.dispose();
        });

        dialogPane = new JPanel();
        dialogPane.add(playerName);
        dialogPane.add(cancelButton);

        setContentPane(dialogPane);

        this.setModal(true);
        this.setSize(new Dimension(400, 200));
        this.setLocation(new Point(100,100));

        this.setVisible(true);
    }
}
