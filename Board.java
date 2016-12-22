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
public class Board {
    int height; //y
    int width; //x
    
    Slot[] slots;
    Space[][] spaces; //x, y
    
    boolean empty = false;
    
    public Board (int width, int height) {
        slots = new Slot[width];
        spaces = new Space[width][height];
        for(int i=0; i < width; i++) {
            slots[i] = new Slot(height);
            for(int j=0; j < height; j++){
                spaces[i][j] = new Space(empty);
            }
        }
    }
    
    public boolean placePiece (int x, Player owner) {
        //getting the number of pieces already in the slot so placement is easy
        int firstOpenSpace = slots[x].getFilled();
        
        //checking to make sure that a piece can be placed in the slot
        if(slots[x].pieceDropped()){
            Piece p = new Piece(owner);
            spaces[x][firstOpenSpace].setPiece(p);
            owner.piecePlayed();
            return true;
        }
        return false;
    }
    
    public Piece getspace(int x, int y){
        return spaces[x][y].getPiece();
    }
    
    public boolean checkWin(int lastPieceX, Player owner){
        //getting location of last piece played because that would be the one causing the win
        int lastPieceY = slots[lastPieceX].getFilled()-1;
        
        if(owner.getPlayedPieces() > 3){
            if(slots[lastPieceX].getFilled() >= 4){
                if(checkDown(lastPieceX, lastPieceY, owner)){
                    return true;
                }
            } else if(checkHorizontal(lastPieceX, lastPieceY, owner)){
                return true;
            }
        }
        
        return false;
    }
    
    private boolean checkDown (int x, int y, Player owner){
        int currentY = y-1;
        int count = 1;
        
        //We know this will never run into a null because a piece cannot have a an empty space below it.
        while(currentY >= 0 && spaces[x][currentY].getPiece().getOwner() == owner){
            count++;

            if(count == 4){
                return true;
            }
            currentY--;
        }
        return false;
    }
    
    private boolean checkHorizontal (int x, int y, Player owner){
        int currentXLeft = x-1;
        int currentXRight = x+1;
        int count = 1;
        
        //Looking at the left side of the current piece
        while(currentXLeft >= 0 && spaces[currentXLeft][y].getPiece() != null){
            if(spaces[currentXLeft][y].getPiece().getOwner() == owner){
                count++;
                if(count == 4){
                    return true;
                }
                currentXLeft--;
            } else {
                break;
            }
        }
        //Looking at the right side of the current piece 
        while(currentXRight <= 7 && spaces[currentXRight][y].getPiece() != null){
            if(spaces[currentXRight][y].getPiece().getOwner() == owner){
                count++;
                if(count == 4){
                    return true;
                }
                currentXRight++;
            } else {
                return false;
            }
        }
        
        return false;
    }
    
//    //Checking this: \ diagonal
//    private boolean checkUpLeft (int x, int y, int owner){
//        int currentY = y-1;
//        int count = 1;
//        while(spaces[x][currentY].getPiece().getOwner() == owner){
//            count++;
//            if(count == 4){
//                return true;
//            }
//            currentY--;
//        }
//        return false;
//    }
//    //Checking this: / diagonal
//    private boolean checkUpRight (int x, int y, int owner){
//        int currentY = y-1;
//        int count = 1;
//        while(spaces[x][currentY].getPiece().getOwner() == owner){
//            count++;
//            if(count == 4){
//                return true;
//            }
//            currentY--;
//        }
//        return false;
//    }
}
