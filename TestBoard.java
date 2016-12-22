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
public class TestBoard {
    public void TestPlace(){
        Board b = new Board(8, 8);
        //b.placePiece(2, 1);
        
        Piece p = b.getspace(2, 0);
        
        if(p != null){
            System.out.println("p");
        }
    }
}
