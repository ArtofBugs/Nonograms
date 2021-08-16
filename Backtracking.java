/*
Uses backtracking algorithm to solve a nonogram.
Author: ArtofBugs | Date: Summer 2021
*/
import java.io.File;
import java.util.Arrays;
public class Backtracking {
    
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
        int r = currBoard.getRows();
        int c = currBoard.getCols();
        
        // base case
        if (currRow >= r) {
            return currBoard.solved();
        }
        
        int nextCol = currCol + 1;
        int nextRow = currRow;
        
        currBoard.set(currRow, currCol, WHITE);
        System.err.println("to white " + currRow + "," + currCol);
        
        if (nextCol >= c) {
            boolean[] currLine = currBoard.getSquares()[currRow];
            int[] currClues = currBoard.generateClues(currLine);
            int[] givenClues = currBoard.rowClues.get(currRow);
            if (Arrays.equals(currClues, givenClues)) {
                System.err.println("this line works ~ " + currRow);
                currBoard.printBoard();
                nextCol = 0;
                nextRow = currRow + 1;
            }
            else {
                System.err.println("line doesn't work ~ " + currRow);
                currBoard.printBoard();
                continuing = false;
            }
        }
        
        if (continuing) {
            System.err.println("solving " + nextRow + "," + nextCol);
            currBoard.printBoard();
            if (solve (currBoard, nextRow, nextCol)) {
                System.err.println("trying");
                return true;
            }
        }
        
        currBoard.set(currRow, currCol, BLACK);
        System.err.println("to black " + currRow + "," + currCol);
        
        if (nextCol >= c) {
            boolean[] currLine = currBoard.getSquares()[currRow];
            int[] currClues = currBoard.generateClues(currLine);
            int[] givenClues = currBoard.rowClues.get(currRow);
            if (Arrays.equals(currClues, givenClues)) {
                System.err.println("this line works ~ " + currRow);
                currBoard.printBoard();
                nextCol = 0;
                nextRow = currRow + 1;
            }
            else {
                System.err.println("line doesn't work ~ " + currRow);
                currBoard.printBoard();
                return false;
            }
        }
        System.err.println("solving " + nextRow + "," + nextCol);
        currBoard.printBoard();
        System.err.println("trying");
        return solve (currBoard, nextRow, nextCol);
        
    }
}
