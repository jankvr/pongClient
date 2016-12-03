/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;


import com.pong.interfaces.IPlayer;
import javafx.scene.canvas.GraphicsContext;
import com.pong.Pong;
import com.pong.Sprite;
import com.pong.input.Kb;

/**
 *
 * Class extending entity provides all operations for a player (movement, etc.)
 * 
 * @author jankovar
 */
public class Player extends Entity implements IPlayer {
    private static final Sprite POD = new Sprite("images/pod.png");
    
    private final int speed;
    private final GraphicsContext gc;
    private final Kb kb;

    private static final int SPEED = 5;
    
    /**
     * Constructor.
     * @param x     current position on x-axis
     * @param y     current position on y-axis
     * @param ps
     */
    public Player(double x, double y, Pong ps) {
        super(POD, x, y, ps);
        this.cantMoveUp = false;
        this.cantMoveDown = false;
        this.speed = SPEED;
        this.gc = ps.getGc();
        this.kb = new Kb(ps);
    }
    
    /**
     * Method considering movement. 
     * And setting a direction
     */
    private void moveUp() {
        if (this.position.y > 0) {
            this.position.y = this.position.y - (speed * 1);
            this.sprite.setXY(position.x, position.y);
        }
        
    }
    
    /**
     * Method considering movement. 
     * And setting a direction
     */
    private void moveDown() {
        if ((this.position.y + this.sprite.getImage().getHeight()) < super.height) {
            this.position.y = this.position.y + (speed * 1);
            this.sprite.setXY(position.x, position.y);
        }
    }

    /**
     * Movement method considering all possible obstacles.
     * Second thing is animating the player.
     * 
     */
    @Override
    public void movement() {
        kb.update();

        if (kb.isUp() && !this.cantMoveUp) {
            this.moveUp();
        }

        if (kb.isDown() && !this.cantMoveDown) {
            this.moveDown();
        }
    }
    
    @Override
    public void render() {
        this.sprite.render(gc);
    }
    
    @Override
    public String currentLocation() {
        return "OPPONENTPOSITION " + this.position.y;
    }
    
    @Override
    public void setX(double x) {
        this.position.x = x;
        this.sprite.setXY(position.x, position.y);
    }
    
    @Override
    public void setY(double y) {
        this.position.y = y;
        this.sprite.setXY(position.x, position.y);
    }
}
