package KnockKnock_QuizTest;

//This is the COPY from Övning11
import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {

    private ServerSocket serverSocket;

    ServerListener() throws IOException {
        serverSocket = ServerSocketFactory.getDefault()
                .createServerSocket(44445);
    }

    @Override
    public void run() {
        while (true) {
            try {
                final Socket socketToClient = serverSocket.accept();// accept
                //--------Initiate ClientHamdlar-------------
                ClientHandler clientHandler =
                        new ClientHandler(socketToClient);
                clientHandler.start();// anropa run method från ClientHandler class

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerListener sl = new ServerListener();
        sl.start();// anropa run method
    }
}