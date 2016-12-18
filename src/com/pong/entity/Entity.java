/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;

import com.pong.Main;
import com.pong.Sprite;

/**
 *
 * @author jankovar
 */
public class Entity {

    
    public int windowWidth, windowHeight;
    public int width, height;
    public EntityPosition position;
    

    protected Sprite sprite;
    protected boolean cantMoveRight, cantMoveLeft, cantMoveUp, cantMoveDown;    
    protected final Main main;
    
    /**
     * Konstruktor
     * 
     * @param sprite
     * @param x pozice na X
     * @param y pozice na Y
     * @param main
     */
    public Entity(Sprite sprite, double x, double y, Main main) {
        this.windowWidth = Main.MAP_WIDTH;
        this.windowHeight = Main.MAP_WIDTH;
        this.main = main;
        this.sprite = sprite;
        this.sprite.setXY(x, y);
        int entityHeight = (int) this.sprite.getImage().getHeight();
        int entityWidth = (int) this.sprite.getImage().getWidth();
        
        this.position = new EntityPosition(x,y,entityWidth,entityHeight);
    }
    
    /**
     * Nastavuje vysku a sirku mapy
     * 
     * @param width sirka mapy
     * @param height vyska mapy
     */
    public void setMapSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Vraci pozici entity
     * @return pozice entity
     */
    public EntityPosition getPosition() {
        return this.position;
    }

    /**
     * Vraci sprite entity.
     * @return sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }
}
