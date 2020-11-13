import javax.swing.*;
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

    JFrame frame = new JFrame();
    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JPanel stats = new JPanel();
    JPanel categoriepanel = new JPanel();

    JLabel playerName1 = new JLabel();
    JLabel playerName2 = new JLabel("Player 2");
    JLabel score = new JLabel("0 - 0");

    JButton play = new JButton("Play");


    List<JButton> buttonList_questions;

    Properties p = new Properties();



    Player pro = new Player();
    public GamePage(Player player) throws FileNotFoundException, IOException {

        pro = player;


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


        player1Panel.setLayout(new GridLayout(numberOfRounds+1, numberOfQuestions+1));
        categoriepanel.setLayout(new GridLayout(numberOfRounds+1, 1));
        player2Panel.setLayout(new GridLayout(numberOfRounds+1, numberOfQuestions+1));


        int totalbuttons = numberOfQuestions * numberOfRounds;
        buttonList_questions = createButtonList(totalbuttons);
        List<JButton> buttonList_Questions2 = createButtonList(totalbuttons);


        for (int i = 0; i < totalbuttons; i++) {
            player1Panel.add(buttonList_questions.get(i));
        }
        for (int i = 0; i < numberOfRounds; i++) {
            categoriepanel.add(new JLabel("Category"));
        }
        for (int i = 0; i <totalbuttons ; i++) {
            player2Panel.add(buttonList_Questions2.get(i));
        }


        /*
        Rond            Till
        1       0       3
        2       3       6
        3       6       9
        4       9       12

         */


        for (int i = (pro.getRound()*numberOfQuestions)- numberOfQuestions; i < numberOfQuestions*pro.getRound(); i++) {
            buttonList_questions.get(i);
        }

        stats.add(playerName1);
        stats.add(score);
        stats.add(playerName2);
        frame.add(stats, BorderLayout.NORTH);
        frame.add(player1Panel, BorderLayout.WEST);
        frame.add(categoriepanel, BorderLayout.CENTER);
        frame.add(player2Panel, BorderLayout.EAST);
        frame.add(play, BorderLayout.SOUTH);
        playerName1.setText(pro.getName());




        play.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }




    public List<JButton> createButtonList(int numberOfButtons) {
        List<JButton> buttonList = new ArrayList<>();
        for (int i = 0; i <numberOfButtons; i++) {
            buttonList.add(new JButton());
        }
        return buttonList;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            pro.setRound(pro.getRound()+1);
            frame.dispose();
            CategoryPage c = new CategoryPage(pro);
        }
    }
}
