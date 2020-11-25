package QuestionsHandler;

import java.io.Serializable;
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
public class Answers implements Serializable {

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

    public List<String> getShuffledAnswersList(){

        List<String> shuffledAnswerList = new ArrayList<>();

        shuffledAnswerList.add(rightAnswer);
        shuffledAnswerList.add(wrongAnswer1);
        shuffledAnswerList.add(wrongAnswer2);
        shuffledAnswerList.add(wrongAnswer3);

        Collections.shuffle(shuffledAnswerList);

        return shuffledAnswerList;

    }

    public void shuffleAnswers(List<String> answersToShuffle){
        Collections.shuffle(answersToShuffle);
    }

    /**
     * This method gives you a list of the type List<String> in correct order.
     *
     * @return AnswerList
     */

    public List<String> getAnswersList(){
        List<String> answerList = new ArrayList<>();

        answerList.add(this.rightAnswer);
        answerList.add(this.wrongAnswer1);
        answerList.add(this.wrongAnswer2);
        answerList.add(this.wrongAnswer3);

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

        if (getRightAnswer() == getTextFromClick){
            return true;
        }else {
            return false;
        }

    }

}
