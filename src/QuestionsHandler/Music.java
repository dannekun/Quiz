package QuestionsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/13/2020
 * 4:48 PM
 * Quiz2
 * Copyright: MIT
 */
public class Music extends Database{

            //FIXME: GÖR OM FRÅGORNA!
    //--------------------------------------------------------------------------------------//
    // General Knowledge Answers
    private final List<QuestionsPage> musicList = new ArrayList<>();

    private final AnswerPage MA1 = new AnswerPage("Laos", "Thailand",
            "Vietnam", "Cambodia");

    private final AnswerPage MA2 = new AnswerPage("Desamma", "Detsamma",
            "Det samma", "Detsamman");

    private final AnswerPage MA3 = new AnswerPage("195", "220",
            "90", "164");

    private final AnswerPage MA4 = new AnswerPage("1965", "1970",
            "1975", "1980");

    private final AnswerPage MA5 = new AnswerPage("Edo", "Edoyo",
            "Kyoto", "Kygo");

    private final AnswerPage MA6 = new AnswerPage("2004", "2001",
            "2008", "1994");

    private final AnswerPage MA7 = new AnswerPage("Ryssland", "USA",
            "Canada", "Mongoliet");

    private final AnswerPage MA8 = new AnswerPage("12", "8",
            "6", "16");

    private final AnswerPage MA9 = new AnswerPage("40mg", "1g",
            "400mg", "15g");

    private final AnswerPage MA10 = new AnswerPage("160", "210",
            "90", "110");

    //--------------------------------------------------------------------------------------//
    // General Knowledge Questions
    private final QuestionsPage MQ1 = new QuestionsPage("I vilket land är Vientiane huvudstad?", MA1);

    private final QuestionsPage MQ2 = new QuestionsPage("Vilket av följande är stavning?", MA2);

    private final QuestionsPage MQ3 = new QuestionsPage("Hur många länder finns det i världen?", MA3);

    private final QuestionsPage MQ4 = new QuestionsPage("När fick Sverige färg-TV?", MA4);

    private final QuestionsPage MQ5 = new QuestionsPage("Vad hette Tokyo förr", MA5);

    private final QuestionsPage MQ6 = new QuestionsPage("När skapades Facebook?", MA6);

    private final QuestionsPage MQ7 = new QuestionsPage("Vilket land har störst yta?", MA7);

    private final QuestionsPage MQ8 = new QuestionsPage("Hur många kanter har en kub?", MA8);

    private final QuestionsPage MQ9 = new QuestionsPage("Ungefär hur mycket koffein är de i 100 kaffe", MA9);

    private final QuestionsPage MQ10 = new QuestionsPage("Vad hade Einstein för IQ?", MA10);

    public Music(){

        musicList.add(MQ1);
        musicList.add(MQ2);
        musicList.add(MQ3);
        musicList.add(MQ4);
        musicList.add(MQ5);
        musicList.add(MQ6);
        musicList.add(MQ7);
        musicList.add(MQ8);
        musicList.add(MQ9);
        musicList.add(MQ10);

        Collections.shuffle(musicList);

    }


}
