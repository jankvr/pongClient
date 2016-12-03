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
    private final Pong game;
    
    
    public CmdParser(Pong game){
        this.game=game;
    }
    
    public void parse(String input){
        //rozdelenie stringu podľa medzier do poľa
        String delims = " ";
        String[] tokens = input.split(delims);  
    
        if(tokens[0] != null)
            switch (tokens[0]) {
                case "BALLPOSITION":
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    game.getBall().setxPosition(x);
                    game.getBall().setyPosition(y);
                    break;
                case "OPPONENTPOSITION":
                    game.getOpponent().setY(Double.parseDouble(tokens[1]));
                    break;
                case "SCORE":
                    game.getScore().setRec1(Integer.parseInt(tokens[1]));
                    game.getScore().setRec2(Integer.parseInt(tokens[2]));
                    break;
                case "ERROR":
                    System.out.print("Server informuje o chybe: ");
                    for(int i=1;i<tokens.length;i++){
                        System.out.print(tokens[i]+" ");
                    }   System.out.println();
                    break;
                default:
                    System.out.println("nesprávny reťazec poslaný na CmdParser " + input);
                    break;
        }
        
        
    }
    
}
