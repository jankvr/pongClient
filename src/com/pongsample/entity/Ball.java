/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pongsample.entity;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import com.pongsample.PongSample;
import com.pongsample.Sprite;

/**
 *
 * Tahle trida by mela fungovat na strane serveru, protoze ji budeme na chvili uspavat, aby se koule po bodu nehybala.
 * 
 * @author User
 */
public class Ball extends Entity {
    
    public double directionX, directionY;
    private final PongSample ps;
    
    private boolean canMove;
    
    private static final int SPEED = 2;
    
    public Ball(Sprite sprite, int x, int y, PongSample ps) {
        super(sprite, x, y, ps);
        this.ps = ps;
        this.canMove = true;
        
        // random direction
        this.directionX = -1;
        this.directionY = -1 * (Math.random());
    }

    
    public void render(GraphicsContext gc) {
        try {
            this.sprite.render(gc);
            this.move();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean isCollisionWithPod() {
        // melo by se kontrolovat s tim, kdo ted bude odehravat
        return (this.ps.getPlayer().getSprite().intersects(this.getSprite()));
    }
    
    public void move() throws InterruptedException {
        if (!this.canMove) {
            return;
        }
        
        // kolize s ploskou
        if (isCollisionWithPod()) {
            this.directionX *= -1;
            
            double podLength = this.ps.getPlayer().getSprite().getImage().getHeight();
        
            double positionOnY = super.position.y - this.ps.getPlayer().getSprite().y;
            
            if ((positionOnY + this.getSprite().getImage().getHeight() <= podLength/3) 
                    || (positionOnY > 2*podLength/3)) {

                this.directionX *= 1;
                System.out.println("okraj");
            }
            
            else {
                this.directionX *= 1;
                
                System.out.println("stred");
            }
            
        }

        if (super.position.y < 0) {
            this.directionY *= -1;
        }
        else if ((super.position.y + this.getSprite().getImage().getWidth()) > PongSample.MAP_HEIGHT) {
            this.directionY *= -1; 
        }
        // toto je jen kolize s pravou stranou, od te se nebude odrazet
        else if ((super.position.x + this.getSprite().getImage().getHeight()) > PongSample.MAP_WIDTH) {
            this.directionX *= -1;
//            System.out.println("bod pro leveho");
//            this.canMove = false;
//            this.resetBall();
        }
        
        // toto je kolize s levou stranou
        else if (super.position.x < 0) {
            System.out.println("bod pro praveho");
            this.canMove = false;
            this.resetBall();
        }
        
        this.position.x = this.position.x + (this.directionX * SPEED);
        this.position.y = this.position.y + (this.directionY * SPEED);
        this.sprite.setXY(position.x, position.y);
        
        
    }
    
    public boolean canMove() {
        return this.canMove;
    }
    
    private void resetBall() throws InterruptedException {
        super.position.x = 400;
        super.position.y = 200;
        
        //Thread.sleep(1000);
        this.canMove = true;
    }
}
