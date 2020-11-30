import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientHandler extends Thread implements Serializable {

    private Socket spelare1;
    private Socket spelare2;

    Player player1 = new Player();
    Player player2 = new Player();


    public ClientHandler(Socket socketToClient, Socket socketToClient2) {
        this.spelare1 = socketToClient;
        this.spelare2 = socketToClient2;
    }

/*
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

 */



    @Override
    public void run() {

        if (spelare1.isConnected()&&spelare2.isConnected()) {

            while (!player2.getFinished()){
                try {

                    player1 = (Player) new ObjectInputStream(spelare1.getInputStream()).readObject();

                    player2 = (Player) new ObjectInputStream(spelare2.getInputStream()).readObject();


                    player1.setPLAYER(1);
                    player2.setPLAYER(2);


                    System.out.println("HÄR!!!!");
                    player1.setConnected(true);
                    player2.setConnected(true);



                    new ObjectOutputStream(spelare1.getOutputStream()).writeObject(player2);

                    new ObjectOutputStream(spelare2.getOutputStream()).writeObject(player1);


                    System.out.println("Uppdaterat: " + player1.getName());
                    System.out.println("Uppdaterat: " + player2.getName());

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }



        }


    }


}
