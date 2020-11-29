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

            //FIRST ROUND

            return gamePagePlayProtocol(pro, player2);


        } else if (pro.getSTATE() == 5){
            CategoryPage q = new CategoryPage(pro);
            while(!pro.isClicked()){
                pro = q.findClickPlay();
                pro.setSTATE(6);
                pro.setChooseCategories(true);
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

            //LÄGG TILL IF ELSE HÄR PÅ OM MAN HAR KÖRT ELLER INTE

            //OM DU INTE HAR FÅTT VÄLJA KATERGORI

            if (pro.getRound() > 0 && pro.getPLAYER() == 2 && player2.isPlayer1PlayedRound1() && !pro.isChooseCategories()){

                for (int i = 0; i <pro.getMaxQuestion() ; i++) {

                    QuestionPage_NotChoseCat quest_wait = new QuestionPage_NotChoseCat(pro,player2);

                    while(!pro.isClicked()){
                        pro = quest_wait.findClickPlay();
                        //workForMe = q.findClickPlay();
                        //pro.setSTATE(6);
                        sleepThisProgram();
                    }
                    //HÄR SKA VI LÄGGA TILL POÄNG
                    pro = quest_wait.addPoints(pro);
                    System.out.println(pro.getQuestion()+ " FRÅGA PROTOKOLL");
                    System.out.println(pro.getMaxQuestion());
                    pro = quest_wait.endGame(pro);
                    System.out.println(pro.getQuestion());
                    pro.setClicked(false);

                }

            }else {
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
            }


            //OM DU HAR FÅTT VÄLJA KATEGORI



                pro.setSTATE(3);

            System.out.println("vi når till true");
            pro.setPlayer1PlayedRound1(true);



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

public Player gamePagePlayProtocol(Player pro, Player player2) throws IOException, InterruptedException {

    if (pro.getRound() == 0 && pro.getPLAYER() == 1){
        //RUNDA 1 FÖR PLAYER 1

        pro = gamePage_PlayPlayer(pro,player2);
        pro.setRound(pro.getRound()+1);
        pro = checkState5or6(pro);

        //PLUSSAR PÅ 1 FÖR ATT HITTA KATEGORINAMN I GAMEPAGE SEN KODDA MODULES
    }else if (pro.getRound() == 0 && pro.getPLAYER() == 2 && player2.isPlayer1PlayedRound1()){
        pro.addToList(player2.getRoundCategories().get(0));
        pro.setRound(pro.getRound()+1);
        pro = gamePage_PlayPlayer(pro,player2);
        pro.setRound(pro.getRound()-1);
        pro = checkState5or6(pro);
        pro.setRound(pro.getRound()+1);

        pro.setChooseCategories(false);
        System.out.println("bram du är inte false");
        pro.setPlayer1PlayedRound1(false);
        //RETURN METOD MED FALSE PÅ PLAYED ROUND FÖR PLAYER 2

    }else {
        pro = gamePage_PlayPlayer(pro, player2);
       // pro.setRound(pro.getRound()+1);
        if (pro.getRound()%2 == 0 && pro.getPLAYER() == 1){
            pro.setSTATE(5);
        }else if (pro.getRound()%2 !=0 && pro.getPLAYER() == 2){
            pro.setSTATE(6);
        }
    }




    System.out.println("HÄR LOOPAR VI ROUND");
    System.out.println("PRO GET ROUND: " + pro.getRound());

    pro.setClicked(false);
    return pro;
/*
    else if (player2.isPlayer1playedRound() && pro.getPLAYER() == 1 ||player2.getPLAYER() == 2 && player2.isPlayers2PlayedRound() ||
            pro.getPLAYER() == 2 && pro.isPlayers2PlayedRound() || player2.getPLAYER() == 1 && player2.isPlayer1playedRound()){
        //OM DU INTE HAR VALT SÅ FÅR DU VÄLJA KAT
        System.out.println("State 5 är du i");
        pro.setSTATE(5);
    }else if (!pro.isPlayer1playedRound() && pro.getPLAYER() == 1 ||player2.getPLAYER() == 2 && !player2.isPlayers2PlayedRound() ||
            pro.getPLAYER() == 2 && !pro.isPlayers2PlayedRound() || player2.getPLAYER() == 1 && !player2.isPlayer1playedRound()){
        //OM DU HAR PRECIS SPELAT EN DU INTE HAR VALT
        pro.setSTATE(6);
        pro.setChooseCategories(false);
    }

 */



}

public Player gamePage_PlayPlayer(Player pro, Player player2) throws IOException, InterruptedException {
    GamePage_play gamePage_play = new GamePage_play(pro, player2);

    while(!pro.isClicked()){
        pro = gamePage_play.findClickPlay();
        sleepThisProgram();
    }
      return pro;
}

public Player checkState5or6(Player pro){
    if (pro.getRound()%2 != 0 && pro.getPLAYER() == 1){
        pro.setSTATE(5);
    }else if (pro.getRound()%2 ==0 && pro.getPLAYER() == 2){
        pro.setSTATE(6);
    }

        return pro;
}

}
