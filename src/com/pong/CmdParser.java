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
    private final Main game;
    
    
    public CmdParser(Main game){
        this.game = game;
    }
    
    public void parse(String input){
        //rozdelenie stringu podľa medzier do poľa
        String delims = " ";
        String[] tokens = input.split(delims);  
    
        if(tokens[0] != null)
            switch (tokens[0]) {
                case "BALL":
                    double x = Double.parseDouble(tokens[1]);
                    double y = Double.parseDouble(tokens[2]);
                    this.game.getBall().setPositionX(x);
                    this.game.getBall().setPositionY(y);
                    break;
                    
                case "OPPONENTPOSITION":
                    this.game.getOpponent().setX(Double.parseDouble(tokens[1]));
                    this.game.getOpponent().setY(Double.parseDouble(tokens[2]));
                    break;
                    
                case "SCORE":
                    this.game.getScore().setRec1(Integer.parseInt(tokens[1]));
                    this.game.getScore().setRec2(Integer.parseInt(tokens[2]));
                    System.out.println("Dostal som skóre hurá "+tokens[1]+tokens[2]);
                    break;
                    
                case "BALLPOSITIONANDSCORE":
                    double xPos = Double.parseDouble(tokens[1]);
                    double yPos = Double.parseDouble(tokens[2]);
                    this.game.getBall().setPositionX(xPos);
                    this.game.getBall().setPositionY(yPos);
                    this.game.getScore().setRec1(Integer.parseInt(tokens[3]));
                    this.game.getScore().setRec2(Integer.parseInt(tokens[4]));
                    System.out.println("Dostal som skóre hurá "+tokens[3]+tokens[4]);
                    break;
                    
                case "ERROR":
                    for(int i = 1; i < tokens.length; i++){
                        System.out.print(tokens[i]+" ");
                    }   System.out.println();
                    break;
                    
                case "LOGIN":
                    if(tokens[1].equals("OK")){
                        game.getClient().setLoggedIn("true");
                        break;
                    }
                    else{
                        game.getClient().setLoggedIn("false");
                        break;
                    }
                case "LEFT":
                    this.game.getPlayer().setX(Static.LEFT_POS);
                    this.game.getOpponent().setX(Static.RIGHT_POS);
                    break;
                
                case "RIGHT":
                    this.game.getPlayer().setX(Static.RIGHT_POS);
                    this.game.getOpponent().setX(Static.LEFT_POS);
                    break;
                    
                case "START":
                    this.game.getClient().setStarted(true);
                    break;
                    
                default:
                    System.out.println("nesprávny reťazec poslaný na CmdParser " + input);
                    break;
        } 
    }
}
