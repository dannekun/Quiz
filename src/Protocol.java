import java.io.IOException;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 18:59
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Protocol {
    private static final int LOGGIN = 0;
    private static final int QUEUE = 1;
    private static final int GAME = 2;
    private static final int CHOSECAT = 3;
    private static final int PLAY = 4;
    private static final int RESULT = 5;

    private int STATE = LOGGIN;

    public Player processInput(Player pro) throws IOException {

        
        if (STATE == LOGGIN){
            LoginGUI loginGUI = new LoginGUI();



            while (pro.getName() == null){

                    pro = loginGUI.findPlayerAndReturn();

            }

            STATE = QUEUE;
            return pro;
        }else if (STATE == QUEUE){

            HomePage_waiting h = new HomePage_waiting(pro);

            /*
            while (pro.getName() == null){
                if (pro.getName() != null) {

                }
            }

             */

            STATE = LOGGIN;
            return pro;

            //STATE = GAME;
        }else if (STATE == GAME){
            GamePage_waiting g = new GamePage_waiting(pro);


           // STATE = CHOSECAT;
        } else if (STATE == CHOSECAT){
            CategoryPage q = new CategoryPage(pro);
           // STATE = PLAY;
        }else if (STATE == PLAY){
            QuestionPage quest = new QuestionPage(pro);
           // STATE = RESULT;
        }else if (STATE == RESULT){
            ResultPage r = new ResultPage(pro);
        }

     return pro;
    }
}
