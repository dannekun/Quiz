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

    String name = null;
    int points = 0;
    int round = 0;
    int maxRound;
    int question = 0;
    int maxQuestion;
    boolean finished = false;
    boolean startedGame = false;

    boolean connected = false;

    boolean endState = true;


    public boolean isEndState() {
        return endState;
    }

    public void setEndState(boolean endState) {
        this.endState = endState;
    }

    private static final int LOGGIN = 0; //LOGGINGUI
    private static final int QUEUE_NOTCONNECTED = 1; //HOMEPAGE_WAITING
    private static final int QUEUE_CONNECTED = 2;
    private static final int GAME_WAITING = 3;
    private static final int GAME_READY = 4;
    private static final int CHOSECAT = 5;
    private static final int PLAY = 6;
    private static final int RESULT = 7;

    private int STATE = LOGGIN;

    public int getSTATE() {
        return STATE;
    }

    public void setSTATE(int STATE) {

        if (STATE == 0){
            this.STATE = LOGGIN;
        }else if (STATE == 1){
            this.STATE = QUEUE_NOTCONNECTED;
        }else if (STATE == 2){
            this.STATE = QUEUE_CONNECTED;
        }else if (STATE == 3){
            this.STATE = GAME_WAITING;
        }else if (STATE == 4){
            this.STATE = GAME_READY;
        }else if (STATE == 5){
            this.STATE = CHOSECAT;
        }else if (STATE == 6){
            this.STATE = PLAY;
        }else if (STATE == 7){
            this.STATE = RESULT;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    private static final int PLAYER1 = 1;
    private static final int PLAYER2 = 2;

    private int PLAYER;

    public static int getPLAYER1() {
        return PLAYER1;
    }

    public static int getPLAYER2() {
        return PLAYER2;
    }

    public int getPLAYER() {
        return PLAYER;
    }

    public void setPLAYER(int PLAYER) {
        if (PLAYER == 1){
            this.PLAYER = PLAYER1;
        }else if (PLAYER == 2){
            this.PLAYER = PLAYER2;
        }

    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    List<String> currentQuestion = new ArrayList<>();

    List<String> roundCategories = new ArrayList<>();

    List<Boolean> answers = new ArrayList<>();

    List<Boolean> roundAnswers = new ArrayList<>();

    List<Questions> questionToPassBetweenPlayers = new ArrayList<>();


    public void addQuestionBetweenPlayers(Questions quest){
        this.questionToPassBetweenPlayers.add(quest);
    }

    public List<Questions> getQuestionToPassBetweenPlayers() {
        return questionToPassBetweenPlayers;
    }

    public void setQuestionToPassBetweenPlayers(List<Questions> questionToPassBetweenPlayers) {
        this.questionToPassBetweenPlayers = questionToPassBetweenPlayers;
    }

    public void addQuestionToCurrentList(String s){
        currentQuestion.add(s);
    }

    public List<String> getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(List<String> currentQuestion) {
        this.currentQuestion = currentQuestion;
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

    public void setRoundAnswers(List<Boolean> roundAnswers) {
        this.roundAnswers = roundAnswers;
    }

    public void answersAddToList(Boolean b){
        this.answers.add(b);
    }

    public List<Boolean> getAnswers() {
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

    public void setRoundCategories(List<String> roundCategories) {
        this.roundCategories = roundCategories;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    Player(){
    }

    Player(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
