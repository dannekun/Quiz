import javax.swing.*;
import java.awt.*;
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
    JPanel userPanel = new JPanel();
    JLabel user = new JLabel("Player");
    JTextField userText = new JTextField(20);
    JButton login = new JButton("Log in");


    public LoginGUI(){

        add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(100, 80)));
        panel.setBorder(BorderFactory.createEmptyBorder(10,50,10,50));
        panel.add(user);

    //    userText.setBounds(100,20,165,25);
        panel.add(userText);
        userText.setSize(20,10);

    //    login.setBounds(10,80,80,25);
        panel.add(login);


        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        login.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login){
            Player p = new Player(userText.getText());
            dispose();
            HomePage page = new HomePage(p);
        }
    }


}
