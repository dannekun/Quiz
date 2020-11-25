import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
    JButton empty = new JButton("Empty");

    JButton playerName1 = new JButton();
    JButton playerName2 = new JButton("Spelare");
 //   JLabel scorePlayer1 = new JLabel("0");
 //   JLabel colon = new JLabel(" : ");
 //   JLabel scorePlayer2 = new JLabel("0");

    JButton play = new JButton("Play");

    List<JButton> player1_answers;
    List<JButton> player2_answers;

    List<JLabel> labelNames;

    Properties p = new Properties();

    CategoryPage catToFindNamesForLabel = new CategoryPage();


    int numberOfRounds;
    int numberOfQuestions;

    public int getNumberOfRounds() {     //YAGNI
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {   //YAGNI
        this.numberOfRounds = numberOfRounds;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    Player pro;
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

        add(stats, BorderLayout.NORTH);
        stats.setLayout(new BoxLayout(stats, BoxLayout.LINE_AXIS));
        stats.setBackground( new Color(51, 133, 255));
        stats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //    stats.add(Box.createRigidArea(new Dimension(30, 40)));
        stats.add(playerName1);
        stats.add(Box.createRigidArea(new Dimension(20, 40)));
        stats.add(playerName2);
    //    stats.add(Box.createRigidArea(new Dimension(30, 40)));
        playerName1.setFont(new Font("Arial", Font.PLAIN, 14));
        playerName1.setForeground(Color.WHITE);
        playerName1.setBackground(new Color(0, 51, 204));
        playerName1.setOpaque(true);
        playerName1.setPreferredSize(new Dimension(150, 40));
        playerName1.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerName2.setFont(new Font("Arial", Font.PLAIN, 14));
        playerName2.setForeground(Color.WHITE);
        playerName2.setBackground(new Color(191, 64, 191));
        playerName2.setOpaque(true);
        playerName2.setPreferredSize(new Dimension(150, 40));
        playerName2.setAlignmentX(Component.RIGHT_ALIGNMENT);
    //    stats.add(Box.createRigidArea(new Dimension(100, 30)));

        add(player1Panel, BorderLayout.WEST);
        player1Panel.setLayout(new GridLayout(getNumberOfRounds()+1, getNumberOfQuestions()+1));
        player1Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 2));
        player1Panel.setBackground( new Color(51, 133, 255));
        add(categoriepanel, BorderLayout.CENTER);
        categoriepanel.setLayout(new GridLayout(getNumberOfRounds()+1, 1));
        categoriepanel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
        categoriepanel.setBackground( new Color(51, 133, 255));
        add(player2Panel, BorderLayout.EAST);
        player2Panel.setLayout(new GridLayout(getNumberOfRounds()+1, getNumberOfQuestions()+1));
        player2Panel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 10));
        player2Panel.setBackground( new Color(51, 133, 255));


        pro.setMaxRound(getNumberOfRounds()); //YAGNI
        pro.setMaxQuestion(getNumberOfQuestions()); //YAGNI

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
            player1_answers.get(i).setPreferredSize(new Dimension(35,10));
            player1_answers.get(i).setMaximumSize(new Dimension(35,10));
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
            player2_answers.get(i).setPreferredSize(new Dimension(35,10));
            player2_answers.get(i).setMaximumSize(new Dimension(35,10));
        }

        if (pro.getRound() >= 1){
            for (int i = 0; i < pro.getRoundCategories().size(); i++) {
                labelNames.get(i).setText(pro.roundCategories.get(i));
                labelNames.get(i).setFont(new Font("Arial", Font.PLAIN, 10));
                labelNames.get(i).setBackground( new Color(51, 133, 255));
                labelNames.get(i).setForeground(Color.WHITE);
                labelNames.get(i).setOpaque(true);
                labelNames.get(i).setHorizontalAlignment(SwingConstants.CENTER);

            }
        }

    //    player1Panel.add(Box.createRigidArea(new Dimension(40, 150)));
    //    player2Panel.add(Box.createRigidArea(new Dimension(40, 150)));
    //    categoriepanel.add(Box.createRigidArea(new Dimension(40, 150)));

        add(empty, BorderLayout.SOUTH);
        Border line2 = new LineBorder(new Color(51, 133, 255));
        Border margin2 = new EmptyBorder(5, 80, 5, 80);
        Border compound2 = new CompoundBorder(line2, margin2);
        empty.setBorder(compound2);
        empty.setBackground(new Color(51, 133, 255));
        empty.setForeground(Color.WHITE);
        empty.setFont(new Font("Arial", Font.PLAIN, 16));
        empty.setContentAreaFilled(false);
        empty.setOpaque(true);
    //    empty.setVisible(false);
        add(play, BorderLayout.PAGE_END);
        Border line = new LineBorder(new Color(128, 255, 128));
        Border margin = new EmptyBorder(5, 80, 5, 80);
        Border compound = new CompoundBorder(line, margin);
        play.setBorder(compound);
        play.setBackground(new Color(77, 255, 77));
        play.setForeground(Color.WHITE);
        play.setFont(new Font("Arial", Font.PLAIN, 16));
        play.setContentAreaFilled(false);
        play.setOpaque(true);

        playerName1.setText(pro.getName());

        playerName1.setMaximumSize(new Dimension(150, 40));
        playerName2.setMaximumSize(new Dimension(150, 40));

        play.addActionListener(this);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            dispose();
            CategoryPage c = new CategoryPage(pro);
        }
    }
}
