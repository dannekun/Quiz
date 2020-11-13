package QuestionsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/13/2020
 * 5:12 PM
 * Quiz2
 * Copyright: MIT
 */
public class AnimalsNature {

    //--------------------------------------------------------------------------------------//
    // Animals & Nature Answers

    private final List<QuestionsPage> generalKnowledgeList = new ArrayList<>();

    private final AnswerPage ANA1 = new AnswerPage("Laos", "Thailand",
            "Vietnam", "Cambodia");

    private final AnswerPage ANA2 = new AnswerPage("Desamma", "Detsamma",
            "Det samma", "Detsamman");

    private final AnswerPage ANA3 = new AnswerPage("195", "220",
            "90", "164");

    private final AnswerPage ANA4 = new AnswerPage("1965", "1970",
            "1975", "1980");

    private final AnswerPage ANA5 = new AnswerPage("Edo", "Edoyo",
            "Kyoto", "Kygo");

    private final AnswerPage ANA6 = new AnswerPage("2004", "2001",
            "2008", "1994");

    private final AnswerPage ANA7 = new AnswerPage("Ryssland", "USA",
            "Canada", "Mongoliet");

    private final AnswerPage ANA8 = new AnswerPage("12", "8",
            "6", "16");

    private final AnswerPage ANA9 = new AnswerPage("40mg", "1g",
            "400mg", "15g");

    private final AnswerPage ANA10 = new AnswerPage("160", "210",
            "90", "110");

    //--------------------------------------------------------------------------------------//
    // Animals & Nature Questions

    private final QuestionsPage ANQ1 = new QuestionsPage("I vilket land är Vientiane huvudstad?", ANA1);

    private final QuestionsPage ANQ2 = new QuestionsPage("Vilket av följande är stavning?", ANA2);

    private final QuestionsPage ANQ3 = new QuestionsPage("Hur många länder finns det i världen?", ANA3);

    private final QuestionsPage ANQ4 = new QuestionsPage("När fick Sverige färg-TV?", ANA4);

    private final QuestionsPage ANQ5 = new QuestionsPage("Vad hette Tokyo förr", ANA5);

    private final QuestionsPage ANQ6 = new QuestionsPage("När skapades Facebook?", ANA6);

    private final QuestionsPage ANQ7 = new QuestionsPage("Vilket land har störst yta?", ANA7);

    private final QuestionsPage ANQ8 = new QuestionsPage("Hur många kanter har en kub?", ANA8);

    private final QuestionsPage ANQ9 = new QuestionsPage("Ungefär hur mycket koffein är de i 100 kaffe", ANA9);

    private final QuestionsPage ANQ10X = new QuestionsPage("Vad hade Einstein för IQ?", ANA10);


    public AnimalsNature(){

        // General knowledge
//        generalKnowledgeList.add(GQ1);
//        generalKnowledgeList.add(GQ2);
//        generalKnowledgeList.add(GQ3);
//        generalKnowledgeList.add(GQ4);
//        generalKnowledgeList.add(GQ5);
//        generalKnowledgeList.add(GQ6);
//        generalKnowledgeList.add(GQ7);
//        generalKnowledgeList.add(GQ8);
//        generalKnowledgeList.add(GQ9);
//        generalKnowledgeList.add(GQ10);

        Collections.shuffle(generalKnowledgeList);

    }


    public static void main(String[] args) {
        new AnimalsNature();
    }
    public List<QuestionsPage> getGeneralKnowledgeList() {
        return generalKnowledgeList;
    }
}
