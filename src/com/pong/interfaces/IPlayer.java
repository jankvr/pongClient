/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.interfaces;

/**
 * Rozhrani pro hrace
 * @author User
 */
public interface IPlayer {

    /**
     * Metoda zajistujici pohyb padla
     */
    public void movement();
    
    /**
     * Nastavuje vysku a sirku mapy
     * 
     * @param width sirka mapy
     * @param height vyska mapy
     */
    public void setMapSize(int width, int height);
    
    /**
     * Renderovani spritu.
     */
    public void render();
    
    /**
     * Vytvoreni zpravy urcene k poslani na server
     * @return  zprava o pozici
     */
    public String currentLocation();
    
    /**
     * Nastaveni pozice na ose X
     * @param x pozice na ose X
     */
    public void setX(double x);
    
    /**
     * Nastaveni pozice na ose Y
     * @param y pozice na ose Y
     */
    public void setY(double y);

}
