import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;


class UserInfo extends JPanel {
    public UserInfo() {
      this.setLayout(new GridLayout(1, 2));
      JPanel p1 = new JPanel(new FlowLayout());
      p1.add(new JTextField("User Name"));
      this.add(p1);
      JPanel p2 = new JPanel(new FlowLayout());
      p2.add(new JTextField("User Score"));
      this.add(p2);
  
    }
  }