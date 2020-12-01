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

    static Player player1 = new Player();
    static Player player2 = new Player();
    static boolean work = true;
    static Socket socket;
    static HomePage_waiting homePageWaitForConnection = new HomePage_waiting();
    static HomePage_play homePageConnectedPlay = new HomePage_play();
    static GamePage_waiting gamePage_waitingPlayer2Round1 = new GamePage_waiting();
    static int maxAmountOfRounds = checkPropertiesForMaxRound();
    static boolean gameIsPlaying = true;
    static boolean neverEndingStory = true;


    public static int checkPropertiesForMaxRound() {
        Properties propertiesValue = new Properties();
        try {
            propertiesValue.load(new FileInputStream("src/RoundQuestions.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String stringMaxRounds = propertiesValue.getProperty("numberOfRounds", "2");

        return Integer.parseInt(stringMaxRounds);

    }

    public static void sleepThisProgram() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void logIn() throws InterruptedException {

        while (work) {

            LoginGUI logInScreen = new LoginGUI();
            logInScreen.showWindow();

            while (player1.getName() == null) {

                String name = logInScreen.findPlayerAndReturn();
                player1.setName(name);
                sleepThisProgram();

            }

            if (player1.getName() != null) {

                work = false;

            }

        }
    }

    public static void sendObject() throws IOException {
        new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);
    }

    public static void receiveObject() throws IOException, ClassNotFoundException {
        player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
    }

    public static void transferObject() throws IOException, ClassNotFoundException {
        sendObject();
        receiveObject();
    }

    public static void assignPlayer() {

        if (player1.getPLAYER() == 0) {

            if (player2.getPLAYER() == 2) {

                player1.setPLAYER(1);

            } else {

                player1.setPLAYER(2);

            }
        }
    }

    public static void closeWaitingWindowWhenConnected() {
        if (player2.isConnected()) {

            homePageWaitForConnection.closeWindow();
        }
    }

    public static void openPlayWindowWhenConnected() throws InterruptedException {

        homePageConnectedPlay.showWindow(player1);


        while (!work) {

            work = homePageConnectedPlay.findClickPlay();
            sleepThisProgram();

        }

    }

    public static void resetBothPlayersWhenNewGame() {

        player1 = player1.clearAllFromPlayer(player1);
        player2 = player2.clearAllFromPlayer(player2);

    }

    public static void player1PlayChooseCategoryRound() throws InterruptedException, IOException, ClassNotFoundException {

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

            transferObject();


        } else if (player1.getPLAYER() == 2) {

            gamePage_waitingPlayer2Round1.showWindow(player1, player2);

            transferObject();

            gamePage_waitingPlayer2Round1.closeWindow();

            findQuestionsFromLastPlayedPlayer();

        }
    }

    /**
     * Metoden används för att hitta tidigare spelarens frågor som har valt kategori samt överföra frågorna mellan spelarna.
     * För att båda spelarna ska ha samma frågor.
     */
    public static void findQuestionsFromLastPlayedPlayer(){

        int addQuestionFromLastRound = (player2.getRound() * player1.getMaxQuestion()) - player1.getMaxQuestion();

        for (int i = 0; i < player1.getMaxQuestion(); i++) {

            player1.addQuestionBetweenPlayers(player2.getQuestionToPassBetweenPlayers().get(addQuestionFromLastRound));
            addQuestionFromLastRound++;

        }
    }

    public static void player2PlayNotChooseCategoryRound() throws IOException, ClassNotFoundException, InterruptedException {
        if (player1.getPLAYER() == 1) {
            GamePage_waiting gamePage_waiting2 = new GamePage_waiting();
            gamePage_waiting2.showWindow(player1, player2);


            transferObject();

            gamePage_waiting2.closeWindow();
        } else if (player1.getPLAYER() == 2) {

            List<Boolean> temp = player2.getAnswers();

            List<Boolean> change = player2.getAnswers();
            player2.changeList(player2.removeAnswersFromList(change, (player2.getMaxQuestion()), player2.getRound()));

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

            transferObject();
        }
    }

    public static void player2PlayChooseCategoryRound() throws IOException, ClassNotFoundException, InterruptedException {
        if (player1.getPLAYER() == 1) {
            GamePage_waiting gamePage_waiting3 = new GamePage_waiting();
            gamePage_waiting3.showWindow(player1, player2);

            transferObject();

            gamePage_waiting3.closeWindow();
            player2.getQuestionToPassBetweenPlayers();
            int läggtill2 = (player2.getRound() * player1.getMaxQuestion()) - player1.getMaxQuestion();
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

            transferObject();

        }
    }

    public static void player1PlayNotChooseCategory() throws IOException, InterruptedException, ClassNotFoundException {
        if (player1.getPLAYER() == 1) {

            List<Boolean> temp2 = player2.getAnswers();
            List<Boolean> change2 = player2.getAnswers();
            player2.changeList(player2.removeAnswersFromList(change2, (player2.getMaxQuestion()), player2.getRound()));

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

            transferObject();

        } else if (player1.getPLAYER() == 2) {
            GamePage_waiting gamePage_waiting3 = new GamePage_waiting();
            gamePage_waiting3.showWindow(player1, player2);


            transferObject();

            gamePage_waiting3.closeWindow();


        }
    }

    public static void winLoseWindow() {
        if (player1.getRound() == maxAmountOfRounds && player2.getRound() == maxAmountOfRounds) {
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
        }
    }

    public static void resultWindowWithPlayAgainOption() throws IOException, ClassNotFoundException {
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

            transferObject();

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

            transferObject();

            gamePage_result2.dispose();
        }

    }

    public static void playAgainOrClose() throws IOException, ClassNotFoundException {
        if (player1.getCloseGameOption() == 1 && player2.getCloseGameOption() == 1) {
            gameIsPlaying = true;
        } else if (player1.getCloseGameOption() == 2 || player2.getCloseGameOption() == 2) {
            HejDå bye = new HejDå(player1, player2);

            //while loop här för click
            player1.setFinished(true);
            player1.setConnected(false);
            gameIsPlaying = false;
            neverEndingStory = false;
            transferObject();

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {


        InetAddress iadr = InetAddress.getLocalHost();

        logIn();

        homePageWaitForConnection.showWindow(player1);

        socket = new Socket(iadr, 7777);

        try {

            while (!player1.getFinished() && !player2.getFinished()) {

                transferObject();

                assignPlayer();

                closeWaitingWindowWhenConnected();

                openPlayWindowWhenConnected();


                while (neverEndingStory) {

                    resetBothPlayersWhenNewGame();

                    while (gameIsPlaying) {

                        player1PlayChooseCategoryRound();

                        player2PlayNotChooseCategoryRound();


                        //Check if max round is reached
                        if (player1.getRound() == maxAmountOfRounds && player2.getRound() == maxAmountOfRounds) {
                            gameIsPlaying = false;
                            break;
                        }

                        player2PlayChooseCategoryRound();

                        player1PlayNotChooseCategory();

                        //Check if max round is reached
                        if (player1.getRound() == maxAmountOfRounds && player2.getRound() == maxAmountOfRounds) {
                            gameIsPlaying = false;
                            break;
                        }

                        //Loop game until max round is reached
                    }
                    winLoseWindow();

                    resultWindowWithPlayAgainOption();

                    playAgainOrClose();


                }

            }

            socket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();


        }
    }


}



