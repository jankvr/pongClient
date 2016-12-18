/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.interfaces;

/**
 *
 * Rozhrani pro ovladani aplikace
 * 
 * @author User
 */
public interface IDriver {
    
    /**
     * Update (ne)zmacknutych tlacitek
     */
    public void update();
    
    /**
     * Vraci, jestli je zmacknuto tlacitko UP
     * @return true, jestli je zmacknuto
     */
    public boolean isUp();
    
    /**
     * Vraci, jestli je zmacknuto tlacitko DOWN
     * @return true, jestli je zmacknuto
     */
    public boolean isDown();
}
