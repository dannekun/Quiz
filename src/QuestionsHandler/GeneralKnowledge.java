package QuestionsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/13/2020
 * 4:01 PM
 * Quiz2
 * Copyright: MIT
 */
public class GeneralKnowledge extends Database{

    String name = "General Knowledge";
    //--------------------------------------------------------------------------------------//
    // General Knowledge Answers
    private final List<QuestionsPage> generalKnowledgeList = new ArrayList<>();

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
    private final QuestionsPage GQ1 = new QuestionsPage("I vilket land är Vientiane huvudstad?", GA1);

    private final QuestionsPage GQ2 = new QuestionsPage("Vilket av följande är stavning?", GA2);

    private final QuestionsPage GQ3 = new QuestionsPage("Hur många länder finns det i världen?", GA3);

    private final QuestionsPage GQ4 = new QuestionsPage("När fick Sverige färg-TV?", GA4);

    private final QuestionsPage GQ5 = new QuestionsPage("Vad hette Tokyo förr", GA5);

    private final QuestionsPage GQ6 = new QuestionsPage("När skapades Facebook?", GA6);

    private final QuestionsPage GQ7 = new QuestionsPage("Vilket land har störst yta?", GA7);

    private final QuestionsPage GQ8 = new QuestionsPage("Hur många kanter har en kub?", GA8);

    private final QuestionsPage GQ9 = new QuestionsPage("Ungefär hur mycket koffein är de i 100 kaffe", GA9);

    private final QuestionsPage GQ10 = new QuestionsPage("Vad hade Einstein för IQ?", GA10);


    public GeneralKnowledge(){

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

        var a = generalKnowledgeList.get(0).getAnswers();

        generalKnowledgeList.get(0).getAnswers().ShuffleAnswers();

        var b = generalKnowledgeList.get(0).getAnswers();

        System.out.println(a);
        System.out.println(b);


    }

    public List<QuestionsPage> getAnswersFromCategory(){
        Collections.shuffle(generalKnowledgeList);

        return generalKnowledgeList;
    }

    public static void main(String[] args) {
        new GeneralKnowledge();
    }
}
