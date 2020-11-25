package QuestionsHandler;

import java.io.Serializable;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */

public class Questions implements Serializable {

    private String question;
    private Answers answers;

    public Questions(String question, Answers answers) {

        this.question = question;
        this.answers = answers;
    }

    public Answers getAnswerObject(){
        return answers;
    }

    public String getQuestion() {
        return question;
    }
}
