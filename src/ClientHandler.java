import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class ClientHandler extends Thread {

    int portNumber = 7777;
    private Socket spelare1;
    private Socket spelare2;

    OutputStream outputStream;
    ObjectOutputStream objectOutputStream;

    InputStream inputStream;
    ObjectInputStream objectInputStream;



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

        try {
         sendPlayerInfo(spelare1);
         sendPlayerInfo(spelare2);

        } catch (IOException e) {
            e.printStackTrace();
        }




        if (spelare1.isConnected()&&spelare2.isConnected()){
            System.out.println("HÄR!!!!");
        }


        System.out.println("Connection from " + spelare1 + "!");

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
