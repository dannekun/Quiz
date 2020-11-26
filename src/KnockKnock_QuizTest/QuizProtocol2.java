package KnockKnock_QuizTest;

import QuestionsHandler.Answers;
import QuestionsHandler.Categories.AnimalsNature;
import QuestionsHandler.Categories.GeneralKnowledge;
import QuestionsHandler.Database;
import QuestionsHandler.Questions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miwa Guhrés
 * Date: 2020-11-25
 * Time: 10:19
 * Project: Quiz
 * Copyright: MIT
 */
public class QuizProtocol2{
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

    Database database = new Database();
    AnimalsNature animalsNature= database.animalsNature;


    AnimalsNature category = database.animalsNature;
    String categoryName = category.getCategoryName();
    //-----------1----------------
    Questions questionObject1 = category.getAnimalsNatureList().get(0);


    String stringQuestion1 =  questionObject1.getQuestion();

    Answers answerObject1 = questionObject1.getAnswerObject();

    List<String> randomList = answerObject1.getShuffledAnswersList();

    String answer1_1 = randomList.get(0);
    String answer1_2 = randomList.get(1);
    String answer1_3 = randomList.get(2);
    String answer1_4 = randomList.get(3);

    String rightAnswerAN1 = questionObject1.getAnswerObject().getRightAnswer();

//-----------2----------------
    Questions questionObject2 = category.getAnimalsNatureList().get(1);

    String stringQuestion2 =  questionObject2.getQuestion();

    Answers answerObject2= questionObject2.getAnswerObject();

    List<String> randomList2 = answerObject2.getShuffledAnswersList();

    String answer2_1 = randomList2.get(0);
    String answer2_2 = randomList2.get(1);
    String answer2_3 = randomList2.get(2);
    String answer2_4= randomList2.get(3);

    String rightAnswerAN2 = questionObject2.getAnswerObject().getRightAnswer();

    //-----------3----------------

    Questions questionObject3 = category.getAnimalsNatureList().get(2);

    String stringQuestion3 =  questionObject3.getQuestion();

    Answers answerObject3= questionObject3.getAnswerObject();

    List<String> randomList3 = answerObject3.getShuffledAnswersList();

    String answer3_1 = randomList3.get(0);
    String answer3_2 = randomList3.get(1);
    String answer3_3 = randomList3.get(2);
    String answer3_4= randomList3.get(3);

    String rightAnswerAN3 = questionObject3.getAnswerObject().getRightAnswer();

    String[] quiz = {stringQuestion1,stringQuestion2, stringQuestion3};

    String[][] answerOptions = {{answer1_1,answer1_2,answer1_3,answer1_4}, {answer2_1,answer2_2,answer2_3,answer2_4}, {answer3_1,answer3_2,answer3_3,answer3_4}};

    String[] answer = {rightAnswerAN1,rightAnswerAN2,rightAnswerAN3};

    public String processInput(String input) {
        String output = null;

        if (state == WAITING||state == SENTANSWER) {

            output = quiz[currentQuestion] + " " +
                    "alternativ1: " + answerOptions[currentQuestion][0] + ", " +
                    "alternativ2: " + answerOptions[currentQuestion][1] + ", " +
                    "alternativ3: " + answerOptions[currentQuestion][2] + ", " +
                    "alternativ4: " + answerOptions[currentQuestion][3];

            state = SENTQUESTION;// state0->1

        } else if (state == SENTQUESTION) { // state 1 -> Server send question to Client

            if (currentQuestion != (numberOfQuestions - 1)) {// currentQuestion index 0 or 1
                if (input.equalsIgnoreCase(answer[currentQuestion])) {
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
                if (input.equalsIgnoreCase(answer[currentQuestion])) {
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
