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
public class Slot {
    private int filled;
    private int size;
    
    public Slot(int size) {
        this.size = size;
        filled = 0;
    }
    
    public boolean pieceDropped () {
        filled++;
        
        if (filled > size) {
            return false;
        }
        return true;
    }
    
    public int getFilled () {
        return filled;
    }
    
    //This is for testing only
    public void removePiece (){
        filled--;
    }
}
