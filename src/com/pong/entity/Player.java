/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.entity;


import com.pong.interfaces.IPlayer;
import javafx.scene.canvas.GraphicsContext;
import com.pong.Main;
import com.pong.Sprite;
import com.pong.input.Kb;

/**
 *
 * Trida dedici z tridy Entity, ktera slouzi k realizaci pohybu hrace.
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
     * Konstruktor
     * @param x pozice na ose X
     * @param y pozice na ose Y
     * @param ps odkaz na hlavni tridu klienta (Main)
     */
    public Player(double x, double y, Main ps) {
        super(POD, x, y, ps);
        this.cantMoveUp = false;
        this.cantMoveDown = false;
        this.speed = SPEED;
        this.gc = ps.getGc();
        this.kb = new Kb(ps);
    }
    
    /**
     * Metoda nastavujici pohyb padla nahoru.
     */
    private void moveUp() {
        if (this.position.y > 0) {
            this.position.y = this.position.y - (speed * 1);
            this.sprite.setXY(position.x, position.y);
        }
        
    }
    
    /**
     * Metoda nastavujici pohyb padla dolu.
     */
    private void moveDown() {
        if ((this.position.y + this.sprite.getImage().getHeight()) < super.height) {
            this.position.y = this.position.y + (speed * 1);
            this.sprite.setXY(position.x, position.y);
        }
    }

    /**
     * Kontrola toho, co je zmacknuto, pripadna realizace pohybu.
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
    
    /**
     * Renderovani spritu.
     */
    @Override
    public void render() {
        this.sprite.render(gc);
    }
    
    /**
     * Vytvoreni zpravy urcene k poslani na server
     * @return  zprava o pozici
     */
    @Override
    public String currentLocation() {
        return "OPPONENTPOSITION " + this.position.x + " " + this.position.y;
    }
    
    /**
     * Nastaveni pozice na ose X
     * @param x pozice na ose X
     */
    @Override
    public void setX(double x) {
        this.position.x = x;
        this.sprite.setXY(position.x, position.y);
    }
    
    /**
     * Nastaveni pozice na ose Y
     * @param y pozice na ose Y
     */
    @Override
    public void setY(double y) {
        this.position.y = y;
        this.sprite.setXY(position.x, position.y);
    }
}
