package KnockKnock_QuizTest;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Miwa Guhrés
 * Date: 2020-11-16
 * Time: 23:19
 * Project: TEST_GroupArbete
 * Copyright: MIT
 */
public class TestQuizProtocol {


    public static void main(String[] args) {

        final int WAITING = 0;
        final int CHOSECATEGORY=1; //Add chose category
        final int SENTQUIZ = 2;
        final int SENTANSWER_OPTIONS = 3;
        final int SENTANSWER = 4;
        final int SENTPOINT = 5;
        final int SENTTOTALPOINTS = 6;
        final int COUNTCATEGORY=7;
        final int CATEGORYFINISH=8;

        int state = WAITING;
        //private  static final int NUMQUIZ_CATEGORY=3; //3quiz*3category

        int currentCategory = 0;//0-2(3category)
        int currentQuiz = 0;//0-2(3 quiz)
        int currentAnswerOptions = 0;//0-3 (4 options per quiz)

        String category;
        ArrayList<Integer> pointPerRound = new ArrayList<>(3);// 3 quiz per round
        int point;
        //List<Integer> TotalPoints = new ArrayList<Integer>();// round1,2,3

        int numberOfRounds = 3;
        int numberOfQuestions = 3;

        String[][] quiz = {{"m-aaaa", "m-bbbb", "m-cccc"},
                {"mo-dddd", "mo-eeee", "m-ffff"},
                {"g-gggg", "g-hhhh", "g-iii"}};

        String[][][] answerOptions = {{{"m-a", "aa", "aaa", "aaaa"}, {"m-b", "bb", "bbb", "bbbb"}, {"m-c", "cc", "ccc", "cccc"}},
                {{"mo-d", "dd", "ddd", "dddd"}, {"mo-e", "ee", "eee", "eeee"}, {"mo-f", "ff", "fff", "ffff"}},
                {{"g-g", "gg", "ggg", "gggg"}, {"g-h", "hh", "hhh", "hhhh"}, {"g-i", "ii", "iii", "iiii"}}};

        String[][] answer = {{"m-a", "m-b", "m-c"},
                {"mo-d", "mo-e", "m-f"},
                {"g-g", "g-h", "g-i"}};


        // public String processInput(String input) {
        //Properties p = new Properties();
        String output = null;
        //int numberOfRounds = Integer.parseInt(p.getProperty("numberOfRounds"));

        for (int i = 0; i < numberOfRounds; i++) {

            //Scanner sc = new Scanner(System.in);
            //System.out.println("Input Category");
            //String input = sc.next();

            if (state == WAITING || state == CATEGORYFINISH) {
                Scanner sc = new Scanner(System.in);
                output = "Skriva Quiz Category";
                System.out.println(output);
                String input = sc.next();
                state= CHOSECATEGORY;

                if (input.equalsIgnoreCase("Math")) {

                    //---3 questions per round-------------
                    for (int j = 0; j < numberOfQuestions; j++) { //for loop
                        if (state == CHOSECATEGORY || state == SENTTOTALPOINTS) {
                            output = quiz[currentCategory = 0][currentQuiz];
                            System.out.println("Math-Question "+(j+1)+": " + output);
                            state = SENTQUIZ;

                        } else if (state == SENTQUIZ) {

                            //-----for loop 4 options--------------
                            for (int k = 0; k < 4; k++) {
                                output = answerOptions[currentCategory][currentQuiz][k];// 4 alternative answers
                                System.out.println("Answer Option " + (k + 1) + ": " + output);
                            }
                            state = SENTANSWER_OPTIONS;
                        } else if (state == SENTANSWER_OPTIONS) {

                            //Scanner sc = new Scanner(System.in);
                            System.out.println("Input answer");
                            input = sc.next();

                            if (input.equalsIgnoreCase(answer[currentCategory][currentQuiz])) {
                                output = "rightAnswer"; // Front: if(output.equalsIgnoreCase("rightAnswer")) boolean b= true; the button becomes green color.
                                System.out.println("Your answer : " + output);
                                state = SENTANSWER;
                                //----add point ArrayList----
                                point = 1;
                                pointPerRound.add(point);
                                output = String.valueOf(point);
                                System.out.println("You got " + output + "point");
                                state = SENTPOINT;
                            } else {
                                output = "wrongAnswer";// Front: boolean b= false; The button becomes red color.
                                System.out.println("Your answer : " + output);
                                state = SENTANSWER;
                                //----add point ArrayList----
                                point = 0;//
                                pointPerRound.add(point);
                                output = String.valueOf(point);
                                System.out.println("You got " + output + "point");
                                state = SENTPOINT;
                            }
                            currentQuiz++; // question 1-3 within the same category
                            //currentQuiz = currentQuiz++ % 3;//0,1,2,0,1,2....
                            currentAnswerOptions++;//question 1-3 4 options per question
                            //currentAnswerOptions = currentAnswerOptions++ % 3;
                            output = String.valueOf(SumPoints(pointPerRound));// send total points
                            System.out.println("currentQuiz: "+ currentQuiz);
                            System.out.println("Your total points: " + output);
                            System.out.println("Currnt category:"+ j);

                            state = SENTTOTALPOINTS;
                        }
                    }//  for loop finish
                    currentCategory++;
                    System.out.println(currentCategory);
                    currentCategory = 3;
                    state = CATEGORYFINISH;


                } else if (input.equalsIgnoreCase("Movie")) {
                    output = quiz[currentCategory = 1][currentQuiz];
                    System.out.println("Movie-Question: " + output);
                    state = SENTQUIZ;
                } else if (input.equalsIgnoreCase("Game")) {  //I forget {}
                    output = quiz[currentCategory = 2][currentQuiz];
                    System.out.println("Game-Question: " + output);
                    state = SENTQUIZ;
                }

                // for loop to 3questions per round


                // state = SENTTOTALPOINTS;
            }
            //   return output;
            //}
        }
    }

    public static int SumPoints (ArrayList < Integer > list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            sum += n;
        }
        return sum;
    }

}
