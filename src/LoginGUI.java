import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JLabel user = new JLabel("Spelare");
    JTextField userText = new JTextField();
    JButton login = new JButton("Logga in");

    public LoginGUI() {
        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(51, 133, 255));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 80)));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.add(user);
        user.setFont(new Font("Arial", Font.PLAIN, 18));
        user.setForeground(Color.WHITE);
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));
        panel.add(userText);
        userText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 15)));
        panel.add(login);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setBackground(new Color(71, 71, 209));
        login.setForeground(Color.WHITE);
        login.setPreferredSize(new Dimension(50, 40));
        panel.add(Box.createRigidArea(new Dimension(100, 170)));

        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        login.addActionListener(this);
        userText.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userText || e.getSource() == login) {
            Player p = new Player(userText.getText());
            dispose();
            HomePage page = new HomePage(p);
        }
    }


}
