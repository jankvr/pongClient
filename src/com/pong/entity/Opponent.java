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
 * Trida slouzici k drzeni informaci o tom, kde je protihrac, vykreslovani jeho padla.
 * @author jankovar
 */
public class Opponent extends Entity implements IOpponent {

    private static final Sprite POD = new Sprite("images/pod.png");
    private final GraphicsContext gc;
    
    /**
     * Konstruktor.
     * @param x     pozice na X
     * @param y     pozice na Y
     * @param game  odkaz na hlavni tridu klienta (main)
     */
    public Opponent(double x, double y, Main game) {
        super(POD, x, y, game);
        this.cantMoveUp = false;
        this.cantMoveDown = false;
        this.gc = game.getGc();
        }
    
    /**
     * Renderovani spritu protihrace.
     */
    @Override
    public void render() {
        this.sprite.render(gc);
    }
    
    /**
     * Zprava o pozici protihracova padla.
     * @return zprava
     */
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
