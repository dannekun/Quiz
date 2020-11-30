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

    private Socket spelare1;
    private Socket spelare2;

    Player player1 = new Player();
    Player player2 = new Player();


    public ClientHandler(Socket socketToClient, Socket socketToClient2) {
        this.spelare1 = socketToClient;
        this.spelare2 = socketToClient2;
    }


    @Override
    public void run() {

        if (spelare1.isConnected() && spelare2.isConnected()) {

            while (!player2.getFinished()) {
                try {

                    player1 = (Player) new ObjectInputStream(spelare1.getInputStream()).readObject();

                    player2 = (Player) new ObjectInputStream(spelare2.getInputStream()).readObject();


                    player1.setPLAYER(1);
                    player2.setPLAYER(2);


                    player1.setConnected(true);
                    player2.setConnected(true);


                    new ObjectOutputStream(spelare1.getOutputStream()).writeObject(player2);

                    new ObjectOutputStream(spelare2.getOutputStream()).writeObject(player1);


                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }


        }


    }


}
