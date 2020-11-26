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




    public static void main(String[] args) throws IOException, InterruptedException {

        Player pro = new Player();


        InetAddress iadr = InetAddress.getLocalHost();

        Socket socket = new Socket(iadr, 7777);
        System.out.println("Connected!");

        Protocol protocol  = new Protocol();


        OutputStream outputStream = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        while (!pro.getFinished()){
            protocol.processInput(pro);
        }

        System.out.println("Sending message to server");

        objectOutputStream.writeObject(protocol.processInput(pro));

        System.out.println("walla bror");

        System.out.println("Closing socket and terminating program");
        socket.close();


            }

    }

