import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;
import QuestionsHandler.Questions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    GUI_Util util = new GUI_Util();

    boolean clicked = false;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

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

    JPanel north = new JPanel();
    JPanel south = new JPanel();

    Player pro;
    List<Questions> randomListToPull = new ArrayList<>();
    List<String> randomAnswerList;

    String rightAnswerFromList;


    public QuestionPage(Player p) {


        pro = p;

        randomListToPull.clear();

        generateUI();


        add(north);
        north.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));
        util.setMainBackground(north);
        for (JButton jButton : buttonsToPaintList) {
            north.add(jButton);
            util.setSizeButton(jButton, 30, 30, 30, 30);
        }
        north.add(player);
        util.labelSetFontForegBackg_white(player, 0, 14, 0, 51, 204);
        player.setBorder(new EmptyBorder(10, 30, 10, 30));

        add(south);
        south.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        south.setLayout(new GridLayout(8, 1));
        util.setMainBackground(south);

        south.add(round);
        util.labelSetFontForeg_white(round, 0, 14);

        south.add(category);
        util.labelSetFontForegBackg_white(category, 1, 14, 255, 128, 0);
        util.setSizeLabel(category, 250, 30, 250, 30);
        category.setBorder(new EmptyBorder(10, 10, 10, 10));

        south.add(questionNumber);
        util.labelSetFontForeg_white(questionNumber, 0, 14);

        south.add(question);
        util.labelSetFontForegBackg_white(question, 1, 12, 255, 51, 0);
        util.setSizeLabel(question, 250, 30, 250, 30);
        question.setBorder(new EmptyBorder(10, 10, 10, 10));

        south.add(answer1);
        util.buttonSetFontForegBackg_white(answer1, 1, 14, 163, 26, 255);
        util.setSizeButton(answer1, 250, 30, 250, 30);

        south.add(answer2);
        util.buttonSetFontForegBackg_white(answer2, 1, 14, 230, 0, 230);
        util.setSizeButton(answer2, 250, 30, 250, 30);

        south.add(answer3);
        util.buttonSetFontForegBackg_white(answer3, 1, 14, 230, 0, 172);
        util.setSizeButton(answer3, 250, 30, 250, 30);

        south.add(answer4);
        util.buttonSetFontForegBackg_white(answer4, 1, 14, 128, 0, 255);
        util.setSizeButton(answer4, 250, 30, 250, 30);

        Container contentPane = getContentPane();
        contentPane.add(north, BorderLayout.NORTH);
        contentPane.add(south, BorderLayout.CENTER);

        setSize(350, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        answer1.addActionListener(this);
        answer2.addActionListener(this);
        answer3.addActionListener(this);
        answer4.addActionListener(this);

    }

    public void createButtonAndPaint() {
        for (int i = 0; i < pro.getMaxQuestion(); i++) {
            buttonsToPaintList.add(new JButton());
        }


        if (pro.getQuestion() > 0) {

            for (int i = 0; i < pro.getQuestion(); i++) {

                if (!pro.getRoundAnswers().get(i)) {

                    paintRed(buttonsToPaintList.get(i));

                } else if (pro.getRoundAnswers().get(i)) {

                    paintGreen(buttonsToPaintList.get(i));

                }

            }

        } else if (pro.getQuestion() == 0) {

            for (int i = 0; i < pro.getMaxQuestion(); i++) {
                resetPaint(buttonsToPaintList.get(i));

            }

        }
    }

    public void selectQuestionObject() {
        randomListToPull = findList(pro.getRoundCategories().get(pro.getRound() - 1));

        Collections.shuffle(randomListToPull);

        if (pro.currentQuestion.isEmpty()) {
            question.setText(randomListToPull.get(0).getQuestion());
            pro.addQuestionToCurrentList(randomListToPull.get(0).getQuestion());

            pro.addQuestionBetweenPlayers(randomListToPull.get(0));
        } else {
            randomListToPull = findQuestion(randomListToPull, pro.getCurrentQuestion());

            question.setText(randomListToPull.get(0).getQuestion());

            pro.addQuestionToCurrentList(randomListToPull.get(0).getQuestion());
            pro.addQuestionBetweenPlayers(randomListToPull.get(0));
        }

        randomAnswerList = randomListToPull.get(0).getAnswerObject().getAnswersList();

        rightAnswerFromList = randomListToPull.get(0).getAnswerObject().getRightAnswer();

        Collections.shuffle(randomAnswerList);
    }

    public void setButtonText(List<String> listToPullFrom, JButton button1, JButton button2, JButton button3, JButton button4) {
        button1.setText(listToPullFrom.get(0));
        button2.setText(listToPullFrom.get(1));
        button3.setText(listToPullFrom.get(2));
        button4.setText(listToPullFrom.get(3));

    }

    public void generateUI() {

        round.setText(("Rond " + pro.getRound()));
        questionNumber.setText("Fråga " + (pro.getQuestion() + 1));

        player.setText(pro.getName());

        selectQuestionObject();

        setButtonText(randomAnswerList, answer1, answer2, answer3, answer4);

        category.setText(pro.getRoundCategories().get(pro.getRoundCategories().size() - 1));


        createButtonAndPaint();


    }


    public void paintRed(JButton jb) {
        jb.setBackground(Color.RED);
        jb.setOpaque(true);
        jb.setBorder(new LineBorder(new Color(51, 133, 255)));
    }

    public void paintGreen(JButton jb) {
        jb.setBackground(Color.GREEN);
        jb.setOpaque(true);
        jb.setBorder(new LineBorder(new Color(51, 133, 255)));
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
            a.setBorder(new LineBorder(new Color(51, 133, 255)));
        } else if (b.getText().equals(rightAnswer)) {
            b.setBackground(Color.GREEN);
            b.setOpaque(true);
            b.setBorder(new LineBorder(new Color(51, 133, 255)));
        } else if (c.getText().equals(rightAnswer)) {
            c.setBackground(Color.GREEN);
            c.setOpaque(true);
            c.setBorder(new LineBorder(new Color(51, 133, 255)));
        } else if (d.getText().equals(rightAnswer)) {
            d.setBackground(Color.GREEN);
            d.setOpaque(true);
            d.setBorder(new LineBorder(new Color(51, 133, 255)));
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
        pro.setClickedRightAnswer(false);

        if (e.getSource() == answer1) {

            checkAnswerAndPaint(answer1, 0);

        } else if (e.getSource() == answer2) {

            checkAnswerAndPaint(answer2, 1);


        } else if (e.getSource() == answer3) {
            checkAnswerAndPaint(answer3, 2);


        } else if (e.getSource() == answer4) {
            checkAnswerAndPaint(answer4, 3);

        }

        findRightAnswerAndPaint(answer1, answer2, answer3, answer4, rightAnswerFromList);


    }

    public void checkAnswerAndPaint(JButton buttonToPaint, int buttonNumber) {

        if (!checkAnswers(randomAnswerList.get(buttonNumber))) {
            buttonToPaint.setBackground(Color.RED);
            pro.setClickedRightAnswer(false);


        } else if (checkAnswers(randomAnswerList.get(buttonNumber))) {
            buttonToPaint.setBackground(Color.GREEN);
            pro.setClickedRightAnswer(true);
        }
        buttonToPaint.setOpaque(true);
        buttonToPaint.setBorder(new LineBorder(new Color(51, 133, 255)));
        pro.setClicked(true);
        setClicked(true);

    }

    public void checkAnswerAndAddToList() {
        if (pro.isClickedRightAnswer()) {

            pro.answersAddToList(true);
            pro.addToRoundAnswersList(true);


        } else {

            pro.answersAddToList(false);
            pro.addToRoundAnswersList(false);
        }


    }

    public Player endGame(Player p) {

        pro = p;

        if (pro.getQuestion() == pro.getMaxQuestion() && pro.getRound() == pro.getMaxRound()) {

            checkAnswerAndAddToList();

            dispose();


        } else if (pro.getQuestion() == pro.getMaxQuestion()) {

            checkAnswerAndAddToList();
            dispose();


            pro.setQuestion(0);
            pro.getRoundAnswers().clear();
        } else if (pro.getQuestion() < pro.getMaxQuestion()) {

            checkAnswerAndAddToList();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            dispose();

        }

        return pro;

    }

    public Player addPoints(Player player) {
        pro = player;

        if (pro.clickedRightAnswer) {
            pro.setPoints(pro.getPoints() + 1);
            pro.setQuestion(pro.getQuestion() + 1);
        } else {
            pro.setQuestion(pro.getQuestion() + 1);
        }
        return pro;
    }

    public Player findClickPlay() {
        answer1.addActionListener(this);
        answer2.addActionListener(this);
        answer3.addActionListener(this);
        answer4.addActionListener(this);
        return pro;
    }
}
