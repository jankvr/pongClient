/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Class for making the image as a sprite
 * 
 * @author jankovar
 */
public final class Sprite {
    private Image img;
    public double  x, y, lastX, lastY;
    public String path;
    public int width;
    public int height;
    public String displayedText = "";

    /**
     * Constructor. 
     * Default setup is that sprite is not moving and is passable.
     * 
     * @param path image path 
     */
    public Sprite(String path) {
        this.setImage(path);
        this.width = (int) img.getWidth();
        this.height = (int) img.getHeight();
        x = 0;
        y = 0;
    }

    /**
     * Saving an image into img variable and path into path variable
     * 
     * @param path image path
     */
    public void setImage(String path) {
        try {
            img = new Image(Sprite.class.getResourceAsStream(path));
            this.path = path;
            
        }
        catch(Exception e) {
            System.out.println(e + ", path: " + this.path);
        }
    }
    
    /**
     * Setting x and y positions of a sprite.
     * Second thought is setting last known position of a sprite (for collision with moving objects)
     * 
     * @param x new position on x-axis
     * @param y new position on y-axis
     * @return object pointer
     */
    public Sprite setXY(double x, double y) {
        this.lastX = this.x;
        this.lastY = this.y;
        this.x = x;
        this.y = y;
        return this;
    }
    
    /**
     * Return an image.
     * 
     * @return image
     */
    public Image getImage() {
        return this.img;
    }
    
    /**
     * This method is creating a rectangle for every sprite (for collision detection).
     * 
     * @return rectangle
     */
    public Rectangle2D getBoundary() { // creating rectangle (for collision detection)
        return new Rectangle2D(x, y, this.img.getWidth(), this.img.getHeight());
    }

    public String getDisplayedText() {
        return displayedText;
    }

    public void setDisplayedText(String displayedText) {
        this.displayedText = displayedText;
    }
    
    
    
    
    /**
     * Kontrola, jestli neni kolize se spritem v parametru.
     * 
     * @param s kontrolovany sprite
     * @return true, jestlize doslo ke kolizi
     */
    public boolean intersects(Sprite s) { // checking if collision
        return this.getBoundary().intersects(s.getBoundary());
    }
    
    /**
     * Renderovani spritu a vytvareni hranicniho obrazku
     * 
     * @param gc graphics context
     */
    public void render(GraphicsContext gc) {
        this.getBoundary();
        
        gc.drawImage(this.img, x, y);
        if(!displayedText.isEmpty()){
            gc.strokeText(displayedText, 400, y+20);
        }
    }
    
    /**
     * Override toString() method.
     * @return 
     */
    @Override
    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
    
    
}
