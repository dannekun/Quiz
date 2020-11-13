import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GamePage extends JFrame implements ActionListener {

    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JPanel mainPanel = new JPanel();

    JLabel playerName = new JLabel();
    JLabel score = new JLabel("0 - 0");

    JButton play = new JButton("Play");

 //   List<JButton> buttonList_round;
    List<JButton> buttonList_questions;
    List<JPanel> rounds;

    Properties p = new Properties();


 /*   JLabel player1 = new JLabel();
    JLabel player2 = new JLabel("Player 2");

    JLabel score = new JLabel("0 - 0");

    JButton p1k1b1 = new JButton();
    JButton p1k1b2 = new JButton();
    JLabel kategori1 = new JLabel("Tv-serier");
    JButton p2k1b1 = new JButton();
    JButton p2k1b2 = new JButton();

    JButton p1k2b1 = new JButton();
    JButton p1k2b2 = new JButton();
    JLabel kategori2 = new JLabel("Tv-spel");
    JButton p2k2b1 = new JButton();
    JButton p2k2b2 = new JButton();

    JButton play = new JButton("Play");

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel4 = new JPanel();
    JFrame frame = new JFrame();*/


    Player pro = new Player();
    public GamePage(Player player) throws FileNotFoundException, IOException {

        pro.setName(player.getName());

        try{
            p.load(new FileInputStream("src/RoundQuestions_Properties.properties"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String stringRounds = p.getProperty("numberOfRounds", "2");
        int numberOfRounds = Integer.parseInt(stringRounds);
        String stringQuestions = p.getProperty("numberOfQuestions", "2");
        int numberOfQuestions = Integer.parseInt(stringQuestions);

    //    buttonList_round = createButtonList(numberOfRounds);
        buttonList_questions = createButtonList(numberOfQuestions);
        rounds = createRounds(numberOfRounds);

        for (JPanel panel : rounds){
            panel.add((Component) buttonList_questions);
        }

        mainPanel.setLayout(new BorderLayout());
        add(score, BorderLayout.NORTH);
        add(player1Panel, BorderLayout.WEST);
        add(player2Panel, BorderLayout.EAST);
        add(play, BorderLayout.SOUTH);

        playerName.setText(pro.getName());

        player1Panel.setLayout(new GridLayout(numberOfRounds+1, 1));
        player1Panel.add(playerName);
        player1Panel.add((Component) rounds);

        player2Panel.setLayout(new GridLayout(numberOfRounds+1, 1));
        player2Panel.add(playerName);
        player2Panel.add((Component) rounds);

        play.addActionListener(this);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    /*    player1.setText(pro.getName());

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

        play.addActionListener(this);*/
    }

    public List<JButton> createButtonList(int numberOfButtons) {
        List<JButton> buttonList = new <>();
        for (int i = 0; i <numberOfButtons; i++) {
            buttonList.add(new JButton());
        }
        return buttonList;
    }

    public List<JPanel> createRounds(int numberOfRounds){
        List<JPanel> panelList = new ArrayList<>();
        for (int i = 0; i < numberOfRounds; i++) {
            panelList.add(new JPanel());
        }
        return panelList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            //frame.dispose();
            CategoryPage c = new CategoryPage(pro);
        }
    }
}
