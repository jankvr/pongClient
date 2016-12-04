/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.interfaces;

/**
 *
 * @author User
 */
public interface IOpponent {
    
    public void setMapSize(int width, int height);
    
    public void render();
    
    public String currentLocation();
    
    public void setX(double x);
    
    public void setY(double y);
}
