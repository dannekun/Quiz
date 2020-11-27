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

    public void sleepThisProgram() throws InterruptedException {
        Thread.sleep(1000);
    }


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
        }
        else if (pro.getSTATE() == 3){
            GamePage_waiting g = new GamePage_waiting(pro);


           // STATE = CHOSECAT;
        } else if (pro.getSTATE() == 4){
            GamePage_play gamePage_play = new GamePage_play(pro);

        } else if (pro.getSTATE() == 5){
            CategoryPage q = new CategoryPage(pro);
           // STATE = PLAY;
        }else if (pro.getSTATE() == 6){
            QuestionPage quest = new QuestionPage(pro);
           // STATE = RESULT;
        }else if (pro.getSTATE() == 7){
            ResultPage r = new ResultPage(pro);
        }



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
        String name = gui.findPlayerAndReturn();
        pro.setName(name);
        System.out.println("Loop");
        sleepThisProgram();
    }


    return pro;

}

public Player queueWaitProtocol(Player pro){

    pro.setEndState(false);
    //  STATE = LOGGIN;
    return pro;
}

public Player queuePlayProtocol(Player pro) throws InterruptedException {
    HomePage_play hPlay = new HomePage_play();
    hPlay.showWindow(pro);

    boolean workForMe = false;

    while(!workForMe){
        workForMe = hPlay.findClickPlay();
        pro.setSTATE(4);
        pro.setConnected(false);
        sleepThisProgram();
    }

    pro.setEndState(false);
    return pro;


}

}
