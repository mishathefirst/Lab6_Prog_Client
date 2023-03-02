package com.lab6.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerInteraction {

    private Socket socket;
    private BufferedReader inputBuffer;
    private BufferedWriter outputBuffer;

    public void start() {
        try {
            try{
                socket = new Socket("localhost", 8080);
                inputBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outputBuffer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                outputBuffer.write();
                outputBuffer.flush();



            } finally {
                socket.close();
                inputBuffer.close();
                outputBuffer.close();
                System.out.println("Connection closed.");
            }
        } catch (IOException ex) {
            System.out.println("Error connecting to the server!");
        }


    }




}
