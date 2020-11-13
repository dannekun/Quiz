package QuestionsHandler;


/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */
public class QuestionPage{

    private String question;
    private AnswerPage answers;

    public QuestionPage(String question, AnswerPage answers) {
        this.question = question;
        this.answers = answers;
    }

    public AnswerPage getAnswers() {
        return answers;
    }


    public String getQuestion() {
        return question;
    }
}
