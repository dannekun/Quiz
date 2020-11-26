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
    int round;
    int maxRound;
    int question;
    int maxQuestion;
    boolean finished = false;
    boolean startedGame = false;

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
