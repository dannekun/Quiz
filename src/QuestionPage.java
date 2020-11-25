import QuestionsHandler.Answers;
import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;
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
import java.util.concurrent.TimeUnit;


/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */
public class QuestionPage extends JFrame implements ActionListener {

    Database database = new Database();

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();
    JButton b1empty = new JButton();
    JButton b2empty = new JButton();
    JButton b3empty = new JButton();


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

    JLabel timer = new JLabel("Timer here");


    JPanel north = new JPanel();
    JPanel center = new JPanel();
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

        Collections.shuffle(newQuestionsForPlayerToAsk);

        randomListToPull = newQuestionsForPlayerToAsk;

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
                } else {
                    paintGreen(buttonsToPaintList.get(i));
                }
            }
        } else if (pro.getQuestion() == 0) {
            for (int i = 0; i < pro.getMaxQuestion(); i++) {
                resetPaint(buttonsToPaintList.get(i));
            }
        }

        for (JButton button : buttonsToPaintList) {
            north.add(button);
        }

            //    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
            add(north);
            north.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
            //    north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
            north.setBackground(new Color(51, 133, 255));
            for (int i = 0; i < buttonsToPaintList.size(); i++) {
                north.add(buttonsToPaintList.get(i));
            }

            north.setLayout(new GridLayout(1, 5));


            north.add(round);

            north.add(player);

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
            answer1.setBorderPainted(false);
            answer1.setOpaque(true);
            answer1.setPreferredSize(new Dimension(250, 30));

            south.add(answer2);
            answer2.setBackground(new Color(163, 102, 255));
            answer2.setForeground(Color.WHITE);
            answer2.setFont(new Font("Arial", Font.BOLD, 14));
            answer2.setContentAreaFilled(false);
            answer2.setBorderPainted(false);
            answer2.setOpaque(true);
            answer2.setPreferredSize(new Dimension(250, 30));

            south.add(answer3);
            answer3.setBackground(new Color(163, 102, 255));
            answer3.setForeground(Color.WHITE);
            answer3.setFont(new Font("Arial", Font.BOLD, 14));
            answer3.setContentAreaFilled(false);
            answer3.setBorderPainted(false);
            answer3.setOpaque(true);
            answer3.setPreferredSize(new Dimension(250, 30));

            south.add(answer4);
            answer4.setBackground(new Color(163, 102, 255));
            answer4.setForeground(Color.WHITE);
            answer4.setFont(new Font("Arial", Font.BOLD, 14));
            answer4.setContentAreaFilled(false);
            answer4.setBorderPainted(false);
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




        public void paintRed (JButton jb){
            jb.setBackground(Color.RED);
            jb.setOpaque(true);
            jb.setBorderPainted(false);
        }
        public void paintGreen (JButton jb){
            jb.setBackground(Color.GREEN);
            jb.setOpaque(true);
            jb.setBorderPainted(false);
        }
        public void resetPaint (JButton jb){
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


        public List<Questions> findList (String categoryName){

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

        public Boolean checkAnswers (String a){

            return rightAnswerFromList.equals(a);

        }

        public void findRightAnswerAndPaint (JButton a, JButton b, JButton c, JButton d, String rightAnswer){
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

        public List<Questions> findQuestion (List < Questions > questionListToFind, List < String > listToCompareWith){
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
        boolean didYouGetIt = false;
        if (e.getSource() == answer1) {
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == false){
            if (!checkAnswers(randomAnswerList.get(0))) {
                answer1.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;


                //}else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == true){
            } else if (checkAnswers(randomAnswerList.get(0))) {
                answer1.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);
                didYouGetIt = true;
            }
            answer1.setOpaque(true);
            answer1.setBorderPainted(false);
        } else if (e.getSource() == answer2) {
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == false){
            if (!checkAnswers(randomAnswerList.get(1))) {
                answer2.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;
                //  }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == true){
            } else if (checkAnswers(randomAnswerList.get(1))) {
                answer2.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);
                didYouGetIt = true;
            }
            answer2.setOpaque(true);
            answer2.setBorderPainted(false);

        } else if (e.getSource() == answer3) {
            // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == false){
            if (!checkAnswers(randomAnswerList.get(2))) {
                answer3.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;
                //    }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == true){
            } else if (checkAnswers(randomAnswerList.get(2))) {
                answer3.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);
                didYouGetIt = true;
            }
            answer3.setOpaque(true);
            answer3.setBorderPainted(false);

        } else if (e.getSource() == answer4) {
            // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == false){
            if (!checkAnswers(randomAnswerList.get(3))) {
                answer4.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;
                // }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == true){
            } else if (checkAnswers(randomAnswerList.get(3))) {
                answer4.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints() + 1);
                didYouGetIt = true;
            }
            answer4.setOpaque(true);
            answer4.setBorderPainted(false);

        }


        if (pro.getQuestion() == pro.getMaxQuestion() && pro.getRound() == pro.getMaxRound()) {
            pro.setQuestion(0);
            if (didYouGetIt) {
                JOptionPane.showMessageDialog(null, "Right!");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList + "'\'");
            }
            dispose();
            ResultPage r = new ResultPage(pro);

        } else if (pro.getQuestion() == pro.getMaxQuestion()) {
            pro.setQuestion(0);
            pro.currentQuestion.clear();
            pro.roundAnswers.clear();
            if (didYouGetIt) {
                JOptionPane.showMessageDialog(null, "Right!");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList + "'\'");
            }
            try {
                dispose();
                //Client clientMinBroder = new Client(pro);
                GamePage g = new GamePage(pro);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (pro.getQuestion() <= pro.getMaxQuestion()) {
            pro.setQuestion(pro.getQuestion() + 1);
            if (didYouGetIt) {
                JOptionPane.showMessageDialog(null, "Right!");
            } else {
                JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList + "'\'");
            }
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
