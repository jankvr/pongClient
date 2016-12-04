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
import java.net.SocketException;
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
    private final Main game;
    private CmdParser parser;
    
    private boolean started = false;
    private boolean alive = false;
    
    
    // Set up the screen
    public Client(String host, int port, Main game) {

        this.game = game;
        this.parser = new CmdParser(game);
        try {
            //initiate new connection
            this.socket = new Socket(host, port);
            this.alive = true;
            
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
            outputStream.writeUTF(Static.QUIT_CMD);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void processMessage(String message) {
        try {
            outputStream.writeUTF(message);
            outputStream.flush();   
        } 
        catch (SocketException ex) {
            // zaloguj chybu, posli quit msg a ukonci hru... nejak :)
            this.sendQuitMessage();
        } 
        catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                String message = inputStream.readUTF();
                
                parser.parse(message);
                
                System.out.println("Prijato: " + message);
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
    
    public void setStarted(boolean started) {
        this.started = started;
    }
}
