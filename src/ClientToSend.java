import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientToSend implements Serializable {

    public ObjectInputStream recievePlayerInfo(Socket spelare) throws IOException {
        InputStream inputStream = spelare.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        return objectInputStream;
    }

    public ObjectOutputStream sendPlayerInfo(Socket spelare) throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(spelare.getOutputStream());
        return objectOutputStream;

    }



    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Player player1 = new Player();
        Player player2 = new Player();

        Player player4 = new Player();
        Player player3 = new Player();

        player3.setName("Mahmud");
        player4.setName("Sigrun");



        HomePage_waiting homePage_waiting = new HomePage_waiting();
        GamePage_waiting gamePage_waiting = new GamePage_waiting();
        ;

        InetAddress iadr = InetAddress.getLocalHost();

        Protocol protocol  = new Protocol();

boolean work  = true;
        while (work == true){
            player1 = protocol.processInput(player1, player2);


            System.out.println(player1.getName());

            //player1.setName("daniel");
            if (player1.getName() != null){
                //player1.setSTATE(1);
                //player1 = protocol.processInput(player1);
               // homePage_waiting.showWindow(player1);
                work = false;
            }
        }


            homePage_waiting.showWindow(player1);


        Socket socket = new Socket(iadr, 7777);

        System.out.println("Connected!");

        try
                //(OutputStream outputStream = socket.getOutputStream();

           //  ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

             //InputStream inputStream = socket.getInputStream();)

        {

        while (!player1.getFinished()) {
            System.out.println("Du är här");




/*                List<Player> playersToSend = new ArrayList<>();
                playersToSend.clear();
                playersToSend.add(player1);

 */

                //objectOutputStream.writeObject(playersToSend);
                new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);
               // new ObjectOutputStream(socket.getOutputStream()).writeObject(player1);

            System.out.println("Send success!!!!!");






            //    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

               // player2 = (Player) objectInputStream.readObject();
              //  List<Player> player2Find = (List<Player>) objectInputStream.readObject();
           // List<Player> player2Find = (List<Player>) new ObjectInputStream(socket.getInputStream()).readObject();
            player2 = (Player) new ObjectInputStream(socket.getInputStream()).readObject();
            System.out.println("Receive success!!!!");
           //     player2 = player2Find.get(0);

            System.out.println(player2.getName());



           // objectOutputStream.flush();
           // objectOutputStream.reset();

                if (player1.getPLAYER() == 0) {
                    if (player2.getPLAYER() == 2) {
                        player1.setPLAYER(1);
                    } else {
                        player1.setPLAYER(2);
                    }
                }







/*
               // objectOutputStream.flush();
                    objectOutputStream.reset();
                objectOutputStream.close();
                objectInputStream.close();

                objectOutputStream.writeObject(player3);


                List<Player> playersToSend = new ArrayList<>();
                playersToSend.add(player3);
                objectOutputStream.writeObject(playersToSend);
 */

                System.out.println(player2.getName());
                System.out.println(player2.getPLAYER());

                if (player2.isConnected()) {
                    homePage_waiting.closeWindow();
                    if (player1.getSTATE() == 0){
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
                    /*else if (player2.isPlayedRound()) {
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);
                    }

                     */

                    //VARJE OUTPUTSTREAM ENDAST ANVÄNDAS EN GÅNG

                    if (player2.isPlayedRound()){
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);
                    }

                    player1 = protocol.processInput(player1, player2);

                    System.out.println("du kommer ur alla frågor");


                    if (player1.getSTATE() == 3 && player2.getQuestion() == 0 && player1.getPLAYER() == 2) {
                        gamePage_waiting.showWindow(player1, player2);
                        player1.setEndState(false);

                        System.out.println("player 2 väntar");
                    }
                    /*else if (player2.isPlayedRound()) {
                        gamePage_waiting.closeWindow();
                        player1.setSTATE(4);

                        //UPPDATERA GUI MED POÄNG FRÅN FÖRRA SPELAREN
                        //NY SPELA KNAPP ÖPPNAS???
                    }

                     */
/*
                        List<Player> testSend = new ArrayList<>();
                        testSend.add(player1);
                        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream1.writeObject(testSend);


                        objectOutputStream.flush();

                    //    testSend.clear();
                        List<Player> TESTIFWORKS = new ArrayList<>();
                       // InputStream newTestInput = socket.getInputStream();
                       // ObjectInputStream newTest2Input = new ObjectInputStream(newTestInput);
                       // TESTIFWORKS = (List<Player>)newTest2Input.readObject();
                       TESTIFWORKS = (List<Player>) new ObjectInputStream(socket.getInputStream()).readObject();

                        System.out.println(TESTIFWORKS.get(0).getName());


 */

                        /*
                        List<Player> player3test = new ArrayList<>();
                        player3test.add(player3);
                        objectOutputStream.writeObject(player3test);

                        while (true){
                            Thread.sleep(5000);
                        }

                         */




                }

                //LÄGG IN HÄR WRITE MED LIST
            }

            System.out.println("Sending message to server");


            //SKICKA TILLBAKA PLAYER OCH SE OM MAN ÄR PLAYER 1 ELLER 2


            System.out.println("Closing socket and terminating program");

            socket.close();



        }catch(IOException e){
            e.printStackTrace();
        }




    }

    }

