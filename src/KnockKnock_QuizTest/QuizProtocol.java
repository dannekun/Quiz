package KnockKnock_QuizTest;

import java.util.ArrayList;


/**
 * Created by Miwa Guhrés
 * Date: 2020-11-16
 * Time: 23:19
 * Project: TEST_GroupArbete
 * Copyright: MIT
 */

//----------------It DOSE NOT work OLD I need fix TestQuizProtocol-----------

public class QuizProtocol {
    private static final int WAITING=0;
    private static final int SENTQUIZ = 1;
    private static final int SENTANSWER_OPTIONS = 2;
    private static final int SENTANSWER=3;
    private static final int SENTPOINT=4;
    private static final int SENTTOTALPOINTS=5;

    private int state = WAITING;
    //private  static final int NUMQUIZ_CATEGORY=3; //3quiz*3category

    private int currentCategory=0;//0-2(3category)
    private int currentQuiz=0;//0-2(3 quiz)
    private int currentAnswerOptions=0;//0-3 (4 options per quiz)

    public String category;
    private ArrayList<Integer> pointPerRound= new ArrayList<>(3);// 3 quiz per round
    private int point;
    //List<Integer> TotalPoints = new ArrayList<Integer>();// round1,2,3

    private int numberOfRounds=3;




    private  String[][] quiz={{"m-aaaa","m-bbbb","m-cccc"},
            {"mo-dddd","mo-eeee","m-ffff"},
            {"g-gggg","g-hhhh","g-iii"}};

    private String [][][] answerOptions={{{"m-a","aa","aaa","aaaa"},{"m-b","bb","bbb","bbbb"},{"m-c","cc","ccc","cccc"}},
            {{"mo-d","dd","ddd","dddd"},{"mo-e","ee","eee","eeee"},{"mo-f","ff","fff","ffff"}},
            {{"g-g","gg","ggg","gggg"},{"g-h","hh","hhh","hhhh"},{"g-i","ii","iii","iiii"}}};

    private  String[][] answer={{"m-a","m-b","m-c"},
            {"mo-d","mo-e","m-f"},
            {"g-g","g-h","g-i"}};

    public String processInput(String input) {
        //Properties p = new Properties();
        String output = null;
        //int numberOfRounds = Integer.parseInt(p.getProperty("numberOfRounds"));


        for (int i = 0; i < numberOfRounds; i++) {
            if (state == WAITING || state == SENTTOTALPOINTS) {
                output="Skriva Quiz Category";

                if (input.equalsIgnoreCase("Math")) {
                    output = quiz[currentCategory = 0][currentQuiz];
                    state = SENTQUIZ;
                } else if (input.equalsIgnoreCase("Movie")) {
                    output = quiz[currentCategory = 1][currentQuiz];
                    state = SENTQUIZ;
                } else if(input.equalsIgnoreCase("Game")) {
                    output = quiz[currentCategory = 2][currentQuiz];
                    state = SENTQUIZ;
                }

            } else if (state == SENTQUIZ) {
                output = answerOptions[currentCategory][currentQuiz][currentAnswerOptions];// 4 alternative answers
                state = SENTANSWER_OPTIONS;
            } else if (state == SENTANSWER_OPTIONS) {

                if (input.equalsIgnoreCase(answer[currentCategory][currentQuiz])) {
                    output = "rightAnswer"; // Front: if(output.equalsIgnoreCase("rightAnswer")) boolean b= true; the button becomes green color.
                    state = SENTANSWER;
                    //----add point ArrayList----
                    point = 1;
                    pointPerRound.add(currentQuiz);
                    output=String.valueOf(point);
                    state = SENTPOINT;
                } else {
                    output = "WrongAnswer";// Front: boolean b= false; The button becomes red color.
                    state = SENTANSWER;
                    //----add point ArrayList----
                    point = 0;//
                    pointPerRound.add(currentQuiz);
                    output=String.valueOf(point);
                    state = SENTPOINT;
                }
                currentQuiz++; // quiz 1-3 within the same category
                currentAnswerOptions++;// 4 options per quiz
                output= String.valueOf(SumPoints(pointPerRound));// send total points
                state= SENTTOTALPOINTS;

            }

        }
        return output;
    }


    public static int SumPoints(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            sum += n;
        }
        return sum;
    }

}