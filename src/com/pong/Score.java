/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Jaroslav
 */
public class Score {
    private int rec1 = 0;//skóre hráča 1 (ľavého)
    private int rec2 = 0;//skóre hráča 2 (pravého)
    
    
    public int getRec1() {
        return rec1;
    }

    public void setRec1(int rec1) {
        this.rec1 = rec1;
    }

    public int getRec2() {
        return rec2;
    }

    public void setRec2(int rec2) {
        this.rec2 = rec2;
    }
   
    
}
