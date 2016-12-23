/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.io.BufferedReader;
import java.io.FileReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robbie
 */
public class BoardTest {
    
    Player player1 = new Player("one");
    Player player2 = new Player("two");

    /**
     * Testing checkWin method
     * A file containing a set of moves is read in. Once the final move has been played the test checks for a win.
     * The file is of this type: 
     * one-0
     * one-1
     * two-2
     * two-7
     * ...
     * final
     * one-2, one-5, one-6
     * 
     * Because of the way connect 4 works with pieces falling from the top to the bottom the moves must be made with that in mind.
     * The best way to do this would be going row by row from the lowest row to the highest.
     * 
     * The final keyword is used to indicate that the next entry in the file should be a winning move.
     * If a user wants to test multiple winning moves then they can comma separate the moves and all will be tested.
     */
    @Test
    public void TestVictory (){
        int boardWidth = 9;
        int boardHeight = 8;
        int winCondition = 4;
        Board b = new Board(boardWidth, boardHeight, winCondition);
        
        String fileName = "testMoves.txt";
        //This is the move that will be read in
        String move = null;
        
        boolean finalMove = false;
        String winningMoves[] = null;
        
        
        try {
            FileReader testMoves = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(testMoves);
            
            while((move = bufferedReader.readLine()) != null) {
                if(move.equals("final")){
                    finalMove = true;
                    continue;
                }
                if(finalMove){
                    if(move.contains(",")){
                        winningMoves = move.split(",");
                        for(int i=0; i < winningMoves.length; i++){
                            assertTrue(parseAndPlace(b, winningMoves[i], true));
                            System.out.println("test " + (i+1) + " passed");
                        }
                    } else {
                        assertTrue(parseAndPlace(b, move, true));
                    }
                } else {
                    if(!parseAndPlace(b, move, false)){
                        assertTrue(false);
                    }
                }
            }
            bufferedReader.close();
            
        }catch(Exception e){
            System.out.println(e);
            assertTrue(false);
        }
    }
    
    private boolean parseAndPlace(Board b, String move, boolean checkWin){
        String[] playerSlot = null;
        Player activePlayer = null;
        
        playerSlot = move.split("-");
        String playerName = playerSlot[0];
        int slot = Integer.parseInt(playerSlot[1]);


        if(playerName.equals("one")){
            activePlayer = player1;
        } else {
            activePlayer = player2;
        }

        if(!b.placePiece(slot, activePlayer)){
            System.out.println("Piece used in move: " + move + " cannot be placed. Either the slot is full or it does not exist.");
            return false;
        }
        
        if(checkWin){
            boolean win = b.checkWin(slot, activePlayer);
            b.removePiece(slot);
            return (win);
        }
        
        return true;
    }
}
