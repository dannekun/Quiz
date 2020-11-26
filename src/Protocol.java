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
    private static final int LOGGIN = 0; //LOGGINGUI
    private static final int QUEUE_NOTCONNECTED = 1; //HOMEPAGE_WAITING
    private static final int QUEUE_CONNECTED = 2;
    private static final int GAME_WAITING = 3;
    private static final int GAME_READY = 4;
    private static final int CHOSECAT = 5;
    private static final int PLAY = 6;
    private static final int RESULT = 7;

    private int STATE = LOGGIN;

    public Player processInput(Player pro) throws IOException, InterruptedException {

        if (pro.getSTATE() == LOGGIN){
            LoginGUI gui = new LoginGUI();
            //BEHÖVER INTE SKICKA
            while (pro.getName() == null){
                pro =  gui.findPlayerAndReturn();
                pro.setSTATE(1);
            }

            System.out.println(pro.getName());

            return pro;
        }else if (pro.getSTATE() == QUEUE_NOTCONNECTED){

            //BEHÖVER SKICKA
          //  HomePage_waiting h = new HomePage_waiting(pro);



            /*
            while (pro.getName() == null){
                if (pro.getName() != null) {

                }
            }

             */
            pro.setEndState(false);
          //  STATE = LOGGIN;
            return pro;

            //STATE = GAME;
        }else if(pro.getSTATE() == QUEUE_CONNECTED){
            HomePage_play hPlay = new HomePage_play(pro);

            while(!hPlay.isClicked()){
                hPlay.findClickPlay();
            }

        } else if (pro.getSTATE() == GAME_WAITING){
            GamePage_waiting g = new GamePage_waiting(pro);


           // STATE = CHOSECAT;
        } else if (pro.getSTATE() == GAME_READY){

        } else if (pro.getSTATE() == CHOSECAT){
            CategoryPage q = new CategoryPage(pro);
           // STATE = PLAY;
        }else if (pro.getSTATE() == PLAY){
            QuestionPage quest = new QuestionPage(pro);
           // STATE = RESULT;
        }else if (pro.getSTATE() == RESULT){
            ResultPage r = new ResultPage(pro);
        }

     return pro;
    }
}
