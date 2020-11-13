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
public class GamePage extends JFrame implements ActionListener {

    JLabel player1 = new JLabel();
    JLabel player2 = new JLabel("Player 2");

    JLabel score = new JLabel("0 - 0");

    JButton p1k1b1 = new JButton();
    JButton p1k1b2 = new JButton();
    JLabel kategori1 = new JLabel();
    JButton p2k1b1 = new JButton();
    JButton p2k1b2 = new JButton();

    JButton p1k2b1 = new JButton();
    JButton p1k2b2 = new JButton();
    JLabel kategori2 = new JLabel();
    JButton p2k2b1 = new JButton();
    JButton p2k2b2 = new JButton();

    JButton play = new JButton("Play");

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel4 = new JPanel();
    JFrame frame = new JFrame();


    Player pro = new Player();
    public GamePage(Player p){

        pro.setName(p.getName());
        player1.setText(pro.getName());

        frame.setSize(400, 200);
        panel1.setLayout(new GridLayout(1,3));
        panel2.setLayout(new GridLayout(2,5));
        panel4.setLayout(new GridLayout(1,1));

        panel1.add(player1);
        panel1.add(score);
        panel1.add(player2);

        panel2.add(p1k1b1);
        panel2.add(p1k1b2);
        panel2.add(kategori1);
        panel2.add(p2k1b1);
        panel2.add(p2k1b2);

        panel2.add(p1k2b1);
        panel2.add(p1k2b2);
        panel2.add(kategori2);
        panel2.add(p2k2b1);
        panel2.add(p2k2b2);

        panel4.add(play);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel4, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        play.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            frame.dispose();
            CategoryPage c = new CategoryPage(pro);
        }
    }
}
