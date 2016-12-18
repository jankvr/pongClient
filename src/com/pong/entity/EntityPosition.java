/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;

/**
 * Kontejner pro pozici entity
 * @author jankovar
 */
public class EntityPosition {
    
    public double x;
    public double y;
    public final int width;
    public final int height;
    
    /**
     * Konstruktor kontejneru.
     * @param x pozice X
     * @param y pozice Y
     * @param width sirka pole
     * @param height vyska pole
     */
    public EntityPosition(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
}
