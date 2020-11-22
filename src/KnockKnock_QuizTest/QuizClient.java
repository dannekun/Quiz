package KnockKnock_QuizTest;

/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.*;
import java.io.*;
import java.net.*;

/**

 * Modda knockknock-koden så att den blir bättre rustad för parallellism
 * inom att servern skapar upp ett eget klienthanterar-objekt,
 * som kör i egen tråd, för varje anropande klient.
 *
 * Gör även klient-klassen trådad
 * (för att på ett enkelt sätt skapa upp flera klientprocesser
 * som parallellt kan anropa servern).
 *
 *  För att testa parallellismen, i ditt huvudprogram på klientsidan,
 *  starta upp x antal klienttrådar som alla anropar servern.
 *
 */

/**
 * This is the COPY from Övning11
 */

public class QuizClient extends Thread {

    /*
            if (args.length != 2) {
                System.err.println(
                        "Usage: java EchoClient <host name> <port number>");
                System.exit(1);
            }
    */
    //------För att flera cliant ska skriva samtidigt-------
    protected  String clientName;
    QuizClient(String s){
        clientName=s;
    }

    @Override
    public void run(){
        String hostName="127.0.0.5";
        int portNumber=44445;
        //String hostName = args[0];
        //int portNumber = Integer.parseInt(args[1]);

        try (
                Socket kkSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                String serverMess ="Client "+ clientName+ " says: Server: "+fromServer;
                if (fromServer.equals("Bye."))
                //----------för att avsluta program---------------------------------
                //if (fromServer.matches(".*slut.*"))//from theOutput(protocol) plocka ord
                    break;

                //fromUser = stdIn.readLine();
                fromUser= JOptionPane.showInputDialog(null,serverMess); // Skriva nånting till server genom dialog

                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
    // Flyttar det från top till botten och initera och starta två client trådar
    public static void main(String[] args) {
        QuizClient cl1 = new QuizClient("Client1");
        QuizClient cl2 = new QuizClient("Client2");

        cl1.start();
        cl2.start();
    }
}