import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Matte;
import QuestionsHandler.Database;
import QuestionsHandler.Questions;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */
public class QuestionPage extends JFrame implements ActionListener {

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b1empty = new JButton();
    JButton b2empty = new JButton();
    JButton b3empty = new JButton();


    List<JButton> buttonsToPaintList = new ArrayList<>();

    JLabel round = new JLabel();
    JLabel player = new JLabel();
    JLabel questionNumber = new JLabel();

    JLabel category = new JLabel();
    JLabel question = new JLabel();

    JButton answer1 = new JButton();
    JButton answer2 = new JButton();
    JButton answer3 = new JButton();
    JButton answer4 = new JButton();

    JLabel timer = new JLabel("Timer here");

    JPanel north = new JPanel();
    JPanel south = new JPanel();
    JFrame frame = new JFrame();

    Player pro;
    List<Questions> randomListToPull = new ArrayList<>();
    List<String> randomAnswerList = new ArrayList<>();

    String rightAnswerFromList;

    public QuestionPage(Player p){
        pro = p;

        round.setText(("Rond " + pro.getRound()));
        questionNumber.setText("Fråga " + pro.getQuestion());

        player.setText(pro.getName());

        randomListToPull = findList(pro.getRoundCategories().get(pro.getRound()-1));

        question.setText(randomListToPull.get(pro.getRound()).getQuestion());

        randomAnswerList = randomListToPull.get(pro.getRound()).getAnswerObject().getAnswersList();

       rightAnswerFromList = randomListToPull.get(pro.getRound()).getAnswerObject().getRightAnswer();

        Collections.shuffle(randomAnswerList);

        answer1.setText(randomAnswerList.get(0));
        answer2.setText(randomAnswerList.get(1));
        answer3.setText(randomAnswerList.get(2));
        answer4.setText(randomAnswerList.get(3));

        category.setText(pro.getRoundCategories().get(pro.getRoundCategories().size()-1));

        buttonsToPaintList.add(b1);
        buttonsToPaintList.add(b2);
        buttonsToPaintList.add(b3);


        if (pro.getQuestion() > 0){
            for (int i = 0; i < pro.getQuestion()-1; i++) {
                if (pro.getAnswers().get(i) == false){
                    paintRed(buttonsToPaintList.get(i));
                }else {
                    paintGreen(buttonsToPaintList.get(i));
                }
            }
        }else if (pro.getQuestion() == 0){
            for (int i = 0; i < pro.getMaxQuestion(); i++) {
                resetPaint(buttonsToPaintList.get(i));
            }
        }
        //TODO: fördela text i JLabel på flera rader


    //    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(north);
        north.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
    //    north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        north.setBackground( new Color(51, 133, 255));
        north.add(b1);
        b1.setPreferredSize(new Dimension(35,30));
        b1.setMaximumSize(new Dimension(35,30));
        north.add(b2);
        b2.setPreferredSize(new Dimension(35,30));
        b2.setMaximumSize(new Dimension(35,30));

        north.add(b3);
        b3.setPreferredSize(new Dimension(35,30));
        b3.setMaximumSize(new Dimension(35,30));

        north.add(b1empty);
        b1empty.setPreferredSize(new Dimension(35,30));
        b1empty.setMaximumSize(new Dimension(35,30));
        b1empty.setBackground(new Color(51, 133, 255));
        b1empty.setBorderPainted(false);


        north.add(b2empty);
        b2empty.setPreferredSize(new Dimension(35,30));
        b2empty.setMaximumSize(new Dimension(35,30));
        b2empty.setBackground(new Color(51, 133, 255));
        b2empty.setBorderPainted(false);



    /*    north.add(b3empty);
        b3empty.setPreferredSize(new Dimension(35,10));
        b3empty.setMaximumSize(new Dimension(35,10));
        b3empty.setVisible(false);*/


        north.add(player);
        player.setFont(new Font("Arial", Font.PLAIN, 16));
        player.setForeground(Color.WHITE);
        player.setBackground(new Color(0, 51, 204));
        player.setOpaque(true);
        player.setBorder(new EmptyBorder(10, 30, 10, 30));

        frame.add(south);
        south.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        south.setLayout(new GridLayout(8,1));
        south.setBackground( new Color(51, 133, 255));
        south.add(round);
        round.setFont(new Font("Arial", Font.PLAIN, 14));
        round.setForeground(Color.WHITE);

        south.add(category);
        category.setBackground(new Color(204, 0, 204));
        category.setForeground(Color.WHITE);
        category.setFont(new Font("Arial", Font.BOLD, 14));
    //    category.setContentAreaFilled(false);
        category.setOpaque(true);
        category.setPreferredSize(new Dimension(250, 30));
        category.setBorder(new EmptyBorder(10, 10, 10, 10));

        //    Border border = BorderFactory.createLineBorder(new Color(51, 133, 255),10);
    //    category.setBorder(border);
        south.add(questionNumber);
        questionNumber.setFont(new Font("Arial", Font.PLAIN, 14));
        questionNumber.setForeground(Color.WHITE);

        south.add(question);
        question.setBackground(new Color(255, 51, 133));
        question.setForeground(Color.WHITE);
        question.setFont(new Font("Arial", Font.BOLD, 12));
        question.setBorder(new EmptyBorder(10, 10, 10, 10));

        //    question.setContentAreaFilled(false);
        question.setOpaque(true);
        question.setPreferredSize(new Dimension(250, 30));

        south.add(answer1);
        answer1.setBackground(new Color(163, 102, 255));
        answer1.setForeground(Color.WHITE);
        answer1.setFont(new Font("Arial", Font.BOLD, 14));
        answer1.setContentAreaFilled(false);
        answer1.setOpaque(true);
        answer1.setPreferredSize(new Dimension(250, 30));

        south.add(answer2);
        answer2.setBackground(new Color(163, 102, 255));
        answer2.setForeground(Color.WHITE);
        answer2.setFont(new Font("Arial", Font.BOLD, 14));
        answer2.setContentAreaFilled(false);
        answer2.setOpaque(true);
        answer2.setPreferredSize(new Dimension(250, 30));

        south.add(answer3);
        answer3.setBackground(new Color(163, 102, 255));
        answer3.setForeground(Color.WHITE);
        answer3.setFont(new Font("Arial", Font.BOLD, 14));
        answer3.setContentAreaFilled(false);
        answer3.setOpaque(true);
        answer3.setPreferredSize(new Dimension(250, 30));

        south.add(answer4);
        answer4.setBackground(new Color(163, 102, 255));
        answer4.setForeground(Color.WHITE);
        answer4.setFont(new Font("Arial", Font.BOLD, 14));
        answer4.setContentAreaFilled(false);
        answer4.setOpaque(true);
        answer4.setPreferredSize(new Dimension(250, 30));

        Container contentPane = getContentPane();
        contentPane.add(north, BorderLayout.NORTH);
        contentPane.add(south, BorderLayout.CENTER);

        category.setMaximumSize(new Dimension(250, 30));
        question.setMaximumSize(new Dimension(250, 30));
        answer1.setMaximumSize(new Dimension(250, 30));
        answer2.setMaximumSize(new Dimension(250, 30));
        answer3.setMaximumSize(new Dimension(250, 30));
        answer4.setMaximumSize(new Dimension(250, 30));


        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        answer1.addActionListener(this);
        answer2.addActionListener(this);
        answer3.addActionListener(this);
        answer4.addActionListener(this);

    }

    public void setAnswer1(JButton answer1) {
        this.answer1 = answer1;
    }

    public void setAnswer2(JButton answer2) {
        this.answer2 = answer2;
    }

    public void setAnswer3(JButton answer3) {
        this.answer3 = answer3;
    }

    public void setAnswer4(JButton answer4) {
        this.answer4 = answer4;
    }

    public void setCategory(JLabel category, String categroyText) {
        this.category = category;
        category.setText(categroyText);
    }

    public void paintRed(JButton jb){
        jb.setBackground(Color.RED);
        jb.setOpaque(true);
        jb.setBorderPainted(false);
    }
    public void paintGreen(JButton jb){
        jb.setBackground(Color.GREEN);
        jb.setOpaque(true);
        jb.setBorderPainted(false);
    }
    public void resetPaint(JButton jb){
        jb.setBackground(null);
    }

    final String animalNatureName = "Djur & natur";
    final String artLiteratureName = "Konst & literatur";
    final String generalKnowledgeName = "Allmän kunskap";
    final String mathName = "Matte";
    final String musicName = "Musik";
    final String popCultureName = "Pop Kultur";
    final String sportsName = "Idrott";
    final String technologyName = "Teknologi";
    final String tvShowsName = "TV-show";


    public List<Questions> findList(String categoryName) {

        return switch (categoryName) {
            case animalNatureName -> new AnimalsNature().getAnimalsNatureList();
            case artLiteratureName -> new ArtLiterature().getArtLiteratureList();
            case generalKnowledgeName -> new GeneralKnowledge().getGeneralKnowledgeList();
            case mathName -> new Matte().getMathList();
            case musicName -> new Music().getMusicList();
            case popCultureName -> new PopCulture().getPopCultureList();
            case sportsName -> new Sports().getSportsList();
            case technologyName -> new Technology().getTechnologyList();
            case tvShowsName -> new TVShows().getTvShowsList();
            default -> null;
        };
    }

    public Boolean checkAnswers(String a){

    if (rightAnswerFromList.equals(a)){
        return true;
    }else {
        return false;
    }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == answer1){
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == false){
            if (!checkAnswers(randomAnswerList.get(0))) {
                answer1.setBackground(Color.RED);
                pro.answersAddToList(false);
                System.out.println("Du fick fel");
                //}else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == true){
            }else if (checkAnswers(randomAnswerList.get(0))){
                answer1.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.setPoints(pro.getPoints()+1);
                System.out.println("Du fick rätt");
            }
            answer1.setOpaque(true);
            answer1.setBorderPainted(false);
        }else if (e.getSource() == answer2){
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == false){
            if (!checkAnswers(randomAnswerList.get(1))) {
                answer2.setBackground(Color.RED);
                pro.answersAddToList(false);
                System.out.println("Du fick fel");
          //  }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == true){
            }else if (checkAnswers(randomAnswerList.get(1))){
                answer2.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.setPoints(pro.getPoints()+1);
                System.out.println("Du fick rätt");
            }
            answer2.setOpaque(true);
            answer2.setBorderPainted(false);

        }else if (e.getSource() == answer3){
           // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == false){
            if (!checkAnswers(randomAnswerList.get(2))) {
                answer3.setBackground(Color.RED);
                pro.answersAddToList(false);
                System.out.println("Du fick fel");
        //    }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == true){
            }else if (checkAnswers(randomAnswerList.get(2))){
                answer3.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.setPoints(pro.getPoints()+1);
                System.out.println("Du fick rätt");
            }
            answer3.setOpaque(true);
            answer3.setBorderPainted(false);

        }else if (e.getSource() == answer4){
           // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == false){
            if (!checkAnswers(randomAnswerList.get(3))) {
                answer4.setBackground(Color.RED);
                pro.answersAddToList(false);
                System.out.println("Du fick fel");
           // }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == true){
            }else if (checkAnswers(randomAnswerList.get(3))){
                answer4.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.setPoints(pro.getPoints()+1);
                System.out.println("Du fick rätt");
            }
            answer4.setOpaque(true);
            answer4.setBorderPainted(false);

        }


        if (pro.getQuestion() == pro.getMaxQuestion() && pro.getRound() == pro.getMaxRound()){
            pro.setQuestion(0);
            dispose();
            ResultPage r = new ResultPage(pro);

        }else if (pro.getQuestion() == pro.getMaxQuestion()){
            pro.setQuestion(0);
            try {
                dispose();
                GamePage g = new GamePage(pro);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if (pro.getQuestion() <= pro.getMaxQuestion()){
            pro.setQuestion(pro.getQuestion()+1);

            JOptionPane.showMessageDialog(null, " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }


            dispose();
            QuestionPage q = new QuestionPage(pro);
        }
    }
}
