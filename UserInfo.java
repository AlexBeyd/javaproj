import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;

class UserInfo extends JPanel {
  public JTextField userNameField;
  public JTextField userScoreField;
  public UserInfo() {
    this.setLayout(new GridLayout(1, 2));

    JPanel p1 = new JPanel(new FlowLayout());
    p1.add(new JLabel("Player Name"));
    userNameField = new JTextField("User Name Here");
    userNameField.setEditable(false);
    p1.add(userNameField);
    this.add(p1);

    JPanel p2 = new JPanel(new FlowLayout());
    p2.add(new JLabel("Player Score"));
    userScoreField = new JTextField("User Score Here");
    userScoreField.setEditable(false);
    p2.add(userScoreField);
    this.add(p2);
  }

  public String GetUserScore(){
    return userScoreField.getText();
  }

  public void SetUserScore(String score){
    userScoreField.setText(score);
  }

  public String GetUserName(){
    return userNameField.getText();
  }

  public void SetUserName(String name){
    userNameField.setText(name);
  }
}