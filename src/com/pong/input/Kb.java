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

    private void keyPressed(KeyEvent e) {
        map.put(e.getCode().toString(), true);
    }
    
    private void keyReleased(KeyEvent e) {
        map.put(e.getCode().toString(), false);
    }
    /**
     * updates informatio about pressed keys
     */
    public void update() {
        up = map.get("UP");
        down = map.get("DOWN");
    }
    /**
     * Returns whether the up key is pressed 
     * @return true if the up key is pressed
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Returns whether the down key is pressed 
     * @return true if the down key is pressed
     */
    public boolean isDown() {
        return down;
    }
    
    /**
     * Setting up a keyboard actions.
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
