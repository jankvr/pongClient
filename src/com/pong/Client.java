/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author User
 */
public class Client implements Runnable {
    
    private Socket socket;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;
    private final Pong gui;
    
    
    // Set up the screen
    public Client(String host, int port, Pong gui) {

        this.gui = gui;
        
        try {
            //initiate new connection
            this.socket = new Socket(host, port);
            
            
            //message
            System.out.println("Connection established");
            
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            
            //start a new thread
            new Thread(this).start();
        }
        catch (Exception e) {
            System.out.println("Quit the game");
        }
        
    }
    
    public void processMessage(String message) {
        try {
            outputStream.writeUTF(message);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {
            outputStream.writeUTF("PONG");
            while (true) {
                
                String message = inputStream.readUTF();

            }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    } 
    
}
