import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;
import QuestionsHandler.Questions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    final String animalNatureName = "Animals & Nature";
    final String artLiteratureName = "Art & Literature";
    final String generalKnowledgeName = "General Knowledge";
    final String mathName = "Math";
    final String musicName = "Music";
    final String popCultureName = "Pop Culture";
    final String sportsName = "Sports";
    final String technologyName = "Technology";
    final String tvShowsName = "TVShows";

    int unique1;
    int unique2;
    int unique3;

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();

    JButton category1 = new JButton();
    JButton category2 = new JButton();
    JButton category3 = new JButton();

    JLabel choose = new JLabel("Choose a category");

    Player pro;

    public CategoryPage(Player p) {
        pro = p;

        category1.setText(null);
        category2.setText(null);
        category3.setText(null);

        getCategoryText();

        frame.setSize(400, 200);
        panel1.setLayout(new GridLayout(1, 1));
        panel.setLayout(new GridLayout(1, 3));
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
     */

    public void getCategoryText() {

        // Generate 3 random numbers and set unique[1, 2, 3]
        uniqueRandomNumber();

        findCategory(unique1);
        findCategory(unique2);
        findCategory(unique3);

    }

    /**
     * This method finds the right category based on a unique random number 0 - 9
     * All the cases (Categories) have been assigned to a number that will corresponds to the given unique number
     * <p>
     * <p>
     * t.ex: unique1 = 5 && popCulture = 5 -> category1.setText("Pop Culture)
     *
     * @param randomCategoryIndex = [unique1 || unique2 || unique3]
     */


    public void findCategory(int randomCategoryIndex) {

        // Operator = randomCategoryIndex = t.ex: unique1
        // animalsNature = 0 && if(unique1 == 0) -> case animalsNature is active:
        // Set Category name.
        switch (randomCategoryIndex) {
            case animalsNature -> setCategoryText(animalNatureName);
            case artLiterature -> setCategoryText(artLiteratureName);
            case generalKnowledge -> setCategoryText(generalKnowledgeName);
            case math -> setCategoryText(mathName);
            case music -> setCategoryText(musicName);
            case popCulture -> setCategoryText(popCultureName);
            case sports -> setCategoryText(sportsName);
            case technology -> setCategoryText(technologyName);
            case tvShows -> setCategoryText(tvShowsName);
        }
    }

    /**
     * This method makes sure to set the correct category in the right button.
     *
     * @param categoryText Comes from the names the categories.
     */

    public void setCategoryText(String categoryText) {

        // if all category names are empty ->
        if (category1.getText() == null && category2.getText() == null && category3.getText() == null) {

            // Set category1 as categoryText
            category1.setText(categoryText);

            // if first category name is taken and the other two are empty ->
        } else if ((category1.getText() != null) && category2.getText() == null && category3.getText() == null) {

            // Set category2 as categoryText
            category2.setText(categoryText);

            // if the two first category names are taken and last one is empty ->
        } else if ((category1.getText() != null) && (category2.getText() != null) && category3.getText() == null) {

            // Set category3 as categoryText
            category3.setText(categoryText);
        }

    }

    /**
     * This method creates 3 random numbers that is used for the selection of categories.
     */

    public void uniqueRandomNumber() {

        ArrayList<Integer> listOfNumbers = new ArrayList<>();

        // Add the numbers 0 - 8 in a list
        for (int i = 0; i < 9; i++) {
            listOfNumbers.add(i);
        }
        // Shuffle said list
        Collections.shuffle(listOfNumbers);

        // Get 3 numbers from list. since list is shuffled these will always me 3 different random numbers between 0-8
        unique1 = listOfNumbers.get(0);
        unique2 = listOfNumbers.get(1);
        unique3 = listOfNumbers.get(2);

    }

    public List<Questions> findList(JButton categoryName) {

        return switch (categoryName.getText()) {
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

    public List<Questions> forwardList(ActionEvent e) {

        if (e.getSource() == category1) {

            return findList(category1);

        } else if (e.getSource() == category2) {

            return findList(category2);


        } else if (e.getSource() == category3) {

            return findList(category3);

        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == category1) {

            new QuestionPage(pro, forwardList(e), category1.getText());
            frame.dispose();


        } else if (e.getSource() == category2) {

            new QuestionPage(pro, forwardList(e), category2.getText());
            frame.dispose();

        } else if (e.getSource() == category3) {

            new QuestionPage(pro, forwardList(e), category3.getText());
            frame.dispose();


        }
    }


}
