/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.input;
    
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyEvent;
import com.pong.Main;

/**
 * Keyboard, to receive user's interaction with the game.
 * @author jankovar
 */

public class Kb {
    
    private boolean up, down;
    private final Map<String, Boolean> map;
    private final Main gui;
    
    public Kb(Main gui) {
        this.gui = gui;
        map = new HashMap<>();
        map.put("UP", false);
        map.put("DOWN", false);
        
        this.setKeyBoard();
    }

    /**
     * Nastavi true ke konkretnimu kodu tlacitka (true = tlacitko zmacknuto)
     * @param e keyevent
     */
    private void keyPressed(KeyEvent e) {
        map.put(e.getCode().toString(), true);
    }
    
    /**
     * Nastavi false ke konkretnimu kodu tlacitka (false = tlacitku neni zmacknuto)
     * @param e 
     */
    private void keyReleased(KeyEvent e) {
        map.put(e.getCode().toString(), false);
    }
    /**
     * Update (ne)zmacknutych tlacitek
     */
    public void update() {
        up = map.get("UP");
        down = map.get("DOWN");
    }
    /**
     * Vraci, jestli je zmacknuto tlacitko UP (sipka nahoru)
     * @return true, jestli je zmacknuto
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Vraci, jestli je zmacknuto tlacitko DOWN (sipka dolu)
     * @return true, jestli je zmacknuto
     */
    public boolean isDown() {
        return down;
    }
    
    /**
     * Nastaveni akce na scene.
     */
    private void setKeyBoard() {
        this.gui.getScene().setOnKeyPressed((KeyEvent e) -> {
            this.keyPressed(e);
        });
        
        this.gui.getScene().setOnKeyReleased((KeyEvent e) -> {
            this.keyReleased(e);
        });
    }
}
