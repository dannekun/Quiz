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


        InetAddress iadr = InetAddress.getLocalHost();

        Socket socket = new Socket(iadr, 7777);

        System.out.println("Connected!");

        Protocol protocol  = new Protocol();

        try(  OutputStream outputStream = socket.getOutputStream();

              ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

              InputStream inputStream = socket.getInputStream();



              ){

            while (!player1.getFinished()){
                player1 = protocol.processInput(player1);
            }






            System.out.println("Sending message to server");

            objectOutputStream.writeObject(player1);


        //HÄR ÄR PROBLEMET
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            player2 = (Player) objectInputStream.readObject();



            System.out.println("vi hitta shuno, han heter: " + player2.getName());


            System.out.println(player1.getName());

            System.out.println("Closing socket and terminating program");

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }





            }

    }

