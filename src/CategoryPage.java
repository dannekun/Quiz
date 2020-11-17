import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;
import QuestionsHandler.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-13
 * Time: 09:14
 * Project: Quizkampen
 * Copyright: MIT
 */
public class CategoryPage extends JFrame implements ActionListener {

    final int animalsNature = 0;
    final int artLiterature = 1;
    final int generalKnowledge = 2;
    final int math = 3;
    final int music = 4;
    final int popCulture = 5;
    final int sports = 6;
    final int technology = 7;
    final int tvShows = 8;

    int unique1;
    int unique2;
    int unique3;

    String categoryName;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();

    JButton category1 = new JButton();
    JButton category2 = new JButton();
    JButton category3 = new JButton();

    JLabel choose = new JLabel("Choose a category");

    Database database = new Database();


    Player pro = new Player();



    public CategoryPage(Player p){
        pro = p;

        category1.setText(null);
        category2.setText(null);
        category3.setText(null);

        getCategoryText();

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

    /**
     * This methods calls the uniqueRandomNumber(); method to assing values to the 3 unique numbers
     * and uses those as parameters for the method findCategoryText();
     *
     *
     */

    public void getCategoryText(){

        uniqueRandomNumber();

        findCategory(unique1);
        findCategory(unique2);
        findCategory(unique3);

    }

    /**
     * This method finds the right category based on a unique random number 0 - 9
     * All the cases (Categories) have been assigned to a number that will corresponds to the given unique number
     *
     * t.ex: unique1 = 5 && popCulture = 5 -> category1.setText("Pop Culture)
     *
     * @param randomCategoryIndex = [unique1 || unique2 || unique3]
     */


    public void findCategory(int randomCategoryIndex){


        switch (randomCategoryIndex) {

            case animalsNature:

                categoryName = new AnimalsNature().getCategoryName();

                break;

            case artLiterature:

                // Forsätt här!
                var categoryAL = new ArtLiterature();
                categoryName = categoryAL.getCategoryName();

                setCategoryText(categoryName);

                break;

            case generalKnowledge:

                categoryName = new GeneralKnowledge().getCategoryName();

                setCategoryText(categoryName);

                break;

            case math:

                categoryName = new Math().getCategoryName();

                setCategoryText(categoryName);

                break;

            case music:

                categoryName = new Music().getCategoryName();

                setCategoryText(categoryName);

                break;

            case popCulture:

                categoryName = new PopCulture().getCategoryName();

                setCategoryText(categoryName);

                break;

            case sports:

                categoryName = new Sports().getCategoryName();

                setCategoryText(categoryName);

                break;

            case technology:

                categoryName = new Technology().getCategoryName();

                setCategoryText(categoryName);

                break;

            case tvShows:

                categoryName = new TVShows().getCategoryName();

                setCategoryText(categoryName);

                break;

        }

    }

    /**
     * This method makes sure to set the correct category in the right button.
     *
     * @param categoryText
     */

    public void setCategoryText(String categoryText){

        if (category1.getText() == null && category2.getText() == null && category3.getText() == null){

            category1.setText(categoryText);

        } else if ((category1.getText() != null) && category2.getText() == null && category3.getText() == null){

            category2.setText(categoryText);

        } else if ((category1.getText() != null) && (category2.getText() != null) && category3.getText() == null){

            category3.setText(categoryText);
        }

    }

    /**
     * This method creates 3 random numbers that is used for the selection of categories.
     *
     * @return
     */

    public void uniqueRandomNumber(){

        ArrayList<Integer> listOfNumbers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            listOfNumbers.add(i);
        }

        Collections.shuffle(listOfNumbers);

            unique1 = listOfNumbers.get(0);
            unique2 = listOfNumbers.get(1);
            unique3 = listOfNumbers.get(2);

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == category1){
            frame.dispose();
            QuestionPage q = new QuestionPage(pro, database);

        }else if (e.getSource() == category2){
            frame.dispose();
            QuestionPage q = new QuestionPage(pro, database);
        }else if (e.getSource() == category3){
            frame.dispose();
            QuestionPage q = new QuestionPage(pro, database);
        }
    }
}
