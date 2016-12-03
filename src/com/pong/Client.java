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
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private final Pong gui;
    
    private boolean alive = false;
    
    
    // Set up the screen
    public Client(String host, int port, Pong gui) {

        this.gui = gui;
        
        try {
            //initiate new connection
            this.socket = new Socket(host, port);
            
            
            //message
            System.out.println("Connection established");
            
            alive = true;
            
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
            //outputStream.writeUTF("PONG");
            while (true) {
                //System.out.println("reading");
                String message = inputStream.readUTF();
                if (message != null) {
                    System.out.println("Message from server: " + message);
                }
                
                
            }
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    } 
    
    public boolean isAlive() {
        return alive;
    }
}
