import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-13
 * Time: 09:14
 * Project: Quizkampen
 * Copyright: MIT
 */
public class CategoryPage extends JFrame implements ActionListener {

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();

    JButton category1 = new JButton("1");
    JButton category2 = new JButton("2");
    JButton category3 = new JButton("3");

    JLabel choose = new JLabel("Choose a category");


    Player pro = new Player();
    public CategoryPage(Player p){
        pro.setName(p.getName());

        frame.setSize(400,200);
        panel1.setLayout(new GridLayout(1,1));
        panel.setLayout(new GridLayout(1,3));
        panel.add(choose);
        panel1.add(category1);
        panel1.add(category2);
        panel1.add(category3);

        frame.add(panel1, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        category1.addActionListener(this);
        category2.addActionListener(this);
        category3.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == category1){
            frame.dispose();
            QuestionPage q = new QuestionPage(pro);
        }else if (e.getSource() == category2){
            frame.dispose();
            QuestionPage q = new QuestionPage(pro);
        }else if (e.getSource() == category3){
            frame.dispose();
            QuestionPage q = new QuestionPage(pro);
        }

    }
}