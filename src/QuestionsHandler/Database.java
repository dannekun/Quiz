package QuestionsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/13/2020
 * 10:49 AM
 * Quiz
 * Copyright: MIT
 */
public class Database {

    //--------------------------------------------------------------------------------------//
    // General Knowledge Answers
    private final List<QuestionPage> generalKnowledgeList = new ArrayList<>();

    private final AnswerPage GA1 = new AnswerPage("Laos", "Thailand",
            "Vietnam", "Cambodia");

    private final AnswerPage GA2 = new AnswerPage("Desamma", "Detsamma",
            "Det samma", "Detsamman");

    private final AnswerPage GA3 = new AnswerPage("195", "220",
            "90", "164");

    private final AnswerPage GA4 = new AnswerPage("1965", "1970",
            "1975", "1980");

    private final AnswerPage GA5 = new AnswerPage("Edo", "Edoyo",
            "Kyoto", "Kygo");

    private final AnswerPage GA6 = new AnswerPage("2004", "2001",
            "2008", "1994");

    private final AnswerPage GA7 = new AnswerPage("Ryssland", "USA",
            "Canada", "Mongoliet");

    private final AnswerPage GA8 = new AnswerPage("12", "8",
            "6", "16");

    private final AnswerPage GA9 = new AnswerPage("40mg", "1g",
            "400mg", "15g");

    private final AnswerPage GA10 = new AnswerPage("160", "210",
            "90", "110");

    //--------------------------------------------------------------------------------------//
    // General Knowledge Questions
    private final QuestionPage GQ1 = new QuestionPage("I vilket land är Vientiane huvudstad?", GA1);

    private final QuestionPage GQ2 = new QuestionPage("Vilket av följande är stavning?", GA2);

    private final QuestionPage GQ3 = new QuestionPage("Hur många länder finns det i världen?", GA3);

    private final QuestionPage GQ4 = new QuestionPage("När fick Sverige färg-TV?", GA4);

    private final QuestionPage GQ5 = new QuestionPage("Vad hette Tokyo förr", GA5);

    private final QuestionPage GQ6 = new QuestionPage("När skapades Facebook?", GA6);

    private final QuestionPage GQ7 = new QuestionPage("Vilket land har störst yta?", GA7);

    private final QuestionPage GQ8 = new QuestionPage("Hur många kanter har en kub?", GA8);

    private final QuestionPage GQ9 = new QuestionPage("Ungefär hur mycket koffein är de i 100 kaffe", GA9);

    private final QuestionPage GQ10 = new QuestionPage("Vad hade Einstein för IQ?", GA10);

    //--------------------------------------------------------------------------------------//
    // Geography Answers
    private final List<QuestionPage> geograohyList = new ArrayList<>();


    //--------------------------------------------------------------------------------------//
    // Geography Answers


    //--------------------------------------------------------------------------------------//
    //--------------------------------------------------------------------------------------//
    // History Answers
    private final List<QuestionPage> HistoryList = new ArrayList<>();


    //--------------------------------------------------------------------------------------//
    // History Answers

    //--------------------------------------------------------------------------------------//




    Database(){


        // General knowledge
        generalKnowledgeList.add(GQ1);
        generalKnowledgeList.add(GQ2);
        generalKnowledgeList.add(GQ3);
        generalKnowledgeList.add(GQ4);
        generalKnowledgeList.add(GQ5);
        generalKnowledgeList.add(GQ6);
        generalKnowledgeList.add(GQ7);
        generalKnowledgeList.add(GQ8);
        generalKnowledgeList.add(GQ9);
        generalKnowledgeList.add(GQ10);

        Collections.shuffle(generalKnowledgeList);

       var a = generalKnowledgeList.get(0).getAnswers().getCorrectAnswer(generalKnowledgeList.get(0).getAnswers().getRightAnswer());

        System.out.println(a);
    }

    public static void main(String[] args) {
        new Database();
    }

}
