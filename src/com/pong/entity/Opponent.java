/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;

import com.pong.Main;
import com.pong.Sprite;
import com.pong.interfaces.IOpponent;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author User
 */
public class Opponent extends Entity implements IOpponent {

    private static final Sprite POD = new Sprite("images/pod.png");
    private final GraphicsContext gc;
    
    /**
     * Constructor.
     * @param x     current position on x-axis
     * @param y     current position on y-axis
     * @param game
     */
    public Opponent(double x, double y, Main game) {
        super(POD, x, y, game);
        this.cantMoveUp = false;
        this.cantMoveDown = false;
        this.gc = game.getGc();
        }
    
    @Override
    public void render() {
        this.sprite.render(gc);
    }
    
    @Override
    public String currentLocation() {
        return "PADDLEPOSITION " + this.position.y;
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
