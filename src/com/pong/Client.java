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
 * Tried Client spravuje sieťový aspekt fungovania klientskej aplikácie.
 * @author Jan Kovář, Jaroslav Fedorčák
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
    /**
     * Počiatočné prípravy na fungovanie hry.
     * @param host adresa
     * @param port port na pripojenie
     * @param game odkaz na bežiacu hru, týkajúcu sa tohot klienta
     */
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
    
    /**
     * Informovanie oponenta o ukončení behu tohto klienta
     */
    public void sendQuitMessage() {
        try {
            outputStream.writeUTF(Static.QUIT_CMD);

        } catch (IOException e) {
            LOG.fatal(e.getMessage());
        }
    }

    /**
     * Poslanie správy oponentovi/hre
     * @param message správa opoentovi/hre
     */
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
    
    /**
     * Cyklus prijimani zprav.
     */
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
    
    /**
     * Informuje o tom, či je klientská strana "živá"
     * @return true pokiaľ je hra "živá"
     */
    public boolean isAlive() {
        return alive;
    }
    
    /**
     * informuje o tom, či hra začala
     * @return true, pokiaľ hra začala
     */
    public boolean isStarted() {
        return started;
    }
    
    /**
     * Klient je informovaný o tom, že hra začala
     * @param started informácia o tom, že hra začala
     */
    public void setStarted(boolean started) {
        this.started = started;
    }
    
    /**
     * Prihlasovanie užívateľa
     * @param username používateľské meno zadané používateľom
     * @param password heslo zadané používateľom
     */
    public void logIn(String username, String password){
        try {
            this.outputStream.writeUTF("LOGIN "+username+" "+password);
        } catch (IOException ex) {
            LOG.fatal(ex.getMessage());
        }
    }
    
    
    /**
     * Informuje o tom, či bolo prihlásenie úspešné
     * @return true, pokiaľ sa klient prihlásil
     */
    public boolean isLogged(){
        try {
            String info = this.inputStream.readUTF();
            //System.out.println("dostal som teraz info že "+info);
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
                    System.out.println("Wrong login syntax");
                    return false;
            }
        } catch (IOException ex) {
            LOG.fatal(ex.getMessage());
            return false;
        }
        
    }


    /**
     * informuje o tom, či je klient v súčasnosti prihlásený
     * @return true, pokiaľ je klient s súčasnosti prihlásený
     */
    public String getLoggedIn() {
        return loggedIn;
    }

    /**
     * nastavuje hodnotu atribútu loggedIn, ktorý informuje o tom, či je klient v súčasnosti prihlásený
     * @param loggedIn 
     */
    public void setLoggedIn(String loggedIn) {
        this.loggedIn = loggedIn;
    }    

    
}
