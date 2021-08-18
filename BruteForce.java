/*
Uses brute force to generate all possible solutions to a given nonogram.
Author: ArtofBugs | Date: Summer 2021
*/

import java.io.File;
import java.util.Scanner;

public class BruteForce {
	
	static final boolean WHITE = false;
	static final boolean BLACK = true;
	static Timer timer = new Timer();
	
	public static void main (String [] args) {
		Board work = NonParser.NonParser(new File(args[0]));
		timer.start();
		boolean soln = recurse(work, 0, 0);
		timer.stop();
		if (soln) {
			System.out.println("SOLUTION");
			work.printBoard();
                } else {
			System.out.println("NO SOLUTION");
		}
	}

	// Recursively generate board possibilities.
	public static boolean recurse (Board currBoard, int currRow, int currCol) {

		// Base case
		if (currRow >= currBoard.rows) {
                    return currBoard.solved();
		}

		// Adjust the row and column indices to move to the
		// next position after this.
		int nextCol = currCol + 1;
		int nextRow = currRow;
		if (nextCol >= currBoard.cols) {
			nextCol = 0;
			nextRow = currRow + 1;
		}

		currBoard.set(currRow, currCol, WHITE);
		if (recurse(currBoard, nextRow, nextCol)) {
                    return true;
                }
		currBoard.set(currRow, currCol, BLACK);
		return recurse(currBoard, nextRow, nextCol);
	}
}
