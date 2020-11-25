import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
public class Client {

    public static void main(String[] args) throws IOException {
        Player pro = new Player();

        InetAddress iadr = InetAddress.getLocalHost();

        Socket socket = new Socket(iadr, 7777);
        System.out.println("Connected!");

        LoginGUI log = new LoginGUI();

        OutputStream outputStream = socket.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        System.out.println("Sending message to server");

        //objectOutputStream.writeObject();

        System.out.println("walla bror");

        System.out.println("Closing socket and terminating program");
        socket.close();


    }


}
