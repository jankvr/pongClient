/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong;



/**
 *
 * @author User
 */
public class CmdParser {
    private Pong game;
    
    
    public CmdParser(Pong game){
        this.game=game;
    }
    
    public void parse(String input){
        //rozdelenie stringu podľa medzier do poľa
        String delims = "[ ]";
        String[] tokens = input.split(delims);  
        
        if(tokens[0]=="BALLPOSITION"){            
            double x = Double.parseDouble(tokens[1]);
            double y = Double.parseDouble(tokens[2]);
            game.getBall().setxPosition(x);
            game.getBall().setyPosition(y);
        }
//        else if(tokens[0]=="OPPONENTPOSITION"){
//            game.g
//        }
        
    }
    
}
