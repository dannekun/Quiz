import QuestionsHandler.Questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 19:54
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Player implements Serializable {

    public static final long serialVersionUID = 1L;

    String name = null;
    int points = 0;
    int round = 0;
    int maxRound;
    int question = 0;
    int maxQuestion;
    boolean finished = false;

    int closeGameOption = 0;

    public int getCloseGameOption() {
        return closeGameOption;
    }

    public void setCloseGameOption(int closeGameOption) {
        this.closeGameOption = closeGameOption;
    }

    boolean didYouGetIt = false;

    public boolean isDidYouGetIt() {
        return didYouGetIt;
    }

    public void setDidYouGetIt(boolean didYouGetIt) {
        this.didYouGetIt = didYouGetIt;
    }



    boolean connected = false;

    boolean endState = true;

    boolean clicked = false;


    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    private int PLAYER;
    public int getPLAYER() {
        return PLAYER;
    }

    public void setPLAYER(int PLAYER) {
        if (PLAYER == 1){
            this.PLAYER = 1;
        }else if (PLAYER == 2){
            this.PLAYER = 2;
        }

    }

    public boolean getFinished() {
        return finished;
    }


    List<String> currentQuestion = new ArrayList<>();

    List<String> roundCategories = new ArrayList<>();

    List<Boolean> answers = new ArrayList<>();

    List<Boolean> roundAnswers = new ArrayList<>();

    List<Questions> questionToPassBetweenPlayers = new ArrayList<>();


    public void addQuestionBetweenPlayers(Questions quest){
        questionToPassBetweenPlayers.add(quest);
    }

    public List<Questions> getQuestionToPassBetweenPlayers() {
        return questionToPassBetweenPlayers;
    }


    public void addQuestionToCurrentList(String s){
        currentQuestion.add(s);
    }

    public List<String> getCurrentQuestion() {
        return currentQuestion;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMaxQuestion() {
        return maxQuestion;
    }

    public void setMaxQuestion(int maxQuestion) {
        this.maxQuestion = maxQuestion;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getMaxRound() {
        return maxRound;
    }

    public void setMaxRound(int maxRound) {
        this.maxRound = maxRound;
    }


    public void addToRoundAnswersList(Boolean b){
        roundAnswers.add(b);
    }

    public List<Boolean> getRoundAnswers() {
        return roundAnswers;
    }

    public void answersAddToList(Boolean b){
        this.answers.add(b);
    }

    public List<Boolean> getAnswers() {
        return answers;
    }







    public List<Boolean> removeAnswersFromList(List<Boolean> listToRemoveFrom, int antal, int round){
        int max = antal * round;
        System.out.println("MAX: " + max);
        int back = max-1;
        for (int i = 0; i < antal; i++) {

            System.out.println("i: "+ i);
            System.out.println("max: "+ max);
            System.out.println("back: "+ back);
            listToRemoveFrom.remove(back);
            System.out.println();
            back--;
        }
        if (max == 0){
            listToRemoveFrom.clear();
        }
        return listToRemoveFrom;
    }






    public List<Boolean> changeList(List<Boolean> temp) {
        answers = temp;
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.answers = answers;
    }

    public void addToList(String input){
        this.roundCategories.add(input);
    }

    public List<String> getRoundCategories() {
        return roundCategories;
    }


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    Player(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
