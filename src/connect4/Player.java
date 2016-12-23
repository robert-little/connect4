/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

/**
 *
 * @author Robbie
 */
public class Player {
    private int playedPieces;
    private String name;
    
    public Player(String name){
        this.name = name;
        playedPieces = 0;
    }
    
    public String getName(){
        return name;
    }
    
    public void piecePlayed(){
        playedPieces++;
    }
    
    public int getPlayedPieces(){
        return playedPieces;
    }
}
