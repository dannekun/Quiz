import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */
public class HomePage extends JFrame implements ActionListener {


    JFrame frame = new JFrame();
    JPanel panelUp = new JPanel();
    JPanel panelMid = new JPanel();
    JPanel panelBot = new JPanel();

    JLabel home = new JLabel("Home");


    JButton settins = new JButton("Settings");
    JLabel player = new JLabel("Player");
    JButton play = new JButton("Play");


    public void HomeGame(){

        panelUp.setLayout(new GridLayout(1,4));
        panelMid.setLayout(new GridLayout(1,4));
        panelBot.setLayout(new GridLayout(2,1));

        frame.add(panelUp, BorderLayout.NORTH);
        frame.add(panelMid,BorderLayout.CENTER);
        frame.add(panelBot, BorderLayout.SOUTH);

        panelUp.add(settins);
        panelMid.add(player);
        panelBot.add(play);
        panelBot.add(home);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
