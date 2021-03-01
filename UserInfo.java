import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;


class UserInfo extends JPanel {
    public UserInfo() {
      this.setLayout(new GridLayout(1, 2));
      JPanel p1 = new JPanel(new FlowLayout());
      JTextField userNameField = new JTextField("User Name Here");
      userNameField.setEditable(false);
      p1.add(userNameField);
      this.add(p1);
      JPanel p2 = new JPanel(new FlowLayout());
      JTextField userScoreField = new JTextField("User Score Here");
      userScoreField.setEditable(false);
      p2.add(userNameField);
      this.add(p2);
  
    }
  }