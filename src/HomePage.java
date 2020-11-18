import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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


    JButton settings = new JButton("Settings");
    JLabel player = new JLabel();
    JButton play = new JButton("Play");


    Player pro = new Player();

    public HomePage(Player p){

        pro.setName(p.getName());
        player.setText(pro.getName());
    //    panelUp.setLayout(new GridLayout(1,4));
        panelMid.setLayout(new GridLayout(1,1));
        panelBot.setLayout(new GridLayout(2,1));

        frame.add(panelUp, BorderLayout.NORTH);
        frame.add(panelMid,BorderLayout.CENTER);
        frame.add(panelBot, BorderLayout.SOUTH);

       // panelUp.add(settings);
        panelMid.add(player);
        panelBot.add(play);
        panelBot.add(home);

        frame.setSize(200,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        play.addActionListener(this);
        settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Settings s = new Settings();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            frame.dispose();
            try {
                GamePage g = new GamePage(pro);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
