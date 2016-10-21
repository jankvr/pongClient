/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;

import com.pong.Pong;
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
    private final Pong ps;
    
    /**
     * Constructor for entity. 
     * Width and height means size of the window.
     * Direction is set to 1, the default direction.
     * 
     * @param sprite
     * @param x sets entity position on x-axis
     * @param y sets entity position on y-axis
     * @param ps
     */
    public Entity(Sprite sprite, double x, double y, Pong ps) {
        this.windowWidth = Pong.MAP_WIDTH;
        this.windowHeight = Pong.MAP_WIDTH;
        this.ps = ps;
        this.sprite = sprite;
        this.sprite.setXY(x, y);
        int entityHeight = (int) this.sprite.getImage().getHeight();
        int entityWidth = (int) this.sprite.getImage().getWidth();
        
        this.position = new EntityPosition(x,y,entityWidth,entityHeight);
    }
    
    /**
     * Setting up a map size.
     * 
     * @param width width of map
     * @param height height of map
     */
    public void setMapSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public EntityPosition getPosition() {
        return this.position;
    }
   
    
    public Sprite getSprite() {
        return this.sprite;
    }
}
