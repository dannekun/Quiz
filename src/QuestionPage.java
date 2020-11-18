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

    Database database = new Database();

    JButton b1 = new JButton();
    JButton b2 = new JButton();
    JButton b3 = new JButton();

    JLabel round = new JLabel("Round nr");
    JLabel player = new JLabel();

    JLabel category = new JLabel("Category");
    JLabel question = new JLabel();


    JButton answer1 = new JButton(database.artLiterature.getArtLiteratureList().get(0).getAnswerObject()
            .getAnswersList().get(0).toString());
    JButton answer2 = new JButton(database.artLiterature.getArtLiteratureList().get(0).getAnswerObject()
            .getAnswersList().get(1).toString());
    JButton answer3 = new JButton(database.artLiterature.getArtLiteratureList().get(0).getAnswerObject()
            .getAnswersList().get(2).toString());
    JButton answer4 = new JButton(database.artLiterature.getArtLiteratureList().get(0).getAnswerObject()
            .getAnswersList().get(3).toString());

    JLabel timer = new JLabel("Timer here");

    JFrame frame = new JFrame();

    JPanel north = new JPanel();
    JPanel center = new JPanel();
    JPanel south = new JPanel();

    Player pro = new Player();




    public QuestionPage(Player p, Database d){




        pro = p;
        round.setText(String.valueOf(pro.getRound()));

        player.setText(pro.getName());

        frame.setSize(400,200);

        List<Questions> randomListToPull = d.artLiterature.getArtLiteratureList();

        question.setText(randomListToPull.get(0).getQuestion());



        north.setLayout(new GridLayout(1,5));
        north.add(b1);
        north.add(b2);
        north.add(b3);
        north.add(round);
        north.add(player);

        center.setLayout(new GridLayout(2,1));
        center.add(category);
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

    // Radera

    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean b = true;
        if (e.getSource() == answer1){
            answer1.setBackground(Color.RED);
            answer1.setOpaque(true);
            answer1.setBorderPainted(false);
            b = false;
        }else if (e.getSource() == answer2){
            answer2.setBackground(Color.RED);
            answer2.setOpaque(true);
            answer2.setBorderPainted(false);
            b = false;
        }else if (e.getSource() == answer3){
            answer3.setBackground(Color.RED);
            answer3.setOpaque(true);
            answer3.setBorderPainted(false);
            b = false;
        }else if (e.getSource() == answer4){
            answer4.setBackground(Color.GREEN);
            answer4.setOpaque(true);
            answer4.setBorderPainted(false);
            b = true;
        }
        ResultPage result = new ResultPage(pro,b);
        frame.dispose();
    }
}
