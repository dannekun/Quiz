package QuestionsHandler.Categories;

import QuestionsHandler.Answers;
import QuestionsHandler.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/14/2020
 * 5:10 PM
 * Quiz2
 * Copyright: MIT
 */
public class ArtLiterature {

    //--------------------------------------------------------------------------------------//
    // Art & Literature Answers
    private final List<Questions> artLiteratureList;

    private final Answers A1 = new Answers("Laos", "Thailand", "Vietnam", "Cambodia");

    private final Answers A2 = new Answers("Desamma", "Detsamma", "Det samma", "Detsamman");

    private final Answers A3 = new Answers("195", "220", "90", "164");

    private final Answers A4 = new Answers("1965", "1970", "1975", "1980");

    private final Answers A5 = new Answers("Edo", "Edoyo", "Kyoto", "Kygo");

    private final Answers A6 = new Answers("2004", "2001", "2008", "1994");

    private final Answers A7 = new Answers("Ryssland", "USA", "Canada", "Mongoliet");

    private final Answers A8 = new Answers("12", "8", "6", "16");

    private final Answers A9 = new Answers("40mg", "1g", "400mg", "15g");

    private final Answers A10 = new Answers("160", "210", "90", "110");

    //--------------------------------------------------------------------------------------//
    // Art & Literature Questions
    private final Questions Q1 = new Questions("I vilket land är Vientiane huvudstad?", A1);

    private final Questions Q2 = new Questions("Vilket av följande är stavning?", A2);

    private final Questions Q3 = new Questions("Hur många länder finns det i världen?", A3);

    private final Questions Q4 = new Questions("När fick Sverige färg-TV?", A4);

    private final Questions Q5 = new Questions("Vad hette Tokyo förr", A5);

    private final Questions Q6 = new Questions("När skapades Facebook?", A6);

    private final Questions Q7 = new Questions("Vilket land har störst yta?", A7);

    private final Questions Q8 = new Questions("Hur många kanter har en kub?", A8);

    private final Questions Q9 = new Questions("Ungefär hur mycket koffein är de i 100 kaffe", A9);

    private final Questions Q10 = new Questions("Vad hade Einstein för IQ?", A10);


    public ArtLiterature(){

        artLiteratureList = new ArrayList<>();

        artLiteratureList.add(Q1);
        artLiteratureList.add(Q2);
        artLiteratureList.add(Q3);
        artLiteratureList.add(Q4);
        artLiteratureList.add(Q5);
        artLiteratureList.add(Q6);
        artLiteratureList.add(Q7);
        artLiteratureList.add(Q8);
        artLiteratureList.add(Q9);
        artLiteratureList.add(Q10);

        Collections.shuffle(artLiteratureList);

    }

    public String getCategoryName() {
        return "Art & Literature";
    }

    public List<Questions> getArtLiteratureList() {
        return artLiteratureList;
    }

}
