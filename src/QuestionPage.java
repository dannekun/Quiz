import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;
import QuestionsHandler.Database;
import QuestionsHandler.Questions;

import javax.swing.*;
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

 //   Database database = new Database();

    List<JButton> buttonsToPaintList = new ArrayList<>();

    JLabel round = new JLabel("Round nr");
    JLabel player = new JLabel();
    JLabel questionNumber = new JLabel();

    JLabel category = new JLabel("Category");
    JLabel question = new JLabel();

    JButton answer1 = new JButton();
    JButton answer2 = new JButton();
    JButton answer3 = new JButton();
    JButton answer4 = new JButton();

  //  JLabel timer = new JLabel("Timer here");

    JPanel north = new JPanel();
    JPanel south = new JPanel();

    Player pro;
    List<Questions> randomListToPull = new ArrayList<>();
    List<String> randomAnswerList = new ArrayList<>();

    String rightAnswerFromList;


    Database d = new Database();

    String questionToAsk = null;

    public QuestionPage(Player p) {


        pro = p;

        round.setText(("Rond " + pro.getRound()));
        questionNumber.setText("Fråga " + pro.getQuestion());

        player.setText(pro.getName());

        randomListToPull = findList(pro.getRoundCategories().get(pro.getRound() - 1));

        //questionToAsk = randomListToPull.get(pro.getRound()).getQuestion();

        //questionToAsk = randomListToPull.get(whichCatToChoose).getQuestion();


        List<Questions> newQuestionsForPlayerToAsk = randomListToPull;

        randomListToPull = newQuestionsForPlayerToAsk;

        Collections.shuffle(randomListToPull);

        if (pro.currentQuestion.isEmpty()) {
            question.setText(randomListToPull.get(0).getQuestion());
            pro.addQuestionToCurrentList(randomListToPull.get(0).getQuestion());

            pro.addQuestionBetweenPlayers(randomListToPull.get(0));
        } else {


            randomListToPull = findQuestion(randomListToPull, pro.getCurrentQuestion());

            //question.setText(randomListToPull.get(pro.getRound()).getQuestion());
            question.setText(randomListToPull.get(0).getQuestion());
            // pro.addQuestionToCurrentList(randomListToPull.get(pro.getRound()).getQuestion());
            pro.addQuestionToCurrentList(randomListToPull.get(0).getQuestion());
            pro.addQuestionBetweenPlayers(randomListToPull.get(0));
        }


        // randomAnswerList = randomListToPull.get(pro.getRound()).getAnswerObject().getAnswersList();
        randomAnswerList = randomListToPull.get(0).getAnswerObject().getAnswersList();

        // rightAnswerFromList = randomListToPull.get(pro.getRound()).getAnswerObject().getRightAnswer();

        rightAnswerFromList = randomListToPull.get(0).getAnswerObject().getRightAnswer();
        Collections.shuffle(randomAnswerList);


        answer1.setText(randomAnswerList.get(0));
        answer2.setText(randomAnswerList.get(1));
        answer3.setText(randomAnswerList.get(2));
        answer4.setText(randomAnswerList.get(3));

        category.setText(pro.getRoundCategories().get(pro.getRoundCategories().size() - 1));


        for (int i = 0; i < pro.getMaxQuestion(); i++) {
            buttonsToPaintList.add(new JButton());
        }


        if (pro.getQuestion() > 0) {
            for (int i = 0; i < pro.getQuestion() - 1; i++) {
                //   if (pro.getAnswers().get(i) == false){
                if (!pro.getRoundAnswers().get(i)) {
                    paintRed(buttonsToPaintList.get(i));
                    System.out.println("röd");
                } else if (pro.getRoundAnswers().get(i)){
                    paintGreen(buttonsToPaintList.get(i));
                    System.out.println("grön");
                }
            }
        } else if (pro.getQuestion() == 0) {
            for (int i = 0; i < pro.getMaxQuestion(); i++) {
                resetPaint(buttonsToPaintList.get(i));
            }
        }

    /*    for (JButton button : buttonsToPaintList) {
            north.add(button);
        }*/

        add(north);
        north.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        north.setBackground(new Color(51, 133, 255));
        for (int i = 0; i < buttonsToPaintList.size(); i++) {
            north.add(buttonsToPaintList.get(i));
            buttonsToPaintList.get(i).setPreferredSize(new Dimension(30,30));
            buttonsToPaintList.get(i).setMaximumSize(new Dimension(30,30));
        }
        north.add(player);
        player.setFont(new Font("Arial", Font.PLAIN, 14));
        player.setForeground(Color.WHITE);
        player.setBackground(new Color(0, 51, 204));
        player.setOpaque(true);
        player.setBorder(new EmptyBorder(10, 30, 10, 30));

        add(south);
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
        category.setOpaque(true);
        category.setPreferredSize(new Dimension(250, 30));
        category.setBorder(new EmptyBorder(10, 10, 10, 10));

        south.add(questionNumber);
        questionNumber.setFont(new Font("Arial", Font.PLAIN, 14));
        questionNumber.setForeground(Color.WHITE);

        south.add(question);
        question.setBackground(new Color(255, 51, 133));
        question.setForeground(Color.WHITE);
        question.setFont(new Font("Arial", Font.BOLD, 12));
        question.setBorder(new EmptyBorder(10, 10, 10, 10));
        question.setOpaque(true);
        question.setPreferredSize(new Dimension(250, 30));

        south.add(answer1);
        answer1.setBackground(new Color(163, 102, 255));
        answer1.setForeground(Color.WHITE);
        answer1.setFont(new Font("Arial", Font.BOLD, 14));
        answer1.setContentAreaFilled(false);
        answer1.setOpaque(true);
        answer1.setBorderPainted(false);
        answer1.setPreferredSize(new Dimension(250, 30));

        south.add(answer2);
        answer2.setBackground(new Color(163, 102, 255));
        answer2.setForeground(Color.WHITE);
        answer2.setFont(new Font("Arial", Font.BOLD, 14));
        answer2.setContentAreaFilled(false);
        answer2.setOpaque(true);
        answer2.setBorderPainted(false);
        answer2.setPreferredSize(new Dimension(250, 30));

        south.add(answer3);
        answer3.setBackground(new Color(163, 102, 255));
        answer3.setForeground(Color.WHITE);
        answer3.setFont(new Font("Arial", Font.BOLD, 14));
        answer3.setContentAreaFilled(false);
        answer3.setOpaque(true);
        answer3.setBorderPainted(false);
        answer3.setPreferredSize(new Dimension(250, 30));

        south.add(answer4);
        answer4.setBackground(new Color(163, 102, 255));
        answer4.setForeground(Color.WHITE);
        answer4.setFont(new Font("Arial", Font.BOLD, 14));
        answer4.setContentAreaFilled(false);
        answer4.setOpaque(true);
        answer4.setBorderPainted(false);
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

    public void paintRed(JButton jb) {
        jb.setBackground(Color.RED);
        jb.setOpaque(true);
        jb.setBorderPainted(false);
    }

    public void paintGreen(JButton jb) {
        jb.setBackground(Color.GREEN);
        jb.setOpaque(true);
        jb.setBorderPainted(false);
    }

    public void resetPaint(JButton jb) {
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
            case mathName -> new Math().getMathList();
            case musicName -> new Music().getMusicList();
            case popCultureName -> new PopCulture().getPopCultureList();
            case sportsName -> new Sports().getSportsList();
            case technologyName -> new Technology().getTechnologyList();
            case tvShowsName -> new TVShows().getTvShowsList();
            default -> null;
        };
    }

    public Boolean checkAnswers(String a) {

        return rightAnswerFromList.equals(a);

    }

    public void findRightAnswerAndPaint(JButton a, JButton b, JButton c, JButton d, String rightAnswer) {
        if (a.getText().equals(rightAnswer)) {
            a.setBackground(Color.GREEN);
            a.setOpaque(true);
            a.setBorderPainted(false);
        } else if (b.getText().equals(rightAnswer)) {
            b.setBackground(Color.GREEN);
            b.setOpaque(true);
            b.setBorderPainted(false);
        } else if (c.getText().equals(rightAnswer)) {
            c.setBackground(Color.GREEN);
            c.setOpaque(true);
            c.setBorderPainted(false);
        } else if (d.getText().equals(rightAnswer)) {
            d.setBackground(Color.GREEN);
            d.setOpaque(true);
            d.setBorderPainted(false);
        }
    }

    public List<Questions> findQuestion(List<Questions> questionListToFind, List<String> listToCompareWith) {
        boolean found = false;
        List<Questions> questionsToUse = new ArrayList<>();
        for (Questions questions : questionListToFind) {
            for (String s : listToCompareWith) {
                if (questions.getQuestion().equals(s)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                questionsToUse.add(questions);
            }
            found = false;
        }
        Collections.shuffle(questionsToUse);
        return questionsToUse;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        pro.setDidYouGetIt(false);

        if (e.getSource() == answer1) {
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == false){
            if (!checkAnswers(randomAnswerList.get(0))) {
                answer1.setBackground(Color.RED);
                pro.setDidYouGetIt(false);


                //}else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == true){
            } else if (checkAnswers(randomAnswerList.get(0))) {
                answer1.setBackground(Color.GREEN);
                pro.setDidYouGetIt(true);
            }
            answer1.setOpaque(true);
            answer1.setBorderPainted(false);
            pro.setClicked(true);
        } else if (e.getSource() == answer2) {
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == false){
            if (!checkAnswers(randomAnswerList.get(1))) {
                answer2.setBackground(Color.RED);
                pro.setDidYouGetIt(false);
                //  }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == true){
            } else if (checkAnswers(randomAnswerList.get(1))) {
                answer2.setBackground(Color.GREEN);
                pro.setDidYouGetIt(true);
            }
            answer2.setOpaque(true);
            answer2.setBorderPainted(false);
            pro.setClicked(true);

        } else if (e.getSource() == answer3) {
            // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == false){
            if (!checkAnswers(randomAnswerList.get(2))) {
                answer3.setBackground(Color.RED);
                pro.setDidYouGetIt(false);
                //    }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == true){
            } else if (checkAnswers(randomAnswerList.get(2))) {
                answer3.setBackground(Color.GREEN);
                pro.setDidYouGetIt(true);
            }
            answer3.setOpaque(true);
            answer3.setBorderPainted(false);
            pro.setClicked(true);
        } else if (e.getSource() == answer4) {
            // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == false){
            if (!checkAnswers(randomAnswerList.get(3))) {
                answer4.setBackground(Color.RED);
                pro.setDidYouGetIt(false);
                // }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == true){
            } else if (checkAnswers(randomAnswerList.get(3))) {
                answer4.setBackground(Color.GREEN);
                pro.setDidYouGetIt(true);
            }
            answer4.setOpaque(true);
            answer4.setBorderPainted(false);
            pro.setClicked(true);
        }

        findRightAnswerAndPaint(answer1,answer2,answer3,answer4,rightAnswerFromList);


    }

    public void answers(Player p){

    }

    public Player endGame(Player p){

        pro = p;

        if (pro.getQuestion() == pro.getMaxQuestion() && pro.getRound() == pro.getMaxRound()) {
            pro.setQuestion(0);

            if (pro.isDidYouGetIt()) {
               // JOptionPane.showMessageDialog(null, "Right!");
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);

                System.out.println("du är i allt");
            } else {
                //JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList + "'\'");
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
            }


            dispose();
            ResultPage r = new ResultPage(pro);

        } else if (pro.getQuestion() == pro.getMaxQuestion()) {
            pro.setQuestion(0);
            pro.currentQuestion.clear();
            pro.roundAnswers.clear();
            pro.setPlayedRound(true);

            if (pro.isDidYouGetIt()) {
              //  JOptionPane.showMessageDialog(null, "Right!");
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);
                System.out.println("du är i lika mycket ");
            } else {
               // JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList + "'\'");
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
            }


            dispose();
            // HÄR SKA DET VA GAMEPAGE_WAITING
            // STATE 3
            //
            // GamePage_play g = new GamePage_play(pro);
        } else if (pro.getQuestion() <= pro.getMaxQuestion()) {
            pro.setQuestion(pro.getQuestion() + 1);

            if (pro.isDidYouGetIt()) {
              //  JOptionPane.showMessageDialog(null, "Right!");
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);
                System.out.println("du är i för lite ");
            } else {
               // JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList + "'\'");
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }


            dispose();
            //QuestionPage q = new QuestionPage(pro);
        }

        return pro;

    }

    public Player findClickPlay(){
        answer1.addActionListener(this);
        answer2.addActionListener(this);
        answer3.addActionListener(this);
        answer4.addActionListener(this);
        return pro;
    }
}
