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
public class Piece {
    private Player owner;
    
    //Owners are either 1 or 2 for player one or two
    public Piece (Player owner) {
        this.owner = owner;
    }
    
    public Player getOwner() {
        return owner;
    }
}
