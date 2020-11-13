package QuestionsHandler;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 14:06
 * Project: Quizkampen
 * Copyright: MIT
 */

public class QuestionsPage {

    private String question;
    private AnswerPage answers;

    public QuestionsPage(String question, AnswerPage answers) {
        this.question = question;
        this.answers = answers;

    }

    public static void main(String[] args) {
        Database g =  new GeneralKnowledge();
        g.getName();

        QuestionsPage a = new QuestionsPage("fråga", new AnswerPage("1","2","3","4"));

    }



    public AnswerPage getAnswers() {
        return answers;
    }


    public String getQuestion() {
        return question;
    }
}
