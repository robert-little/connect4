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
        int height = 8;
        int width = 8;
        int winCondition = 4;
        Board board = new Board(width, height, winCondition);
        Player playerOne = new Player("one");
        Player playerTwo= new Player("two");
        int slot = 0;
        
        Player activePlayer = playerOne;
        
        //printBoard(board, height, width);
        while(slot != 99){
            System.out.println("enter a slot you would like to play in:");
            slot = keyboard.nextInt();
            
            board.placePiece(slot, activePlayer);
            
            //printBoard(board, height, width);
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
    
    //This is a very basic UI I built so I could play the game. It shows the board upside down but still is good enough to use to play
    /*static private void printBoard(Board board, int height, int width){
        for(int i=0; i < height; i++){
            System.out.printf("|");
            for(int j=0; j < width; j++){
                Piece p = board.getSpace(j, i);
                if(p != null){
                    Player owner = p.getOwner();
                    if(owner.getName().equals("one")){
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
    }*/
    
}
