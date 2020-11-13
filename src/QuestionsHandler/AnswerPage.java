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
public class AnswerPage {

    private String rightAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;

    public AnswerPage(String rightAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3) {

        this.rightAnswer = rightAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;

    }

    public List ShuffleAnswers(){

        List<String> answerPageList = new ArrayList<>();

        answerPageList.add(rightAnswer);
        answerPageList.add(wrongAnswer1);
        answerPageList.add(wrongAnswer2);
        answerPageList.add(wrongAnswer3);

        Collections.shuffle(answerPageList);

        return answerPageList;

    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    /**
     * This method gives the user the correct answer as true/false
     *
     * @param getTextFromClick
     * @return
     */

    // Byt denna till e.getSource
    public boolean getCorrectAnswer(String getTextFromClick){

        if (getRightAnswer() == getTextFromClick)
            return true;

        return false;
    }

}
