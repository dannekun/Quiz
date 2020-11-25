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

    List<JButton> player1_answers;
    List<JButton> player2_answers;

    List<JLabel> labelNames;

    Properties p = new Properties();

    CategoryPage catToFindNamesForLabel = new CategoryPage();


    int numberOfRounds;
    int numberOfQuestions;


    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    Player pro = new Player();
    public GamePage(Player player) throws FileNotFoundException, IOException {

        pro = player;

        try{
            p.load(new FileInputStream("src/RoundQuestions.properties"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String stringRounds = p.getProperty("numberOfRounds", "2");
        //numberOfRounds = Integer.parseInt(stringRounds);
        setNumberOfRounds(Integer.parseInt(stringRounds));
        String stringQuestions = p.getProperty("numberOfQuestions", "2");
        //numberOfQuestions = Integer.parseInt(stringQuestions);
        setNumberOfQuestions(Integer.parseInt(stringQuestions));

        //player1Panel.setLayout(new GridLayout(numberOfRounds+1, numberOfQuestions+1));
        //categoriepanel.setLayout(new GridLayout(numberOfRounds+1, 1));
        //player2Panel.setLayout(new GridLayout(numberOfRounds+1, numberOfQuestions+1));

        player1Panel.setLayout(new GridLayout(getNumberOfRounds(), getNumberOfQuestions()+1));
        categoriepanel.setLayout(new GridLayout(getNumberOfRounds()+1, 1));
        player2Panel.setLayout(new GridLayout(getNumberOfRounds(), getNumberOfQuestions()+1));


        pro.setMaxRound(getNumberOfRounds());
        pro.setMaxQuestion(getNumberOfQuestions());

        //int totalbuttons = numberOfQuestions * numberOfRounds;
        int totalbuttons = getNumberOfQuestions() * getNumberOfRounds();



        player1_answers = createButtonList(totalbuttons);
        player2_answers = createButtonList(totalbuttons);

        for (int i = 0; i < totalbuttons; i++) {
            if (i < pro.getAnswers().size()){
                if (pro.getAnswers().get(i) == false){
                    player1_answers.get(i).setBackground(Color.RED);
                    player1_answers.get(i).setOpaque(true);
                    player1_answers.get(i).setBorderPainted(false);
                }else {
                    player1_answers.get(i).setBackground(Color.GREEN);
                    player1_answers.get(i).setOpaque(true);
                    player1_answers.get(i).setBorderPainted(false);
                }
            }
            player1Panel.add(player1_answers.get(i));
        }


/*
        for (int i = 0; i < numberOfRounds; i++) {
            categoriepanel.add(new JLabel("Category"));
        }

 */
        labelNames = createLabelList(getNumberOfRounds());

        for (int i = 0; i < pro.getMaxRound(); i++) {
            if (i < pro.getRound()){
                labelNames.get(i).setText(pro.roundCategories.get(i));
            }else {
                labelNames.get(i).setText("          ");
            }
            categoriepanel.add(labelNames.get(i));
        }

        /*
        for (int i = 0; i < getNumberOfRounds(); i++) {
            categoriepanel.add(new JLabel("Category"));
        }


         */

        for (int i = 0; i <totalbuttons ; i++) {
            player2Panel.add(player2_answers.get(i));
        }

        if (pro.getRound() >= 1){
            for (int i = 0; i < pro.getRoundCategories().size(); i++) {
                labelNames.get(i).setText(pro.roundCategories.get(i));
            }
        }
        if (pro.getPoints() == 0){
            score.setText("0 - 0");
        }else {
            score.setText(String.valueOf(pro.getPoints()) + " - 0");
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

    public List<JLabel> createLabelList(int numberOfRounds){
        List<JLabel> labelList= new ArrayList<>();
        for (int i = 0; i < numberOfRounds; i++) {
            labelList.add(new JLabel("Cat"));
        }
        return labelList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play){
            pro.setRound(pro.getRound()+1);
            pro.setQuestion(pro.getQuestion()+1);
            frame.dispose();
            CategoryPage c = new CategoryPage(pro);
        }
    }
}
