/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;

import com.pong.Pong;
import com.pong.Sprite;
import com.pong.interfaces.IPlayer;

/**
 *
 * @author User
 */
public class Opponent extends Entity implements IPlayer {
    private double yPosition;
    public Opponent(Sprite sprite, double x, double y, Pong ps) {
        super(sprite, x, y, ps);
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public void movement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String currentLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
