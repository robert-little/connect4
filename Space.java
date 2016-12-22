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
public class Space {
    boolean full;
    Piece p;
    
    public Space (boolean full) {
        this.full = full;
        p = null;
    }
    
    public void setPiece (Piece p) {
        this.full = true; 
        this.p = p;
    }
    
    public Piece getPiece () {
        if(p == null){
            return null;
        } else {
            return p;
        }
    }
}
