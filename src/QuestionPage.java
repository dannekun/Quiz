import QuestionsHandler.Answers;
import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;
import QuestionsHandler.Database;
import QuestionsHandler.Questions;

import javax.swing.*;
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

    JFrame frame = new JFrame();

    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JPanel south = new JPanel();

    Player pro = new Player();
    List<Questions> randomListToPull = new ArrayList<>();
    List<String> randomAnswerList = new ArrayList<>();

    String rightAnswerFromList;


    Database d = new Database();

    String questionToAsk = null;
    public QuestionPage(Player p){



        pro = p;
        round.setText(("Round " + pro.getRound()));
        questionNumber.setText("Question " + pro.getQuestion());

        player.setText(pro.getName());

        frame.setSize(600,200);


        randomListToPull = findList(pro.getRoundCategories().get(pro.getRound()-1));

        questionToAsk = randomListToPull.get(pro.getRound()).getQuestion();

        //questionToAsk = randomListToPull.get(whichCatToChoose).getQuestion();


        List<Questions> newQuestionsForPlayerToAsk = new ArrayList<>();
        int randomNumber = 0;
        for (Questions s : randomListToPull){
            if (!s.getQuestion().equals(questionToAsk)){
                newQuestionsForPlayerToAsk.add(randomListToPull.get(randomNumber));
            }
            randomNumber++;
        }

        Collections.shuffle(newQuestionsForPlayerToAsk);

        if (pro.currentQuestion.isEmpty()){
            question.setText(questionToAsk);
            pro.addQuestionToCurrentList(questionToAsk);
        }else {
            randomListToPull = newQuestionsForPlayerToAsk;
            question.setText(randomListToPull.get(pro.getRound()).getQuestion());
            pro.addQuestionToCurrentList(randomListToPull.get(pro.getRound()).getQuestion());
        }


        randomAnswerList = randomListToPull.get(pro.getRound()).getAnswerObject().getAnswersList();

        /*
        if (pro.getCurrentQuestion() == null){
            whichCatToChoose = pro.getRound();
            question.setText(questionToAsk);
            pro.setCurrentQuestion(questionToAsk);
       // }else if (randomListToPull.get(pro.getRound()).getQuestion().equals(pro.getCurrentQuestion())){
        }else if (randomListToPull.get(pro.getRound()).getQuestion().equals(pro.getCurrentQuestion())){
            whichCatToChoose = pro.getRound()+1;
            question.setText(questionToAsk);
            pro.setCurrentQuestion(questionToAsk);
        }else {
            question.setText(questionToAsk);
            pro.setCurrentQuestion(questionToAsk);
        }



         */

        //d.artLiterature.getArtLiteratureList().get(0).getAnswerObject().shuffleAnswers(randomListToPull.get(0).getAnswerObject().getAnswersList());


       //randomListToPull.get(0).getAnswerObject().shuffleAnswers();



       rightAnswerFromList = randomListToPull.get(pro.getRound()).getAnswerObject().getRightAnswer();

        Collections.shuffle(randomAnswerList);


        answer1.setText(randomAnswerList.get(0));
        answer2.setText(randomAnswerList.get(1));
        answer3.setText(randomAnswerList.get(2));
        answer4.setText(randomAnswerList.get(3));

        /*
        answer1.setText(randomListToPull.get(0).getAnswerObject().getAnswersList().get(0).toString());
        answer2.setText(randomListToPull.get(0).getAnswerObject().getAnswersList().get(1).toString());
        answer3.setText(randomListToPull.get(0).getAnswerObject().getAnswersList().get(2).toString());
        answer4.setText(randomListToPull.get(0).getAnswerObject().getAnswersList().get(3).toString());

         */
        category.setText(pro.getRoundCategories().get(pro.getRoundCategories().size()-1));

        buttonsToPaintList.add(b1);
        buttonsToPaintList.add(b2);
        buttonsToPaintList.add(b3);



        if (pro.getQuestion() > 0){
            for (int i = 0; i < pro.getQuestion()-1; i++) {
             //   if (pro.getAnswers().get(i) == false){
                if (pro.getRoundAnswers().get(i) == false){
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



        north.setLayout(new GridLayout(1,5));
        north.add(b1);
        north.add(b2);
        north.add(b3);
        north.add(round);
        north.add(player);

        center.setLayout(new GridLayout(3,1));
        center.add(category);
        center.add(questionNumber);
        center.add(question);

        south.setLayout(new GridLayout(3,2));
        south.add(answer1);
        south.add(answer3);
        south.add(answer2);
        south.add(answer4);
        south.add(timer);

        frame.add(north, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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


    final String animalNatureName = "Animals & Nature";
    final String artLiteratureName = "Art & Literature";
    final String generalKnowledgeName = "General Knowledge";
    final String mathName = "Math";
    final String musicName = "Music";
    final String popCultureName = "Pop Culture";
    final String sportsName = "Sports";
    final String technologyName = "Technology";
    final String tvShowsName = "TVShows";




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

    public Boolean checkAnswers(String a){

    if (rightAnswerFromList.equals(a)){
        return true;
    }else {
        return false;
    }

    }

    public void findRightAnswerAndPaint(JButton a, JButton b, JButton c, JButton d, String rightAnswer){
        if (a.getText().equals(rightAnswer)){
            a.setBackground(Color.GREEN);
            a.setOpaque(true);
            a.setBorderPainted(false);
        }else if (b.getText().equals(rightAnswer)){
            b.setBackground(Color.GREEN);
            b.setOpaque(true);
            b.setBorderPainted(false);
        }else if (c.getText().equals(rightAnswer)){
            c.setBackground(Color.GREEN);
            c.setOpaque(true);
            c.setBorderPainted(false);
        }else if (d.getText().equals(rightAnswer)){
            d.setBackground(Color.GREEN);
            d.setOpaque(true);
            d.setBorderPainted(false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean didYouGetIt = false;
        if (e.getSource() == answer1){
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == false){
            if (!checkAnswers(randomAnswerList.get(0))) {
                answer1.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;



                //}else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(0)) == true){
            }else if (checkAnswers(randomAnswerList.get(0))){
                answer1.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints()+1);
                didYouGetIt = true;
            }
            answer1.setOpaque(true);
            answer1.setBorderPainted(false);
        }else if (e.getSource() == answer2){
            //if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == false){
            if (!checkAnswers(randomAnswerList.get(1))) {
                answer2.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;
          //  }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(1)) == true){
            }else if (checkAnswers(randomAnswerList.get(1))){
                answer2.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints()+1);
                didYouGetIt = true;
            }
            answer2.setOpaque(true);
            answer2.setBorderPainted(false);

        }else if (e.getSource() == answer3){
           // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == false){
            if (!checkAnswers(randomAnswerList.get(2))) {
                answer3.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;
        //    }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(2)) == true){
            }else if (checkAnswers(randomAnswerList.get(2))){
                answer3.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints()+1);
                didYouGetIt = true;
            }
            answer3.setOpaque(true);
            answer3.setBorderPainted(false);

        }else if (e.getSource() == answer4){
           // if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == false){
            if (!checkAnswers(randomAnswerList.get(3))) {
                answer4.setBackground(Color.RED);
                pro.answersAddToList(false);
                pro.addToRoundAnswersList(false);
                didYouGetIt = false;
           // }else if (randomListToPull.get(0).getAnswerObject().checkAnswer(randomAnswerList.get(3)) == true){
            }else if (checkAnswers(randomAnswerList.get(3))){
                answer4.setBackground(Color.GREEN);
                pro.answersAddToList(true);
                pro.addToRoundAnswersList(true);
                pro.setPoints(pro.getPoints()+1);
                didYouGetIt = true;
            }
            answer4.setOpaque(true);
            answer4.setBorderPainted(false);

        }



        if (pro.getQuestion() == pro.getMaxQuestion() && pro.getRound() == pro.getMaxRound()){
            pro.setQuestion(0);
            if (didYouGetIt == true){
                JOptionPane.showMessageDialog(null, "Right!");
            }else {
                JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList+ "'\'");
            }
            frame.dispose();
            ResultPage r = new ResultPage(pro);

        }else if (pro.getQuestion() == pro.getMaxQuestion()){
            pro.setQuestion(0);
            pro.currentQuestion.clear();
            pro.roundAnswers.clear();
            if (didYouGetIt == true){
                JOptionPane.showMessageDialog(null, "Right!");
            }else {
                JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList+ "'\'");
            }
            try {
                frame.dispose();
                GamePage g = new GamePage(pro);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        else if (pro.getQuestion() <= pro.getMaxQuestion()){
            pro.setQuestion(pro.getQuestion()+1);
            if (didYouGetIt == true){
                JOptionPane.showMessageDialog(null, "Right!");
            }else {
                JOptionPane.showMessageDialog(null, "Wrong!" + "\nRight answer is: \n'\'" + rightAnswerFromList+ "'\'");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }


            frame.dispose();
            QuestionPage q = new QuestionPage(pro);
        }
    }
}
