package QuestionsHandler;

import QuestionsHandler.Categories.GeneralKnowledge;

import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/14/2020
 * 4:34 PM
 * Quiz2
 * Copyright: MIT
 */
public class Demo {

    public static void main(String[] args) {

        //____________________________________________________________________________________________________________//
        // For Education Purposes.

        GeneralKnowledge generalKnowledge = new GeneralKnowledge();

        // This gives you a random question from the GeneralKnowledge class. <Type String>

        var question = generalKnowledge.getGeneralKnowledgeList().get(0).getQuestion();

        // This gives you a list of answer in the right order. <Type List>

        var correctList = generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject().getAnswersList();

        // This gives you a list of answers in a shuffled order. BUT if you call the same command twice it
        // will shuffle by it twice, to prevent this you have to save the previous shuffled list in a
        // TempList as shown below.
        //_____________________________________________________________________________________________________________//
        var shuffledList= generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject().getShuffledAnswersList();

        var shuffledListTemp = shuffledList;

        var shuffledList2 = generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject().getShuffledAnswersList();;
        //-------------------------------------------------------------------------------------------------------------//

        // This gives you gives you the same list as the previous "correctList".
        var correctList2 = generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject().getAnswersList();;


        // Run this program for a Demo. Note all the variables are different.
        // Demo 1:

//        System.out.println("Question: " + question);
//        System.out.println("\nCorrect List: " + correctList);
//        System.out.println("\nShuffled List: " + shuffledList);
//        System.out.println("\nSaved Shuffled List: " + shuffledListTemp);
//        System.out.println("\nNew Shuffled List: " + shuffledList2);
//        System.out.println("\nBack to Correct List: " + correctList2);

        //------------------------------------------------------------------------------------------------------------//
        // This is how you get the right answer.
        var rightAnswer = generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject().getRightAnswer();

        // the .checkAnswer-Method is a boolean that checks to see if the answer "e.getSource -> x.getText"
        // is the same as the getRightAnswer(); -- For more info ctrl+click: checkAnswer();

        var checkAnswerTrue = generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject()
                .checkAnswer(rightAnswer);

        var checkAnswerFalse = generalKnowledge.getGeneralKnowledgeList().get(0).getAnswerObject()
                .checkAnswer("BlaBlaBla");


        // Run this program for a Demo.
        // Demo 2:

//        System.out.println("Question: " + question);
//        System.out.println("\nAnswer Options: " + correctList);
//        System.out.println("\nRight Answer: " + rightAnswer);
//        System.out.println("\nChecking Right Answer: " + checkAnswerTrue);
//        System.out.println("\nChecking Wrong Answer: " + checkAnswerFalse);

        //____________________________________________________________________________________________________________//
        // Test block: Feel free to try diffrent combos.







        //_____________________________________________________________________________________________________________//

        // For Education Purposes.
        //____________________________________________________________________________________________________________//

    }
}
