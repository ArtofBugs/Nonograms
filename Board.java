/*
Class for Board objects, which have squares, rows, columns, and clues.
ArtofBugs in collaboration with Bart Massey | August 2021
*/

import java.util.ArrayList;

public class Board {
    int rows, cols;
    boolean squares[][];
    // See NonParser for explanation of clue layouts.
    ArrayList<int[]> rowClues = new ArrayList<int[]>();
    ArrayList<int[]> colClues = new ArrayList<int[]>();
    // True/false currently refer to filled and unfilled;
    // colored nonograms not yet supported. If or when they
    // become supported, colors could be indicated by ints.
    final static boolean BLACK = true;
    final static boolean WHITE = false;
   

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.squares = new boolean[rows][cols];
    }
    
    // Returns a new Board that is a copy of this one.
    public Board clone() {
        Board result = new Board(this.rows, this.cols);
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                result.set(r, c, this.get(r, c));
			}
		}
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
    
    public boolean[][] getSquares (){
    	return this.squares;
    }
    
    public void setRowClues(ArrayList<int[]> newRowClues) {
    	this.rowClues = newRowClues;
    }
    
    public void setColClues(ArrayList<int[]> newColClues) {
    	this.colClues = newColClues;
    }
    
    public static int[] generateClues(boolean[] line) {
        ArrayList<Integer> currClues = new ArrayList<Integer>();
        // Count of how many filled squares in succession we've seen.
        int currCount = 0; 
        for (boolean s: line) {
            if (s == BLACK) {
                currCount++;
            }
            else {
                if (currCount != 0) {
                    currClues.add(currCount);
                }
                currCount = 0;
            }
        }
        if (currCount != 0) {
            currClues.add(currCount);
        }
        int[] result = new int[currClues.size()];
        for (int i = 0; i < currClues.size(); i++) {
            result[i] = currClues.get(i);
        }
		return result;
    }

    // Gets board's describing row clues based on what's in the board.
    public ArrayList<int[]> getRowClues() {
    	
        ArrayList<int[]> clues = new ArrayList<int[]>();

    	for (int r = 0; r < rows; r++) {
            boolean[] rowLine = new boolean[cols];
            for (int c = 0; c < cols; c++) {
               rowLine[c] = squares[r][c];
            }
            clues.add(generateClues(rowLine));
        }

        return clues;
    	
    }
    
    // Gets board's describing column clues based on what's in the board.
    public ArrayList<int[]> getColClues() {
    	
        ArrayList<int[]> clues = new ArrayList<int[]>();

    	for (int c = 0; c < cols; c++) {
            boolean[] colLine = new boolean[rows];
            for (int r = 0; r < rows; r++) {
               colLine[r] = squares[r][c];
            }
            clues.add(generateClues(colLine));
        }

        return clues;
    }
    
    // Checks if this board's describing row clues are the same as
    // a set of given row clues.
    public boolean checkRowsSolution(ArrayList<int[]> newRowClues) {
    	if (newRowClues.size() != rowClues.size()) {
    		return false;
    	}
		for (int r = 0; r < rowClues.size(); r++) {
			if (rowClues.get(r).length != newRowClues.get(r).length) {
				return false;
			}
			if (rowClues.get(r).length != 0) {
				for (int c = 0; c < rowClues.get(r).length; c++) {
					if (rowClues.get(r)[c] != newRowClues.get(r)[c]) {
						return false;
					}
				}
			}
    	}
    	return true;
    }

    // Checks if this board's describing col clues are the same as
    // a set of given column clues.
    public boolean checkColsSolution(ArrayList<int[]> newColClues) {
    	if (newColClues.size() != colClues.size()) {
    		return false;
    	}
		for (int c = 0; c < colClues.size(); c++) {
			if (colClues.get(c).length != newColClues.get(c).length) {
				return false;
			}
			if (colClues.get(c).length != 0) {
				for (int r = 0; r < colClues.get(c).length; r++) {
					if (colClues.get(c)[r] != newColClues.get(c)[r]) {
						return false;
					}
				}
			}
    	}
    	return true;
    }

    public boolean solved() {
        ArrayList<int[]> cc = getColClues();
        if (!checkColsSolution(cc)) {
            return false;
        }
        ArrayList<int[]> rc = getRowClues();
        return checkRowsSolution(rc);
    }
    
    
    // For troubleshooting: print out rowClues.
    public void printRowClues() {
        assert rowClues.size() == rows;
        System.err.println();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (squares[r][c]) {
                    System.err.print('*');
                }
                else {
                    System.err.print('.');
                }
            }
            System.err.print(" |");
            for (int c = 0; c < rowClues.get(r).length; c++) {
                System.err.print(" ");
                System.err.print(rowClues.get(r)[c]);
            }
            System.err.println();
		}
        System.err.println();
	}
	
	// For troubleshooting: print out colClues
	// (it will be rotated 90 degrees clockwise).
    public void printColClues() {
        assert colClues.size() == cols;
        System.err.println();
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (squares[rows-1-r][c]) {
                    System.err.print('*');
                }
                else {
                    System.err.print('.');
                }
            }
            System.err.print(" |");
            for (int r = 0; r < colClues.get(c).length; r++) {
                System.err.print(" ");
                System.err.print(colClues.get(c)[colClues.get(c).length-1-r]);
            }
            System.err.println();
		}
        System.err.println();
	}
	
	// Print boards to standard output.
	public void printBoard() {
		System.out.println("-------------------");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (this.getSquares()[i][j]) {
					System.out.print("* ");
				}
				else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	}
}

