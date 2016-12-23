/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

/**
 *
 * @author Robbie
 * The board should be thought of like this:
 *      _ _ _ _ _ _ _ _ _ _
 * (>7)|                   |
 *    7|                   |
 *    6|                   |
 *    5|                   |
 *    4|                   |
 *    3|                   |
 *    2|                   |
 *    1|                   |
 *    0|_ _ _ _ _ _ _ _ _ _|
 *      0 1 2 3 4 5 6 7 (>7)
 * 
 * With 0,0 being at the bottom left.
 */
public class Board {
    private int height; //y
    private int width; //x
    
    private int winCondtion;
    
    private Slot[] slots;
    private Space[][] spaces; //x, y
    
    private boolean empty = false;
    
    public Board (int width, int height, int winCondtion) {
        this.winCondtion = winCondtion;
        this.width = width;
        this.height = height;
        slots = new Slot[width];
        spaces = new Space[width][height];
        for(int i=0; i < width; i++) {
            slots[i] = new Slot(height);
            for(int j=0; j < height; j++){
                spaces[i][j] = new Space();
            }
        }
    }
    
    public boolean placePiece (int x, Player owner) {
        if(x > width-1){
            return false;
        }
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
    
    public Piece getSpace(int x, int y){
        return spaces[x][y].getPiece();
    }
    
    public boolean checkWin(int lastPieceX, Player owner){
        //getting location of last piece played because that would be the one causing the win
        int lastPieceY = slots[lastPieceX].getFilled()-1;
        
        if(owner.getPlayedPieces() > 3){
            if(slots[lastPieceX].getFilled() >= winCondtion){
                if(checkDown(lastPieceX, lastPieceY, owner)){
                    return true;
                }
            }
            if(checkHorizontal(lastPieceX, lastPieceY, owner)){
                return true;
            } else if(checkLeftToRight(lastPieceX, lastPieceY, owner)){
                return true;
            } else if(checkRightToLeft(lastPieceX, lastPieceY, owner)){
                return true;
            }
        }
        return false;
    }
    
    
    //Checking different win conditions for the next 4 methods
    private boolean checkDown (int x, int y, Player owner){
        int currentY = y-1;
        int count = 1;
        
        //We know this will never run into a null because a piece cannot have a an empty space below it.
        while(currentY >= 0 && spaces[x][currentY].getPiece().getOwner() == owner){
            count++;

            if(count == winCondtion){
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
                if(count == winCondtion){
                    return true;
                }
                currentXLeft--;
            } else {
                break;
            }
        }
        //Looking at the right side of the current piece 
        while(currentXRight < width && spaces[currentXRight][y].getPiece() != null){
            if(spaces[currentXRight][y].getPiece().getOwner() == owner){
                count++;
                if(count == winCondtion){
                    return true;
                }
                currentXRight++;
            } else {
                return false;
            }
        }
        
        return false;
    }
    
    //Checking this: / diagonal
    private boolean checkLeftToRight (int x, int y, Player owner){
        int currentY = y+1;
        int currentX = x+1;
        int count = 1;
        //First we go up
        while(currentY < height && currentX < width && spaces[currentX][currentY].getPiece() != null){
            if(spaces[currentX][currentY].getPiece().getOwner() == owner){
                count++;
                if(count == winCondtion){
                    return true;
                }
                currentY++;
                currentX++;
            } else {
                break;
            }
        }
        currentY = y-1;
        currentX = x-1;
        //We then go down
        while(currentY >= 0 && currentX >= 0 &&spaces[currentX][currentY].getPiece() != null){
            if(spaces[currentX][currentY].getPiece().getOwner() == owner){
                count++;
                if(count == winCondtion){
                    return true;
                }
                currentY--;
                currentX--;
            } else {
                break;
            }
        }
        
        return false;
    }
    //Checking this: \ diagonal
    private boolean checkRightToLeft (int x, int y, Player owner){
        int currentY = y+1;
        int currentX = x-1;
        int count = 1;
        //First we go up
        while(currentY < height && currentX >= 0 && spaces[currentX][currentY].getPiece() != null){
            if(spaces[currentX][currentY].getPiece().getOwner() == owner){
                count++;
                if(count == winCondtion){
                    return true;
                }
                currentY++;
                currentX--;
            } else {
                break;
            }
        }
        currentY = y-1;
        currentX = x+1;
        //We then go down
        while(currentY >= 0 && currentX < width && spaces[currentX][currentY].getPiece() != null){
            if(spaces[currentX][currentY].getPiece().getOwner() == owner){
                count++;
                if(count == winCondtion){
                    return true;
                }
                currentY--;
                currentX++;
            } else {
                break;
            }
        }
        
        return false;
    }
    
    //This method only exists for testing purposes. Removes the last piece from a specified slot.
    public void removePiece(int slot){
        int lastPieceY = slots[slot].getFilled() - 1;
        slots[slot].removePiece();
        spaces[slot][lastPieceY].removePiece();
    }
}
