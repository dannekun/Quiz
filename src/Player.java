/**
 * Created by Daniel Bojic
 * Date: 2020-11-12
 * Time: 19:54
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Player {

    String name;
    int points;
    int round;



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
