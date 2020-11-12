import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:34
 * Project: Quizkampen
 * Copyright: MIT
 */
public class LoginGUI extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JLabel user = new JLabel("Player");
    JTextField userText = new JTextField(20);
    JButton login = new JButton("Log in");


    public LoginGUI(){

        frame.setSize(350, 100);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);
        user.setBounds(10,20,80,25);
        panel.add(user);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
        login.setBounds(10,80,80,25);
        panel.add(login);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        login.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            Player p = new Player(userText.getText());
            frame.dispose();
            HomePage page = new HomePage(p);
        }
    }


}
