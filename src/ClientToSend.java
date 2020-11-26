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


        HomePage_waiting homePage_waiting = new HomePage_waiting();

        InetAddress iadr = InetAddress.getLocalHost();

        Protocol protocol  = new Protocol();


        while (player1.isEndState()){
            player1 = protocol.processInput(player1);


            System.out.println(player1.getName());

            //player1.setName("daniel");
            if (player1.getName() != null){
                player1.setSTATE(1);
                //player1 = protocol.processInput(player1);
               // homePage_waiting.showWindow(player1);
                player1.setEndState(false);
            }
        }


            homePage_waiting.showWindow(player1);


        Socket socket = new Socket(iadr, 7777);

        System.out.println("Connected!");



        try(OutputStream outputStream = socket.getOutputStream();

              ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

              InputStream inputStream = socket.getInputStream();){

            while (!player1.getFinished()){
                System.out.println("Du är här");
                objectOutputStream.writeObject(player1);

                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);


                player2 = (Player) objectInputStream.readObject();

                objectOutputStream.flush();

                System.out.println(player2.getName());

                if (player2.isConnected()){
                    homePage_waiting.closeWindow();
                    player1.setSTATE(2);
                }

            player1.setEndState(true);

                while (player1.isEndState()){
                    player1 = protocol.processInput(player1);

                    
                }





            }

            System.out.println("Sending message to server");








            //SKICKA TILLBAKA PLAYER OCH SE OM MAN ÄR PLAYER 1 ELLER 2



            System.out.println("Closing socket and terminating program");

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }





            }

    }

