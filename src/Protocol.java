import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 18:59
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Protocol {


    public Player processInput(Player pro) throws IOException, InterruptedException {

        //HomePage_waiting homePage_waiting = new HomePage_waiting();

        if (pro.getSTATE() == 0){
            return logginProtocol(pro);

        }else if (pro.getSTATE() == 1){
//queueWaitProtocol(pro);



            //STATE = GAME;
        }else if(pro.getSTATE() == 2){
           // homePage_waiting.closeWindow();
      queuePlayProtocol(pro);
        }/*
        else if (pro.getSTATE() == GAME_WAITING){
            GamePage_waiting g = new GamePage_waiting(pro);


           // STATE = CHOSECAT;
        } else if (pro.getSTATE() == GAME_READY){
            GamePage_play gamePage_play = new GamePage_play(pro);

        } else if (pro.getSTATE() == CHOSECAT){
            CategoryPage q = new CategoryPage(pro);
           // STATE = PLAY;
        }else if (pro.getSTATE() == PLAY){
            QuestionPage quest = new QuestionPage(pro);
           // STATE = RESULT;
        }else if (pro.getSTATE() == RESULT){
            ResultPage r = new ResultPage(pro);
        }

      */

     return pro;
    }

public Player logginProtocol(Player pro) throws InterruptedException {
    LoginGUI gui = new LoginGUI();
    gui.showWindow();
    //BEHÖVER INTE SKICKA
    //pro.setName("daniel");

    //PROGRAMMET FUNGERAR UTMÄRKT OM MAN SETNAME
    while (pro.getName() == null){
        //pro =  gui.findPlayerAndReturn();
        pro.setName(gui.findPlayerAndReturn());


    }


    return pro;

}

public Player queueWaitProtocol(Player pro){

    pro.setEndState(false);
    //  STATE = LOGGIN;
    return pro;
}

public Player queuePlayProtocol(Player pro){
    HomePage_play hPlay = new HomePage_play();
    hPlay.showWindow(pro);

    while(!hPlay.isClicked()){
        hPlay.findClickPlay();
        pro.setSTATE(4);
        pro.setConnected(false);
    }

    pro.setEndState(false);
    return pro;


}

}
