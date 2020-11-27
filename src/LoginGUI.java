import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame implements ActionListener {

    GUI_Util util = new GUI_Util();

    JPanel panel = new JPanel();
    JLabel user = new JLabel("Spelare");
    JTextField userText = new JTextField();
    JButton login = new JButton("Logga in");

    public LoginGUI() {

        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        util.setMainBackground(panel);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 80)));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        panel.add(user);
        util.labelSetFontForeg(user,"Arial",0,18,255,255,255);
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        panel.add(userText);
        userText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 15)));

        panel.add(login);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        util.buttonSetFontForegBackg(login,"Arial",0,12,
                255,255,255,71,71,209);
        util.setSizeButton(login,90,40,90,40);
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
            HomePage_play page = new HomePage_play(p);
        }
    }


}
