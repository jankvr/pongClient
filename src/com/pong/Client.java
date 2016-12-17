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
import org.apache.log4j.Logger;


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
    
    private String loggedIn="notYet";
    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    
    
    
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
            LOG.warn("Quit the game, " + e.getMessage());
        }
        
    }
    
    public void sendQuitMessage() {
        try {
            outputStream.writeUTF(Static.QUIT_CMD);

        } catch (IOException e) {
            LOG.fatal(e.getMessage());
        }
    }

    public void processMessage(String message) {
        try {
            outputStream.writeUTF(message);
            outputStream.flush();   
        } 
        catch (SocketException ex) {
            LOG.warn("Socket error: " + ex.getMessage());
            this.alive = false;
        } 
        catch (IOException ex) {
            LOG.fatal(ex.getMessage());
        }
        
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                String message = inputStream.readUTF();
                
                parser.parse(message);
                
            }
        } 
        catch (IOException e) {
            LOG.fatal(e.getMessage());
        }
        catch (Exception e) {
            LOG.fatal(e.getMessage());
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
    
    public void logIn(String username, String password){
        try {
            this.outputStream.writeUTF("LOGIN "+username+" "+password);
        } catch (IOException ex) {
            LOG.fatal(ex.getMessage());
        }
    }
    
    
    public boolean isLogged(){
        try {
            String info = this.inputStream.readUTF();
            System.out.println("dostal som terza info že "+info);
            String[] tokens = info.split(" ");
            if(tokens[0].equals("LOGIN")){
                    if(tokens[1].equals("OK")){
                        return true;
                    }
                    else{
                        return false;
                    }
            }
            else{
                    System.out.println("What the hell, man?");
                    return false;
            }
        } catch (IOException ex) {
            LOG.fatal(ex.getMessage());
            return false;
        }
        
    }



    public String getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }    

    
    //čítanie protokolu LOGIN
}
