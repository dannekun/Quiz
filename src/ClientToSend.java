import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientToSend implements Serializable {

    public static int checkPropertiesForMaxRound(){
        Properties p = new Properties();
        try{
            p.load(new FileInputStream("src/RoundQuestions.properties"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        String stringRounds = p.getProperty("numberOfRounds", "2");

       return Integer.parseInt(stringRounds);

    }

    public static void sleepThisProgram() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        boolean gameIsPlaying = true;

        Player player1 = new Player();
        Player player2 = new Player();

        int waitingForPlayer = 0;

        HomePage_waiting homePage_waiting = new HomePage_waiting();
        GamePage_waiting gamePage_waiting = new GamePage_waiting();

        InetAddress iadr = InetAddress.getLocalHost();

        Protocol protocol = new Protocol();


        //LOGGIN

        boolean work = true;

        while (work) {
            player1 = protocol.processInput(player1, player2);


            System.out.println(player1.getName());
            if (player1.getName() != null) {
                work = false;
            }
        }


        //QUENOTCONNECTEC
        homePage_waiting.showWindow(player1);


        Socket socket = new Socket(iadr, 7777);

        System.out.println("Connected!");

        try {

            while (!player1.getFinished() && !player2.getFinished()) {

                System.out.println("STORLEK PÅ ROUNDCATEGORI VID START: " + player1.getRoundCategories());


                new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                System.out.println("Send success!!!!!");


                player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                System.out.println("Receive success!!!!");


                if (player1.getPLAYER() == 0) {
                    if (player2.getPLAYER() == 2) {
                        player1.setPLAYER(1);
                    } else {
                        player1.setPLAYER(2);
                    }
                }

                //QUEUE_CONNECTED
                if (player2.isConnected()) {
                    homePage_waiting.closeWindow();
                    if (player1.getSTATE() == 0) {
                        player1.setSTATE(2);
                    }
                    System.out.println(player1.getPLAYER());
                    System.out.println(player2.getPLAYER());
                }


                HomePage_play hPlay = new HomePage_play();
                hPlay.showWindow(player1);


                while (!work) {
                    work = hPlay.findClickPlay();
                    sleepThisProgram();
                }

                while(gameIsPlaying) {

                    if (player1.getPLAYER() == 1) {
                        GamePage_play gamePage_play = new GamePage_play(player1, player2);

                        player1.setClicked(false);
                        while (!player1.isClicked()) {
                            player1 = gamePage_play.findClickPlay();
                            sleepThisProgram();
                        }


                        player1.setRound(player1.getRound() + 1);

                        CategoryPage q = new CategoryPage(player1);
                        work = false;
                        while (!work) {
                            work = q.findClickPlay();
                            sleepThisProgram();
                        }


                        player1 = q.addCatToPlayer();

                        player1.setClicked(false);
                        for (int i = 0; i < player1.getMaxQuestion(); i++) {
                            QuestionPage questionPage = new QuestionPage(player1);

                            while (!player1.isClicked()) {
                                player1 = questionPage.findClickPlay();
                                sleepThisProgram();
                            }
                            player1.setClicked(false);
                            player1 = questionPage.addPoints(player1);
                            player1 = questionPage.endGame(player1);

                        }

                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");


                    } else if (player1.getPLAYER() == 2) {
                        GamePage_waiting gamePage_waiting1 = new GamePage_waiting();
                        gamePage_waiting1.showWindow(player1, player2);

                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");
                        gamePage_waiting1.closeWindow();
                    }


                    if (player1.getPLAYER() == 1) {
                        GamePage_waiting gamePage_waiting2 = new GamePage_waiting();
                        gamePage_waiting2.showWindow(player1, player2);


                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");

                        gamePage_waiting2.closeWindow();
                    } else if (player1.getPLAYER() == 2) {
                        //FIXME TA BORT -3 PÅ FÄRGERNA SÅ MAN INTE SER RONDENS FÄRGER FÖR MOTSTÅNDAREN
                        //List<Boolean> temp = player2.getAnswers();
                        GamePage_play gamePage_play1 = new GamePage_play(player1, player2);
                        player1.setClicked(false);
                        while (!player1.isClicked()) {
                            player1 = gamePage_play1.findClickPlay();
                            sleepThisProgram();
                        }
                        // player2.changeList(temp);
                        player1.setRound(player1.getRound() + 1);
                        player1.addToList(player2.getRoundCategories().get(player1.getRound() - 1));

                        player1.setClicked(false);

                        for (int i = 0; i < player1.getMaxQuestion(); i++) {
                            QuestionPage_NotChoseCat questionPage_notChoseCat1 = new QuestionPage_NotChoseCat(player1, player2);

                            while (!player1.isClicked()) {
                                player1 = questionPage_notChoseCat1.findClickPlay();
                                sleepThisProgram();
                            }
                            player1.setClicked(false);
                            player1 = questionPage_notChoseCat1.addPoints(player1);
                            player1 = questionPage_notChoseCat1.endGame(player1);

                        }

                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");

                    }


                    if (player1.getRound() < checkPropertiesForMaxRound() && player2.getRound() == checkPropertiesForMaxRound()){
                        gameIsPlaying = false;
                        break;
                    }

                    if (player1.getPLAYER() == 1) {
                        GamePage_waiting gamePage_waiting3 = new GamePage_waiting();
                        gamePage_waiting3.showWindow(player1, player2);


                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");

                        gamePage_waiting3.closeWindow();

                    } else if (player1.getPLAYER() == 2) {
                        GamePage_play gamePage_play2 = new GamePage_play(player1, player2);
                        player1.setClicked(false);
                        while (!player1.isClicked()) {
                            player1 = gamePage_play2.findClickPlay();
                            sleepThisProgram();
                        }
                        // player2.changeList(temp);
                        player1.setRound(player1.getRound() + 1);


                        CategoryPage q = new CategoryPage(player1);
                        work = false;
                        while (!work) {
                            work = q.findClickPlay();
                            sleepThisProgram();
                        }

                        player1 = q.addCatToPlayer();
                        player1.setClicked(false);


                        for (int i = 0; i < player1.getMaxQuestion(); i++) {
                            QuestionPage questionPage = new QuestionPage(player1);

                            while (!player1.isClicked()) {
                                player1 = questionPage.findClickPlay();
                                sleepThisProgram();
                            }
                            player1.setClicked(false);
                            player1 = questionPage.addPoints(player1);
                            player1 = questionPage.endGame(player1);

                        }

                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");


                    }

                    if (player1.getPLAYER() == 1) {

                        GamePage_play gamePage_play3 = new GamePage_play(player1, player2);
                        player1.setClicked(false);
                        while (!player1.isClicked()) {
                            player1 = gamePage_play3.findClickPlay();
                            sleepThisProgram();
                        }
                        // player2.changeList(temp);
                        player1.setRound(player1.getRound() + 1);
                        player1.addToList(player2.getRoundCategories().get(player1.getRound() - 1));

                        player1.setClicked(false);

                        for (int i = 0; i < player1.getMaxQuestion(); i++) {
                            QuestionPage_NotChoseCat questionPage_notChoseCat1 = new QuestionPage_NotChoseCat(player1, player2);

                            while (!player1.isClicked()) {
                                player1 = questionPage_notChoseCat1.findClickPlay();
                                sleepThisProgram();
                            }
                            player1.setClicked(false);
                            player1 = questionPage_notChoseCat1.addPoints(player1);
                            player1 = questionPage_notChoseCat1.endGame(player1);

                        }

                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");


                    } else if (player1.getPLAYER() == 2) {
                        GamePage_waiting gamePage_waiting3 = new GamePage_waiting();
                        gamePage_waiting3.showWindow(player1, player2);


                        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                        System.out.println("Send success!!!!!");


                        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                        System.out.println("Receive success!!!!");

                        gamePage_waiting3.closeWindow();


                    }

                    if (player1.getRound() < checkPropertiesForMaxRound() && player2.getRound() == checkPropertiesForMaxRound()){
                        gameIsPlaying = false;
                        break;
                    }

                }

                if (player1.getRound() == checkPropertiesForMaxRound() && player2.getRound() == checkPropertiesForMaxRound()){
                    if (player1.getPLAYER() == 1){

                        ResultPage r = new ResultPage(player1,player2);


                    }else if (player1.getPLAYER() == 2){

                        ResultPage rw = new ResultPage(player1,player2);


                    }
                }


                work = true;
                while (work){
                    System.out.println("round cat size: " + player1.getRoundCategories().size());
                    System.out.println(player1.getRoundCategories());
                    Thread.sleep(10000);
                }




                /*
                player1.setEndState(true);

                while (player1.isEndState()) {

                    //SÅ LÄNGE QUESTION ÄR ÖVER 0. question > 0
                    if (player1.getSTATE() == 3 && player1.getPLAYER() == 1 && player1.getRound() == 1 && player2.getRound() == 2){
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);
                    }else if (player1.getSTATE() == 3 && player1.getPLAYER() == 2 && player1.getRound() == 0 && player2.getRound() == 1){
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);
                    }



                    if (player1.getSTATE() == 3 && player2.getRound() == 0 && player1.getPLAYER() == 1 && player1.getRound() == 1) {
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);
                        System.out.println("player 1 väntar");
                        System.out.println(player1.getRoundCategories().size());
                        if (waitingForPlayer == 1 && player1.getPLAYER() == 1){
                            player1.setPlayer1PlayedRound1(false);
                        }else if (waitingForPlayer == 1 && player1.getPLAYER() == 2){
                            player1.setPlayers2PlayedRound1(false);
                        }
                        waitingForPlayer++;
                    }

                    if (player1.getSTATE() == 3 && player2.getRound() == 2 && player1.getPLAYER() == 1){
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);
                    }else if (player1.getSTATE() == 3 && player2.getRound() == 3 && player1.getPLAYER() == 1){
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);
                    }

                    if (player1.getSTATE() == 4 && player2.getRound() == 3 && player1.getPLAYER() == 1){
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);
                    }else if (player1.getSTATE() == 4 && player2.getRound() == 4 && player1.getPLAYER() == 1){
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);
                    }



                    player1 = protocol.processInput(player1, player2);

                    System.out.println("du kommer ur alla frågor");


                    if (player1.getSTATE() == 3 && player2.getRound() == 0 && player1.getPLAYER() == 2) {
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);

                        System.out.println("player 2 väntar");
                    }
                }


                 */


            }


            System.out.println("Closing socket and terminating program");
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

