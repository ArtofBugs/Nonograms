/*
Nonogram board representation
Oria Weng
Summer 2021
*/

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    int rows, cols;
    boolean squares[][];
    // Clues provided with the puzzle.
    // See NonParser for explanation of clue layouts.
    ArrayList<int[]> rowClues;
    ArrayList<int[]> colClues;
    // True/false currently refer to filled and unfilled;
    // colored nonograms not yet supported. If or when they
    // become supported, colors could be indicated by ints.
    final static boolean BLACK = true;
    final static boolean WHITE = false;
   
    final static boolean debugEqualClues = false;

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
    public ArrayList<int[]> generateRowClues() {
    	
        ArrayList<int[]> rowClues = new ArrayList<int[]>();
    	for (int r = 0; r < rows; r++) {
            boolean[] rowLine = new boolean[cols];
            for (int c = 0; c < cols; c++) {
               rowLine[c] = squares[r][c];
            }
            rowClues.add(generateClues(rowLine));
        }
    	return rowClues;
    }
    
    // Gets board's describing column clues based on what's in the board.
    public ArrayList<int[]> generateColClues() {
    	
        ArrayList<int[]> colClues = new ArrayList<int[]>();
    	for (int c = 0; c < cols; c++) {
            boolean[] colLine = new boolean[rows];
            for (int r = 0; r < rows; r++) {
               colLine[r] = squares[r][c];
            }
            colClues.add(generateClues(colLine));
        }
    	return colClues;
    }

    static void printClues(ArrayList<int[]> c) {
        for (int[] line: c) {
            System.err.println(Arrays.toString(line));
        }
        System.err.println();
    }

    static boolean equalClues(ArrayList<int[]> c1, ArrayList<int[]> c2) {
        if (debugEqualClues) {
            System.err.println("equalClues");
            System.err.println("c1");
            printClues(c1);
            System.err.println("c2");
            printClues(c2);
            System.err.println("size check");
        }
        int nc1 = c1.size();
        int nc2 = c2.size();
        if (nc1 != nc2) {
            return false;
        }
        if (debugEqualClues) {
            System.err.println("size check passed");
        }
        for (int i = 0; i < nc1; i++) {
            int[] a1 = c1.get(i);
            int[] a2 = c2.get(i);
            if (!Arrays.equals(a1, a2)) {
                return false;
            }
        }
        if (debugEqualClues) {
            System.err.println("passed");
        }
        return true;
    }
    
    // Checks whether the current board matches the current clues.
    public boolean solved() {
        return
            equalClues(generateRowClues(), rowClues) &&
            equalClues(generateColClues(), colClues);
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

