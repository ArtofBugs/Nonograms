/*
Summer 2021
*/

import java.util.ArrayList;

public class Board {
    int rows, cols;
    boolean squares[][];
    ArrayList<int[]> rowClues =  new ArrayList<int[]>();
    ArrayList<int[]> colClues =  new ArrayList<int[]>();
    final boolean BLACK = true; //as in, filled and unfilled -- colored nonograms not yet supported
    final boolean WHITE = false; //an additional idea (for whenever, if ever, colored nonograms will
    //be supported): instead of true vs false, colors could be indicated by ints
    //see NonParser for explanation of clue layouts

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.squares = new boolean[rows][cols];
        rowClues = updateRowClues();
    }
    
    //Creates a new Board that is a copy of this one
    public Board clone() {
        Board result = new Board(this.rows, this.cols);
        for (int r = 0; r < this.rows; r++)
            for (int c = 0; c < this.cols; c++)
                result.set(r, c, this.get(r, c));
        return result;
    }

    public boolean get(int row, int col) {
        return this.squares[row][col];
    }

    public void set(int row, int col, boolean value) {
        this.squares[row][col] = value;
    }
    
    public int getRows() {
    	return rows;
    }
    
    public int getCols() {
    	return cols;
    }
    
    //Sets board's describing row clues based on what's in the board
    public void updateRowClues() {
    	int currCount = 0; //count of how many filled squares in a row we've seen
    	ArrayList <Integer> currRow = new ArrayList<Integer>(); //temporary ArrayList to store
    	//filled square counts since int[] lengths can't be changed once they're created
    	for (int r = 0; r < rows; r++) {//rows in board, which correspond to int arrays in rowClues
    	   for (int c = 0; c < cols; c++) {//cols in board, which could correspond to members of the int arrays
    	      if (this.get(r, c) == BLACK) {
    	      	currCount++;
    	      }
    	      else {
    	      	currRow.add(currCount); //if there are bugs, this may be the source if
    	      	//it passed by reference... though there should be some boxing stuff that prevents that...
    	      	currCount = 0;
    	      }
    	   }
    	   //at the end of the row, create a real int[] to replace the temporary currRow ArrayList
    	   //then copy values to it and add it to the rowClues
    	   //then clear currRow (be careful here with passing!) so it can be used for the next row
    	}
    	
    }
    
    //Checks if this board's describing clues are the same as a set of given row clues
    //(If they are the same, this board is a solution to the given game.)
    public boolean checkRowsSolution(ArrayList<int[]> newRowClues) {
    	/*
    	if arraylists are different sizes: return false
    	for each array in arraylists:
    		if lengths are different: return false
    		otherwise check to see if values are same
    			if they aren't, return false; otherwise, keep going
    			
    	if it gets all the way to the end of the method then return true
    	*/
    	return true;
    }
}

