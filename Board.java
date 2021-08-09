/*
*/

import java.util.ArrayList;

public class Board {
    int rows, cols;
    boolean squares[][];

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.squares = new boolean[rows][cols];
    }

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
    
    //Checks if this board fits a set of row clues
    public boolean checkRowsSolution(ArrayList<int[]> rowClues) {
    	return false;
    }
}

