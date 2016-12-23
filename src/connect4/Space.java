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
    private Piece p;
    
    public Space () {
        p = null;
    }
    
    public void setPiece (Piece p) {
        this.p = p;
    }
    
    public Piece getPiece () {
        if(p == null){
            return null;
        } else {
            return p;
        }
    }
    
    //This is for testing only
    public void removePiece (){
        p = null;
    }
}
