import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 19:54
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Player {

    String name;
    int points = 0;
    int round;
    int maxRound;
    int question;
    int maxQuestion;


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

    List<String> roundCategories = new ArrayList<>();

    List<Boolean> answers = new ArrayList<>();

    List<Boolean> roundAnswers = new ArrayList<>();


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
