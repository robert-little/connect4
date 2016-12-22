/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.util.Scanner;

/**
 *
 * @author Robbie
 */
public class Connect4 {

    /**
     * @param args the command line arguments
     */
    
    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = new Board(8, 8);
        Player playerOne = new Player("one");
        Player playerTwo= new Player("two");
        int slot = 0;
        
        Player activePlayer = playerOne;
        
        printBoard(board);
        while(slot != 99){
            System.out.println("enter a slot you would like to play in:");
            slot = keyboard.nextInt();
            
            board.placePiece(slot, activePlayer);
            
            printBoard(board);
            if(board.checkWin(slot, activePlayer)){
                System.out.printf("Player %s wins!!\n", activePlayer.getName());
                break;
            }
            
            if(activePlayer == playerOne){
                activePlayer = playerTwo;
            } else {
                activePlayer = playerOne;
            }
        }
    }
    
    static private void printBoard(Board board){
        for(int i=0; i < 8; i++){
            System.out.printf("|");
            for(int j=0; j < 8; j++){
                Piece p = board.getspace(j, i);
                if(p != null){
                    Player owner = p.getOwner();
                    if(owner.name.equals("one")){
                        System.out.printf("X");
                    }else {
                        System.out.printf("A");
                    }
                }else{
                    System.out.printf("O");
                }
            }
            System.out.printf("|\n");
        }
    }
    
}
