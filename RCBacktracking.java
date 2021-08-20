/*
    Uses an algorithm similar to Backtracking.java but checks against column
    clues after trying each square as it generates row possibilities.
    ArtofBugs | August 2021
*/
import java.io.File;
import java.util.Arrays;
public class RCBacktracking {
    
    static final boolean WHITE = false;
    static final boolean BLACK = true;
    static Timer timer = new Timer();
    
    public static void main(String[] args) {
        String path = args[0];
        Board work = NonParser.NonParser(new File(path));
        timer.start();
        boolean soln = solve(work, 0, 0);
        timer.stop();
        if (soln) {
            System.out.println("SOLUTION");
            work.printBoard();
        }
        else {
            System.out.println("NO SOLUTION");
        }
    }
    
    public static boolean solve(Board currBoard, int currRow, int currCol) {
        boolean continuing = true;
        // continuing is whether the solver will continue to pursue a lead with
        // the current square as white
        int r = currBoard.getRows();
        int c = currBoard.getCols();
        
        // base case
        if (currRow >= r) {
            return currBoard.solved();
        }
        
        int nextCol = currCol + 1;
        int nextRow = currRow;
        
        currBoard.set(currRow, currCol, WHITE);
        clearForwards(currBoard, currRow, currCol);
        if (!compareColClues(currBoard, currCol, currRow, false)) {
            continuing = false;
        }
        
        if (continuing && nextCol >= c) {
            boolean[] currLine = currBoard.getSquares()[currRow];
            int[] currClues = currBoard.generateClues(currLine);
            int[] givenClues = currBoard.rowClues.get(currRow);
            if (Arrays.equals(currClues, givenClues)) {
                nextCol = 0;
                nextRow = currRow + 1;
            }
            else {
                continuing = false;
            }
        }
        
        if (continuing) {
            if (solve (currBoard, nextRow, nextCol)) {
                return true;
            }
        }
        
        currBoard.set(currRow, currCol, BLACK);
        clearForwards(currBoard, currRow, currCol);
        if (!compareColClues(currBoard, currCol, currRow, true)) {
            return false;
        }
        
        if (nextCol >= c) {
            boolean[] currLine = currBoard.getSquares()[currRow];
            int[] currClues = currBoard.generateClues(currLine);
            int[] givenClues = currBoard.rowClues.get(currRow);
            if (Arrays.equals(currClues, givenClues)) {
                nextCol = 0;
                nextRow = currRow + 1;
            }
            else {
                return false;
            }
        }
        return solve(currBoard, nextRow, nextCol);
        
    }
    
    // if ignoreLast is true, the last clue in the column is not finished, so
    // it shouldn't be compared to the given clues.
    public static boolean compareColClues(Board currBoard, int currCol,
        int currRow, boolean ignoreLast) {
        int c = currBoard.getCols();
        int r = currBoard.getRows();
        if (currRow >= r-1) {
            ignoreLast = false;
        }
        boolean[] currLine = new boolean[r];
        for (int row = 0; row < r; row++) {
            currLine[row] = currBoard.get(row, currCol);
        }
        int[] currClues = currBoard.generateClues(currLine);
        int[] givenClues = currBoard.colClues.get(currCol);
        int n = currClues.length;
        if (n > givenClues.length) {
            return false;
        }
        if (ignoreLast && currClues.length != 0) {
            n--;
        }
        for (int row = 0; row < n; row++) {
           if (currClues[row] != givenClues[row]) {
               return false;
           }
        }
        return true;
    }
    
    public static void clearForwards(Board currBoard, int currRow, int currCol) {
        int rows = currBoard.getRows();
        int cols = currBoard.getCols();
        for (int r = 0; r < rows; r++) {
           for (int c = 0; c < cols; c++) {
              if ((r == currRow && c > currCol) || (r > currRow)) {
                  currBoard.set(r, c, WHITE);
              }
           }
        }
    }
}
