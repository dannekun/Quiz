import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JLabel user = new JLabel("Spelare");
    JTextField userText = new JTextField();

    public LoginGUI(){

        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground( new Color(51, 133, 255));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 80)));
        panel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        panel.add(user);
        user.setFont(new Font("Arial", Font.PLAIN, 18));
        user.setForeground(Color.WHITE);
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(userText);
        userText.setAlignmentX(Component.CENTER_ALIGNMENT);
        userText.setPreferredSize(new Dimension(200,30));
        userText.setMaximumSize(new Dimension(200,30));

        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        userText.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userText){
            Player p = new Player(userText.getText());
            dispose();
            HomePage page = new HomePage(p);
        }
    }


}
