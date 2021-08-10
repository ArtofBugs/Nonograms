/*
Summer 2021
*/

import java.util.ArrayList;

public class Board {
    int rows, cols;
    boolean squares[][];
    ArrayList<int[]> rowClues;
    ArrayList<int[]> colClues;

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
    
    //Sets board's row clues based on what's in the board
    public void updateRowClues() {
    	
    }
    
    //Checks if this board's describing clues are the same as a set of given row clues
    //(If they are the same, this board is a solution to the given game.)
    public boolean checkRowsSolution(ArrayList<int[]> newRowClues) {
    	boolean match = true;
    	return match;
    }
}

