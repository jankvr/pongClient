/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pongsample.entity;

/**
 *
 * @author Honza
 */
public class EntityPosition {
    
    public double x;
    public double y;
    public final int width;
    public final int height;
    
    public EntityPosition(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
}
