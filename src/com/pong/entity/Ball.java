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
 * Táto trieda spravuje obraz lopty na klientskej strane.
 * 
 * @author jankovar
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

    /**
     * vracia pozíciu lopty na x-ovej osi
     * @return pozícia lotpy na x-ovej osi
     */
    public double getPositionX() {
        return this.position.x;
    }

    /**
     * nastavuje pozíciu lopty na x-ovej osi
     * @param xPosition pozícia lopty na x-ovej osi
     */
    public void setPositionX(double xPosition) {
        this.position.x = xPosition;
    }

    /**
     * vracia pozíciu lopty na y-ovej osi
     * @return pozícia lotpy na y-ovej osi
     */    
    public double getPositionY() {
        return this.position.y;
    }

    /**
     * nastavuje pozíciu lopty na y-ovej osi
     * @param yPosition pozícia lopty na y-ovej osi
     */    
    public void setPositionY(double yPosition) {
        this.position.y = yPosition;
    }

    /**
     * spravuje vykreslenie lopty na hernom pláne
     * @param gc grafický kontext na vykreslenie
     */
    public void render(GraphicsContext gc) {
        this.sprite.setXY(position.x, position.y);
        this.sprite.render(gc);
    }
}