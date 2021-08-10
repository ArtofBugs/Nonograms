/*
Summer 2021
*/

import java.util.ArrayList;

public class Board {
    int rows, cols;
    boolean squares[][];
    ArrayList<int[]> rowClues = new ArrayList<int[]>();
    ArrayList<int[]> colClues = new ArrayList<int[]>();
    final boolean BLACK = true; //as in, filled and unfilled -- colored nonograms not yet supported
    final boolean WHITE = false; //an additional idea (for whenever, if ever, colored nonograms will
    //be supported): instead of true vs false, colors could be indicated by ints
    //see NonParser for explanation of clue layouts

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.squares = new boolean[rows][cols];
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
    	
    	for (int r = 0; r < rows; r++) {//rows in board, which correspond to int arrays in rowClues
		ArrayList <Integer> currRowList = new ArrayList<Integer>(); //temporary ArrayList to store
		//filled square counts since int[] lengths can't be changed once they're created
		int currCount = 0; //count of how many filled squares in a row we've seen
		for (int c = 0; c < cols; c++) {//cols in board, which could correspond to members of the int arrays
			if (this.get(r, c) == BLACK) {
				System.out.println(r + "," + c + "|" + "BLACK");
				currCount++;
				System.out.println("currCount:" + currCount);
    	     		}
    	    		else {
    	      			if (currCount != 0) {currRowList.add(currCount);} //if there are bugs, this may be the source if it passed by reference... 
    	      			//though there should be some boxing stuff that prevents that...
    	      			//as you can tell, passing by reference vs passing by value is not my strong suit
    	      			//(hopefully I'll improve on it as I get more practice and experience :) )
    	      			currCount = 0;
    	     		}
    	   	}
    	   	//end of row reached
    	   	int[] currRowArray = new int[currRowList.size()];
    	   	//(there is potential for bugs here as well if currRowArray is deleted after the for loop,
    	   	//or there aren't new arrays being created)
    		for (int i = 0; i < currRowList.size(); i++) { //copy values from currRowList to currRowArray
    		      currRowArray[i] = currRowList.get(i);
    		}
    		rowClues.add(currRowArray);
    		//don't need to clear currRowArray because new one is created every time :)
	}
    	
    }
    
    //Checks if this board's describing clues are the same as a set of given row clues
    //(If they are the same, this board is a solution to the given game.)
    public boolean checkRowsSolution(ArrayList<int[]> newRowClues) {
    	if (newRowClues.size() != rowClues.size()) {return false;}
    	for (int r = 0; r < rowClues.size(); r++) {
    	   if (rowClues.get(r).length != newRowClues.get(r).length) {return false;}
    	   if (rowClues.get(r).length != 0) {
    	   	for (int c = 0; c < rowClues.get(r).length; c++) {
    	   	   
    	   	}
    	   }
    	   //otherwise they both have length 0 and no comparison happens
    	}
    	/*
    	if arraylists are different sizes: return false
    	for each array in arraylists:
    		it's possible that they're both empty arrays; move on in that case
    		if lengths are different: return false
    		otherwise check to see if values are same
    			if they aren't, return false; otherwise, keep going

    			
    	if it gets all the way to the end of the method then return true
    	*/
    	return true;
    }
    
    //For troubleshooting: print out rowClues
    public void printRowClues() {
	for (int r = 0; r < rowClues.size(); r++) {
	   for (int c = 0; c < rowClues.get(r).length; c++) {
	      System.out.println(r + "," + c + ":" + rowClues.get(r)[c]);
	   }
	}
	System.out.println("rowClues size:" + rowClues.size());    
    }
}

