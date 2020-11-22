package KnockKnock_QuizTest;
import java.util.ArrayList;

/**
 * Created by Miwa Guhrés
 * Date: 2020-11-21
 * Time: 15:43
 * Project: OOPJava_4
 * Copyright: MIT
 */
public class QuizProtocol {

    private static final int WAITING = 0;
    private static final int SENTQUESTION = 1;
    private static final int SENTANSWER = 2;


    private int state = WAITING;

    private ArrayList<Integer> pointPerRound = new ArrayList<>(3);// 3 quiz per round
    private int point;

    private int currentCategory = 0;//0-2(3category)
    private int currentQuestion = 0;//0-2(3 quiz)

    private int numberOfQuestions = 3;


    //----------Ver. 4--------------------
    /** This is protocol for QuestionPage
     * CategoryPage-->Press category x button
     * QuestionPage-->Take data x and set the data to lists index 0,
     * player 1 go through 3 questions
     * player2 go through  3 questions
     *
     * I don´t know how the server fetch the questions from database.....
     * User will click the category button at the CategoryPage.
     * The selected 3 questions, Options and answers
     * are added to the quiz list, answerOptions list and answer.
     *
     * May be the lists does not need to index 0-2......
     * Every time the selected category´s questions options and answers added to index 0?????
     */

    String[][] quiz = {{"m-aaaa", "m-bbbb", "m-cccc"},
            {"mo-dddd", "mo-eeee", "m-ffff"},
            {"g-gggg", "g-hhhh", "g-iii"}};

    String[][][] answerOptions = {{{"m-a", "aa", "aaa", "aaaa"}, {"m-b", "bb", "bbb", "bbbb"}, {"m-c", "cc", "ccc", "cccc"}},
            {{"mo-d", "dd", "ddd", "dddd"}, {"mo-e", "ee", "eee", "eeee"}, {"mo-f", "ff", "fff", "ffff"}},
            {{"g-g", "gg", "ggg", "gggg"}, {"g-h", "hh", "hhh", "hhhh"}, {"g-i", "ii", "iii", "iiii"}}};

    String[][] answer = {{"m-a", "m-b", "m-c"},
            {"mo-d", "mo-e", "m-f"},
            {"g-g", "g-h", "g-i"}};

    public String processInput(String input) {
        String output = null;

        if (state == WAITING||state == SENTANSWER) {

            output = quiz[currentCategory][currentQuestion] + " " +
                    "alternativ1: " + answerOptions[currentCategory][currentQuestion][0] + ", " +
                    "alternativ2: " + answerOptions[currentCategory][currentQuestion][1] + ", " +
                    "alternativ3: " + answerOptions[currentCategory][currentQuestion][2] + ", " +
                    "alternativ4: " + answerOptions[currentCategory][currentQuestion][3];

            state = SENTQUESTION;// state0->1

        } else if (state == SENTQUESTION) { // state 1 -> Server send question to Client

            if (currentQuestion != (numberOfQuestions - 1)) {// currentQuestion index 0 or 1
                if (input.equalsIgnoreCase(answer[currentCategory][currentQuestion])) {
                    //----add point ArrayList----
                    point = 1;
                    pointPerRound.add(point);
                    //---------send to client side---------
                    output = "rightAnswer" + ", " + // To change green color of the answer button
                            //"Du har fått" + String.valueOf(point) +" poäng" +", "+ // To show the result
                            "Din total poäng: " + String.valueOf(SumPoints(pointPerRound)) + ", " + //To show the result(Total points)
                            "Skriva f (Trycka Fortsätt button)";// This is the trigger to the next question

                } else {
                    //----add point ArrayList----
                    point = 0;
                    pointPerRound.add(point);
                    output = "wrongAnswer" + ", " + // To change green color of the answer button
                            //"Du har fått " + String.valueOf(point) +" poäng"+", "+
                            "Din total poäng: " + String.valueOf(SumPoints(pointPerRound)) + ", " + //To show the result(Total points)
                            "Skriva f (Trycka Fortsätt button)";// This is the trigger to the next question

                }
                currentQuestion++;
                state = SENTANSWER;//state1->2

            } else { //last question
                if (input.equalsIgnoreCase(answer[currentCategory][currentQuestion])) {
                    //----add point ArrayList----
                    point = 1;
                    pointPerRound.add(point);
                    //---------send to client side---------
                    output = "rightAnswer" + ", " + // To change green color of the answer button
                            //"Du har fått" + String.valueOf(point) +" poäng" +", "+ // To show the result
                            "Din total poäng: " + String.valueOf(SumPoints(pointPerRound)) + ", " + //To show the result(Total points)
                            "En rond är slut! Trycka Cancel";

                } else {
                    //----add point ArrayList----
                    point = 0;
                    pointPerRound.add(point);
                    output = "wrongAnswer" + ", " + // To change green color of the answer button
                            //"Du har fått " + String.valueOf(point) +" poäng"+", "+
                            "Din total poäng: " + String.valueOf(SumPoints(pointPerRound)) + ", " + //To show the result(Total points)
                            "En rond är slut! Trycka Cancel";
                }
            }
        }

        return output;
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