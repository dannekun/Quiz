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
public class GamePage_result extends JFrame implements ActionListener {

    GUI_Util util = new GUI_Util();

    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JPanel stats = new JPanel();
    JPanel categoriepanel = new JPanel();
    JPanel lowestPanel = new JPanel();
    JButton info = new JButton("Spelare 2 spelar");
    JButton playerName1 = new JButton();
    JButton playerName2 = new JButton("Spelare");


    JButton play = new JButton("Spela igen");
    JButton closeGame = new JButton("Stäng av");

    List<JButton> player1_answers;
    List<JButton> player2_answers;

    List<JLabel> labelNames;

    Properties p = new Properties();

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
    public GamePage_result(Player player, Player player2) throws FileNotFoundException, IOException {

        categoriepanel.removeAll();
        revalidate();
        repaint();

        pro = player;

        playerName2.setText(player2.getName());

        try{
            p.load(new FileInputStream("src/RoundQuestions.properties"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String stringRounds = p.getProperty("numberOfRounds", "2");

        setNumberOfRounds(Integer.parseInt(stringRounds));
        String stringQuestions = p.getProperty("numberOfQuestions", "2");

        setNumberOfQuestions(Integer.parseInt(stringQuestions));


        pro.setMaxRound(getNumberOfRounds());
        pro.setMaxQuestion(getNumberOfQuestions());

        int totalbuttons = getNumberOfQuestions() * getNumberOfRounds();

        player1_answers = createButtonList(totalbuttons);
        player2_answers = createButtonList(totalbuttons);

        for (int i = 0; i < totalbuttons; i++) {

            if (i < pro.getAnswers().size()){

                if (!pro.getAnswers().get(i)){

                    player1_answers.get(i).setBackground(Color.RED);
                    player1_answers.get(i).setOpaque(true);
                    player1_answers.get(i).setBorder(new LineBorder(new Color(51, 133, 255)));

                }else {

                    player1_answers.get(i).setBackground(Color.GREEN);
                    player1_answers.get(i).setOpaque(true);
                    player1_answers.get(i).setBorder(new LineBorder(new Color(51, 133, 255)));

                }

            }

            player1Panel.add(player1_answers.get(i));
            util.setSizeButton( player1_answers.get(i), 35,10,35,10);

        }

        labelNames = createLabelList(getNumberOfRounds());

        for (int i = 0; i < pro.getMaxRound(); i++) {

            if (i < pro.getRound()){

                labelNames.get(i).setText(pro.getRoundCategories().get(i));

            }else {

                labelNames.get(i).setText("          ");

            }

            categoriepanel.add(labelNames.get(i));

        }

        for (int i = 0; i <totalbuttons; i++) {

            if (i < player2.getAnswers().size()){

                if (!player2.getAnswers().get(i)){

                    player2_answers.get(i).setBackground(Color.RED);
                    player2_answers.get(i).setOpaque(true);
                    player2_answers.get(i).setBorder(new LineBorder(new Color(51, 133, 255)));

                }else {

                    player2_answers.get(i).setBackground(Color.GREEN);
                    player2_answers.get(i).setOpaque(true);
                    player2_answers.get(i).setBorder(new LineBorder(new Color(51, 133, 255)));
                }

            }

            player2Panel.add(player2_answers.get(i));
            util.setSizeButton(player2_answers.get(i), 35,10,35,10);
        }

        if (pro.getRound() >= 1){

            for (int i = 0; i < pro.getRoundCategories().size(); i++) {

                labelNames.get(i).setText(pro.roundCategories.get(i));
                util.labelSetFontForegBackg_white(labelNames.get(i),0,12,51,133,255);
                labelNames.get(i).setHorizontalAlignment(SwingConstants.CENTER);

            }

        }
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
        player2Panel.setLayout(new GridLayout(getNumberOfRounds(), getNumberOfQuestions()+1));
        categoriepanel.setBorder(BorderFactory.createEmptyBorder(20, 2, 0, 2));
        util.setMainBackground(categoriepanel);

        add(player2Panel);
        player2Panel.setLayout(new GridLayout(getNumberOfRounds(), getNumberOfQuestions()+1));
        player2Panel.setBorder(BorderFactory.createEmptyBorder(10, 2, 10, 20));
        util.setMainBackground(player2Panel);

        add(lowestPanel);
        lowestPanel.setLayout(new BorderLayout());
        util.setMainBackground(lowestPanel);

        lowestPanel.add(info, BorderLayout.NORTH);
        util.buttonSetFontForeg(info,2,16,51,133,255);
        info.setContentAreaFilled(false);
        info.setBorderPainted(false);
        info.setOpaque(false);
        util.setSizeButton(info,350,180,350,180);

        lowestPanel.add(play, BorderLayout.WEST);
        Border compound = util.setCompoundBorder(128,255,128,5,45,5,45);
        play.setBorder(compound);
        util.buttonSetFontForegBackg_white(play,0,16,77,255,77);

        lowestPanel.add(closeGame, BorderLayout.EAST);
        Border compound2 = util.setCompoundBorder(255, 77, 77,5,50,5,50);
        closeGame.setBorder(compound2);
        util.buttonSetFontForegBackg_white(closeGame,0,16,255, 77, 77);

        playerName1.setText(pro.getName());

        Container contentPane = getContentPane();
        contentPane.add(stats, BorderLayout.NORTH);
        contentPane.add(player1Panel, BorderLayout.WEST);
        contentPane.add(categoriepanel, BorderLayout.CENTER);
        contentPane.add(player2Panel, BorderLayout.EAST);
        contentPane.add(lowestPanel, BorderLayout.SOUTH);

        play.addActionListener(this);
        closeGame.addActionListener(this);

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
            pro.setCloseGameOption(1);
            dispose();

        }else if (e.getSource() == closeGame){
            pro.setCloseGameOption(2);
            dispose();

        }

    }

    public Player findClickPlay(){
        play.addActionListener(this);
        closeGame.addActionListener(this);

        return pro;
    }
}
