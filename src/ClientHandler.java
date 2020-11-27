import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientHandler extends Thread implements Serializable {

    int portNumber = 7777;
    private Socket spelare1;
    private Socket spelare2;

    OutputStream outputStream;
    ObjectOutputStream objectOutputStream;

    InputStream inputStream;
    ObjectInputStream objectInputStream;

    Player player1 = new Player();
    Player player2 = new Player();


    public ClientHandler(Socket socketToClient, Socket socketToClient2) {
        this.spelare1 = socketToClient;
        this.spelare2 = socketToClient2;
    }

    public ObjectOutputStream sendPlayerInfo(Socket spelare) throws IOException {
        outputStream = spelare.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);

       return objectOutputStream;
    }

    public ObjectInputStream receivePlayerInfo(Socket spelare) throws IOException {
        inputStream = spelare.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        return objectInputStream;
    }

    @Override
    public void run() {







        if (spelare1.isConnected()&&spelare2.isConnected()){

            try {
                player1 = (Player) receivePlayerInfo(spelare1).readObject();
                player1.setPLAYER(1);

                player2 = (Player) receivePlayerInfo(spelare2).readObject();
                player2.setPLAYER(2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("HÄR!!!!");
            player1.setConnected(true);
            player2.setConnected(true);

            try {
                sendPlayerInfo(spelare1).writeObject(player2);
                sendPlayerInfo(spelare2).writeObject(player1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Connection from " + spelare1 + "!");
        System.out.println("Connection from " + spelare2 + "!");


        try {
            player1 = (Player) receivePlayerInfo(spelare1).readObject();


            player2 = (Player) receivePlayerInfo(spelare2).readObject();

            sendPlayerInfo(spelare2).writeObject(player2);
            sendPlayerInfo(spelare1).writeObject(player1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        System.out.println("Första spelaren heter: " + player1.getName());
        System.out.println("Andra spelaren heter: " + player2.getName());


        //Dessa är för player 1

        //   Player pro1 = (Player) objectInputStream.readObject();

        //Dessa är för player 2????
        /*
        InputStream inputStream2 = socket.getInputStream();
        ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
        Player pro2 = (Player) objectInputStream.readObject();


         */


       // System.out.println("Recieved " + pro1.getName()+ " from " + socket);

       // System.out.println(pro1.getName());


        /*
        System.out.println(pro2.getPoints());
        for (Questions p : pro2.getQuestionToPassBetweenPlayers()){
            System.out.println(p.getQuestion());
            System.out.println(p.getAnswerObject().getRightAnswer());
        }

         */

        System.out.println("Closing sockets");
       // ss.close();
       // socket.close();

    }

}
