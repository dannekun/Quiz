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

        boolean neverEndingStory = true;

        boolean gameIsPlaying = true;

        Player player1 = new Player();
        Player player2 = new Player();


        HomePage_waiting homePage_waiting = new HomePage_waiting();

        InetAddress iadr = InetAddress.getLocalHost();


        boolean work = true;

        while (work) {

            LoginGUI gui = new LoginGUI();
            gui.showWindow();

            while (player1.getName() == null){

                String name = gui.findPlayerAndReturn();
                player1.setName(name);
                sleepThisProgram();

            }

            if (player1.getName() != null) {

                work = false;

            }

        }

        homePage_waiting.showWindow(player1);

        Socket socket = new Socket(iadr, 7777);

        try {

            while (!player1.getFinished() && !player2.getFinished()) {

                new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

                player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                if (player1.getPLAYER() == 0) {
                    if (player2.getPLAYER() == 2) {
                        player1.setPLAYER(1);
                    } else {
                        player1.setPLAYER(2);
                    }
                }

                if (player2.isConnected()) {

                    homePage_waiting.closeWindow();

                }


                HomePage_play hPlay = new HomePage_play();
                hPlay.showWindow(player1);


                while (!work) {
                    work = hPlay.findClickPlay();
                    sleepThisProgram();
                }


                while (neverEndingStory) {



                    while (gameIsPlaying) {

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


                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();



                        } else if (player1.getPLAYER() == 2) {

                            GamePage_waiting gamePage_waiting1 = new GamePage_waiting();
                            gamePage_waiting1.showWindow(player1, player2);

                            new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                            gamePage_waiting1.closeWindow();
                            player2.getQuestionToPassBetweenPlayers();
                            int läggtill = (player2.getRound() * player1.getMaxQuestion())-player1.getMaxQuestion();
                            for (int i = 0; i < player1.getMaxQuestion(); i++) {
                                player1.addQuestionBetweenPlayers(player2.getQuestionToPassBetweenPlayers().get(läggtill));
                                läggtill++;
                            }
                        }


                        if (player1.getPLAYER() == 1) {
                            GamePage_waiting gamePage_waiting2 = new GamePage_waiting();
                            gamePage_waiting2.showWindow(player1, player2);


                            new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                            gamePage_waiting2.closeWindow();
                        } else if (player1.getPLAYER() == 2) {

                            List<Boolean> temp = player2.getAnswers();
                            List<Boolean>change = player2.getAnswers();
                            player2.changeList(player2.removeAnswersFromList(change,(player2.getMaxQuestion()), player2.getRound()));

                            GamePage_play gamePage_play1 = new GamePage_play(player1, player2);
                            player1.setClicked(false);
                            while (!player1.isClicked()) {
                                player1 = gamePage_play1.findClickPlay();
                                sleepThisProgram();
                            }
                            player2.changeList(temp);
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

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                        }


                        if (player1.getRound() == checkPropertiesForMaxRound() && player2.getRound() == checkPropertiesForMaxRound()) {
                            gameIsPlaying = false;
                            break;
                        }

                        if (player1.getPLAYER() == 1) {
                            GamePage_waiting gamePage_waiting3 = new GamePage_waiting();
                            gamePage_waiting3.showWindow(player1, player2);

                            new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                            gamePage_waiting3.closeWindow();
                            player2.getQuestionToPassBetweenPlayers();
                            int läggtill2 = (player2.getRound() * player1.getMaxQuestion())-player1.getMaxQuestion();
                            for (int i = 0; i < player1.getMaxQuestion(); i++) {
                                player1.addQuestionBetweenPlayers(player2.getQuestionToPassBetweenPlayers().get(läggtill2));
                                läggtill2++;
                            }

                        } else if (player1.getPLAYER() == 2) {
                            GamePage_play gamePage_play2 = new GamePage_play(player1, player2);
                            player1.setClicked(false);
                            while (!player1.isClicked()) {
                                player1 = gamePage_play2.findClickPlay();
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

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                        }

                        if (player1.getPLAYER() == 1) {




                            List<Boolean> temp2 = player2.getAnswers();
                            List<Boolean>change2 = player2.getAnswers();
                            player2.changeList(player2.removeAnswersFromList(change2,(player2.getMaxQuestion()), player2.getRound()));

                            GamePage_play gamePage_play3 = new GamePage_play(player1, player2);


                            player1.setClicked(false);
                            while (!player1.isClicked()) {
                                player1 = gamePage_play3.findClickPlay();
                                sleepThisProgram();
                            }

                            player2.changeList(temp2);
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

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                        } else if (player1.getPLAYER() == 2) {
                            GamePage_waiting gamePage_waiting3 = new GamePage_waiting();
                            gamePage_waiting3.showWindow(player1, player2);


                            new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                            gamePage_waiting3.closeWindow();


                        }

                        if (player1.getRound() == checkPropertiesForMaxRound() && player2.getRound() == checkPropertiesForMaxRound()) {
                            gameIsPlaying = false;
                            break;
                        }

                    }

                    if (player1.getRound() == checkPropertiesForMaxRound() && player2.getRound() == checkPropertiesForMaxRound()) {
                        if (player1.getPLAYER() == 1) {

                            ResultPage r = new ResultPage(player1, player2);

                            work = false;
                            while (!work) {
                                work = r.findClickAndPlay();

                            }
                            r.dispose();

                        } else if (player1.getPLAYER() == 2) {

                            ResultPage rw = new ResultPage(player1, player2);

                            work = false;
                            while (!work) {
                                work = rw.findClickAndPlay();

                            }
                            rw.dispose();
                        }

                        if (player1.getPLAYER() == 1) {

                            GamePage_result gamePage_result = new GamePage_result(player1, player2);

                            player1.setClicked(false);
                            work = false;
                            player1.setCloseGameOption(0);

                            while (!work) {

                                player1 = gamePage_result.findClickPlay();
                                if (player1.getCloseGameOption() == 1 || player1.getCloseGameOption() == 2) {
                                    work = true;
                                }
                            }

                            new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                            gamePage_result.dispose();


                        } else if (player1.getPLAYER() == 2) {
                            GamePage_result gamePage_result2 = new GamePage_result(player1, player2);

                            player1.setClicked(false);

                            player1.setCloseGameOption(0);
                            work = false;

                            while (!work) {
                                player1 = gamePage_result2.findClickPlay();

                                if (player1.getCloseGameOption() == 1 || player1.getCloseGameOption() == 2) {
                                    work = true;
                                }
                            }

                            new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

                            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();

                            gamePage_result2.dispose();
                        }

                        if (player1.getCloseGameOption() == 1 && player2.getCloseGameOption() == 1) {
                            gameIsPlaying = true;
                        } else if (player1.getCloseGameOption() == 2 || player2.getCloseGameOption() == 2){
                            HejDå bye = new HejDå(player1, player2);
                            //System.out.println("hej då!!!");
                            //Thread.sleep(3000);
                            System.out.println("VI ÖPPNAR HEJ DÅ");
                        }
                        //TODO FIXA SÅ MAN KAN SPELA IGEN OCH RÄTT FRÅGOR, SISTA SPELAREN PÅ SISTA RONDEN FÅR FEL FRÅGOR MEN RÄTT KATEGORI, FIXA RÄTT FÄRGER I GAMEPAGE WAITING



/*                        boolean bajstest = true;
                        while (bajstest){

                        }

 */

                    }


                }
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

