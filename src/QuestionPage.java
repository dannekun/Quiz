import QuestionsHandler.Database;
import QuestionsHandler.Questions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    JLabel round = new JLabel("Round nr");
    JLabel player = new JLabel();

    JLabel category = new JLabel("Category");

    JLabel question = new JLabel();

    String rightAnswer;

    boolean result;

    JButton answer1 = new JButton();
    JButton answer2 = new JButton();
    JButton answer3 = new JButton();
    JButton answer4 = new JButton();

    JLabel timer = new JLabel("Timer here");

    JFrame frame = new JFrame();

    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JPanel south = new JPanel();

    Player pro;

    public QuestionPage(Player p, List<Questions> chosenCategory, String categoryName) {

        pro = p;
        round.setText(String.valueOf(pro.getRound()));

        player.setText(pro.getName());

        frame.setSize(400, 200);

        setCategoryText(chosenCategory, categoryName);

        north.setLayout(new GridLayout(1, 5));
        north.add(b1);
        north.add(b2);
        north.add(b3);
        north.add(round);
        north.add(player);

        center.setLayout(new GridLayout(2, 1));
        center.add(category);
        center.add(question);

        south.setLayout(new GridLayout(3, 2));
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

    private void setCategoryText(List<Questions> chosenCategory, String categoryName) {

        category.setText(categoryName);

        var questionObject = chosenCategory.get(0);
        var answerObject = questionObject.getAnswerObject();

        String questionString = questionObject.getQuestion();

        var shuffleAnswers = answerObject.getShuffledAnswersList();

        answer1.setText(shuffleAnswers.get(0));
        answer2.setText(shuffleAnswers.get(1));
        answer3.setText(shuffleAnswers.get(2));
        answer4.setText(shuffleAnswers.get(3));

        question.setText(questionString);

        rightAnswer = questionObject.getAnswerObject().getRightAnswer();
    }

    public boolean checkAnswer(JButton answer) {

        boolean answerCheck;

        answer.setOpaque(true);
        answer.setBorderPainted(false);

        if (answer.getText().equals(rightAnswer)) {
            answerCheck = true;
            answer.setBackground(Color.GREEN);
        } else {
            answerCheck = false;
            answer.setBackground(Color.RED);

        }
        return answerCheck;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == answer1) {

           result = checkAnswer(answer1);


        } else if (e.getSource() == answer2) {

            result = checkAnswer(answer2);

        } else if (e.getSource() == answer3) {

            result = checkAnswer(answer3);

        } else if (e.getSource() == answer4) {

            result = checkAnswer(answer4);

        }

        new ResultPage(pro, result);
        frame.dispose();
    }
}
