package QuestionsHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Salah Abdinoor
 * 11/13/2020
 * 10:09 AM
 * Quiz
 * Copyright: MIT
 */
public class Answers {

    private String rightAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    public Answers(String rightAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {

        this.rightAnswer = rightAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;

    }

    /**
     * This method gives you a shuffled list of the type List<String>.
     *
     * @return shuffledAnswerList
     */

    public List getShuffledAnswersList(){

        List<String> shuffledAnswerList = new ArrayList<>();

        shuffledAnswerList.add(rightAnswer);
        shuffledAnswerList.add(wrongAnswer1);
        shuffledAnswerList.add(wrongAnswer2);
        shuffledAnswerList.add(wrongAnswer3);

        Collections.shuffle(shuffledAnswerList);

        return shuffledAnswerList;

    }

    /**
     * This method gives you a list of the type List<String> in correct order.
     *
     * @return AnswerList
     */

    public List getAnswersList(){
        List<String> answerList = new ArrayList<>();

        answerList.add(rightAnswer);
        answerList.add(wrongAnswer1);
        answerList.add(wrongAnswer2);
        answerList.add(wrongAnswer3);

        return answerList;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }


    /**
     * This method gives the user the correct answer as true/false
     *
     * @param getTextFromClick
     * @return
     */

    //FixMe:
    // Byt denna till e.getSource
    // När användaren klickar på ett svarsalternativ så ska klicket(e.getSource) hämta texten från knappen(getText())
    // och om den texten motsvarar getRightAnswer så spottas en true boolean ut, om inte så blir det false.

    public boolean checkAnswer(String getTextFromClick){

        if (getRightAnswer() == getTextFromClick)
            return true;

        return false;
    }

}