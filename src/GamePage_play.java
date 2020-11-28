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
public class GamePage_play extends JFrame implements ActionListener {

    GUI_Util util = new GUI_Util();

    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JPanel stats = new JPanel();
    JPanel categoriepanel = new JPanel();
    JPanel lowestPanel = new JPanel();
    JButton info = new JButton("Spelare 2 spelar");
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

    Player pro;
    public GamePage_play(Player player) throws FileNotFoundException, IOException {

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



        pro.setMaxRound(getNumberOfRounds());
        pro.setMaxQuestion(getNumberOfQuestions());

        //int totalbuttons = numberOfQuestions * numberOfRounds;
        int totalbuttons = getNumberOfQuestions() * getNumberOfRounds();



        player1_answers = createButtonList(totalbuttons);
        player2_answers = createButtonList(totalbuttons);

        for (int i = 0; i < totalbuttons; i++) {
            if (i < pro.getAnswers().size()){
                if (!pro.getAnswers().get(i)){
                    player1_answers.get(i).setBackground(Color.RED);
                    player1_answers.get(i).setOpaque(true);
                //    player1_answers.get(i).setBorderPainted(false);
                }else {
                    player1_answers.get(i).setBackground(Color.GREEN);
                    player1_answers.get(i).setOpaque(true);
                //    player1_answers.get(i).setBorderPainted(false);
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

        /*
        if (pro.getPoints() == 0){
            score.setText("0 - 0");
        }else {
            score.setText(String.valueOf(pro.getPoints()) + " - 0");
        }


         */

        add(stats);
        stats.setLayout(new BoxLayout(stats, BoxLayout.LINE_AXIS));
        util.setMainBackground(stats);
        stats.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        stats.add(playerName1);
        util.buttonSetFontForegBackg_white(playerName1,0,14,0,51,204);
        util.setSizeButton(playerName1,150,40,150,40);
        playerName1.setAlignmentX(Component.LEFT_ALIGNMENT);

        stats.add(Box.createRigidArea(new Dimension(20, 40)));
        stats.add(playerName2);
        util.buttonSetFontForegBackg_white(playerName2,0,14,191,64,191);
        util.setSizeButton(playerName2,150,40,150,40);
        playerName2.setAlignmentX(Component.RIGHT_ALIGNMENT);

        add(player1Panel);
        player1Panel.setLayout(new GridLayout(getNumberOfRounds(), getNumberOfQuestions()+1));
        player1Panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 2));
        util.setMainBackground(player1Panel);

        add(categoriepanel);
        categoriepanel.setLayout(new GridLayout(getNumberOfRounds()+1, 1));
        categoriepanel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 2));
        util.setMainBackground(categoriepanel);

        add(player2Panel);
        player2Panel.setLayout(new GridLayout(getNumberOfRounds(), getNumberOfQuestions()+1));
        player2Panel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 20));
        util.setMainBackground(player2Panel);

        add(lowestPanel);
        lowestPanel.setLayout(new BoxLayout(lowestPanel, BoxLayout.Y_AXIS));
        util.setMainBackground(lowestPanel);

        lowestPanel.add(info);
        util.buttonSetFontForeg(info,2,16,51,133,255);
        info.setContentAreaFilled(false);
        info.setBorderPainted(false);
        info.setOpaque(false);
        util.setSizeButton(info,350,180,350,180);

        lowestPanel.add(play);
        Border compound = util.setCompoundBorder(128,255,128,5,151,5,151);
        play.setBorder(compound);
        util.buttonSetFontForegBackg_white(play,0,16,77,255,77);

        playerName1.setText(pro.getName());

        Container contentPane = getContentPane();
        contentPane.add(stats, BorderLayout.NORTH);
        contentPane.add(player1Panel, BorderLayout.WEST);
        contentPane.add(categoriepanel, BorderLayout.CENTER);
        contentPane.add(player2Panel, BorderLayout.EAST);
        contentPane.add(lowestPanel, BorderLayout.SOUTH);

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
