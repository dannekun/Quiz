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
        Thread.sleep(2000);
    }


    public Player processInput(Player pro, Player player2) throws IOException, InterruptedException {

        //HomePage_waiting homePage_waiting = new HomePage_waiting();

        if (pro.getSTATE() == 0){
            return logginProtocol(pro);

        }else if (pro.getSTATE() == 1){
//queueWaitProtocol(pro);

            //STATE = GAME;
        }else if(pro.getSTATE() == 2){
     return queuePlayProtocol(pro);
        }
        else if (pro.getSTATE() == 3){

            System.out.println("du kom till 3");
            //GamePage_waiting g = new GamePage_waiting(pro);
            if (pro.getRound() == 0 && pro.getPLAYER() == 1){
                pro.setSTATE(4);
            }else if (pro.getRound() == 0 && pro.getPLAYER() == 2) {
                pro.setSTATE(4);
            }

            System.out.println("du kom till state 3 men har redan spelat");
            Thread.sleep(2000);
            return pro;

           // STATE = CHOSECAT;
        } else if (pro.getSTATE() == 4){
            GamePage_play gamePage_play = new GamePage_play(pro, player2);




            while(!pro.isClicked()){
                pro = gamePage_play.findClickPlay();
                sleepThisProgram();
            }
            System.out.println(pro.getRound());
            System.out.println(pro.isPlayer1playedRound());
            System.out.println(pro.getPLAYER());
            System.out.println(pro.isPlayers2PlayedRound());

            if (pro.getRound() == 0 && pro.getPLAYER() == 2 && !pro.isPlayer1playedRound() && pro.isPlayers2PlayedRound()){
                pro.setSTATE(5);
                System.out.println("vi kom till den här jävla skiten");
            }else if (pro.getRound() == 0 && pro.getPLAYER() == 2 && pro.isPlayer1playedRound()){
                pro.setSTATE(6);
                System.out.println("bram du är inte false");
                pro.setPlayer1playedRound(false);
                //RETURN METOD MED FALSE PÅ PLAYED ROUND FÖR PLAYER 2
            }else if (pro.getRound() == 0 && pro.getPLAYER() == 1){
                pro.setSTATE(5);
            }else {
                pro.setSTATE(6);
            }

            pro.setRound(pro.getRound()+1);
           // pro.setQuestion(pro.getQuestion()+1);

            pro.setClicked(false);
            return pro;

        } else if (pro.getSTATE() == 5){
            CategoryPage q = new CategoryPage(pro);



            while(!pro.isClicked()){
                pro = q.findClickPlay();
                pro.setSTATE(6);
                sleepThisProgram();
                System.out.println("du kom in i den");
            }
            //HÄR LÄGG TILL CAT
            pro = q.addCatToPlayer();

            System.out.println("du kom ut ur den");
            pro.setClicked(false);
            //DEN SOM SPELAR SAMMA KATEGORI FÅR INTE VÄLJA KATEGORI
            return pro;
           // STATE = PLAY;
        }else if (pro.getSTATE() == 6){

            for (int i = 0; i < pro.getMaxQuestion(); i++) {
                QuestionPage quest = new QuestionPage(pro);

                System.out.println("du är i questionpage");


                while(!pro.isClicked()){
                    pro = quest.findClickPlay();
                    //workForMe = q.findClickPlay();
                    //pro.setSTATE(6);
                    sleepThisProgram();
                }
                //HÄR SKA VI LÄGGA TILL POÄNG
                pro = quest.addPoints(pro);
                System.out.println(pro.getQuestion()+ " FRÅGA PROTOKOLL");
                System.out.println(pro.getMaxQuestion());
                pro = quest.endGame(pro);
                System.out.println(pro.getQuestion());
                pro.setClicked(false);

            }
            if (pro.getMaxQuestion() == pro.getQuestion()){
                pro.setSTATE(3);
            }
            System.out.println("vi når till true");
            pro.setPlayer1playedRound(true);



            return pro;
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
        pro.setSTATE(3);
        //pro.setConnected(false);
        sleepThisProgram();
    }


    return pro;


}

}
