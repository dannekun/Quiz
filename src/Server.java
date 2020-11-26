import QuestionsHandler.Categories.AnimalsNature;
import QuestionsHandler.Questions;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Daniel Bojic
 * Date: 2020-11-25
 * Time: 15:40
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Server {


    public static void main(String[] args) throws IOException, ClassNotFoundException {


        ServerSocket ss = new ServerSocket(7777);

        System.out.println("Väntar på connection...");

        Socket socket = ss.accept();
      /*
        Socket socket2 = ss.accept();

        if (socket.isConnected()&&socket2.isConnected()){
            System.out.println("HÄR!!!!");
        }

       */

        System.out.println("Connection from " + socket + "!");

        //Dessa är för player 1
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Player pro1 = (Player) objectInputStream.readObject();

        //Dessa är för player 2????
        /*
        InputStream inputStream2 = socket.getInputStream();
        ObjectInputStream objectInputStream2 = new ObjectInputStream(inputStream);
        Player pro2 = (Player) objectInputStream.readObject();


         */


       System.out.println("Recieved " + pro1.getName()+ " from " + socket);

       System.out.println(pro1.getName());


        /*
        System.out.println(pro2.getPoints());
        for (Questions p : pro2.getQuestionToPassBetweenPlayers()){
            System.out.println(p.getQuestion());
            System.out.println(p.getAnswerObject().getRightAnswer());
        }

         */

        System.out.println("Closing sockets");
        ss.close();
        socket.close();

    }
}
