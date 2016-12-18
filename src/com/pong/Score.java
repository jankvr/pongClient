/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong;

/**
 * Trieda skóre spravuje informácie o stave hry medzi hráčmi
 * @author Jaroslav Fedorčák, Jan Kovář
 */
public class Score {
    private int rec1 = 0;//skóre hráča 1 (ľavého)
    private int rec2 = 0;//skóre hráča 2 (pravého)
    
    /**
     * Getter na body ľavého hráča
     * @return body ľavého hráča
     */
    public int getRec1() {
        return rec1;
    }

    /**
     * nastavuje body ľavého hráča
     * @param rec1 body ľavého hráča
     */
    public void setRec1(int rec1) {
        this.rec1 = rec1;
    }

    /**
     * vracia body pravého hráča
     * @return body pravého hráča
     */
    public int getRec2() {
        return rec2;
    }
    /**
     * nastavje body pravého hráča
     * @param rec2 body pravého hráča
     */
    public void setRec2(int rec2) {
        this.rec2 = rec2;
    }
   
    
}
