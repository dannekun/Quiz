import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Ivona Zoricic
 * Date: 2020-11-26
 * Time: 12:09
 * Project: Quiz
 * Copywrite: MIT
 */
public class GamePage_waiting extends JFrame {

    GUI_Util util = new GUI_Util();

    JPanel player1Panel = new JPanel();
    JPanel player2Panel = new JPanel();
    JPanel stats = new JPanel();
    JPanel categoriepanel = new JPanel();
    JPanel lowestPanel = new JPanel();
    JButton info = new JButton("Spelare 2 spelar");
    JButton playerName1 = new JButton();
    JButton playerName2 = new JButton("Spelare");

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

    public void showWindow(Player player, Player player2){

        lowestPanel.removeAll();
        stats.removeAll();
        player1Panel.removeAll();
        player2Panel.removeAll();
        categoriepanel.removeAll();

        revalidate();
        repaint();

        pro = player;

        info.setText(player2.getName() + " spelar...");
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

        for (int i = 0; i <totalbuttons; i++) {

            if (i < player2.getAnswers().size()){

                if (!player2.getAnswers().get(i)){

                    player2_answers.get(i).setBackground(Color.RED);
                    player2_answers.get(i).setOpaque(true);
                    player2_answers.get(i).setBorderPainted(false);

                }else {

                    player2_answers.get(i).setBackground(Color.GREEN);
                    player2_answers.get(i).setOpaque(true);
                    player2_answers.get(i).setBorderPainted(false);

                }

            }

            player2Panel.add(player2_answers.get(i));
            player2_answers.get(i).setPreferredSize(new Dimension(35,10));
            player2_answers.get(i).setMaximumSize(new Dimension(35,10));

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

        if (pro.getRound() >= 1){

            for (int i = 0; i < pro.getRoundCategories().size(); i++) {

                labelNames.get(i).setText(pro.getRoundCategories().get(i));
                labelNames.get(i).setFont(new Font("Arial", Font.PLAIN, 10));
                labelNames.get(i).setBackground( new Color(51, 133, 255));
                labelNames.get(i).setForeground(Color.WHITE);
                labelNames.get(i).setOpaque(true);
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
        util.buttonSetFontForeg(info,2,16,0,0,77);
        util.setSizeButton(info,350,200,350,200);
        info.setContentAreaFilled(false);
        info.setBorderPainted(false);
        info.setOpaque(false);

        playerName1.setText(pro.getName());

        Container contentPane = getContentPane();
        contentPane.add(stats, BorderLayout.NORTH);
        contentPane.add(player1Panel, BorderLayout.WEST);
        contentPane.add(categoriepanel, BorderLayout.CENTER);
        contentPane.add(player2Panel, BorderLayout.EAST);
        contentPane.add(lowestPanel, BorderLayout.SOUTH);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void closeWindow(){
        dispose();
    }

}
