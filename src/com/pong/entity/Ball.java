/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;

import javafx.scene.canvas.GraphicsContext;
import com.pong.Main;
import com.pong.Sprite;

/**
 *
 * Tahle trida by mela fungovat na strane serveru, protoze ji budeme na chvili uspavat, aby se koule po bodu nehybala.
 * 
 * @author User
 */
public class Ball extends Entity {
    
    public double directionX, directionY;

    private double xPosition;
    private double yPosition;
    
    public Ball(Sprite sprite, int x, int y, Main game) {
        super(sprite, x, y, game);
        // random direction
        this.directionX = -1;
        this.directionY = -1 * (Math.random());
    }

    public double getPositionX() {
        return this.position.x;
    }

    public void setPositionX(double xPosition) {
        this.position.x = xPosition;
    }

    public double getPositionY() {
        return this.position.y;
    }

    public void setPositionY(double yPosition) {
        this.position.y = yPosition;
    }

    
    public void render(GraphicsContext gc) {
        this.sprite.setXY(position.x, position.y);
        this.sprite.render(gc);
    }
}