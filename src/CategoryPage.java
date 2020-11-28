import QuestionsHandler.Categories.*;
import QuestionsHandler.Categories.Math;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    int unique1;
    int unique2;
    int unique3;

    boolean a = false;
    boolean b = false;
    boolean c = false;

    boolean clicked = false;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public int getUnique1() {
        return unique1;
    }

    public void setUnique1(int unique1) {
        this.unique1 = unique1;
    }

    public int getUnique2() {
        return unique2;
    }

    public void setUnique2(int unique2) {
        this.unique2 = unique2;
    }

    public int getUnique3() {
        return unique3;
    }

    public void setUnique3(int unique3) {
        this.unique3 = unique3;
    }

    String categoryName;

    JPanel panel = new JPanel();
    JFrame frame = new JFrame();

    JButton category1 = new JButton();
    JButton category2 = new JButton();
    JButton category3 = new JButton();

    JLabel choose = new JLabel("Välj en kategori");

    Player pro = new Player();

    //List<String> categoriesChosen = new ArrayList<>();

   String cat1 = "";
   String cat2 = "";
   String cat3 = "";

    public CategoryPage(){}

    public CategoryPage(Player p){
        pro = p;

        category1.setText(null);
        category2.setText(null);
        category3.setText(null);

        getCategoryText();

        cat1 = category1.getText();
        cat2 = category2.getText();
        cat3 = category3.getText();

    //    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground( new Color(51, 133, 255));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 60)));
        panel.add(choose);
        choose.setFont(new Font("Arial", Font.PLAIN, 18));
        choose.setForeground(Color.WHITE);
        choose.setBackground(new Color(0, 51, 204));
        choose.setOpaque(true);
        choose.setBorder(new EmptyBorder(10, 30, 10, 30));
        choose.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 40)));
        panel.add(category1);
        category1.setBackground(new Color(204, 0, 204));
        category1.setForeground(Color.WHITE);
        category1.setFont(new Font("Arial", Font.BOLD, 14));
        category1.setContentAreaFilled(false);
        category1.setBorderPainted(false);
        category1.setOpaque(true);
        category1.setPreferredSize(new Dimension(200, 70));
        category1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 15)));
        panel.add(category2);
        category2.setBackground(new Color(0, 204, 102));
        category2.setBorderPainted(false);
        category2.setForeground(Color.WHITE);
        category2.setFont(new Font("Arial", Font.BOLD, 14));
        category2.setContentAreaFilled(false);
        category2.setOpaque(true);
        category2.setPreferredSize(new Dimension(200, 70));
        category2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(100, 15)));
        panel.add(category3);
        category3.setBackground(new Color(255, 92, 51));
        category3.setForeground(Color.WHITE);
        category3.setFont(new Font("Arial", Font.BOLD, 14));
        category3.setContentAreaFilled(false);
        category3.setBorderPainted(false);
        category3.setOpaque(true);
        category3.setPreferredSize(new Dimension(200, 70));
        category3.setAlignmentX(Component.CENTER_ALIGNMENT);

        category1.setMaximumSize(new Dimension(200, 70));
        category2.setMaximumSize(new Dimension(200, 70));
        category3.setMaximumSize(new Dimension(200, 70));

        setSize(350, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.add(panel);

        category1.addActionListener(this);
        category2.addActionListener(this);
        category3.addActionListener(this);

    }

    public List<String> findCategoryNamiestoDisplay(String input){
        List<String> categorieNames = new ArrayList<>();
        categorieNames.add(input);

        return categorieNames;
    }

    /**
     * This methods calls the uniqueRandomNumber(); method to assing values to the 3 unique numbers
     * and uses those as parameters for the method findCategoryText();
     *
     *
     */

    public void getCategoryText(){

        // Generate 3 random numbers and set unique[1, 2, 3]
        uniqueRandomNumber();

        findCategory(unique1);
        findCategory(unique2);
        findCategory(unique3);

    }

    /**
     * This method finds the right category based on a unique random number 0 - 9
     * All the cases (Categories) have been assigned to a number that will corresponds to the given unique number
     *
     *
     * t.ex: unique1 = 5 && popCulture = 5 -> category1.setText("Pop Culture)
     *
     * @param randomCategoryIndex = [unique1 || unique2 || unique3]
     */


    public void findCategory(int randomCategoryIndex){

        // Operator = randomCategoryIndex = t.ex: unique1
        // animalsNature = 0 && if(unique1 == 0) -> case animalsNature is active:
        // Get category name from AnimalsNature.java
        // Use that name to setCategoryText();
        switch (randomCategoryIndex) {
            case animalsNature -> {
                categoryName = new AnimalsNature().getCategoryName();
                setCategoryText(categoryName);
            }
            case artLiterature -> {
                categoryName = new ArtLiterature().getCategoryName();
                setCategoryText(categoryName);
            }
            case generalKnowledge -> {
                categoryName = new GeneralKnowledge().getCategoryName();
                setCategoryText(categoryName);
            }
            case math -> {
                categoryName = new Math().getCategoryName();
                setCategoryText(categoryName);
            }
            case music -> {
                categoryName = new Music().getCategoryName();
                setCategoryText(categoryName);
            }
            case popCulture -> {
                categoryName = new PopCulture().getCategoryName();
                setCategoryText(categoryName);
            }
            case sports -> {
                categoryName = new Sports().getCategoryName();
                setCategoryText(categoryName);
            }
            case technology -> {
                categoryName = new Technology().getCategoryName();
                setCategoryText(categoryName);
            }
            case tvShows -> {
                categoryName = new TVShows().getCategoryName();
                setCategoryText(categoryName);
            }
        }

    }

    /**
     * This method makes sure to set the correct category in the right button.
     *
     * @param categoryText
     */

    public void setCategoryText(String categoryText){

        // if all category names are empty ->
        if (category1.getText() == null && category2.getText() == null && category3.getText() == null){

            // Set category1 as categoryText
            category1.setText(categoryText);

        // if first category name is taken and the other two are empty ->
        } else if ((category1.getText() != null) && category2.getText() == null && category3.getText() == null){

            // Set category2 as categoryText
            category2.setText(categoryText);

        // if the two first category names are taken and last one is empty ->
        } else if ((category1.getText() != null) && (category2.getText() != null) && category3.getText() == null){

            // Set category3 as categoryText
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


    // Factory method();
    // Factory pattern.
    // Lista till QuestionPage.


    @Override
    public void actionPerformed(ActionEvent e) {
       //LÄGG TILL BOOLEAN HÄR
         a = false;
         b = false;
         c = false;
        if (e.getSource() == category1){
            dispose();
            //categoriesChosen = findCategoryNamiestoDisplay(category1.getText());
           // categoriesChosen.add(cat1);
           a = true;
            pro.setClicked(true);
           // QuestionPage q = new QuestionPage(pro);

        }else if (e.getSource() == category2){
            dispose();
            //categoriesChosen = findCategoryNamiestoDisplay(category2.getText());
           // categoriesChosen.add(cat2);
            b = true;
            pro.setClicked(true);
           // QuestionPage q = new QuestionPage(pro);

        }else if (e.getSource() == category3){
            dispose();
            //categoriesChosen.add(cat3);
           c = true;
            pro.setClicked(true);
            //categoriesChosen = findCategoryNamiestoDisplay(category3.getText());
            //QuestionPage q = new QuestionPage(pro);

        }
    }

    public Player addCatToPlayer(){
        if (a){
            pro.addToList(cat1);
        }else if (b){
            pro.addToList(cat2);
        }else if (c){
            pro.addToList(cat3);
        }
        return pro;
    }

    public Player findClickPlay(){
        category1.addActionListener(this);
        category2.addActionListener(this);
        category3.addActionListener(this);
        pro = addCatToPlayer();
        return pro;
    }
}
