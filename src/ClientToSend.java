import QuestionsHandler.Categories.AnimalsNature;
import QuestionsHandler.Database;
import QuestionsHandler.Questions;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientToSend implements Serializable {

private static final int LOGGIN = 0;
private static final int QUEUE = 1;
private static final int CHOSECAT = 2;
private static final int PLAY = 3;
private static final int RESULT = 4;


    public static void main(String[] args) throws IOException, InterruptedException {

        int STATE = LOGGIN;

        Player pro = new Player();
        pro.setPoints(10);
        pro.setRound(3);
        pro.setName("Daniel");




        InetAddress iadr = InetAddress.getLocalHost();

        Socket socket = new Socket(iadr, 7777);
        System.out.println("Connected!");
        Player shuno = new Player();
        LoginGUI jaKnullarDig = new LoginGUI();
        if (socket.isConnected()){
            while (jaKnullarDig.isActive()){

            }
        }
        shuno = jaKnullarDig.returnThisMotherFucker();

        OutputStream outputStream = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        System.out.println("Sending message to server");

        objectOutputStream.writeObject(shuno);

        System.out.println("walla bror");

        System.out.println("Closing socket and terminating program");
        socket.close();


    }


}
