import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.io.ObjectInputStream;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientToSend implements Serializable {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Player player1 = new Player();
        Player player2 = new Player();

        Player player4 = new Player();
        Player player3 = new Player();

        player3.setName("Mahmud");
        player4.setName("Sigrun");


        HomePage_waiting homePage_waiting = new HomePage_waiting();
        GamePage_waiting gamePage_waiting = new GamePage_waiting();

        InetAddress iadr = InetAddress.getLocalHost();

        Protocol protocol = new Protocol();

        boolean work = true;

        while (work) {
            player1 = protocol.processInput(player1, player2);


            System.out.println(player1.getName());
            if (player1.getName() != null) {
                work = false;
            }
        }


        homePage_waiting.showWindow(player1);


        Socket socket = new Socket(iadr, 7777);

        System.out.println("Connected!");

        try {

            while (!player1.getFinished()) {
                System.out.println("Du är här");


                new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);


                System.out.println("Send success!!!!!");


                player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
                System.out.println("Receive success!!!!");


                System.out.println(player2.getName());


                if (player1.getPLAYER() == 0) {
                    if (player2.getPLAYER() == 2) {
                        player1.setPLAYER(1);
                    } else {
                        player1.setPLAYER(2);
                    }
                }

                System.out.println(player2.getName());
                System.out.println(player2.getPLAYER());

                if (player2.isConnected()) {
                    homePage_waiting.closeWindow();
                    if (player1.getSTATE() == 0) {
                        player1.setSTATE(2);
                    }
                    System.out.println(player1.getPLAYER());
                    System.out.println(player2.getPLAYER());
                }

                player1.setEndState(true);

                while (player1.isEndState()) {


                    if (player1.getSTATE() == 3 && player2.getQuestion() == 0 && player1.getPLAYER() == 1 && player1.getRound() >= 1) {
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);
                        System.out.println("player 1 väntar");
                        System.out.println(player1.getRoundCategories().size());

                    }

                    if (player2.isPlayer1playedRound()) {
                        gamePage_waiting.closeWindow();
                        player1.getQuestionToPassBetweenPlayers().clear();
                    } else if (player1.isPlayers2PlayedRound()) {
                        gamePage_waiting.closeWindow();
                        player1.getQuestionToPassBetweenPlayers().clear();
                    }


                    player1 = protocol.processInput(player1, player2);

                    System.out.println("du kommer ur alla frågor");


                    if (player1.getSTATE() == 3 && player2.getQuestion() == 0 && player1.getPLAYER() == 2) {
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);

                        System.out.println("player 2 väntar");
                    }
                }
                //LÄGG IN HÄR WRITE MED LIST
            }

            System.out.println("Closing socket and terminating program");

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

