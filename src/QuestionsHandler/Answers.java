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

    public List<String> getShuffledAnswersList(){

        List<String> shuffledAnswerList = new ArrayList<>();

        shuffledAnswerList.add(rightAnswer);
        shuffledAnswerList.add(wrongAnswer1);
        shuffledAnswerList.add(wrongAnswer2);
        shuffledAnswerList.add(wrongAnswer3);

        Collections.shuffle(shuffledAnswerList);

        return shuffledAnswerList;

    }

    public String getRightAnswer() {
        return rightAnswer;
    }

}
