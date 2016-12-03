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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Client implements Runnable {
    
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private final Pong gui;
    private boolean started = false;
    
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
    
    public void sendQuitMessage() {
        try {
            System.out.println("sending quit message " + outputStream);
            outputStream.writeUTF(Static.QUIT_CMD);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void processMessage(String message) {
        try {
            outputStream.writeUTF(message);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                String message = inputStream.readUTF();
                
                if (message.equals(Static.START_CMD)) {
                    this.started = true;
                }
                else if (message.equals("LEFT") || message.equals("RIGHT")) {
                    
                }
                
                System.out.println(message);
                
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
    
    public boolean isStarted() {
        return started;
    }
}
